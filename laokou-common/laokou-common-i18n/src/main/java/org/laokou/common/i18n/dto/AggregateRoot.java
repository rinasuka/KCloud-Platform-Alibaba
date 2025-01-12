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

package org.laokou.common.i18n.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.laokou.common.i18n.common.exception.AuthException;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.i18n.utils.ValidatorUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
import static org.laokou.common.i18n.common.MybatisPlusConstants.*;
import static org.laokou.common.i18n.common.StatusCodes.CUSTOM_SERVER_ERROR;
import static org.laokou.common.i18n.common.ValCodes.OAUTH2_TENANT_ID_REQUIRE;
import static org.laokou.common.i18n.common.ValCodes.SYSTEM_ID_REQUIRE;

/**
 * @author laokou
 */
@Data
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Schema(name = "AggregateRoot", description = "聚合根")
public abstract class AggregateRoot<ID> extends Identifier<ID> {

	@Schema(name = CREATOR, description = "创建人")
	protected ID creator;

	@Schema(name = EDITOR, description = "编辑人")
	protected ID editor;

	@Schema(name = DEPT_ID, description = "部门ID")
	protected ID deptId;

	@Schema(name = DEPT_PATH, description = "部门PATH")
	protected String deptPath;

	@Schema(name = TENANT_ID, description = "租户ID")
	protected ID tenantId;

	@Schema(name = CREATE_DATE, description = "创建时间")
	protected LocalDateTime createDate;

	@Schema(name = UPDATE_DATE, description = "修改时间")
	protected LocalDateTime updateDate;

	@Schema(name = "events", description = "事件集合")
	private List<DomainEvent<ID>> events;

	public void checkNullId() {
		if (ObjectUtil.isNull(this.id)) {
			throw new SystemException(ValidatorUtil.getMessage(SYSTEM_ID_REQUIRE));
		}
	}

	public void checkNullTenantId() {
		if (ObjectUtil.isNull(this.tenantId)) {
			throw new AuthException(CUSTOM_SERVER_ERROR, ValidatorUtil.getMessage(OAUTH2_TENANT_ID_REQUIRE));
		}
	}

	protected void addEvent(DomainEvent<ID> event) {
		events().add(event);
	}

	public void clearEvents() {
		events = null;
	}

	private List<DomainEvent<ID>> events() {
		if (ObjectUtil.isNull(events)) {
			events = new ArrayList<>(16);
		}
		return events;
	}

}
