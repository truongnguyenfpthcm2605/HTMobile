<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Fashi Template">
<meta name="keywords" content="Fashi, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Đăng Ký</title>
<%@include file="/taglib/css.jsp"%>
<style type="text/css">
	i{
	 font-size: 20px;
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
						<a href="#"><i class="fa fa-home"></i> Trang chủ</a> <span>Đăng
							ký</span>
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
				<div class="col-lg-8 offset-lg-3" style="margin: 0 auto;">
					<div class="register-form">
						<h2>Đăng Ký</h2>
						<form:form action="/register" modelAttribute="userRegister" method="post">
							<div class="row" style="margin-bottom: 10px">
								<div class="col-md-8">
									<label>Email *</label>
									<form:input type="email" path="email" onblur="check()"
										id="email" class="form-control"  placeholder="Nhập email" />
								</div>
								<div class="col-md-4">
									<label>Lấy mã xác nhận *</label> <a
										class="btn btn-light disabled " id="sendButton" style="width: 100%">Gửi</a>
								</div>
								<form:errors path="email" style="color:red ;margin-left:12px" />
							</div>

							<div class="form-group">
								<label for="pass">Họ Và Tên *</label>
								<form:input type="text" path="fullname" class="form-control"
									placeholder="Nhập họ và tên" />
								<form:errors path="fullname" style="color:red" />
							</div>
							<div class="form-group">
								<label for="pass">Ngày Sinh *</label>
								<form:input type="date" path="birth" class="form-control"  pattern="yyyy-MM-dd"/>
								<form:errors path="birth" style="color:red" />
							</div>
							<div class="form-group">
								<label for="inputState">Giới tính</label>
								<form:select id="inputState" path="gender" class="form-control">
									<form:option value="true">Nam</form:option>
									<form:option value="false">Nữ</form:option>
								</form:select>
							</div>
							<div class="row" style="margin-bottom: 10px">
								<div class="col-md-10">
									<label>Mật Khẩu *</label>
									<form:input type="password" path="password" 
										id="password" class="form-control"  />
								</div>
								<div class="col-md-2">
									<label> Xem</label> <button type="button"
										class="btn btn-light " id="show1" onclick="showPassword()" style="width: 100%"><i class='bx bx-show'></i></button>
								</div>
								<form:errors path="password" style="color:red ;margin-left:12px" />
							</div>
							<div class="row" style="margin-bottom: 10px">
								<div class="col-md-10">
									<label>Xác Nhận *</label>
									<form:input type="password" path="confirm" 
										id="confirm" class="form-control" />
								</div>
								<div class="col-md-2">
									<label> Xem</label>
									<button type="button"
										class="btn btn-light " id="show2" onclick="showConfirm()" style="width: 100%">
										<i class='bx bx-show'></i>
									</button>
								</div>
								<form:errors path="confirm" style="color:red ;margin-left:12px" />
								<span style="color:red ;margin-left:12px">${cofirmError }</span>
							</div>
							<div class="form-group">
								<label for="con-pass">Mã xác nhận *</label>
								<form:input type="text" path="codeKey" class="form-control"
									placeholder="Nhập mã xác nhận từ email" />
								<form:errors path="codeKey" style="color:red" />
								<span style="color:red ;">${keyError }</span>
							</div>
							<p>${message }</p>
							<button type="submit" class="site-btn register-btn">Đăng
								Ký</button>
						</form:form>
						<div class="switch-login">
							<a href="/login" class="or-login">hoặc đăng nhập tại đây</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Register Form Section End -->
	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>
	<script type="text/javascript">
	function check() {
	    var emailInput = document.getElementById("email");
	    var sendButton = document.getElementById("sendButton");
	    if (emailInput.value.trim() !== "") {
	        sendButton.classList.remove("disabled");
	    } else {
	        sendButton.classList.add("disabled");
	    }
	}
	
	function showPassword() {
		var passwordField = document.getElementById("password");
		var showPasswordButton = document.getElementById("show1");
		if (passwordField.type === "password") {
			passwordField.type = "text";
			showPasswordButton.innerHTML = "<i class='bx bx-low-vision'></i>";
		} else {
			passwordField.type = "password";
			showPasswordButton.innerHTML = "<i class='bx bx-show-alt'></i>";
		}
	}
	
	function showConfirm() {
		var passwordField = document.getElementById("confirm");
		var showPasswordButton = document.getElementById("show2");
		if (passwordField.type === "password") {
			passwordField.type = "text";
			showPasswordButton.innerHTML = "<i class='bx bx-low-vision'></i>";
		} else {
			passwordField.type = "password";
			showPasswordButton.innerHTML = "<i class='bx bx-show-alt'></i>";
		}
	}
	
	

	</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	$("#sendButton").click(function(event	){
		 event.preventDefault(); 
			var email = $("#email").val();
		$.ajax({
			 url: "/register/mail?codemail=" + email,
			 type: 'GET',
			 success: function(data) {
			        alert(data);
			    },
			    error: function(xhr, status, error) {
			     	alert("Không thể gửi mail")
			    }
		});
	});
	</script>
	
</body>
</html>