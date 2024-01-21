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

package org.laokou.auth.service;

import lombok.RequiredArgsConstructor;
import org.laokou.auth.api.SecretsServiceI;
import org.laokou.auth.command.secret.query.SecretGetQryExe;
import org.laokou.auth.dto.secret.SecretGetQry;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

/**
 * 密钥.
 *
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class SecretsServiceImpl implements SecretsServiceI {

	private final SecretGetQryExe secretGetQryExe;

	/**
	 * 获取密钥.
	 * @param qry 获取密钥参数
	 * @return 密钥
	 */
	@Override
	public Result<String> get(SecretGetQry qry) {
		return secretGetQryExe.execute(qry);
	}

}
