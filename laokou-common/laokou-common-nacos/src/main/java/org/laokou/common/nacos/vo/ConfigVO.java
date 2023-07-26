/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
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

package org.laokou.common.nacos.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laokou
 */
@Data
public class ConfigVO implements Serializable {

	private String appName;

	private String configTags;

	private String content;

	private String createIp;

	private Long createTime;

	private String createUser;

	private String dataId;

	private String desc;

	private String effect;

	private String encryptedDataKey;

	private String group;

	private String id;

	private String md5;

	private Long modifyTime;

	private String schema;

	private String tenant;

	private String type;

	private String use;

}