<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Đăng nhập</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="images/icons/favicon.png" />
    <link rel="stylesheet" type="text/css" media="screen" href="css/sign_in.css" />
    <script src="main.js"></script>
</head>
<body>
    <section>
        <div class="container">
            <div class="form_content">
                <h2>NTTT Shop</h2>
                <p style="text-align:justify">NTTT Shop hiểu rằng, thời trang không phải chỉ là mặt một chiếc áo đẹp mà là mặc một chiếc áo thật sự phù hợp với mình. Góp mặt trên thị trường thời trang Việt Nam từ năm 2020 với gần 1 năm không ngừng phát triển đổi mới và tạo dấu ấn.</p>
                <a href="about.jsp">Đọc thêm</a>
                <a style="margin-left:35px; background:steelblue" href="${pageContext.request.contextPath}/home">Home</a>
            </div>
            <div class="login_form">
                <h1>Đăng nhập</h1>
                <form action="signIn" method="post">
                    <input type="text" name="username" required="" placeholder="Username">
                    <input type="password" name="password" required="" placeholder="Password">
                    <div style="display: flex;">
                        <input style="width: 15px; height: 15px; margin: 0 3px 7px 3px" type="checkbox" name="rememberMe" value="Y">
                        <Span style="font-size:10px">Remember Me</Span>
                    </div>
                    <span style="color: rgb(238, 17, 17);" id="message_error">${errorString }</span>
                    <input type="submit" name="" value="Đăng nhập">
                </form>
                <a href="#" style="font-size: 15px;">Quên mật khẩu</a>
                <a href="signUp" style="float: right;">Đăng ký</a>
            </div>
        </div>
    </section>
</body>
</html>