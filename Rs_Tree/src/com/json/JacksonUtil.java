package com.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * <p>
 * Title: JacksonUtil
 * </p>
 * <p>
 * Description: Jackson工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-30 下午1:13:29
 */
public class JacksonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// mapper.getDeserializationConfig().without(
		// DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.getDeserializationConfig().set(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@SuppressWarnings("rawtypes")
	public static class TypeReference<T extends Object> extends
			org.codehaus.jackson.type.TypeReference {
		public int compareTo(Object o) {
			return 0;
		}

		public Type getTypeReference() {
			return this.getType();
		}
	}

	/**
	 * @param
	 * @return String
	 * @throws
	 * @Title: toJson
	 * @Description: 将对象转换为Json字符串
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-28 ����4:47:44
	 */
	public static String toJson(Object obj) {
		try {
			if (obj == null) {
				return null;
			} else {
				return mapper.writeValueAsString(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException("转换Json异常!" + obj.getClass().getName(), e);
		}
	}

	/**
	 * @param
	 * @return Object
	 * @throws
	 * @Title: toObject
	 * @Description: 转换Json to 对象
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-28 ����4:54:25
	 */
	public static Object toObject(String json) {
		return fromJson(json, Object.class);
	}

	/**
	 * @param
	 * @return T
	 * @throws
	 * @Title: fromJson
	 * @Description: 转换json to Java对象
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-28 ����4:51:42
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		try {
			if (json == null) {
				return null;
			} else {
				return mapper.readValue(json, type);
			}
		} catch (Exception e) {
			throw new RuntimeException(json + "解析Json异常！" + type.getName(), e);
		}
	}

	/**
	 * @param
	 * @return T
	 * @throws
	 * @Title: fromJSON
	 * @Description: 转换复杂对象
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-28 ����4:53:39
	 */
	public static <T> T fromJSON(String json, TypeReference<T> typeReference) {
		try {
			if (json == null) {
				return null;
			} else {
				return (T) mapper.readValue(json, typeReference);
			}
		} catch (Exception ex) {
			throw new RuntimeException(json + "解析Json异常！", ex);
		}
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: JSONToMap
	 * @Description: 将json字符串转换成Map
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-15 下午9:13:57
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> JSONToMap(String jsonstr) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
		try {
			list = mapper.readValue(jsonstr, List.class);
			for (LinkedHashMap<String, Object> linkedHashMap : list) {
				for (String key : linkedHashMap.keySet()) {
					map.put(key, linkedHashMap.get(key));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(jsonstr + "解析Json异常！", e);
		}
		return map;
	}

	/**
	 * 格式化JSON字符串
	 * 
	 * @param json
	 *            要输入的JSON字符串
	 * @return 格式化后的字符串
	 */
	public static String formatJson(String json) {
		StringBuilder buf = new StringBuilder();
		try {
			String fillStringUnit = "  ";
			if (json == null || json.trim().length() == 0) {
				return null;
			}

			int fixedLenth = 0;
			ArrayList<String> tokenList = new ArrayList<String>();
			{
				String jsonTemp = json;
				// 预读取
				while (jsonTemp.length() > 0) {
					String token = getToken(jsonTemp);
					jsonTemp = jsonTemp.substring(token.length());
					token = token.trim();
					tokenList.add(token);
				}
			}

			for (int i = 0; i < tokenList.size(); i++) {
				String token = tokenList.get(i);
				int length = token.getBytes().length;
				if (length > fixedLenth && i < tokenList.size() - 1
						&& tokenList.get(i + 1).equals(":")) {
					fixedLenth = length;
				}
			}

			int count = 0;
			for (int i = 0; i < tokenList.size(); i++) {

				String token = tokenList.get(i);

				if (token.equals(",")) {
					buf.append(token);
					doFill(buf, count, "  ");
					continue;
				}
				if (token.equals(":")) {
					buf.append(" ").append(token).append(" ");
					continue;
				}
				if (token.equals("{")) {
					String nextToken = tokenList.get(i + 1);
					if (nextToken.equals("}")) {
						i++;
						buf.append("{ }");
					} else {
						count++;
						buf.append(token);
						doFill(buf, count, fillStringUnit);
					}
					continue;
				}
				if (token.equals("}")) {
					count--;
					doFill(buf, count, fillStringUnit);
					buf.append(token);
					continue;
				}
				if (token.equals("[")) {
					String nextToken = tokenList.get(i + 1);
					if (nextToken.equals("]")) {
						i++;
						buf.append("[ ]");
					} else {
						count++;
						buf.append(token);
						doFill(buf, count, fillStringUnit);
					}
					continue;
				}
				if (token.equals("]")) {
					count--;
					doFill(buf, count, fillStringUnit);
					buf.append(token);
					continue;
				}

				buf.append(token);
				// 左对齐
				if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
					int fillLength = fixedLenth - token.getBytes().length;
					if (fillLength > 0) {
						for (int j = 0; j < fillLength; j++) {
							buf.append(" ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	private static String getToken(String json) {
		StringBuilder buf = new StringBuilder();
		boolean isInYinHao = false;
		while (json.length() > 0) {
			String token = json.substring(0, 1);
			json = json.substring(1);

			if (!isInYinHao
					&& (token.equals(":") || token.equals("{") || token.equals("}")
							|| token.equals("[") || token.equals("]") || token.equals(","))) {
				if (buf.toString().trim().length() == 0) {
					buf.append(token);
				}

				break;
			}

			if (token.equals("\\")) {
				buf.append(token);
				buf.append(json.substring(0, 1));
				json = json.substring(1);
				continue;
			}
			if (token.equals("\"")) {
				buf.append(token);
				if (isInYinHao) {
					break;
				} else {
					isInYinHao = true;
					continue;
				}
			}
			buf.append(token);
		}
		return buf.toString();
	}

	private static void doFill(StringBuilder buf, int count, String fillStringUnit) {
		buf.append("\n");
		for (int i = 0; i < count; i++) {
			buf.append(fillStringUnit);
		}
	}
}
