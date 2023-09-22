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

package org.laokou.flowable.service;

import lombok.RequiredArgsConstructor;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.laokou.flowable.api.DefinitionsServiceI;
import org.laokou.flowable.command.definition.DefinitionActiveCmdExe;
import org.laokou.flowable.command.definition.DefinitionDeleteCmdExe;
import org.laokou.flowable.command.definition.DefinitionInsertCmdExe;
import org.laokou.flowable.command.definition.DefinitionSuspendCmdExe;
import org.laokou.flowable.command.definition.query.DefinitionDiagramGetQryExe;
import org.laokou.flowable.command.definition.query.DefinitionListQryExe;
import org.laokou.flowable.dto.definition.*;
import org.laokou.flowable.dto.definition.clientobject.DefinitionCO;
import org.springframework.stereotype.Service;

/**
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class DefinitionsServiceImpl implements DefinitionsServiceI {

	private final DefinitionInsertCmdExe definitionInsertCmdExe;
	private final DefinitionListQryExe definitionListQryExe;
	private final DefinitionActiveCmdExe definitionActiveCmdExe;
	private final DefinitionSuspendCmdExe definitionSuspendCmdExe;
	private final DefinitionDeleteCmdExe definitionDeleteCmdExe;
	private final DefinitionDiagramGetQryExe definitionDiagramGetQryExe;

	@Override
	public Result<Boolean> insert(DefinitionInsertCmd cmd) {
		return definitionInsertCmdExe.execute(cmd);
	}

	@Override
	public Result<Datas<DefinitionCO>> list(DefinitionListQry qry) {
		return definitionListQryExe.execute(qry);
	}

	@Override
	public Result<String> diagram(DefinitionDiagramGetQry qry) {
		return definitionDiagramGetQryExe.execute(qry);
	}

	@Override
	public Result<Boolean> delete(DefinitionDeleteCmd cmd) {
		return definitionDeleteCmdExe.execute(cmd);
	}

	@Override
	public Result<Boolean> suspend(DefinitionSuspendCmd cmd) {
		return definitionSuspendCmdExe.execute(cmd);
	}

	@Override
	public Result<Boolean> activate(DefinitionActivateCmd cmd) {
		return definitionActiveCmdExe.execute(cmd);
	}

}
