package Util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;


@WebServlet("/Register")
public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email = req.getParameter("email");
			String username = req.getParameter("username");
			System.out.println("email:"+email+";user:"+username);
			String[] users = UserDAO.registerIfExist(email,username);
			resp.setCharacterEncoding("UTF-8");  
			resp.setContentType("text/html; charset=utf-8");  
			PrintWriter out = resp.getWriter();
			String Mage = null;
			boolean Flag = true;
			if(users !=null&&users.length>0){
					if(users[0].equals(username)) {
						System.out.println("输入的用户名已存在,请重新输入!");
						Mage="输入的用户名已存在,请重新输入!";
						Flag = false;
					}else if(users[1].equals(email)&&Flag) {
						System.out.println("输入的邮箱已存在,请重新输入!");
						Mage="输入的邮箱已存在,请重新输入!";
					}
			}else{
				Mage="请继续注册!";
				RequestDispatcher rd = req.getRequestDispatcher("sendEmailServlet"+"?email="+email+"&username"+username);
				rd.forward(req, resp);
			}
			
			out.print(Mage);
			System.out.println(Mage);
		}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	
}
