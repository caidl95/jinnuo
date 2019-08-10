package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 业务异常，是当前项目中业务层抛出的异常的基类
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 980104530291206274L;
	
	/**
	 * 未知异常
	 */
	public static final int DEFAULT_CODE = 500;
	
	/**
	 * 系统错误状态码
	 */
	public static final int SYSTEM_ERROR_CODE = 510;
	
	/** 
	 * 数据库异常状态码
	 */
	public static final int DB_ERROR_CODE = 505;
	
	/**
	 * 参数异常状态码
	 */
	public static final int PARAM_ERROR_CODE = 400;
	
	/**
	 *数据为空状态码
	 */
	public static final int EMPTY_DATA_CODE = 405;
	
	/**
	 * 未找到数据代码
	 */
	public static final int NOT_FOUND_DATA_ERROR_CODE = 410;
	
	/**
	 * 未经授权的错误代码
	 */
	public static final int UNAUTHORIZED_ERROR_CODE = 203;
	
	/**
	 *  业务错误代码
	 */
	public static final int BUSINESS_ERROR_CODE = 600;

	/**
	 *  页面错误代码
	 */
	public static final int PAGE_ERROR_CODE = 605;

	/**
	 * 身份错误代码
	 */
	public static final int IDENTITY_ERROR_CODE = 610;
	
	/**
	 *数据不能重复错误代码
	 */
	public static final int DATA_CANNOT_REPETITION_ERROR_CODE = 615;
								 
	/**
	 *  token 失效 码
	 */
	public static final int TOKEN_CODE = 620;
	
	private int code;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(int code, String message) {
		super(message);
		setCode(code);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throwException(DEFAULT_CODE, msg);
	}

	/**
	 * 抛出异常
	 * 
	 * @param code 错误码
	 * @param msg 错误信息
	 */
	public static void throwException(int code, String msg) {
		throw new ServiceException(code, msg);
	}
}
