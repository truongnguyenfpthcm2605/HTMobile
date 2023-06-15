<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tổng Quan</title>
<%@include file="/taglib/bstrap4.3.jsp"%>
<%@include file="/taglib/admincss.jsp"%>
</head>
<body>
	<div class="containers">
		<jsp:include page="menu.jsp" />
		<div class="main">
			<div class="topbar">
				<div class="toggle">
					<i style="font-size: 1.75rem" class='bx bx-list-ul'></i>
				</div>


			</div>

			<!-- ======================= Cards ================== -->
			<div class="cardBox">
				<div class="card">
					<div>
						<div class="numbers">${totalOrders }</div>
						<div class="cardName"> <i class='bx bx-cart-download'></i> Đơn Đã Bán</div>
					</div>

					<div class="iconBx">
						<ion-icon name="eye-outline"></ion-icon>
					</div>
				</div>

				<div class="card">
					<div>
						<div class="numbers">${totalQuantitySold }</div>
						<div class="cardName"> <i class='bx bxs-circle-three-quarter'></i>Sản Phẩm Đã Bán</div>
					</div>

					<div class="iconBx">
						<ion-icon name="cart-outline"></ion-icon>
					</div>
				</div>

				<div class="card">
					<div>
						<div class="numbers">${totalNewUser }</div>
						<div class="cardName"> <i class='bx bx-user' ></i>Người Dùng</div>
					</div>

					<div class="iconBx">
						<ion-icon name="chatbubbles-outline"></ion-icon>
					</div>
				</div>

				<div class="card">
					<div>
						<div class="numbers">234</div>
						<div class="cardName"> <i class='bx bx-money-withdraw'></i>Lượt Truy Cập</div>
					</div>

					<div class="iconBx">
						<ion-icon name="cash-outline"></ion-icon>
					</div>
				</div>
			</div>

			<!-- ================ Order Details List ================= -->
			<div class="row">
				<div class="col-md-8">
					<div class="container"  style="margin: 20px">
						<h2>Thông Kê Tổng Quan</h2>
					</div>

					<table class="table table-hover container" style="width: 80%;margin: 0 auto;">
						<thead>
							<tr>
								<th  scope="col">Stt</th>
								<th  scope="col">Loại Sản Phẩm</th>
								<th  scope="col">Tổng Tiền</th>
								<th  scope="col">Số Lượng</th>
								<th  scope="col">Tổng Giá Trị</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="item" items="${ListReport }" varStatus="index">
								<tr>
									<td>${index.count }</td>
									<td>${item[0]}</td>
									<td><fmt:formatNumber value="${item[1]}" type="currency"
											currencyCode="VND" /></td>
									<td>${item[2] }</td>
									<td><fmt:formatNumber value="${item[1]*item[2]}"
											type="currency" currencyCode="VND" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- ================= New Customers ================ -->
				<div class="col-md-4">
					<div class="container" style="margin: 20px">
						<h2>Mua Nhiều</h2>
					</div>

					<table  class="table table-hover">
						<c:forEach var="item" items="${listOrders }">
						<tr>
							<td width="60px">
								<div class="imgBx">
									<img
										src="${pageContext.request.contextPath}/img/customer02.jpg"
										alt="" style="width: 60px; border-radius: 50%">
								</div>
							</td>
							<td>
								<h6>
									${item[0]} <br> <span>Đơn đã mua : ${item[1] }</span>
								</h6>
							</td>
						</tr>
						</c:forEach>

						
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/taglib/adminjs.jsp"%>
</body>
</html>