/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.laokou.common.core.utils.JacksonUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author laokou
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UriTest {

	@Test
	public void test() {
		String str = "laokou-admin";
		Map<String, List<String>> map = new java.util.HashMap<>(
				Map.of("GET", List.of("/ttt=laokou-admin,laokou-gateway", "/tts=laokou-gateway")));
		map.forEach((k, v) -> map.put(k,
				v.stream().filter(i -> i.contains(str)).map(i -> i.substring(0, i.indexOf("="))).toList()));
		System.out.println(JacksonUtil.toJsonStr(map));
	}

}
