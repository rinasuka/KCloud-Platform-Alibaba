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
import lombok.extern.slf4j.Slf4j;
import org.laokou.auth.domain.user.Auth;
import org.laokou.auth.domain.user.Captcha;
import org.laokou.auth.domain.user.User;
import org.laokou.common.crypto.utils.AesUtil;
import org.laokou.common.i18n.common.exception.GlobalException;
import org.laokou.common.i18n.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.OAuth2Constants.MAIL;
import static org.laokou.common.i18n.common.TenantConstants.TENANT_ID;
import static org.laokou.common.security.handler.OAuth2ExceptionHandler.getException;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CODE;

/**
 * 邮箱处理器.
 *
 * @author laokou
 */
@Slf4j
@Component("mailAuthenticationProvider")
public class OAuth2MailAuthenticationProvider extends AbstractOAuth2AuthenticationProvider {

	public OAuth2MailAuthenticationProvider(OAuth2AuthorizationService authorizationService,
			OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, OAuth2AuthenticationProvider authProvider) {
		super(authorizationService, tokenGenerator, authProvider);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OAuth2MailAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	Authentication principal(HttpServletRequest request) {
		try {
			String tenantId = request.getParameter(TENANT_ID);
			String code = request.getParameter(CODE);
			String mail = request.getParameter(MAIL);
			// log.info("租户ID：{}", tenantId);
			// log.info("验证码：{}", code);
			// log.info("邮箱：{}", SensitiveUtil.format(Type.MAIL, mail));
			Captcha captchaObj = Captcha.builder().captcha(code).uuid(mail).build();
			Auth authObj = Auth.builder().type(getGrantType().getValue()).secretKey(AesUtil.getSecretKeyStr()).build();
			User user = User.builder()
				.tenantId(StringUtil.parseLong(tenantId))
				.mail(mail)
				.captcha(captchaObj)
				.username(encryptAes(mail))
				.auth(authObj)
				.build();
			// 检查租户ID
			user.checkNullTenantId();
			// 检查验证码
			captchaObj.checkNullCaptcha();
			// 检查邮箱
			user.checkMail();
			// 获取用户信息,并认证信息
			return super.authenticationToken(user, request);
		}
		catch (GlobalException e) {
			throw getException(e.getCode(), e.getMsg());
		}
	}

	@Override
	AuthorizationGrantType getGrantType() {
		return new AuthorizationGrantType(MAIL);
	}

}
