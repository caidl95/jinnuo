package xyz.zhuoxuan.jinnuo.util;

import xyz.zhuoxuan.jinnuo.annotation.NotNull;
import xyz.zhuoxuan.jinnuo.serivce.ex.ServiceException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 参数工具类
 */
public class ParamUtil {

	// 手机号正则
	private static final String PHONE_REG = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";

	// 密码正则
	private static final String PASSWORD_REG = "^\\w{6,18}$";

	// 密码错误消息
	private static final String PASSWORD_MSG = "密码长度在6~18之间，只能包含字母、数字和下划线";

	private ParamUtil() {
		throw new RuntimeException("new ParamUtil instance error");
	}

	/**
	 * 检验多个整数数组参数是否小于等于零
	 * 
	 * @param msg：错误信息
	 * @param lines：整数数组
	 *
	 */
	public static void validateLine(String msg, int... lines) {
		if (lines.length <= 0) {
			ServiceException.throwException(msg);
		}
		for (int line : lines) {
			if (line <= 0) {
				ServiceException.throwException(msg);
			}
		}
	}

	/**
	 * 检验参数是否为空，如果是则报错
	 * 
	 * @param param：参数
	 * @param msg：错误信息
	 */
	public static void validateParam(Object param, String msg) {
		if (isNullForParam(param)) {
			ServiceException.throwException(msg);
		}
	}

	/**
	 * 检验集合是否为空，如果是则报错
	 * 
	 * @param collection：参数集合
	 * @param msg：错误信息数组
	 */
	public static void validateParam(Collection<?> collection, String msg) {
		if (isNullForCollection(collection)) {
			ServiceException.throwException(msg);
		}
	}

	/**
	 * 检验多个参数是否为空，如果是则报错
	 * 
	 * @param msg：错误信息
	 * @param params：多个参数
	 */
	public static void validateParams(String msg, Object... params) {
		if (isNullForArray(params)) {
			ServiceException.throwException(msg);
		}
	}

	/**
	 * 检验多个参数是否为空，如果是则报错
	 * 
	 * @param params：参数数组
	 * @param msgArray：错误信息数组
	 */
	public static void validateParams(Object[] params, String[] msgArray) {
		if (isNullForLength(params) || isNullForLength(msgArray) || params.length != msgArray.length) {
			ServiceException.throwException("校验失败");
		}
		for (int i = 0; i < params.length; i++) {
			validateParam(params[i], msgArray[i]);
		}
	}

	/**
	 * 校验对象和属性是否为空
	 * 
	 * @param paramObj：参数
	 */
	public static void validateParamField(Object paramObj) {
		validateParam(paramObj, "参数错误");
		List<Field> fieldList = BeanUtil.getFieldList(paramObj.getClass());
		if (notNullForCollection(fieldList)) {
			for (Field field : fieldList) {
				if (field.isAnnotationPresent(NotNull.class)) {
					Object fieldValue = BeanUtil.invokeGet(paramObj, field.getName());
					if (isNullForParam(fieldValue)) {
						NotNull notNull = field.getAnnotation(NotNull.class);
						ServiceException.throwException(notNull.value());
					}
				}
			}
		}
	}

