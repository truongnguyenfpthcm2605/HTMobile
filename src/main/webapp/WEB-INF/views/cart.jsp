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

</head>
<body>
	<jsp:include page="headerCart.jsp" />
	<!-- Shopping Cart Section Begin -->
	<section class="shopping-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-table">
						<table>
							<thead>
								<tr>
									<th>Ảnh</th>
									<th class="p-name">Tên Sản Phẩm</th>
									<th>Giá</th>
									<th>Số Lượng</th>
									<th>Tổng Tiền</th>
									<th><i class="ti-close"></i></th>
								</tr>
							</thead>
							<tbody>

								<c:choose>
									<c:when test="${not empty cart }">
										<c:forEach var="item" items="${cart }">
											<form method="post" action="/cart/update">
												<tr>
													<td class="cart-pic first-row"><img
														src="${pageContext.request.contextPath}/img/products/${item.img}"
														alt=""></td>
													<td class="cart-title first-row">
														<h5>${item.name }</h5>
													</td>

													<td class="p-price first-row"><fmt:formatNumber
															value="${item.price }" type="currency" currencyCode="VND" /></td>
													<td class="qua-col first-row">
														<div class="quantity">
															<input type="number" value="${item.qty }" name="qty"
																style="width: 30%; border: none; outline: none;"
																onchange="submit()">
														</div> <input type="hidden" name="id" value="${item.id }">
													</td>
													<td class="total-price first-row"><fmt:formatNumber
															value="${item.price * item.qty}" type="currency"
															currencyCode="VND" /></td>
													<td class="close-td first-row"><a
														href="/cart/delete/${item.id }"
														style="text-decoration: none; color: black;"><i
															class="ti-close"></i></a></td>
												</tr>
											</form>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p>Thêm Sản Phẩm Bạn Muốn Mua</p>

									</c:otherwise>
								</c:choose>


							</tbody>
						</table>
					</div>
					<c:choose>
						<c:when test="${not empty cart }">
							<div class="row">
								<a href="/cart/reset"
									style="margin-right: 5px; margin: 0 0 10px 10px"
									class="btn btn-warning"> <i class='bx bx-reset'></i>Xóa Tất
									Cả
								</a> <a href="/product/views"
									style="margin: 0 0 10px 10px; margin-bottom: 10px"
									class="btn btn-success"> <i class='bx bx-cart-add'></i>Mua
									Thêm
								</a>
							</div>
							<div class="row">
								<div class="col-lg-4">

									<div class="discount-coupon">
										<h6>Mã giảm giá</h6>
										<form action="/cart/voucher" class="coupon-form" method="post">
											<input type="text" placeholder="Nhập mã " name="voucher" value="${valueVoucher }">
											<button type="submit" class="site-btn coupon-btn">Sử
												Dụng</button>
										</form>
									</div>
								</div>
								<div class="col-lg-4 offset-lg-4">
									<div class="proceed-checkout">
										<ul>
											<li class="subtotal">Tổng tiền <span><fmt:formatNumber
														value="${total }" type="currency" currencyCode="VND" /></span></li>
											<c:choose>
												<c:when test="${not empty sessionScope.keyVoucher}">
													<li class="subtotal">Voucher Giảm ${ sessionScope.keyVoucher.discount}
														% <span><fmt:formatNumber value="${discount }"
																type="currency" currencyCode="VND" /></span>
													</li>
												</c:when>
												<c:otherwise>
													<li class="subtotal">Voucher <span> Chưa có
															</span>
													</li>
												</c:otherwise>
											</c:choose>
											<li class="cart-total">Tổng Tiền Phải Trả <span><fmt:formatNumber
														value="${totalProduct }" type="currency"
														currencyCode="VND" /></span></li>
										</ul>
										<a href="/bill/view" class="proceed-btn">XUẤT HÓA ĐƠN</a>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</section>
	<!-- Shopping Cart Section End -->
	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>

</body>
</html>