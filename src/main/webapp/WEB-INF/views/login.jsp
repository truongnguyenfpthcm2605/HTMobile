<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@include file="/taglib/tablig.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tin Tức</title>
     <%@include file="/taglib/css.jsp" %>

    <style>
        .social-login a{
            width: 80%;
            margin: 10px auto;
            border-radius: 5px;
            box-shadow:0 8px 8px 0 rgba(103, 151, 255, .11), 0 12px 18px 0 rgba(103, 151, 255, .11);
        }
    </style>

</head>
<body>
		<jsp:include page="header.jsp" />
		<!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i> Trang chủ</a>
                        <span>Đăng nhập</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Form Section Begin -->

    <!-- Register Section Begin -->
    <div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <div class="login-form">
                        <h2>Đăng Nhập</h2>
                        <form action="/login" method="post">
                            <div class="group-input">
                                <label for="username">Email *</label>
                                <input type="email" id="email" name="email">
                            </div>
                            <div class="group-input">
                                <label for="pass">Mật khẩu *</label>
                                <input type="password" id="password" name="password">
                            </div>
                            <div class="group-input gi-check">
                                <div class="gi-more">
                                    <label for="save-pass">
                                        Lưu mật khẩu?
                                        <input type="checkbox" id="save-pass" name="check" value="true">
                                        <span class="checkmark"></span>
                                    </label>
                                    <a href="#" class="forget-pass">Quên mật khẩu?</a>
                                </div>
                            </div>
                            <p style="color: red; font-size: 15px">${message }${param.error }</p>
                            <button type="submit" class="site-btn login-btn">Đăng Nhập</button>
                        </form>
                        <div class="switch-login">
                            <a href="/register" class="or-login">Hoặc Tạo Tài Khoản Tại Đây</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->
		
		<jsp:include page="footer.jsp" />
			<%@include file="/taglib/js.jsp" %>
</body>
</html>