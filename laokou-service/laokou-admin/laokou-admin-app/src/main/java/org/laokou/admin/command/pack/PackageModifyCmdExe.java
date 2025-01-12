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

package org.laokou.admin.command.pack;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.domain.gateway.PackageGateway;
import org.laokou.admin.domain.packages.Package;
import org.laokou.admin.dto.packages.PackageModifyCmd;
import org.laokou.admin.dto.packages.clientobject.PackageCO;
import org.springframework.stereotype.Component;

/**
 * 修改套餐执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class PackageModifyCmdExe {

	private final PackageGateway packageGateway;

	/**
	 * 执行修改套餐.
	 * @param cmd 修改套餐参数
	 */
	public void executeVoid(PackageModifyCmd cmd) {
		packageGateway.modify(convert(cmd.getPackageCO()));
	}

	private Package convert(PackageCO packageCO) {
		return Package.builder()
			.id(packageCO.getId())
			.name(packageCO.getName())
			.menuIds(packageCO.getMenuIds())
			.build();
	}

}
