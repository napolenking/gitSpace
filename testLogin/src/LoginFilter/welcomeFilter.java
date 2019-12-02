package LoginFilter;

import java.io.IOException;
import java.net.URLDecoder;

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
import javax.servlet.http.HttpSession;

import DTO.User;
import Util.StringUtils;

/**
 * Servlet Filter implementation class Filter
 */
public class welcomeFilter implements javax.servlet.Filter {

	public void destroy() {
		System.out.println("----过滤器销毁----");
	}

	@SuppressWarnings("deprecation")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		//对request和response进行一些预处理
		System.out.println("welcomeFilter doFilter......................................");
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();
		User seuser= (User)session.getAttribute("user");
		boolean mage = true;
		
			if(cookies != null){
				for(Cookie cookie:cookies){
					//获得用户输入的用户名和密码
					if (cookie.getName().equals("users")){
						String users = cookie.getValue();
						String AEScode = StringUtils.AESDecode(StringUtils.getEncodeKey(), users);
						String IDPASS = URLDecoder.decode(AEScode, "utf-8"); 
				        String reqname = IDPASS.split(",")[0];
				        String reqpass = IDPASS.split(",")[1];
				        System.out.println("reqid:"+reqname+";reqpass:"+reqpass);
				     if(seuser !=null) {
				    	   System.out.println("Filterusername:"+seuser.getUserName()+";Filterpassword:"+seuser.getUserPwd());
				    	    //判断cookie中的用户名和用户密码与输入中的用户名和密码是否相等
					        if((seuser.getUserName().equals(reqname))&& (seuser.getUserPwd().equals(reqpass))){
					        	chain.doFilter(request, response);//让目标资源放行
					        	mage=false;
					        	break;
					        }
				     	} 
				}
			}                             
		}
	
		
		if(mage){
			resp.sendRedirect("/testLogin/jsp/login.jsp");
        }
	
		System.out.println("welcomeFilter执行后！！！");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}

}
