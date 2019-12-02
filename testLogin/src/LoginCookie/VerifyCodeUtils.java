package LoginCookie;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	/**
	 * 用来生成图片验证码
	 */
	@WebServlet("/VerifyCodeUtils")
	public class VerifyCodeUtils extends HttpServlet {
	   
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	//通知浏览器不要缓存图片
			response.setHeader("Expires", "-1");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			//通知浏览器以图片方式打开
			response.setHeader("Content-type", "image/png");
	        //创建对象
	        VerifyCodeModel vc = new VerifyCodeModel();
	        //获取图片对象
	        BufferedImage bufimg = vc.getImage();
	        //获得图片的文本内容
	        String VerifyCode = vc.getText();
	        System.out.println("Code:"+VerifyCode);
	        // 将系统生成的文本内容保存到session中
	        request.getSession().setAttribute("VerifyCode", VerifyCode);
	        //向浏览器输出图片
	        VerifyCodeModel.output(bufimg, response.getOutputStream());
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	doPost(request, response);
	 }
}

	
