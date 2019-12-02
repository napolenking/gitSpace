
    document.getElementById("imgObj").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[0].src =
            "/testLogin/VerifyCodeUtils?time=" + new Date().getTime();
    };
    
    document.getElementById("exchange").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[0].src =
            "/testLogin/VerifyCodeUtils?time=" + new Date().getTime();
    };