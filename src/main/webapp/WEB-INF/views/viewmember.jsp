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
			<h2 style="margin: 20px; font-weight: 600;">Thông Tin Người Dùng</h2>
			<div class="container">

				<form action="/admin/member/update" class="row" method="post"
					style="margin-top: 10px;">
					<div class="col-md-6">
						<input type="hidden" value="${user.id }" name="id">
						<div class="form-group">
							<label>Email </label> <input type="email" class="form-control"
								disabled="disabled" value="${user.email }" />
						</div>
						<div class="form-group">
							<label>Họ và tên </label> <input type="text" class="form-control"
								disabled="disabled" value="${user.fullname}" />
						</div>
						<div class="form-group">
							<label>Ngày Sinh </label> <input type="date" class="form-control"
								disabled="disabled"
								value="<fmt:formatDate value="${user.birthday}"
										pattern="yyyy-MM-dd" />" />
						</div>
						<div class="form-group">
							<label>Ngày tham gia </label> <input type="datetime"
								class="form-control" disabled="disabled"
								value="<fmt:formatDate value="${user.createday}"
										pattern="HH:mm yyyy-MM-dd" />" />
						</div>
						<div class="form-group">
							<label>Ngày cập nhật </label> <input type="datetime"
								class="form-control" disabled="disabled"
								value="<fmt:formatDate value="${user.updateday}"
										pattern="HH:mm yyyy-MM-dd" />" />
						</div>

					</div>
					<div class="col-md-6">

						<div class="form-group">
							<label>Mật khẩu </label> <input type="text" class="form-control"
								disabled="disabled" value="${pass }" />
						</div>
						<div class="form-group">
							<label>Giới Tính</label> <input type="text" class="form-control"
								value="${user.gender?'Nam':'Nữ' }" disabled="disabled" />
						</div>
						<div class="form-group">
							<label>Hoạt Động</label> 
							<select class="form-control" name="active">
									<option value="true" ${user.active?'selected':'' }>Hoạt Động</option>
									<option value="false" ${user.active?'':'selected' }>Dừng Hoạt Động</option>
							</select>
						</div>


						<div class="form-group">
							<label>Quyền</label> 
							<select class="form-control" name="role">
									<option value="true" ${user.role?'selected':'' }>Quản Lý</option>
									<option value="false" ${user.role?'':'selected' }>Người Dùng</option>
							</select>
						</div>

						<div class="form-group" style="text-align: center;">
							<a href="/admin/member" class=" btn btn-light" style=" border-radius: 5px; width: 30%">
									Trở Lại
							</a>
							
							<input type="submit" class=" btn btn-dark" value="Lưu"
								style=" border-radius: 5px; width: 30%">
						</div>

					</div>
				</form>


			</div>
		</div>
	</div>
	<%@include file="/taglib/adminjs.jsp"%>
</body>
</html>