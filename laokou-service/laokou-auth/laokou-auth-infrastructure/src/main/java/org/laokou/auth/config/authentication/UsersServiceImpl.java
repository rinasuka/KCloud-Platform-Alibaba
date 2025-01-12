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

package org.laokou.auth.config.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.auth.domain.user.Auth;
import org.laokou.auth.domain.user.User;
import org.laokou.common.core.utils.RequestUtil;
import org.laokou.common.crypto.utils.AesUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.OAuth2Constants.AUTHORIZATION_CODE;
import static org.laokou.common.i18n.common.OAuth2Constants.PASSWORD;
import static org.laokou.common.i18n.common.TenantConstants.DEFAULT;

/**
 * 用户认证.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UsersServiceImpl implements UserDetailsService {

	private final OAuth2AuthenticationProvider authProvider;

	/**
	 * 获取用户信息.
	 * @param username 用户名
	 * @return 用户信息
	 * @throws UsernameNotFoundException 异常
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			HttpServletRequest request = RequestUtil.getHttpServletRequest();
			String password = request.getParameter(PASSWORD);
			Auth authObj = Auth.builder().type(AUTHORIZATION_CODE).secretKey(AesUtil.getSecretKeyStr()).build();
			User user = User.builder().auth(authObj).tenantId(DEFAULT).username(username).password(password).build();
			return (UserDetails) authProvider.authenticationToken(user, request).getPrincipal();
		}
		catch (OAuth2AuthenticationException e) {
			throw new UsernameNotFoundException(e.getError().getDescription(), e);
		}
	}

}
