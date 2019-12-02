package LoginCookie;

import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.ServerException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.StringUtils;
import DAO.UserDAO;
import DTO.User;
import DTO.UserInfo;

@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		try {
			doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		// 记录登陆次数，防止恶意登陆
		int loginNum = 0;
		// 建立session管理用户数据
		HttpSession session = request.getSession();
		//得到userid和userpassword
		String username = request.getParameter("userid");
		String password = request.getParameter("password");
		String[] rememberMe = request.getParameterValues("rememberMe");
		int ages =0;
		if(rememberMe !=null) {
			if(rememberMe[0].equals("true")) {
				ages = 60 * 60 * 12;
			}
		}else {
				ages =0;
			}
		
		//得到验证码
		String repVerifyCode = request.getParameter("verifyCode");
		System.out.println(username + ":" + password + "&" + repVerifyCode);
		//数据库查询用户消息
		User users = UserDAO.queryIfExist(username, password);
		//从session中取出验证码
		String sessionVerifyCode = (String) session.getAttribute("VerifyCode");
		if(sessionVerifyCode!=null) {//判断验证码是否生成成功
			if (sessionVerifyCode.equals(repVerifyCode)) {
				session.removeAttribute("VerifyCode");
				if (users!=null) {//判断用户是否存在
					try {
						String IDPASS =(users.getUserName()+","+users.getUserPwd());
						String user = URLEncoder.encode(IDPASS,"utf-8"); // 使用url进行编码
						String AEScode = StringUtils.AESEncode(StringUtils.getEncodeKey(), user);
						Cookie cookie = new Cookie("users", AEScode);// 在浏览器端设置cookie来存储加密后的用户名和密码
						cookie.setMaxAge(ages); // 设置最大生存期
						cookie.setPath("/testLogin"); // 设置作用域可在同一网站内共享方法
						//向浏览写入cookie
						response.addCookie(cookie);
						// 设置session来管理用户
						session.setAttribute("user", users);
						//建立userMap记录登陆用户数
						Map<Object, Object> beanMap = UserInfo.getInstance().getBeanMap();
						//将userMap集合写入session
						request.setAttribute("beanMap", beanMap);
						session.setMaxInactiveInterval(ages); // 设置session最大生存期
						loginNum = 0;// 清空登陆次数
						session.setAttribute("loginNum", loginNum);
						// 转发到登陆成功页面,而不使用response.sendRedirect是为了隐藏真实地址更安全
						request.getRequestDispatcher("jsp/welcome.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
					System.out.println("登陆成功!");
				} else {
					request.setAttribute("errorMsg", "用户名或密码不正确");
					System.out.println("登陆失败!");
					loginNum++;
					session.setAttribute("loginNum", loginNum);
					try {
						request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "验证码不正确！");
				System.out.println("登陆失败!");
				loginNum++;
				session.setAttribute("loginNum", loginNum);
				try {
					request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			}
		}else {
			request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
			System.out.println("验证码为空!");
		}
	}
}
