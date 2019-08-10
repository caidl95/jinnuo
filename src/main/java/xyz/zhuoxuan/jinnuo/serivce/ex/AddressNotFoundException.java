package xyz.zhuoxuan.jinnuo.serivce.ex;

/**
 * 收货地址数据不存在的异常
 */
public class AddressNotFoundException extends ServiceException {

	private static final long serialVersionUID = 4234461510773896049L;

	public AddressNotFoundException() {
		super();
	}

	public AddressNotFoundException(int code, String message) {
		super(code, message);
		
	}

	public AddressNotFoundException(String message) {
		super(NOT_FOUND_DATA_ERROR_CODE,message);
	}

	/**
	 * 抛出异常
	 * 
	 * @param msg 错误信息
	 */
	public static void throwException(String msg) {
		throw new AddressNotFoundException(msg);
	}

}
