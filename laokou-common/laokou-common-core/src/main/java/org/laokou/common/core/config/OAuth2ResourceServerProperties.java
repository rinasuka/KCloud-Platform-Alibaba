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

package org.laokou.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.laokou.common.i18n.common.PropertiesConstants.OAUTH2_RESOURCE_SERVER_PREFIX;

// @formatter:off
/**
 * @author laokou
 * 格式 -> 路径=服务名（多个服务用逗号,隔开）
 * 举例 -> /actuator/**=laokou-admin,laokou-gateway
 */
// @formatter:on
@Data
@Component
@ConfigurationProperties(prefix = OAUTH2_RESOURCE_SERVER_PREFIX)
public class OAuth2ResourceServerProperties {

	private boolean enabled = true;

	private RequestMatcher requestMatcher;

	@Data
	public static class RequestMatcher {

		private Map<String, Set<String>> ignorePatterns = Collections.emptyMap();

	}

}
