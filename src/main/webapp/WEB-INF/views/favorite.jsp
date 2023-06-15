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
	<jsp:include page="header.jsp" />
	<!-- Shopping Cart Section Begin -->
	<section class="shopping-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="cart-table">
						<table>
							
							<tbody>
								<tr>
									<td class="cart-pic first-row"><img
										src="${pageContext.request.contextPath}/img/cart-page/product-1.jpg"
										alt=""></td>
									<td class="cart-title first-row">
										<h5>Pure Pineapple</h5>
									</td>
									<td class="p-price first-row">$60.00</td>
									<td class="qua-col first-row">
										<div class="quantity">
											<div class="pro-qty">
												<input type="text" value="1">
											</div>
										</div>
									</td>
									<td class="total-price first-row">$60.00</td>
									<td class="close-td first-row"><i class="ti-close"></i></td>
								</tr>
								<tr>
									<td class="cart-pic"><img
										src="${pageContext.request.contextPath}/img/cart-page/product-2.jpg"
										alt=""></td>
									<td class="cart-title">
										<h5>American lobster</h5>
									</td>
									<td class="p-price">$60.00</td>
									<td class="qua-col">
										<div class="quantity">
											<div class="pro-qty">
												<input type="text" value="1">
											</div>
										</div>
									</td>
									<td class="total-price">$60.00</td>
									<td class="close-td"><i class="ti-close"></i></td>
								</tr>
								<tr>
									<td class="cart-pic"><img
										src="${pageContext.request.contextPath}/img/cart-page/product-3.jpg"
										alt=""></td>
									<td class="cart-title">
										<h5>Guangzhou sweater</h5>
									</td>
									<td class="p-price">$60.00</td>
									<td class="qua-col">
										<div class="quantity">
											<div class="pro-qty">
												<input type="text" value="1">
											</div>
										</div>
									</td>
									<td class="total-price">$60.00</td>
									<td class="close-td"><i class="ti-close"></i></td>
								</tr>
							</tbody>
						</table>
					</div>
					
				</div>
			</div>
		</div>
	</section>
	<!-- Shopping Cart Section End -->
	<jsp:include page="footer.jsp" />
	<%@include file="/taglib/js.jsp"%>
</body>
</html>