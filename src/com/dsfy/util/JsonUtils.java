/*
 * Copyright 2010 Fuchun.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dsfy.util;

import java.util.ArrayList;
import java.util.List;

import com.dsfy.entity.Parking;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	/** 默认的 {@code JSON} 日期/时间字段的格式化模式。 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private JsonUtils() {
	}

	private static Gson gson = null;

	public static Gson getGson() {
		if (gson == null) {
			GsonBuilder builder = new GsonBuilder();
			builder.setDateFormat(DEFAULT_DATE_PATTERN);
			gson = builder.create();
		}
		return gson;
	}

	public static void main(String[] args) {

		List<Parking> parkings = new ArrayList<Parking>();
		Parking parking = null;
		for (int i = 0; i < 100; i++) {
			parking = new Parking();
			parking.setAddress("深南大道");
			parking.setRegionId(2);
			parking.setRegion("深圳");
			parking.setFloorLevels(1);
			parking.setTotal(200);
			parking.setRemain(200);
			parking.setPhoneNumber("15989348952");
			parking.setPassState(1);
			parking.setParkingType(1);
			parking.setName("西丽停车场");
			parking.setMamager("刘斌");
			parking.setCoordinate("123.3333,234.4444");
			parkings.add(parking);
		}

		long star = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			GsonBuilder builder = new GsonBuilder();
			builder.setDateFormat(DEFAULT_DATE_PATTERN);
			Gson gg = builder.create();
			gg.toJson(parkings);
		}
		System.out.println(System.currentTimeMillis() - star);
		star = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			JsonUtils.getGson().toJson(parkings);
		}
		System.out.println(System.currentTimeMillis() - star);

		//注意这里的Gson的构建方式为GsonBuilder,区别于test1中的Gson gson = new Gson();  
		new GsonBuilder()//
				//不导出实体中没有用@Expose注解的属性  
				.excludeFieldsWithoutExposeAnnotation()
				//支持Map的key为复杂对象的形式  
				.enableComplexMapKeySerialization()//
				.serializeNulls()
				//时间转化为特定格式
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效. 
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				//对json结果格式化.
				.setPrettyPrinting()//
				//有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
				//@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
				//@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用. 
				.setVersion(1.0).create();
	}
}
