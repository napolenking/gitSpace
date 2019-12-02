     		//刷新or取消
            document.getElementById('i77').onclick = function(){
                location.reload();
            }
            document.getElementById('i222').onclick = function(){
            	window.location.href="login.jsp";
            }
            
            //AJAX方法
            /*得到XMLHttpRequest对象*/
           function getXHR(){
           	var xmlhttp=null;
        	if (window.XMLHttpRequest)
        	{
        		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        		xmlhttp=new XMLHttpRequest();
        	}
        	else
        	{
        		// IE6, IE5 浏览器执行代码
        		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        	}
        	
           	return xmlhttp;
           }

            //用户名验证
            function checkname(){ 
                var div = document.getElementById("div1"); 
                div.innerHTML = ""; 
                var name1 = document.tijiao.text1.value; 
                if (name1 == "") { 
                div.innerHTML = "用户名不能为空！"; 
                document.tijiao.text1.focus(); 
                return false; 
            } 
                if (name1.length < 4 || name1.length > 16) { 
                div.innerHTML = "长度4-16个字符"; 
                document.tijiao.text1.select(); 
                return false; 
            } 
                var charname1 = name1.toLowerCase(); 
                for (var i = 0; i < name1.length; i++) { 
                var charname = charname1.charAt(i); 
                if (!(charname >= 0 && charname <= 9) && (!(charname >= 'a' && charname <= 'z')) && (charname != '_')) { 
                    div.innerHTML = "用户名包含非法字符"; 
                    document.form1.text1.select(); 
                    return false; 
                } 
            }
                return true;
        }

            //密码验证
            function checkpassword(){ 
                var div = document.getElementById("div2"); 
                div.innerHTML = ""; 
                var password = document.tijiao.text2.value; 
                if (password == "") { 
                div.innerHTML = "密码不能为空"; 
                document.tijao.text2.focus(); 
                return false; 
            } 
                if (password.length < 4 || password.length > 16) { 
                div.innerHTML = "密码长度为4-16位"; 
                document.tijiao.text2.select(); 
                return false; 
                } 
                return true; 
        }
            //确认密码是否一致
            function checkrepassword(){ 
                var div = document.getElementById("div3"); 
                div.innerHTML = ""; 
                var password = document.tijiao.text2.value; 
                var repass = document.tijiao.text3.value; 
                if (repass == "") { 
                div.innerHTML = "密码不能为空"; 
                document.tijiao.text3.focus(); 
                return false; 
            } 
                if (password != repass) { 
                div.innerHTML = "密码不一致"; 
                document.tijiao.text3.select(); 
                return false; 
            } 
                return true; 
        }
                     
        //邮箱地址验证
        function checkEmail(){ 
            var div = document.getElementById("div4"); 
            div.innerHTML = ""; 
            var email = document.tijiao.text4.value; 
            var sw = email.indexOf("@", 0); 
            var sw1 = email.indexOf(".", 0); 
            var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,10})+$/;
            var tt = sw1 - sw; 
            if (email.length == 0) { 
            div.innerHTML = "邮箱不能为空"; 
            document.tijiao.text4.focus(); 
            return false; 
        }else if (email.indexOf("@", 0) == -1) { 
            div.innerHTML = "必须包含@符号"; 
            document.tijiao.text5.select(); 
            return false; 
        } else if (email.indexOf(".", 0) == -1) { 
            div.innerHTML = "必须包含.符号"; 
            document.tijiao.text5.select(); 
            return false; 
        }else if (tt == 1) { 
            div.innerHTML = "@和.不能一起"; 
            document.tijiao.text5.select(); 
            return false; 
        }else if (sw > sw1) { 
            div.innerHTML  = "@符号必须在.之前"; 
            document.tijiao.text5.select(); 
            return false; 
        }else if(re.test(email)){
        	return true;
        }else{
        	div.innerHTML  = "邮箱地址不合法!"; 
        	return false;
        }
     }
        
        //邮箱验证码验证
        function checkCode(){
        	var div = document.getElementById("div5"); 
            div.innerHTML = ""; 
            var code = document.tijiao.text5.value; 
            if (code == "") { 
            div.innerHTML = "验证码不能为空"; 
            document.tijao.text5.focus(); 
            return false; 
        } 
            if (code.length !=5) { 
            div.innerHTML = "验证码长度为5位"; 
            document.tijiao.text5.select(); 
            return false; 
            } 
            return true; 
        };
  
        //获取验证码
        var eml_btnObj = document.getElementById("eml_btn");
        eml_btnObj.onclick=function reVerifyCode(){
        	var username = document.tijiao.text1.value;	//输入的用户名
        	var emailNum = document.tijiao.text4.value;		//输入的邮箱地址
            var urls="/testLogin/Register";
      		 var againCode=null;
      		 //调用AjaxModel向后端邮箱Servlet发送请求，向注册邮箱发送验证码
    	   	 var xhr=getXHR();//得到xhr对象
    	   	var Mage="请继续注册!";
    	   	//2.注册状态变化监听器
    	   if(xhr!=null){
    		   console.log("判断1");
    		   if(checkEmail()){//判断邮箱是否合法
    			   xhr.onreadystatechange=function(){
           	   		console.log("判断2");
              			if(xhr.readyState==4 && xhr.status==200){
              				console.log("判断3");
              				againCode=xhr.responseText;           				
              				if(againCode!=null){
              					if(againCode === Mage){
              						//计时器功能实现
               	   					var countDown=null;
               	           	   		var tmp = 60;//为计时器设置时间
               	           	   		countDown=setInterval(function(){
               	                    	if(tmp == 0){
               	                    		eml_btnObj.disabled="false";
               	                    		eml_btnObj.value="获取验证码";
               	                    		eml_btnObj.removeAttribute("disabled");
               	                    		tmp=60;
               	                    		clearInterval(countDown);
               	                    	}else{
               	                    		eml_btnObj.setAttribute("disabled", true);
               	                    		eml_btnObj.disabled="true";
               	                    		eml_btnObj.value="重新发送( " + tmp + "s )";
               	                    		tmp--;
               	                    	}},1000); //调用计时器
              						
              					}else{
              						alert(againCode);
              					}
           	   					
           	   				}else{
           	   					alert("服务器错误!");
           	   				}
              				
              				
           				}else{
           					console.log("againCode_readyState:"+xhr.readyState);
           					console.log("againCode_status:"+xhr.status);
           				}
           	   		}
           	   		
           	   			//3.建立与服务器的连接
              	   		xhr.open("POST",urls,true);
              	   		//4.向服务器发出请求
              	   		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
              	   		xhr.send("username="+username+"&email="+emailNum);
    		   }
        	   		
           	   		console.log("againCode:"+againCode);
           	   		
    	   		}else{
    		   		alert("未得到XMLHttpRequest")
    	   		} 
    	   
    	 
          
        };
  
       
        //进行总判断，确认密码、邮箱、验证码、用户名是否合法
        function check(){ 
        	if (checkname() && checkpassword() && checkrepassword() && checkEmail() &&checkCode()) { 
            return true; 
            } 
            else { 
            return false; 
            } 
        } 
        	
        //注册功能
        	document.getElementById('i111').onclick=function registerCheck(){
        		var username = document.tijiao.text1.value; //输入的用户名
                var emailCode = document.tijiao.text5.value; //输入的邮箱验证码
                var emailNum = document.tijiao.text4.value;	 
                
        	}
        	
            

    
 
            