package xyz.zhuoxuan.jinnuo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import xyz.zhuoxuan.jinnuo.commom.Const;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute(Const.CURRENT_USER) == null) {
			response.sendRedirect("/index.html");
			return false;
		}
		return true;
	}

}
