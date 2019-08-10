package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 上传文件时抛出的异常的基类
 */
public class FileUploadException extends RuntimeException {

	private static final long serialVersionUID = -4816421833308748249L;
	
	public static final int FILE_DEFAULT_CODE = 900;
	
	/**
	 * 文件大小异常码
	 */
	public static final int SIZE_ERROR_CODE = 905;
	
	/**
	 * 类型不符合码
	 */
	public static final int TYPE_INCONFORMITY_ERROR_CODE = 910;
	
	/**
	 * 非法的状态或文件码
	 */
	public static final int ILLEGAL_STATE_CODE = 915;
	
	/**
	 * 空的错误码
	 */
	public static final int EMPTY_ERROR_CODE = 920;
	
	/**
	 * 读写的异常码
	 */
	public static final int IO_ERROR_CODE = 925;
	
	
	private int code;
	
	public FileUploadException() {
		super();
	}

	public FileUploadException(String message) {
		super(message);
	}
	
	public FileUploadException(Integer code, String message) {
		super(message);
		setCode(code);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throwException(FILE_DEFAULT_CODE, msg);
	}

	/**
	 * 抛出异常
	 * 
	 * @param code 错误码
	 * @param msg 错误信息
	 */
	public static void throwException(int code, String msg) {
		throw new FileUploadException(code, msg);
	}
}
