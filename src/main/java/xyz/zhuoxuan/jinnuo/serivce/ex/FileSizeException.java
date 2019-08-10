package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 上传文件时文件大小超出限制异常
 */
public class FileSizeException extends FileUploadException {

	private static final long serialVersionUID = 3652563516851916279L;

	public FileSizeException() {
		super();
	}

	

	public FileSizeException(String message) {
		super(SIZE_ERROR_CODE , message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new FileSizeException(msg);
	}
}
