package xyz.zhuoxuan.jinnuo.util;


import javax.servlet.http.HttpServletRequest;

/**
 * request 
 * 
 * @author peng.xy
 */
public class RequestUtil {

	private RequestUtil() {
		throw new RuntimeException("new RequestUtil instance error");
	}
	
	/**
	 * pc端
	 */
    public static final String REQUEST_TYPE_PC = "PC端";
    
    /**
     * app端
     */
    public static final String REQUEST_TYPE_APP = "APP端";
    
    /**
     * H5 端
     */
    public static final String REQUEST_TYPE_H5 = "H5端";
    
    
    /**
     *获取请求类型
     * @param request  HTTP请求
     * @return 类型
     */
    public static String getRequestType(HttpServletRequest request){
        return request.getMethod();
    }

    /**
     * 获取请求路径
     * @param request  HTTP请求
     * @return 路径
     */
    public static String getRequestUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getServletPath();
        return url;
    }
	
	
    /**
	 * 获取ip地址
	 * 
	 * @param request HTTP请求
	 * @return String 地址
	 */
    public static String getIPAddress(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = null;

		// X-Forwarded-For：Squid 服务代理
		String ipAddresses = request.getHeader("X-Forwarded-For");
		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// Proxy-Client-IP：apache 服务代理
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// WL-Proxy-Client-IP：weblogic 服务代理
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// HTTP_CLIENT_IP：有些代理服务器
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// X-Real-IP：nginx服务代理
			ipAddresses = request.getHeader("X-Real-IP");
		}

		// 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}

		// 还是不能获取到，最后再通过request.getRemoteAddr();获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
}
