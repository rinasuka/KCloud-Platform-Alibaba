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

package org.laokou.flowable;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.laokou.common.core.annotation.EnableTaskExecutor;
import org.laokou.common.nacos.annotation.EnableRouter;
import org.laokou.common.nacos.filter.ShutdownFilter;
import org.laokou.common.redis.annotation.EnableRedisRepository;
import org.laokou.common.security.annotation.EnableSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.laokou.common.i18n.common.NetworkConstants.IP;

/**
 * 启动类. exposeProxy=true => 使用Cglib代理，在切面中暴露代理对象，进行方法增强（默认Cglib代理）
 *
 * @author laokou
 */
@EnableRedisRepository
@SpringBootApplication(exclude = { SecurityFilterAutoConfiguration.class }, scanBasePackages = "org.laokou")
@EnableDiscoveryClient
@EnableEncryptableProperties
@ServletComponentScan(basePackageClasses = { ShutdownFilter.class })
@EnableSecurity
@EnableRouter
@EnableTaskExecutor
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan({ "org.laokou.common.domain.repository", "org.laokou.flowable.gatewayimpl.database",
		"org.laokou.common.mybatisplus.repository" })
public class FlowableApp {

	public static void main(String[] args) throws UnknownHostException {
		// System.setProperty(TlsSystemConfig.TLS_ENABLE, TRUE);
		// System.setProperty(TlsSystemConfig.CLIENT_AUTH, TRUE);
		// System.setProperty(TlsSystemConfig.CLIENT_TRUST_CERT, "tls/nacos.cer");
		System.setProperty(IP, InetAddress.getLocalHost().getHostAddress());
		System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		// 因为nacos的log4j2导致本项目的日志不输出的问题
		// 配置关闭nacos日志
		System.setProperty("nacos.logging.default.config.enabled", "false");
		new SpringApplicationBuilder(FlowableApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}
