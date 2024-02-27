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

package org.laokou.admin.actionimpl;

import org.laokou.admin.domain.action.AuditAction;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author laokou
 */
@Component("auditAction")
public class AuditActionImpl implements AuditAction {

    @Override
    public boolean audit(String taskId, Map<String, Object> values) {
        return true;
    }

    @Override
    public boolean compensateAudit(String taskId) {
        return true;
    }

}
