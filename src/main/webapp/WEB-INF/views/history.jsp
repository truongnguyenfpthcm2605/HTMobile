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
<title>Giỏ Hàng</title>
<%@include file="/taglib/css.jsp"%>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Shopping Cart Section Begin -->
	<!-- Breadcrumb Section Begin -->
	<div class="breacrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text product-more">
						<a href="./home.html"><i class="fa fa-home"></i>Trang chủ</a> <span>Lịch
							sử mua hàng</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section Begin -->

	<!-- Shopping Cart Section Begin -->
	<section class="shopping-cart spad">
		<div class="container">
			<c:choose>
				<c:when test="${not empty listHistory }">
					<c:forEach items="${listHistory }" var="item">
						<div class="row" style="margin-top: 15px">
							<div class="col-lg-12">
								<div class="media"
									style="border-radius: 20px; padding: 10px; width: 100%; margin-bottom: 10px; display: flex; justify-content: space-between; align-items: center; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;">

									<img
										src="${pageContext.request.contextPath}/img/products/${item.product.imgproduct}"
										width="200px; border-radius: 10px" style="margin-right: 20px;"
										alt="...">
									<div class="media-body">
										<h3 class="mt-0" style="font-weight: bold;">${item.product.title }
										</h3>
										<h6>Số lượng đã mua : ${item.quantity }</h6>
										<h6>
											Giá :
											<fmt:formatNumber value="${item.price}" type="currency"
												currencyCode="VND" />
										</h6>
										<h6 style="color: Red;">
											Tổng Giá :
											<fmt:formatNumber value="${item.price*item.quantity}"
												type="currency" currencyCode="VND" />
										</h6>
									</div>
									<div>
										<a class="btn btn-dark" href="/product/detail?id=${item.product.id }" >
										Mua Lại	</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h1>Chưa Mua Sản Phẩm Nào</h1>
				</c:otherwise>
			</c:choose>


		</div>
	</section>
	<!-- Shopping Cart Section End -->
	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>
</body>
</html>