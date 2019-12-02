package com.verfiyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LoginCookie.VerifyCodeModel;

@WebServlet("/sendEmailServlet")
public class sendEmailServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);	
	}
	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("邮件发送开始!");
		String Mages = null;
		String GMAIL_PORT = "25";
		resp.setCharacterEncoding("UTF-8");  
		resp.setContentType("text/html; charset=utf-8");  
		PrintWriter out = resp.getWriter();
		try {
			String email = req.getParameter("email");
			String username = req.getParameter("username");
			javaEmailUtil.receiveMailAccount = email;
			Properties props = new Properties();
			props.setProperty("mail.debug", "true");
			//身份认证
		    props.setProperty("mail.smtp.auth", "true");
		    //SSL安全连接
		    props.setProperty("mail.smtp.port", GMAIL_PORT);
		    props.setProperty("mail.smtp.socketFactory.fallback", "false");
		    props.setProperty("mail.smtp.socketFactory.port", GMAIL_PORT);
		    
		    props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.host", javaEmailUtil.emailSMTPHost);
			props.setProperty("mail.transport.protocol", "smtp");
			
			Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, javaEmailUtil.emailPasswd);
                }
              });
			session.setDebug(true);
			
			VerifyCodeModel vc = new VerifyCodeModel();
			if(vc != null){
				String code = vc.getText();
				String html = htmlText.html(code,username);
				MimeMessage message = javaEmailUtil.creatMimeMessage(session, javaEmailUtil.emailAccount, javaEmailUtil.receiveMailAccount, username, html);
				Transport trans = session.getTransport();
				trans.connect(javaEmailUtil.emailAccount,javaEmailUtil.emailPasswd);
				trans.sendMessage(message, message.getAllRecipients());
				trans.close();
				req.getSession().setAttribute("code", code);
				System.out.println("邮箱验证码:"+code);
				Mages="邮件发送成功，请注意查收!";
			}else{
				System.out.println("verifyCode为空!");
			}	
		} catch (MessagingException e) {
			e.printStackTrace();
			Mages="发送失败!";
		}
		out.print(Mages);
	}

	
}
