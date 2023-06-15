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
	<!-- Breadcrumb Section Begin -->
	<div class="breacrumb-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb-text product-more">
						<a href="./index.html"><i class="fa fa-home"></i> Home</a> <a
							href="./shop.html">Giỏ hàng</a> <span>Hóa đơn</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Section Begin -->

	<!-- Shopping Cart Section Begin -->
	<section class="checkout-section spad">
		<div class="container">
			<form:form action="/bill/pay" class="checkout-form" modelAttribute="order" method="post">
				<div class="row">
					<div class="col-lg-6">

						<h4>Thông tin khách hàng</h4>
						<div class="row">
							<div class="col-lg-12">
								<label for="fir">Họ Và Tên<span>*</span></label> <input
									type="text" value="${user.fullname }" id="fir"  disabled="disabled"/>
							</div>
							<div class="col-lg-12">
								<label for="last">Email<span>*</span></label> <input type="text"
									id="last" value="${user.email }"  disabled="disabled">
							</div>
							<div class="col-lg-12">
								<label for="cun-name">Số Điện Thoại</label> <form:input type="text"
								path="phone"	id="cun-name" style=" margin-bottom: 5px;" />
									<form:errors path="phone" style=" font-size: 10px;color:red; margin-bottom: 5px;"    />
							</div>
							<div class="col-lg-12">
								<label for="cun-name">Địa Chỉ Nhận Hàng</label> <form:input
								path="address"	type="text" id="cun-name"  style=" margin-bottom: 5px;"/>
									<form:errors path="address" style=" font-size: 10px;color:red; margin-bottom: 5px;"    />
							</div>
							
							<div class="col-lg-12">
								<label for="street">Thông Tin Thêm</label> <form:textarea type="text"
							 cols="10" rows="5"	path="notes"	id="street" class="form-control"  style=" margin-bottom: 5px;"/>
									
							</div>


						</div>
					</div>
					<div class="col-lg-6">

						<div class="place-order">
							<h4>Sản Phẩm</h4>
							<div class="order-total">
								<ul class="order-table">
									<li>Product <span>Total</span></li>

									<c:forEach var="item" items="${listByProducts }">
										<li class="fw-normal">${item.name } x ${item.qty} <span><fmt:formatNumber
														value="${item.price }" type="currency"
														currencyCode="VND" /></span></li>
									</c:forEach>

									<li class="fw-normal">Tổng Tiền <span><fmt:formatNumber
														value="${total }" type="currency"
														currencyCode="VND" /></span></li>
									<c:choose>
											<c:when test="${not empty sessionScope.keyVoucher }">
													<li class="fw-normal">Mã Giảm Giá ${sessionScope.keyVoucher.discount }% 
													<span><fmt:formatNumber value="${discounts}"
																type="currency" currencyCode="VND" /></span>
													</li>
											</c:when>
											<c:otherwise>
													<li class="fw-normal">Mã Giảm Giá  
																<span>Không có</span>
													</li>
											</c:otherwise>
									</c:choose>
									<li class="fw-normal">Tổng Tiền Phải Trả <span><fmt:formatNumber
														value="${totalAll }" type="currency"
													currencyCode="VND" /></span></li>
								</ul>
								<div class="payment-check">
									<div class="pc-item">
										<label for="pc-check"> Thanh toán khi nhận hàng (Tiền
											mặt) <input type="checkbox" id="pc-check"> <span
											class="checkmark"></span>
										</label>
									</div>
									<div class="pc-item">
										<label for="pc-paypal"> Thanh toán qua tính dụng <input
											type="checkbox" id="pc-paypal"> <span
											class="checkmark"></span>
										</label>
									</div>
								</div>
								<div class="order-btn">
									<button type="submit" class="site-btn place-btn">Đặt
										Hàng</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form>
			<p style="color: red;">${message }</p>
		</div>
	</section>
	<!-- Shopping Cart Section End -->


	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>
</body>
</html>