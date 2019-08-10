package xyz.zhuoxuan.jinnuo.serivce.ex;
/**
* 上传文件时读写异常
 */
public class FileIOException extends FileUploadException {

	private static final long serialVersionUID = 1007256760091647496L;

	public FileIOException() {
		super();
	}

	public FileIOException(String message) {
		super(IO_ERROR_CODE , message);
	}
	
	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new FileIOException(msg);
	}
	

}
