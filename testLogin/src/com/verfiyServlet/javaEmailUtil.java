package com.verfiyServlet;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javaEmailUtil {
	//发件人邮箱
	public static String emailAccount = "napolenking@163.com";
	//发件人邮箱授权码
	public static String emailPasswd = "napolenking163";
	//发件人邮箱地址
	public static String emailSMTPHost = "smtp.163.com";
	//收件人邮箱
	public static String receiveMailAccount = "";

	public static MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String nickName,String html)throws MessagingException, IOException {
		//创建一封邮件对象
		MimeMessage message = new MimeMessage(session);
		//From:发件人
		message.setFrom(new InternetAddress(sendMail,"Napolenking Corporation","UTF-8"));
		//To:收件人
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail,nickName, "UTF-8"));
		//Subject:邮件主题
		message.setSubject("邮箱验证","UTF-8");
		//Content:邮件正文（可以使用Html标签）
		message.setContent(html,"text/html;charset=UTF-8");
		//设置发送时间
		message.setSentDate(new Date());
		//保存设置
		message.saveChanges();
		//可将该邮件保存在本地
		//OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
		//message.writeTo(out);
		//out.flush();
		//out.close();
		return message;
		
	}
}
