package LoginFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class errorFilter
 */
@WebFilter("/errorFilter")
public class errorFilter implements Filter {


	public void destroy() {
		System.out.println("----过滤器销毁----");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("errorFilter开始执行！！！");
		String sessionVerifyCode = (String)req.getSession().getAttribute("VerifyCode");
		Cookie[] cookies = req.getCookies();
			if(sessionVerifyCode != null&&cookies != null) {
					chain.doFilter(request, response);//让目标资源放行
				} else {
			resp.sendRedirect("/testLogin/jsp/login.jsp");
			System.out.println("errorFilter执行后！！！");
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}

}
