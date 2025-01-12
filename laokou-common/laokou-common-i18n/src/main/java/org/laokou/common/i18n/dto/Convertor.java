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

import java.util.List;

/**
 * @author laokou
 */
@Schema(name = "Convertor", description = "对象转换器")
public interface Convertor<Entity, DO> {

	/**
	 * Entity 转 DataObject.
	 * @param e Entity
	 * @return DataObject
	 */
	DO toDataObject(Entity e);

	/**
	 * DataObject 转 Entity.
	 * @param d DataObject
	 * @return Entity
	 */
	Entity convertEntity(DO d);

	/**
	 * DataObject List 转 Entity List.
	 * @param list DataObject List
	 * @return Entity List
	 */
	List<Entity> convertEntityList(List<DO> list);

}
