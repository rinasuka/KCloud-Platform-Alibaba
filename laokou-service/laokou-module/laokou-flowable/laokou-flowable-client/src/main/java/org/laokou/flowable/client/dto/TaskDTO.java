/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *   http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.flowable.client.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * @author laokou
 * @version 1.0
 * @date 2022/7/7 0007 下午 5:40
 */
@Data
public class TaskDTO {
    @NotNull(message = "显示页码不为空")
    private Integer pageNum;
    @NotNull(message = "显示条数不为空")
    private Integer pageSize;
    @NotBlank(message = "流程名称不为空")
    private String processName;
    @NotNull(message = "用户编号不为空")
    private Long userId;
    @NotBlank(message = "用户名不为空")
    private String username;
    @NotBlank(message = "流程编号不为空")
    private String processKey;

}
