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
<title>Tin Tức</title>
<%@include file="/taglib/css.jsp"%>

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1 style="color: #080202; font-size: 50px; font-weight: 400;">
			Cảm ơn bạn đã ủng hộ chúng tôi .
			<p style="font-size: 30px; color: #C38154;">
				Đơn hàng của bạn đã được chúng tôi xác nhận . Bạn có thể kiểm tra
				mail <i style="font-size: 40px; color: #41644A;"
					class='bx bx-mail-send'></i> để xem đơn hàng của mình.
			</p>
		</h1>

		<div
			style="background-color: #C38154; width: 60%; height: 10px; margin: 30px auto; border-radius: 10px; box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;"></div>

		<div class="container" style="margin: 0 auto; text-align: center;">
			<img src="${pageContext.request.contextPath}/img/thanks.png" alt="">
		</div>
		<a href="/index" style="text-decoration: none; ">Quay về trang chủ</a>
	</div>
	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>
</body>
</html>