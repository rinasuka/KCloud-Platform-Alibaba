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

package org.laokou.common.lock.annotation;

import org.laokou.common.i18n.common.LockTypeEnums;

import java.lang.annotation.*;

import static org.laokou.common.i18n.common.LockTypeEnums.LOCK;

/**
 * 分布式式锁注解.
 *
 * @author laokou
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock4j {

	/**
	 * 键.
	 */
	String key();

	/**
	 * 过期时间 单位：毫秒 过期时间一定是要长于业务的执行时间.
	 */
	long expire() default 5000;

	/**
	 * 获取锁超时时间 单位：毫秒 结合业务,建议该时间不宜设置过长,特别在并发高的情况下.
	 */
	long timeout() default 50;

	/**
	 * 分布式锁类型.
	 */
	LockTypeEnums type() default LOCK;

	/**
	 * 开启标识(解决公共模块用同一把锁).
	 */
	boolean enable() default false;

}
