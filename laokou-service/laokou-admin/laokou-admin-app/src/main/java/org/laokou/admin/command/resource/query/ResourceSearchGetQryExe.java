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

package org.laokou.admin.command.resource.query;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.dto.resource.ResourceSearchGetQry;
import org.laokou.common.elasticsearch.template.ElasticsearchTemplate;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 搜索资源执行器
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class ResourceSearchGetQryExe {

	private final ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 执行搜索资源
	 * @param qry 搜索资源参数
	 * @return 资源
	 */
	public Result<Datas<Map<String, Object>>> execute(ResourceSearchGetQry qry) {
		return Result.of(elasticsearchTemplate.highlightSearchIndex(qry.getSearch()));
	}

}
