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

package org.laokou.admin.gatewayimpl.database;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.laokou.admin.gatewayimpl.database.dataobject.UserRoleDO;
import org.laokou.common.mybatisplus.repository.CrudMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.laokou.common.i18n.common.MybatisPlusConstants.USER_ID;

/**
 * 用户角色.
 *
 * @author laokou
 */
@Repository
@Mapper
public interface UserRoleMapper extends CrudMapper<Long, Integer, UserRoleDO> {

	/**
	 * 根据用户ID查询角色IDS.
	 * @param userId 用户ID
	 * @return 角色IDS
	 */
	List<Long> selectRoleIdsByUserId(@Param(USER_ID) Long userId);

	/**
	 * 根据用户ID删除用户角色.
	 * @param userId 用户ID
	 */
	void deleteByUserId(@Param(USER_ID) Long userId);

}
