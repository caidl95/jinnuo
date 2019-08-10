package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 上传文件时文件类型异常
 */
public class FileContentTypeException extends FileUploadException {

	private static final long serialVersionUID = -911131378972632004L;

	public FileContentTypeException() {
		super();
	}

	public FileContentTypeException(String message) {
		super(TYPE_INCONFORMITY_ERROR_CODE , message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg：错误信息
	 */
	public static void throwException(String msg) {
		throw new FileContentTypeException(msg);
	}
	
	
}
