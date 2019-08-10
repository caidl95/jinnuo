package xyz.zhuoxuan.jinnuo.serivce.ex;
/**
 * 删除数据异常
 */
public class DeleteException extends ServiceException {

	private static final long serialVersionUID = -8881103463792318580L;

	public DeleteException() {
		super();
	}

	public DeleteException(int code, String message) {
		super(code, message);
	}

	public DeleteException(String message) {
		super(DB_ERROR_CODE , message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new DeleteException(msg);
	}

}
