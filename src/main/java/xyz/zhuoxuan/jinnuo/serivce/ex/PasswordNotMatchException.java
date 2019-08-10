package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 验证密码失败
 */
public class PasswordNotMatchException extends ServiceException {

	private static final long serialVersionUID = 1903639604855086304L;

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(int code, String message) {
		super(code, message);
	}

	public PasswordNotMatchException(String message) {
		super(IDENTITY_ERROR_CODE , message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg：错误信息
	 */
	public static void throwException(String msg) {
		throw new PasswordNotMatchException(msg);
	}
	

}
