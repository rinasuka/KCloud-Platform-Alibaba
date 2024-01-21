/*
 * Copyright (c) 2022-2024 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.admin.gatewayimpl.database;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.laokou.admin.gatewayimpl.database.dataobject.OperateLogDO;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.mybatisplus.database.BatchMapper;
import org.springframework.stereotype.Repository;

import static org.laokou.common.i18n.dto.PageQuery.PAGE_QUERY;

/**
 * 操作日志.
 *
 * @author laokou
 */
@Repository
@Mapper
public interface OperateLogMapper extends BatchMapper<OperateLogDO> {

	/**
	 * 查询操作日志列表.
	 * @param page 分页参数
	 * @param moduleName 模块名称
	 * @param status 状态
	 * @param pageQuery 分页参数
	 * @return 操作日志列表
	 */
	IPage<OperateLogDO> getOperateListFilter(IPage<OperateLogDO> page, @Param("moduleName") String moduleName,
			@Param("status") Integer status, @Param(PAGE_QUERY) PageQuery pageQuery);

}
