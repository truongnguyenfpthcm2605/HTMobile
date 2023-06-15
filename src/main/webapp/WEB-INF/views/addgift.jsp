<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thêm Quà Tặng</title>
<%@include file="/taglib/bstrap4.3.jsp"%>
<%@include file="/taglib/admincss.jsp"%>
</head>
<body>
	<div class="containers">
		<jsp:include page="menu.jsp" />
		<!-- ========================= Main ==================== -->
		<div class="main">
			<h2 style="margin: 20px; font-weight: 600;">Mã Giảm Giá</h2>
			<div class="container">

				<div class="topbar">
					<div class="toggle">
						<i style="font-size: 1.75rem" class='bx bx-list-ul'></i>
					</div>
				</div>
				<form:form action="/admin/gift/update" class="row"
					modelAttribute="voucher" style="margin: 10px auto;" method="post">

					<div class="col-md-10">
						<div class="form-group">
							<label>Mã giảm giá *</label>
							<form:input type="text" class="form-control" path="id" />
							<form:errors path="id" style="color:red" />
						</div>
						<div class="form-group">
							<label>Tên giảm giá *</label>
							<form:input type="text" class="form-control" path="name" />
							<form:errors path="name" style="color:red" />
						</div>
						<div class="form-group">
							<label>Giảm giá *</label>
							<form:input type="number" class="form-control" path="discount" />
							<form:errors path="discount" style="color:red" />
						</div>
						<div class="form-group">
							<label>Ngày tạo *</label>
							<form:input path="createday" type="date" class="form-control"
								pattern="yyyy-MM-dd" />
						</div>
						<div class="form-group">
							<label>Ngày kết thúc *</label>
							<form:input path="endday" type="date" class="form-control"
								pattern="yyyy-MM-dd" />
						</div>


						<div class="form-group">
							<label>Sử Dụng *</label> <br>
							<form:select path="active" class="form-control">
								<form:option value="true">Kích Hoạt</form:option>
								<form:option value="false">Chưa Kích Hoạt</form:option>
							</form:select>
						</div>
						<button class="btn btn-dark " style="width: 100%;">Cập
							Nhật</button>

					</div>

				</form:form>
				<p style="color: red; margin-left: 10px">${message }</p>


			</div>
		</div>
	</div>
	<%@include file="/taglib/adminjs.jsp"%>
</body>
</html>