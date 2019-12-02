<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>用户注册</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/testLogin/css/register.css" type="text/css" rel="stylesheet" />
</head>
<body id="i88" style="margin: 0">
    <div class = "pg_header">
        <a class = "logo">LOGO</a>
        <a class="pg_dl" id="i77">注册</a>    
    </div>
    <!-- This is a user register page! -->
    <form name="tijiao" method="post"><!--  action="/testLogin/registServlet" -->
        <div class="left"></div>
        <div class="pg_body">
                <div class="menu">用户名:</div>
                    <div class="kong">
                        <input id="text1" type="text" name="username" placeholder="请输入用户名" onblur="check()">
                        <span id="div1" class="tian">*(为必填)<span>
                    </div>
                <div class="menu">姓名:</div>
                    <div class="kong">
                        <input id="i2" type="text" name="name">
                    </div>
                <div class="menu">性别:</div>
                    <div class="kong" style="width:200px;">
                         男<input type="radio" name="gender" value="1">
                         女 <input type="radio" name="gender" value="2">
                         保密<input type="radio" name="gender" value="3">
                    </div>
                <div class="menu">用户密码:</div>
                    <div class="kong">
                        <input id="text2" type="password" name="passwd" onblur="check()">
                        <span id="div2" class="tian">*(为必填)<span>
                    </div>
                <div class="menu">确认密码:</div>
                    <div class="kong">
                        <input id="text3" type="password" name="verifypwd" onblur="check()">
                        <span id="div3" class="tian">*(为必填)<span>
                    </div>
                <div class="menu">电话号码:</div>
                    <div class="kong">
                        <input id="i5" type="text" name="phone">
                    </div>
                <div class="menu">邮箱地址:</div>
                    <div class="kong">
                        <input id="text4" type="text" name="email" onblur="check()">
                        <span id="div4" class="tian">*(为必填)<span>
                    </div>
                 <div class="menu">邮箱验证码:</div>
                 	<div class="kong">
						<input id="text5" type="text" name="verifyeml" onblur="check()"> 
						<span id="div5" class="tian">*(为必填)<span>                		
                 	</div>   
                <div class="menu">头像上传:</div>
                    <div class="kong">
                        <input type="file" name="upfile" value="选择头像" accept="image/*">
                	</div>    
                <div class="menu">兴趣爱好:</div>
                    <div class="kong" style="width: 300px;">
                     <input type="text" placeholder="兴趣爱好" name="hobby" style="height: 50px;width: 200px;">
                    </div>        
                <div class="menu">个人签名:</div>
                <div class="kong">
                    <textarea name="signature" style="width: 280px;height: 40px;">
                    </textarea>
                </div>
        </div>
        <div class="can">
            <input id="i111" type="button" name="regit" value="注  册">
            <input id="i222" type="button" name="returns" value="返回">
            <input id="eml_btn"  type="button"  name="reverify" value="获取验证码">
        </div>
    </form>
    <script src="/testLogin/js/register.js" type="text/javascript"></script>
</body>
</html>