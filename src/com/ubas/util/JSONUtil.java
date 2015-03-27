package com.ubas.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 * JSON封装对象/对象拆分成JSON
 *
 */
public class JSONUtil {
	/**
	 * JSON 所有key的常量
	 */
	public static final String P_WEBSITE = "website";
	public static final String P_STARTTIME = "startTime"; // 查询开始时间
	public static final String P_ENDTIME = "endTime";// 查询结束时间
	public static final String P_CUSTOMERTYPE = "customerType";// 客户类型ID
	public static final String P_FASION = "fasion";// 风格ID
	public static final String P_REGISTERTYPE = "registerType";// 注册类型ID
	public static final String P_ACCOUNT = "account";// 客户账号
	public static final String P_CURRPAGE = "currPage";// 当前第几页（分页中用到）
	public static final String P_SHOWNUMBER = "showNumber";// 总共显示多少条（分页中用到）
	public static final String P_AREANO = "areaNo";// 所属区域代码
	public static final String P_SEARCHMOTH = "searchMouth";// 搜索
	public static final String P_URL = "url";// 浏览URL
	public static final String P_COLUMNS = "columns";// 选择栏目ID集合
	public static final String P_SORTS = "sorts";// 频道集合
	public static final String P_EXPANDFIELD = "expandField";// 扩展查询字段（需要的时候用到）

	/**
	 * 字段类型字符串
	 */
	public static final String INT = "int";
	public static final String LONG = "long";
	public static final String STRING = "string";
	public static final String STRING_ARRAY = "string_array";
	public static final String INT_ARRAY = "int_array";

	/**
	 * Json串转换成对象
	 * 
	 * @param jsonstr
	 * @return
	 */
	public static Parameter jsonToObject(String jsonstr) {
		if (null == jsonstr || jsonstr.length() == 0) {
			return null;
		}
		JSONObject jsonObj = (JSONObject) JSONValue.parse(jsonstr);
		if (null == jsonObj) {
			return null;
		}
		Parameter parameter = new Parameter();

		/* 处理网站ID */
		Object obj = getParameter(jsonObj, P_WEBSITE, INT);
		if (null != obj) {
			parameter.setWebsite((Integer) obj);
		}
		/* 处理查询开始时间 */
		obj = getParameter(jsonObj, P_STARTTIME, LONG);
		if (null != obj) {
			parameter.setStartTime((Long) obj);
		}
		/* 处理查询结束时间 */
		obj = getParameter(jsonObj, P_ENDTIME, LONG);
		if (null != obj) {
			parameter.setEndTime((Long) obj);
		}
		/* 处理客户类型ID */
		obj = getParameter(jsonObj, P_CUSTOMERTYPE, INT);
		if (null != obj) {
			parameter.setCustomerType((Integer) obj);
		}
		/* 处理风格ID */
		obj = getParameter(jsonObj, P_FASION, INT);
		if (null != obj) {
			parameter.setFasion((Integer) obj);
		}
		/* 处理注册类型ID */
		obj = getParameter(jsonObj, P_REGISTERTYPE, INT);
		if (null != obj) {
			parameter.setRegisterType((Integer) obj);
		}
		/* 处理客户账号 */
		obj = getParameter(jsonObj, P_ACCOUNT, STRING);
		if (null != obj) {
			parameter.setAccount((String) obj);
		}
		/* 处理当前第几页（分页中用到） */
		obj = getParameter(jsonObj, P_CURRPAGE, INT);
		if (null != obj) {
			parameter.setCurrPage((Integer) obj);
		}
		/* 总共显示多少条（分页中用到） */
		obj = getParameter(jsonObj, P_SHOWNUMBER, INT);
		if (null != obj) {
			parameter.setShowNumber((Integer) obj);
		}
		/* 所属区域代码 */
		obj = getParameter(jsonObj, P_AREANO, INT);
		if (null != obj) {
			parameter.setAreaNo((Integer) obj);
		}
		/* 处理搜索 */
		obj = getParameter(jsonObj, P_SEARCHMOTH, STRING_ARRAY);
		if (null != obj) {
			parameter.setSearchMouth((String[]) obj);
		}
		/* 处理浏览URL */
		obj = getParameter(jsonObj, P_URL, STRING);
		if (null != obj) {
			parameter.setUrl((String) obj);
		}
		/* 处理选择栏目ID集合 */
		obj = getParameter(jsonObj, P_COLUMNS, INT_ARRAY);
		if (null != obj) {
			parameter.setColumns((int[]) obj);
		}
		/* 处理 频道集合 */
		obj = getParameter(jsonObj, P_SORTS, INT_ARRAY);
		if (null != obj) {
			parameter.setSorts((int[]) obj);
		}
		/* 处理扩展查询字段 */
		obj = getParameter(jsonObj, P_EXPANDFIELD, STRING);
		if (null != obj) {
			parameter.setExpandField((String) obj);
		}
		return parameter;
	}