	/**
	 * 校验一个集合中的字段是否为空
	 * @param collection 集合
	 */
	public static void validateListField(Collection<?> collection) {
		validateParam(collection, "参数错误");
		Iterator<?> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			validateParamField(next);
		}
	}

	/**
	 * 判断一个参数是否为空
	 * 
	 * @param param：参数数组
	 * @return boolean 结果
	 */
	public static boolean isNullForParam(Object param) {
		return (param == null || "".equals(param));
	}

	/**
	 * 判断一个参数是否不为空
	 * 
	 * @param param：参数数组
	 * @return boolean 结果
	 */
	public static boolean notNullForParam(Object param) {
		return !isNullForParam(param);
	}

	/**
	 * 判断多个参数是否其中一个为空
	 * 
	 * @param params：参数数组
	 *@return boolean 结果
	 */
	public static boolean isNullForParams(Object... params) {
		return isNullForArray(params);
	}

	/**
	 * 判断多个参数是否全都不为空
	 * @return boolean 结果
	 * @param params：参数数组
	 */
	public static boolean notNullForParams(Object... params) {
		return !isNullForParams(params);
	}

	/**
	 * 判断参数数组是否其中一个为空
	 * 
	 * @param params：参数数组
	 * @return boolean 结果
	 */
	public static boolean isNullForArray(Object[] params) {
		if (isNullForLength(params)) {
			return true;
		}
		return isNullForContent(params);
	}

	/**
	 * 判断参数数组是否全都不为空
	 * 
	 * @param params：参数数组
	 * @return boolean 结果
	 */
	public static boolean notNullForArray(Object[] params) {
		return !isNullForArray(params);
	}

	/**
	 * 判断数组的长度是否为空
	 * 
	 * @param params：参数数组
	 * @return boolean 结果
	 */
	private static boolean isNullForLength(Object[] params) {
		return (params == null || params.length <= 0);
	}

	/**
	 * 判断数组的长度是否不为空
	 * 
	 * @param params：参数数组
	 * @return boolean 结果
	 */
	private static boolean notNullForLength(Object[] params) {
		return !isNullForLength(params);
	}

	/**
	 * 判断数组内容是否为空
	 * 
	 * @param params：参数数组
	 * @return boolean 结果
	 */
	private static boolean isNullForContent(Object[] params) {
		for (Object param : params) {
			if (isNullForParam(param)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个集合是否为空
	 * 
	 * @param collection 集合
	 * @return boolean 结果
	 */
	public static boolean isNullForCollection(Collection<?> collection) {
		return (collection == null || collection.isEmpty() || collection.size() <= 0);
	}

	/**
	 * 判断一个集合是否不为空
	 * 
	 * @param collection 集合
	 * @return 结果
	 */
	public static boolean notNullForCollection(Collection<?> collection) {
		return !isNullForCollection(collection);
	}

	/**
	 * 判断一个Map是否为空
	 * 
	 * @param map 参数
	 * @return 结果
	 */
	public static boolean isNullForMap(Map<?, ?> map) {
		return (map == null || map.isEmpty() || map.size() <= 0);
	}

	/**
	 * 判断一个Map是否不为空
	 * 
	 * @param map 参数
	 * @return 结果
	 */
	public static boolean notNullForMap(Map<?, ?> map) {
		return !isNullForMap(map);
	}

	/**
	 * 判断是否为手机号
	 * 
	 * @param telephone 手机号
	 * @return 校验结果
	 */
	public static boolean isPhoneNumber(String telephone) {
		validateParam(telephone, "手机号不能为空");
		return telephone.matches(PHONE_REG);
	}

	/**
	 * 判断是否不为手机号
	 * 
	 * @param telephone 手机号
	 * @return 校验结果
	 */
	public static boolean notPhoneNumber(String telephone) {
		return !isPhoneNumber(telephone);
	}

	/**
	 * 手机号码正则校验
	 * 
	 * @param telephone：手机号码
	 */
	public static void validataTelephone(String telephone) {
		if (notPhoneNumber(telephone)) {
			ServiceException.throwException("手机号格式错误");
		}
	}

	/**
	 * 判断是否为密码
	 * @return 校验结果
	 * @param password 密码
	 */
	public static boolean isPassword(String password) {
		validateParam(password, "密码不能为空");
		return password.matches(PASSWORD_REG);
	}

	/**
	 * 判断是否不为密码
	 * 
	 * @param password 密码
	 * @return 校验结果
	 */
	public static boolean notPassword(String password) {
		return !isPassword(password);
	}

	/**
	 * 密码正则校验
	 * 
	 * @param password：密码
	 */
	public static void validatePassword(String password) {
		if (notPassword(password)) {
			ServiceException.throwException(PASSWORD_MSG);
		}
	}

	/**
	 * 屏蔽电话号码中的中间四位
	 * 
	 * @param telephone：手机号码
	 * @return String：屏蔽后的字符串
	 */
	public static String getShieldTelephone(String telephone) {
		return telephone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

}
