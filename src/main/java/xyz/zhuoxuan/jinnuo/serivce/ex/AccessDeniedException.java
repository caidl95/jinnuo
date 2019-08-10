package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 拒绝访问，可能因为权限不足，或数据归属有误
 */
public class AccessDeniedException extends ServiceException {

	private static final long serialVersionUID = 3849396365637118465L;

	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(int code, String message) {
		super(code, message);
	}

	public AccessDeniedException(String message) {
		super(UNAUTHORIZED_ERROR_CODE,message);
	}


	/**
	 * 抛出异常
	 * 
	 * @param msg：错误信息
	 */
	public static void throwException(String msg) {
		throw new AccessDeniedException(msg);
	}

}
