package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
  * 插入数据异常
 */
public class InsertException extends ServiceException {

	private static final long serialVersionUID = 7991875652328476596L;

	public InsertException() {
		super();
	}

	public InsertException(int code, String message) {
		super(code, message);
	}

	public InsertException(String message) {
		super(DB_ERROR_CODE ,message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new InsertException(msg);
	}

}
