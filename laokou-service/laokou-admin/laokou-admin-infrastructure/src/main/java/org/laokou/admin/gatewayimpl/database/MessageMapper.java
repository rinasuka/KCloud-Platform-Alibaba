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
import org.laokou.admin.gatewayimpl.database.dataobject.MessageDO;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.mybatisplus.repository.CrudMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.laokou.common.i18n.dto.PageQuery.PAGE_QUERY;

/**
 * 消息.
 *
 * @author laokou
 */
@Repository
@Mapper
public interface MessageMapper extends CrudMapper<Long, Integer, MessageDO> {

	List<MessageDO> selectListByCondition(@Param("message") MessageDO message, @Param(PAGE_QUERY) PageQuery pageQuery);

	long selectCountByCondition(@Param("message") MessageDO message, @Param(PAGE_QUERY) PageQuery pageQuery);

	/**
	 * 根据详情ID查看消息.
	 * @param detailId 详情ID
	 * @return 消息
	 */
	MessageDO selectByDetailId(@Param("detailId") Long detailId);

	/**
	 * 根据用户ID和Type查询未读消息列表.
	 * @param type 类型
	 * @param userId 用户ID
	 * @param pageQuery 分页
	 * @return 未读消息列表
	 */
	List<MessageDO> selectUnreadListByCondition(@Param("userId") Long userId, @Param("type") Integer type,
			@Param(PAGE_QUERY) PageQuery pageQuery);

	long selectUnreadCountByCondition(@Param("userId") Long userId, @Param("type") Integer type,
			@Param(PAGE_QUERY) PageQuery pageQuery);

}
