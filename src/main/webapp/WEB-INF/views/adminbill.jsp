<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản Lý Đơn Hàng</title>
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
			<div class="container">
				<div class="row">
					<div class="row" style="margin: 15px">
						<h2>Danh Sách Đơn Hàng</h2>
					</div>
				</div>
				<div class="row" style="margin-bottom: 20px">
					<div class=" col-md-6">
						<form action="/admin/bill/search" class="row" method="post">
							<input type="text" class="form-control col-md-7" name="search"
								value="${search }" placeholder="Tên người mua">
							<button class="btn btn-dark col-md-4" style="margin-left: 5px">Tìm</button>
						</form>
					</div>
					
					<div class="col-md-3">
						<form action="/admin/bill/month" method="post">
							<select name="months" class="btn btn-secondary" style="width: 90%"
								onchange="this.form.submit()">
								<option value="1" ${month==1?'selected':'' }>Tháng 1</option>
								<option value="2" ${month==2?'selected':'' }>Tháng 2</option>
								<option value="3" ${month==3?'selected':'' }>Tháng 3</option>
								<option value="4" ${month==4?'selected':'' }>Tháng 4</option>
								<option value="5" ${month==5?'selected':'' }>Tháng 5</option>
								<option value="6" ${month==6?'selected':'' }>Tháng 6</option>
								<option value="7" ${month==7?'selected':'' }>Tháng 7</option>
								<option value="8" ${month==8?'selected':'' }>Tháng 8</option>
								<option value="9" ${month==9?'selected':'' }>Tháng 9</option>
								<option value="10" ${month==10?'selected':'' }>Tháng 10</option>
								<option value="11" ${month==11?'selected':'' }>Tháng 11</option>
								<option value="12" ${month==12?'selected':'' }>Tháng 12</option>
							</select>
						</form>
					</div>
					<div class="col-md-3">

						<form action="/admin/bill/status" method="post">
							<select name="status" class="btn btn-dark"
								onchange="this.form.submit()">
								<option value="Đã Đặt Hàng"${key eq 'Đã Đặt Hàng' ? 'selected' : ''}>Đã Đặt Hàng</option>
								<option value="Đã Xác Nhận"${key eq 'Đã Xác Nhận' ? 'selected' : ''}>Đã Xác Nhận</option>
								<option value="Đã Đóng Gói" ${key eq 'Đã Đóng Gói' ? 'selected' : ''}>Đã Đóng Gói</option>
								<option value="Đã Xuất Kho" ${key eq 'Đã Xuất Kho' ? 'selected' : ''}>Đã Xuất Kho</option>
								<option value="Đã Giao Hàng" ${key eq 'Đã Giao Hàng' ? 'selected' : ''}>Đã Giao Hàng</option>
								<option value="Đã Hủy" ${key eq 'Đã Hủy' ? 'selected' : ''}>Đã Hủy</option>
								<option value="Trả Hàng" ${key eq 'Trả Hàng' ? 'selected' : ''}>Trả Hàng</option>
								<option value="Chờ Thu Hồi" ${key eq 'Chờ Thu Hồi' ? 'selected' : ''}>Chờ Thu Hồi</option>
							</select>
						</form>
					</div>
				</div>

				<table style="width: 90%;" class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Stt</th>
							<th scope="col">Mã Đơn Hàng</th>
							<th scope="col">Tên Khách Hàng</th>
							<th scope="col">Ngày Tạo</th>
							<th scope="col">Giảm Giá</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${listBill }" var="item" varStatus="index">
							<tr>
								<td>${index.count }</td>
								<td>${item.id }</td>
								<td>${item.users.fullname }</td>
								<td><fmt:formatDate value="${item.createday}"
										pattern="HH:mm yyyy-MM-dd" /></td>
								<c:choose>
										<c:when test="${ not empty item.voucher }">
												<td>${item.voucher }</td>
										</c:when>
										<c:otherwise>
												<td>Không có</td>
										</c:otherwise>
								</c:choose>

								<td style="color: green;">${item.status }</td>
								<td>
								 <a href="/admin/bill/view/${item.id }"
									style="text-decoration: none;" class="status inProgress"><i
									class='bx bx-show'></i> Xem</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>


	</div>
	<%@include file="/taglib/adminjs.jsp"%>
</body>
</html>