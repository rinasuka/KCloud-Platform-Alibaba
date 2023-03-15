/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *   http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.common.mybatisplus.utils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.laokou.common.i18n.core.CustomException;
import org.laokou.common.mybatisplus.service.BatchService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author laokou
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BatchUtil<T> {

    private final TransactionalUtil transactionalUtil;
    private final ThreadPoolTaskExecutor taskExecutor;

    /**
     * 批量新增
     * @param dataList 集合
     * @param batchNum 每组多少条数据
     * @param service 基础service
     */
    @SneakyThrows
    public void insertBatch(List<T> dataList, int batchNum, BatchService<T> service) {
        // 数据分组
        List<List<T>> partition = Lists.partition(dataList, batchNum);
        int size = partition.size();
        AtomicBoolean rollback = new AtomicBoolean(false);
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for(int i = 0; i < size; i++) {
            List<T> list = partition.get(i);
            taskExecutor.execute(() -> transactionalUtil.execute(callback -> {
                try{
                    service.insertBatch(list);
                    return true;
                } catch (Exception e) {
                    // 回滚标识
                    rollback.set(true);
                    log.error("错误信息：批量插入数据异常，设置回滚标识");
                    return false;
                } finally {
                    if (rollback.get()) {
                        callback.setRollbackOnly();
                    }
                    countDownLatch.countDown();
                }
            }));
        }
        // 阻塞线程
        countDownLatch.await(30, TimeUnit.SECONDS);
        if (rollback.get()) {
            throw new CustomException("批量插入数据异常，数据已回滚");
        }
    }

}
