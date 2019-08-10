package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 用户名被占用的异常
 */
public class UsernameDuplicateException extends ServiceException {

	private static final long serialVersionUID = -1224474172375139228L;

	public UsernameDuplicateException() {
		super();
	}

	public UsernameDuplicateException(int code, String message) {
		super(code, message);
	}

	public UsernameDuplicateException(String message) {
		super(DATA_CANNOT_REPETITION_ERROR_CODE , message);
		
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new UsernameDuplicateException(msg);
	}


}
