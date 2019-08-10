package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 上传文件时没有选择文件或选中的文件为空时的异常
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 5216328143694529891L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message) {
		super(EMPTY_ERROR_CODE , message);
	}
	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new FileEmptyException(msg);
	}
	

}
