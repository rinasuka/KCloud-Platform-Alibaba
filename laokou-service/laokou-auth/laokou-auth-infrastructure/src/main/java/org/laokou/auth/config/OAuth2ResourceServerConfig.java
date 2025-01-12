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

package org.laokou.auth.config;

import lombok.Data;
import org.laokou.common.core.config.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.laokou.common.i18n.common.PropertiesConstants.OAUTH2_AUTHORIZATION_SERVER_PREFIX;
import static org.laokou.common.i18n.common.StringConstants.TRUE;
import static org.laokou.common.i18n.common.SysConstants.ENABLED;
import static org.laokou.common.security.config.OAuth2ResourceServerAutoConfig.customizer;

/**
 * 资源服务器配置.
 *
 * @author laokou
 */
@Data
@Configuration
@ConditionalOnProperty(havingValue = TRUE, matchIfMissing = true, prefix = OAUTH2_AUTHORIZATION_SERVER_PREFIX,
		name = ENABLED)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class OAuth2ResourceServerConfig {

	/**
	 * 不拦截拦截静态资源 如果您不想要警告消息并且需要性能优化，则可以为静态资源引入第二个过滤器链.
	 * <a href="https://github.com/spring-projects/spring-security/issues/10938">优化配置</a>
	 * @param http http配置
	 * @param oAuth2ResourceServerProperties OAuth2配置文件
	 * @param env 环境配置
	 * @return 认证过滤器
	 * @throws Exception 异常
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
			OAuth2ResourceServerProperties oAuth2ResourceServerProperties, Environment env) throws Exception {
		return http
			.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.httpStrictTransportSecurity(
					hsts -> hsts.includeSubDomains(true).preload(true).maxAgeInSeconds(31536000)))
			.authorizeHttpRequests(customizer(env, oAuth2ResourceServerProperties))
			.cors(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			// 自定义登录页面
			// https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
			// 登录页面 -> DefaultLoginPageGeneratingFilter
			.formLogin(Customizer.withDefaults())
			// 清除session
			.logout(logout -> logout.clearAuthentication(true).invalidateHttpSession(true))
			.build();
	}

}
