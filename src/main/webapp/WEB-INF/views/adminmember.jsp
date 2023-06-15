<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản Lý Thành Viên</title>
<%@include file="/taglib/bstrap4.3.jsp"%>
<%@include file="/taglib/admincss.jsp"%>
</head>
<body>
	<div class="containers">
		<jsp:include page="menu.jsp" />
		<!-- ========================= Main ==================== -->
		<div class="main">
			<div class="topbar">
				<div class="toggle">
					<i style="font-size: 1.75rem" class='bx bx-list-ul'></i>
				</div>




			</div>
			<div class="container">
				<div class="row" style="margin-bottom: 20px">
					<h2 class="col-md-4">Quản Lý Thành Viên</h2>

				</div>
				<div class="row" style="margin-bottom: 20px">
					<div class=" col-md-6">
						<form action="/admin/member/search" class="row" method="post">
							<input type="text" class="form-control col-md-7" name="search"
								value="${search }" placeholder="Tìm người dùng">
							<button class="btn btn-dark col-md-4" style="margin-left: 5px">Tìm</button>
						</form>
					</div>
					<div class="col-md-2">
						<form action="/admin/member/sort" method="post">
							<select name="sortUser" class="btn btn-light"
								onchange="this.form.submit()">
								<option value="createday"
									${key eq 'createday' ? 'selected' : ''}>Ngày đăng ký</option>
								<option value="fullname" ${key eq 'fullname' ? 'selected' : ''}>Họ
									và tên</option>
								<option value="email" ${key eq 'email' ? 'selected' : ''}>Email</option>

							</select>
						</form>
					</div>
					<div class="col-md-2">
						<form action="/admin/member/active" method="post">
							<select name="acitveUser" class="btn btn-light"
								onchange="this.form.submit()">
								<option value="true" ${active?'selected':'' }>Đang Hoạt
									động</option>
								<option value="false" ${active?'':'selected'}>Không
									hoạt động</option>
							</select>
						</form>
					</div>
					<div class="col-md-2">

						<a class="btn btn-success"
							href='<c:url value="/document/Accounts.xlsx"></c:url>'> <i
							class="fas fa-file-excel"></i> Excel
						</a>

					</div>
				</div>

				<table style="width: 100%;" class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Stt</th>
							<th scope="col">Tên thành viên</th>
							<th scope="col">Ngày đăng ký</th>
							<th scope="col">Email</th>
							<th scope="col">Trạng thái</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="item" items="${ListUser }" varStatus="index">
							<tr>
								<td>${index.count }</td>
								<td>${item.fullname }</td>
								<td><fmt:formatDate value="${item.createday}"
										pattern="dd/MM/yyyy" /></td>
								<td>${item.email }</td>
								<c:choose>
									<c:when test="${item.active }">
										<td style="color: green;">Hoạt động</td>
									</c:when>
									<c:otherwise>
										<td style="color: red;">Ngưng hoạt động</td>
									</c:otherwise>
								</c:choose>
								<td><c:choose>
										<c:when test="${item.active }">
											<a href="/admin/member/block/${item.id }"
												style="text-decoration: none;" class="status return"> <i
												class='bx bxs-trash-alt'></i>Khóa
											</a>
										</c:when>
										<c:otherwise>
											<a href="/admin/member/block/${item.id }"
												style="text-decoration: none;" class="status delivered "> <i class='bx bx-reset'></i>Mở
											</a>
												</c:otherwise>
									</c:choose> <a href="/admin/member/view/${item.id }"
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