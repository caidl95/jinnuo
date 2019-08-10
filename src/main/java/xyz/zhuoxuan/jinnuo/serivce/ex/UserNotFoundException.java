package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 用户数据不存在
 */
public class UserNotFoundException extends ServiceException {

	private static final long serialVersionUID = 5156778717593377564L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(int code, String message) {
		super(code, message);
	}

	public UserNotFoundException(String message) {
		super(NOT_FOUND_DATA_ERROR_CODE , message);	
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new UserNotFoundException(msg);
	}
}
