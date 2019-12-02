package LoginFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filter
 */
public class LoginFilter implements javax.servlet.Filter {

	public void destroy() {
		System.out.println("----过滤器销毁----");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//对request和response进行一些预处理
		System.out.println("LoginFilter doFilter......................................");
		HttpServletResponse resp = (HttpServletResponse)response;
		String username = request.getParameter("userid");
		String password = request.getParameter("password");
		String repVerifyCode = request.getParameter("verifyCode");
		if(username!=null&&repVerifyCode!=null&&password!=null){
				chain.doFilter(request, response);//让目标资源放行
			} else {
				resp.sendRedirect("jsp/login.jsp");
			}
		System.out.println("LoginFilter执行后！！！");
		}
		
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}

}
