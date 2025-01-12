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

package org.laokou.common.security.annotation;

import org.laokou.common.security.config.GlobalOpaqueTokenIntrospector;
import org.laokou.common.security.config.UserContextInterceptor;
import org.laokou.common.security.config.OAuth2ResourceServerAutoConfig;
import org.laokou.common.security.config.OAuth2SecurityAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ UserContextInterceptor.class, GlobalOpaqueTokenIntrospector.class, OAuth2SecurityAutoConfig.class,
		OAuth2ResourceServerAutoConfig.class })
public @interface EnableSecurity {

}
