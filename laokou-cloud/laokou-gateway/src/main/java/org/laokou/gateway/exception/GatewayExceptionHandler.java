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
package org.laokou.gateway.exception;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.laokou.gateway.utils.ResponseUtil;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Map;
/**
 * 异常处理器
 * @author laokou
 * @since 1.0.0
 */
@Component
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler, Ordered {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable e) {
		log.error("网关全局处理异常，异常信息:{}",e.getMessage());
		Map<String,Object> result;
		if (e instanceof RuntimeException){
			log.error("服务正在维护，请联系管理员");
			result = ResponseUtil.error(GatewayException.SERVICE_MAINTENANCE);
		} else if (BlockException.isBlockException(e)){
			log.error("操作太频繁，请稍后再试");
			result = ResponseUtil.error(GatewayException.BLOCK_REQUEST);
		} else {
			log.error("未知错误");
			result = ResponseUtil.error(GatewayException.UNKNOWN);
		}
		return ResponseUtil.response(exchange,result);
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
