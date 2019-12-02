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
		// ��¼��½��������ֹ�����½
		int loginNum = 0;
		// ����session�����û�����
		HttpSession session = request.getSession();
		//�õ�userid��userpassword
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
		
		//�õ���֤��
		String repVerifyCode = request.getParameter("verifyCode");
		System.out.println(username + ":" + password + "&" + repVerifyCode);
		//���ݿ��ѯ�û���Ϣ
		User users = UserDAO.queryIfExist(username, password);
		//��session��ȡ����֤��
		String sessionVerifyCode = (String) session.getAttribute("VerifyCode");
		if(sessionVerifyCode!=null) {//�ж���֤���Ƿ����ɳɹ�
			if (sessionVerifyCode.equals(repVerifyCode)) {
				session.removeAttribute("VerifyCode");
				if (users!=null) {//�ж��û��Ƿ����
					try {
						String IDPASS =(users.getUserName()+","+users.getUserPwd());
						String user = URLEncoder.encode(IDPASS,"utf-8"); // ʹ��url���б���
						String AEScode = StringUtils.AESEncode(StringUtils.getEncodeKey(), user);
						Cookie cookie = new Cookie("users", AEScode);// �������������cookie���洢���ܺ���û���������
						cookie.setMaxAge(ages); // �������������
						cookie.setPath("/testLogin"); // �������������ͬһ��վ�ڹ�����
						//�����д��cookie
						response.addCookie(cookie);
						// ����session�������û�
						session.setAttribute("user", users);
						//����userMap��¼��½�û���
						Map<Object, Object> beanMap = UserInfo.getInstance().getBeanMap();
						//��userMap����д��session
						request.setAttribute("beanMap", beanMap);
						session.setMaxInactiveInterval(ages); // ����session���������
						loginNum = 0;// ��յ�½����
						session.setAttribute("loginNum", loginNum);
						// ת������½�ɹ�ҳ��,����ʹ��response.sendRedirect��Ϊ��������ʵ��ַ����ȫ
						request.getRequestDispatcher("jsp/welcome.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
					System.out.println("��½�ɹ�!");
				} else {
					request.setAttribute("errorMsg", "�û��������벻��ȷ");
					System.out.println("��½ʧ��!");
					loginNum++;
					session.setAttribute("loginNum", loginNum);
					try {
						request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "��֤�벻��ȷ��");
				System.out.println("��½ʧ��!");
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
			System.out.println("��֤��Ϊ��!");
		}
	}
}
