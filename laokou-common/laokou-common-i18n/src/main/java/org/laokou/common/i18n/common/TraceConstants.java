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

package org.laokou.common.i18n.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author laokou
 */
@Schema(name = "TraceConstants", description = "分布式链路常量")
public final class TraceConstants {

	private TraceConstants() {
	}

	@Schema(name = "USER_NAME", description = "用户名")
	public static final String USER_NAME = "user-name";

	@Schema(name = "TRACE_ID", description = "分布式链路ID")
	public static final String TRACE_ID = "trace-id";

	@Schema(name = "USER_ID", description = "用户ID")
	public static final String USER_ID = "user-id";

	@Schema(name = "TENANT_ID", description = "租户ID")
	public static final String TENANT_ID = "tenant-id";

	@Schema(name = "DOMAIN_NAME", description = "域名")
	public static final String DOMAIN_NAME = "domain-name";

	@Schema(name = "REQUEST_ID", description = "请求ID")
	public static final String REQUEST_ID = "request-id";

}