	/**
	 * 获取字段值
	 * 
	 * @param jsonObj
	 * @param key
	 * @param returnType
	 * @return
	 */
	public static Object getParameter(JSONObject jsonObj, String key,
			String returnType) {
		Object obj = jsonObj.get(key);
		if (null == obj)
			return null;

		if (obj instanceof String) { // 当前JSON对象是字符串
			switch (returnType) {
			case INT:
				return ConversionUtil.strParseInt((String) obj);
			case STRING:
				return obj;
			case LONG:
				return ConversionUtil.strParseLong((String) obj);
			}
		} else if (obj instanceof Long) { // 当前JSON对象是数值
			switch (returnType) {
			case INT:
				return ((Long) obj).intValue();
			case LONG:
				return obj;
			}
		} else if (obj instanceof JSONArray) { // 当前JSON对象是集合
			JSONArray array = (JSONArray) obj;
			if (array.isEmpty())
				return null;
			Object temp = array.get(0);
			int array_size = array.size();
			if (temp instanceof String) { // JSON集合里的元素是字符串
				switch (returnType) {
				case STRING_ARRAY: // 要求返回的是字符数组
					String[] str_arr = new String[array_size];
					for (int i = 0; i < array_size; i++) {
						str_arr[i] = (String) array.get(i);
					}
					return str_arr;
				case INT_ARRAY: // 要求返回的是数值数组
					int[] int_arr = new int[array.size()];
					for (int i = 0; i < array_size; i++) {
						int_arr[i] = ConversionUtil.strParseInt((String) array
								.get(i));
					}
					return int_arr;
				}
			} else if (temp instanceof Long) { // JSON集合里的元素是数值
				switch (returnType) {
				case STRING_ARRAY:// 要求返回的是字符数组
					String[] str_arr = new String[array.size()];
					for (int i = 0; i < array_size; i++) {
						str_arr[i] = String.valueOf(array.get(i));
					}
					return str_arr;
				case INT_ARRAY:// 要求返回的是数值数组
					int[] int_arr = new int[array.size()];
					for (int i = 0; i < array_size; i++) {
						int_arr[i] = ((Long) array.get(i)).intValue();
					}
					return int_arr;
				}
			}
		}
		return null;
	}

	/**
	 * 对象转换成Json串
	 * 
	 * @param parameter
	 * @return
	 */
	public static String objectToJson(Parameter parameter) {
		return null;

	}

	private static void testStrToObject() {
		JSONObject obj = new JSONObject();
		obj.put(P_WEBSITE, 1);
		obj.put(P_STARTTIME, 123456789);
		obj.put(P_ACCOUNT, "luoyk");
		JSONArray array1 = new JSONArray();
		array1.add("111");
		array1.add("222");
		array1.add("333");
		obj.put(P_SEARCHMOTH, array1);

		JSONArray array2 = new JSONArray();
		array2.add(111);
		array2.add(222);
		array2.add(333);
		obj.put(P_COLUMNS, array2);
		Parameter parameter = jsonToObject(obj.toString());
		System.out.println(parameter.getWebsite());
		System.out.println(parameter.getStartTime());
		System.out.println(parameter.getAccount());
		System.out.println(parameter.getSearchMouth()[1]);
		System.out.println(parameter.getColumns()[1]);
	}
}
