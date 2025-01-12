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

package org.laokou.admin.command.ip;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.gateway.IpGateway;
import org.laokou.admin.domain.ip.Ip;
import org.laokou.admin.dto.ip.IpCreateCmd;
import org.laokou.admin.dto.ip.clientobject.IpCO;
import org.laokou.common.core.utils.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * 新增IP执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class IpCreateCmdExe {

	private final IpGateway ipGateway;

	/**
	 * 执行新增IP.
	 * @param cmd 新增IP参数
	 */
	public void executeVoid(IpCreateCmd cmd) {
		ipGateway.create(convert(cmd.getIpCO()));
	}

	private Ip convert(IpCO ipCO) {
		return Ip.builder().id(IdGenerator.defaultSnowflakeId()).value(ipCO.getValue()).label(ipCO.getLabel()).build();
	}

}
