package Util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.UserInfo;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cancel = req.getParameter("cancel");
		String logout = req.getParameter("logout");
		HttpSession session = req.getSession();
		Map beanMap = UserInfo.getInstance().getBeanMap();
		int index=0;
		if(cancel != null){
			index = 1;
		}else if(logout != null){
			index = 2;
		}
		
		switch (index) {
		case 0:{
			//修改用户名或密码
			break;}
		case 1:{
			session.removeAttribute("user");
			req.setAttribute("beanMap", beanMap);
			resp.sendRedirect("jsp/login.jsp");
			break;}
		case 2:{
			session.invalidate();
			req.setAttribute("beanMap", beanMap);
			resp.sendRedirect("jsp/login.jsp");
			break;}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
