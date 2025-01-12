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

package org.laokou.common.crypto.utils;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.utils.Base64;
import org.laokou.common.core.utils.ResourceUtil;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.crypto.annotation.Aes;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import static org.laokou.common.i18n.common.SysConstants.*;

/**
 * AES加密工具类.
 *
 * @author laokou
 */
public class AesUtil {

	private final static byte[] SECRET_KEY;

	private final static String SECRET_KEY_STRING;

	static {
		try (InputStream inputStream = ResourceUtil.getResource("conf/secretKey.b16").getInputStream()) {
			SECRET_KEY = inputStream.readAllBytes();
			Assert.isTrue(SECRET_KEY.length == 16, "密钥长度必须16位");
			SECRET_KEY_STRING = new String(SECRET_KEY, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取加密密钥.
	 * @return 加密密钥
	 */
	public static String getSecretKeyStr() {
		return SECRET_KEY_STRING;
	}

	/**
	 * 获取加密密钥.
	 * @return 加密密钥
	 */
	private static byte[] getSecretKey() {
		return SECRET_KEY;
	}

	/**
	 * 加密.
	 * @param str 字符串
	 * @return 加密后的字符串
	 */
	@SneakyThrows
	public static String encrypt(String str) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(getSecretKey(), ALGORITHM_AES);
		Cipher cipher = Cipher.getInstance(AES_INSTANCE);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
		byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 解密.
	 * @param str 字符串
	 * @return 解密后的字符串
	 */
	@SneakyThrows
	public static String decrypt(String str) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(getSecretKey(), ALGORITHM_AES);
		Cipher cipher = Cipher.getInstance(AES_INSTANCE);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));
		byte[] bytes = Base64.decodeBase64(str);
		return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
	}

	/**
	 * 对象属性加密/解密.
	 * @param obj 对象
	 * @throws IllegalAccessException 异常
	 */
	public static void transform(Object obj) throws IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			boolean annotationPresent = field.isAnnotationPresent(Aes.class);
			if (annotationPresent) {
				// 私有属性
				field.setAccessible(true);
				Object o = field.get(obj);
				if (ObjectUtil.isNull(o)) {
					continue;
				}
				String data = o.toString();
				Aes aes = field.getAnnotation(Aes.class);
				data = switch (aes.type()) {
					case DECRYPT -> decrypt(data);
					case ENCRYPT -> encrypt(data);
				};
				// 属性赋值
				field.set(obj, data);
			}
		}
	}

}
