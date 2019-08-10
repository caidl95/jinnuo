package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 上传文件时非法状态异常
 */
public class FileIllegalStateException extends FileUploadException {

	private static final long serialVersionUID = 4574697569915816331L;

	public FileIllegalStateException() {
		super();
	}

	public FileIllegalStateException(String message) {
		super(ILLEGAL_STATE_CODE , message);
	}
	
	/**
	 * 抛出异常
	 * @param msg：错误信息
	 */
	public static void throwException(String msg) {
		throw new FileIllegalStateException(msg);
	}
	
}
