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

package org.laokou.auth.gatewayimpl.database;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.laokou.auth.gatewayimpl.database.dataobject.UserDO;
import org.laokou.common.mybatisplus.repository.CrudMapper;
import org.springframework.stereotype.Repository;

import static org.laokou.common.i18n.common.OAuth2Constants.USERNAME;

/**
 * 用户.
 *
 * @author laokou
 */
@Mapper
@Repository
public interface UserMapper extends CrudMapper<Long, Integer, UserDO> {

	/**
	 * 根据用户名和登录类型查看用户信息.
	 * @param username 用户名
	 * @param type 类型 mail邮箱 mobile手机号 password密码 authorization_code授权码
	 * @param secretKey 密钥Key
	 * @return 用户信息
	 */
	UserDO selectByConditions(@Param(USERNAME) String username, @Param("type") String type,
			@Param("secretKey") String secretKey);

}
