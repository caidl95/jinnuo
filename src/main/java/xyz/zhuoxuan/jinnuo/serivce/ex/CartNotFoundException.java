package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 购物车数据不存在的异常
 */
public class CartNotFoundException extends ServiceException {

	private static final long serialVersionUID = -290753401450618624L;

	public CartNotFoundException() {
		super();
	}

	public CartNotFoundException(int code, String message) {
		super(code, message);
	}

	public CartNotFoundException(String message) {
		super(NOT_FOUND_DATA_ERROR_CODE , message);
		
	}
	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new CartNotFoundException(msg);
	}
	

}
