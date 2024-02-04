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

package org.laokou.admin.service;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.api.PackagesServiceI;
import org.laokou.admin.dto.common.clientobject.OptionCO;
import org.laokou.admin.dto.packages.*;
import org.laokou.admin.dto.packages.clientobject.PackageCO;
import org.laokou.admin.command.packages.PackageRemoveCmdExe;
import org.laokou.admin.command.packages.PackageCreateCmdExe;
import org.laokou.admin.command.packages.PackageModifyCmdExe;
import org.laokou.admin.command.packages.query.PackageGetQryExe;
import org.laokou.admin.command.packages.query.PackageListQryExe;
import org.laokou.admin.command.packages.query.PackageOptionListQryExe;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐管理.
 *
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class PackagesServiceImpl implements PackagesServiceI {

	private final PackageCreateCmdExe packageCreateCmdExe;

	private final PackageModifyCmdExe packageModifyCmdExe;

	private final PackageListQryExe packageListQryExe;

	private final PackageRemoveCmdExe packageRemoveCmdExe;

	private final PackageGetQryExe packageGetQryExe;

	private final PackageOptionListQryExe packageOptionListQryExe;

	/**
	 * 新增套餐.
	 * @param cmd 新增套餐参数
	 * @return 新增结果
	 */
	@Override
	public Result<Boolean> insert(PackageCreateCmd cmd) {
		return packageCreateCmdExe.execute(cmd);
	}

	/**
	 * 修改套餐.
	 * @param cmd 修改套餐参数
	 * @return 修改结果
	 */
	@Override
	public Result<Boolean> update(PackageModifyCmd cmd) {
		return packageModifyCmdExe.execute(cmd);
	}

	/**
	 * 根据ID删除套餐.
	 * @param cmd 根据ID删除套餐参数
	 * @return 删除结果
	 */
	@Override
	public Result<Boolean> deleteById(PackageRemoveCmd cmd) {
		return packageRemoveCmdExe.execute(cmd);
	}

	/**
	 * 查询套餐列表.
	 * @param qry 查询套餐列表参数
	 * @return 套餐列表
	 */
	@Override
	public Result<Datas<PackageCO>> list(PackageListQry qry) {
		return packageListQryExe.execute(qry);
	}

	/**
	 * 根据ID查看套餐.
	 * @param qry 根据ID查看套餐参数
	 * @return 套餐
	 */
	@Override
	public Result<PackageCO> getById(PackageGetQry qry) {
		return packageGetQryExe.execute(qry);
	}

	/**
	 * 查询套餐下拉框选择项列表.
	 * @param qry 查询套餐下拉框选择项列表参数
	 * @return 套餐下拉框选择项列表
	 */
	@Override
	public Result<List<OptionCO>> optionList(PackageOptionListQry qry) {
		return packageOptionListQryExe.execute();
	}

}
