package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 更新数据异常
 */
public class UpdateException extends ServiceException {

	private static final long serialVersionUID = -3670870874533766508L;

	public UpdateException() {
		super();
	}

	public UpdateException(int code, String message) {
		super(code, message);
	}

	public UpdateException(String message) {
		super(DB_ERROR_CODE , message);
	}
	
	/**
	 * 抛出异常
	 * 
	 * @param msg：错误信息
	 */
	public static void throwException(String msg) {
		throw new UpdateException(msg);
	}

}
