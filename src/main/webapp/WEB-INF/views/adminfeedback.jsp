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
					<h2 class="col-md-4">Quản Lý Phản Hồi</h2>

				</div>
				<div class="row" style="margin-bottom: 20px">
					<div class=" col-md-6">
						<form action="/admin/feedback/search" class="row" method="post">
							<input type="text" class="form-control col-md-7" name="search"
								value="${search }" placeholder="Tìm người dùng">
							<button class="btn btn-dark col-md-4" style="margin-left: 5px">Tìm</button>
						</form>
					</div>
					<div class="col-md-4">
						<form action="/admin/feedback/active" method="post">
							<select name="active" class="btn btn-light"
								onchange="this.form.submit()">
								<option value="true" ${active?'selected' : ''}>Đã Phản
									Hồi</option>
								<option value="false" ${active?'':'selected'}>Chưa Phản
									Hồi</option>


							</select>
						</form>
					</div>

				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Stt</th>
							<th scope="col">Mã Phản Hồi</th>
							<th scope="col">Tên Người Dùng</th>
							<th scope="col">Email</th>
							<th scope="col">Ngày Phản Hồi</th>
							<th scope="col">Phản Hồi</th>
							<th scope="col">Thao Tác</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="item" items="${ListFeedback }" varStatus="index">
							<tr>
								<td>${index.count }</td>
								<td>${item.id }</td>
								<td>${item.users.fullname }</td>
								<td>${item.users.email }</td>
								<td><fmt:formatDate value="${item.createday}"
										pattern="HH:mm dd/MM/yyyy" /></td>

								<c:choose>
									<c:when test="${item.reply }">
										<td style="color: green;">Đã Phản Hồi</td>
									</c:when>
									<c:otherwise>
										<td style="color: red;">Chưa Phản Hồi</td>
									</c:otherwise>
								</c:choose>
								<td><a href="/admin/feedback/delete/${item.id }"
									style="text-decoration: none;" class="status return"> <i
										class='bx bxs-trash-alt'></i> Xóa
								</a>
									<button type="button" style="text-decoration: none; outline: none; border: none ;margin-left: 5px"
										class="status delivered" data-toggle="modal"
										data-target="#exampleModal">
										<i class='bx bx-reset'></i> Xem
									</button></td>
							</tr>
							<div class="modal fade" id="exampleModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content"
										style="border-radius: 15px; border: none; box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;">

										<div class="modal-body">
											<form action="/admin/feedback/report" method="post">
												<div class="form-group">
													<label for="exampleInputEmail1">Email</label> <input
														type="email" disabled class="form-control" value="${item.users.email }">
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Nội Dung</label>
													<textarea cols="5" rows="5" disabled class="form-control">${item.description } </textarea>
												</div>
												<input value="${item.id }" type="hidden" name="id">
												<div class="form-group">
													<label for="exampleInputPassword1">Phản Hồi</label>
													<textarea name="report" cols="5" rows="5" required="required"
														class="form-control"> </textarea>
												</div>

												<button type="submit" class="btn btn-dark">Xác Nhận</button>
												<button type="button" class="btn btn-light"
													data-dismiss="modal">Đóng</button>
											</form>
										</div>
									</div>

								</div>
							</div>
						</c:forEach>


					</tbody>
				</table>
			</div>


		</div>
	</div>
	<%@include file="/taglib/adminjs.jsp"%>
	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
</body>
</html>