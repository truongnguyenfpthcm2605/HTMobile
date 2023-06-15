<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Xem Thành Viên</title>
<%@include file="/taglib/bstrap4.3.jsp"%>
<%@include file="/taglib/admincss.jsp"%>
</head>
<body>
	<div class="containers">
		<jsp:include page="menu.jsp" />
		<!-- ========================= Main ==================== -->
		<div class="main">
			<h2 style="margin: 20px; font-weight: 600;">Thành Viên</h2>
			<div class="container">

				<div class="topbar">
					<div class="toggle">
						<i style="font-size: 1.75rem" class='bx bx-list-ul'></i>
					</div>


				</div>

				<div class="container"
					style="padding: 20px 20px 150px 20px; height: auto; border: 1px solid black; border-radius: 10px;">
					<h1 style="text-align: center; font-weight: bold;">Hóa Đơn</h1>
					<h4>HTMobile &#169;</h4>
					<ul class="nav flex-column">
						<li class="nav-item mb-2">Hotline : 0999999</li>
						<li class="nav-item mb-2">Mã Đơn Hàng : ${orders.id }</li>
						<li class="nav-item mb-2">Ngày Tạo : <fmt:formatDate
								value="${orders.createday}" pattern="HH:mm yyyy-MM-dd" />
						</li>
						<li class="nav-item mb-2">Địa Chỉ : 22 Nguyen Van Qua Street
							, Q12, TP HCM</li>
						<li class="nav-item mb-2">Email : Htmobile@gmail.com</li>
						<li class="nav-item mb-2">Địa Chỉ Giao Hàng :
							${orders.address }</li>
						<li class="nav-item mb-2">Số điện thoại người nhận :
							${orders.phone }</li>
						<li class="nav-item mb-2" class="nav-link p-0 text-muted">Người
							nhận : ${orders.users.fullname }</li>
						<li class="nav-item mb-2" class="nav-link p-0 text-muted">Thông
							tin thêm : : ${orders.notes }</li>
					</ul>
					<hr>
					<h3>Sản Phẩm Hóa Đơn</h3>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Stt</th>
									<th scope="col">Mã Sản Phẩm</th>
									<th scope="col">Tên Sản Phẩm</th>
									<th scope="col">Giá Tiền</th>
									<th scope="col">Số Lượng</th>
									<th scope="col">Thành Tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lisDetails }" var="item" varStatus="index">
									<tr>
										<td>${index.count }</td>
										<td>${item.product.id }</td>
										<td>${item.product.title }</td>
										<td><fmt:formatNumber value="${item.price}"
												type="currency" currencyCode="VND" /></td>
										<td>${item.quantity }</td>
										<td><fmt:formatNumber
												value="${item.price * item.quantity}" type="currency"
												currencyCode="VND" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>


					<hr>
					<h6>
						Tổng Tiền Sản Phẩm :
						<fmt:formatNumber value="${total}" type="currency"
							currencyCode="VND" />
					</h6>
					<c:choose>
						<c:when test="${not empty voucher }">
							<h6>
								Mã giảm giá sản phẩm ${voucher.discount} %  :
								<fmt:formatNumber value="${mgg}" type="currency"
									currencyCode="VND" />
							</h6>
						</c:when>
						<c:otherwise>
							<h6>
								Mã giảm giá sản phẩm   : Không Có
							</h6>
						</c:otherwise>
					</c:choose>
					<h5>
						Tổng Tiền Đơn Hàng :
						<fmt:formatNumber value="${totalAll}" type="currency"
							currencyCode="VND" />
					</h5>

				</div>
				<div class="container" style="margin: 15px">
					<a href="/admin/bill" class="btn btn-light" style="width: 200px"> Trờ Lại</a> <a href='<c:url value="/document/updateBiil.docx"></c:url>'
						class="btn btn-dark" style="width: 200px; color: white;"> Xuất
						Hóa Đơn</a>
				</div>
				<div class="controller" >

					<form action="/admin/bill/updatestatus" method="post" class="row">
						<input type="hidden" value="${orders.id }" name="id"> <select
							name="update" class="form-control col-md-4 "
							>
							<option value="Đã Đặt Hàng">Đã Đặt Hàng</option>
							<option value="Đã Xác Nhận">Đã Xác Nhận</option>
							<option value="Đã Đóng Gói">Đã Đóng Gói</option>
							<option value="Đã Xuất Kho">Đã Xuất Kho</option>
							<option value="Đã Giao Hàng">Đã Giao Hàng</option>
							<option value="Đã Hủy">Đã Hủy</option>
							<option value="Trả Hàng">Trả Hàng</option>
							<option value="Chờ Thu Hồi">Chờ Thu Hồi</option>
						</select>
						<button class="btn btn-success col-md-2" style="margin-left: 10px">
							<i class='bx bx-edit'></i>Cập Nhật Trạng Thái
						</button>
					</form>

				</div>
			</div>


		</div>
	</div>

	<%@include file="/taglib/adminjs.jsp"%>
</body>
</html>