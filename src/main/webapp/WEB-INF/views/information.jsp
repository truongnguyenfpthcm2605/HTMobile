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
    <title>Thông tin người dùng</title>
     <%@include file="/taglib/css.jsp" %>

</head>
<body>
		<jsp:include page="header.jsp" />
		<div class="register-login-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 ">
                    <div class="register-form">
                        <h2>Thông tin của bạn</h2>
                        <form action="#">
                            <div class="form-group">
                                <label >Email</label>
                                <input type="email" class="form-control" value="${user.email }" disabled>
                              </div>
                            
                            <div class="form-group">
                                <label for="pass">Họ và tên</label>
                                <input type="text" class="form-control" value="${user.fullname }" disabled>
                            </div>
                            <div class="form-group">
                                <label for="con-pass">Giới tính</label>
                                <input type="text" class="form-control" value="${user.gender?'Nam':'Nữ'}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="con-pass">Ngày tham gia</label>
                                <input type="text" class="form-control" value="<fmt:formatDate value="${user.createday}" pattern="dd/MM/yyyy" />" disabled>
                            </div>
                            
                            
                        </form>
                        
                    </div>
                </div>
                <div class="col-lg-6 ">
                    <div class="register-form">
                        <h2>Đổi Mật Khẩu</h2>
                        <form:form action="/information/changepass" method="post" modelAttribute="changepass" >
                            <div class="form-group">
                                <label >Mật Khẩu Cũ *</label>
                                <form:input type="text" class="form-control" path="currentPass" />
                                <form:errors path="currentPass" style="color:red" />
                              </div>
                            
                            <div class="form-group">
                                <label for="pass">Mật Khẩu Mới*</label>
                                <form:input type="text" class="form-control"  path="newPass" />
                                <form:errors path="newPass" style="color:red" />
                            </div>
                            <div class="form-group">
                                <label for="con-pass">Xác nhận mật khẩu *</label>
                                <form:input type="text" class="form-control"  path="confirmpass" />
                                <form:errors path="confirmpass" style="color:red" />
                            </div>
                            <hr>
                            <button type="submit" class="site-btn register-btn">Lưu</button>
                            
                        </form:form>
                        <p style="color:red">${message }</p>
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