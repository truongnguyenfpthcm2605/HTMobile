<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản Lý Quà Tặng</title>
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

				<div class="row" style="margin: 15px">
					<h2 class="col-md-9">Danh Sách Mã Quà Tặng</h2>
					<a href="/admin/gift/add" class=" btn btn-dark col-md-3"> <i
						class='bx bxs-notepad'></i> Thêm Mã Giảm Giá
					</a>
				</div>
				<div class="row">
					<div class=" col-md-6">
						<form action="/admin/gift/search" class="row" method="post">
							<input type="text" class="form-control col-md-7" name="search"
								value="${search }" placeholder="Tên theo tên sự kiện">
							<button class="btn btn-dark col-md-4" style="margin-left: 5px">Tìm</button>
						</form>
					</div>
					<div class="col-md-3">

						<form action="/admin/gift/sort" method="post">
							<select name="sort" class="btn btn-secondary"
								onchange="this.form.submit()">
								<option value="id" ${key eq 'id' ? 'selected' : ''}>Mã
									Giảm Giá Sản Phẩm</option>
								<option value="name" ${key eq 'name' ? 'selected' : ''}>Tên
									Mã Giảm Giá</option>
								<option value="discount" ${key eq 'discount' ? 'selected' : ''}>%
									Giảm Giá</option>
								<option value="createday"
									${key eq 'createday' ? 'selected' : ''}>Ngày Tạo</option>
								<option value="endday" ${key eq 'endday' ? 'selected' : ''}>Ngày
									Kết Thúc</option>


							</select>
						</form>
					</div>
					<div class="col-md-3">
						<form action="/admin/gift/active" method="post">
							<select name="active" class="btn btn-secondary"
								onchange="this.form.submit()">
								<option value="true" ${active? 'selected' : ''}>Đã Kích
									Hoạt</option>
								<option value="false" ${active?'':'selected'}>Chưa Kích
									Hoạt</option>
							</select>
						</form>
					</div>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Stt</th>
							<th scope="col">Mã Giảm Giá</th>
							<th scope="col">Tên Mã Giảm Giá</th>
							<th scope="col">% Giảm Giá</th>
							<th scope="col">Ngày Tạo</th>
							<th scope="col">Ngày Kết Thúc</th>
							<th scope="col">Kích Hoạt</th>
							<th scope="col">Thao Tác</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${listGift }" var="item" varStatus="index">
							<tr>
								<td>${index.count }</td>
								<td>${item.id }</td>
								<td>${item.name }</td>
								<td>${item.discount }</td>
								<td><fmt:formatDate value="${item.createday}"
										pattern="HH:mm yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${item.endday}"
										pattern="HH:mm yyyy-MM-dd" /></td>
								<c:choose>
									<c:when test="${item.active }">
										<td style="color: green;">Hoạt động</td>
									</c:when>
									<c:otherwise>
										<td style="color: green;">Không hoạt động</td>
									</c:otherwise>
								</c:choose>
								<td><a href="/admin/gift/view/${item.id }"
									style="text-decoration: none;" class="status pending"><i
										class='bx bx-edit-alt'></i> Sửa</a> <a
									href="/admin/gift/delete/${item.id }"
									style="text-decoration: none;" class="status return"><i
										class='bx bxs-trash-alt'></i>Xóa</a> <c:choose>
										<c:when test="${item.active }">
											<a href="/admin/gift/block/${item.id }"
												style="text-decoration: none;" class="status return"><i
												class='bx bxs-trash-alt'></i>Khóa</a>
										</c:when>
										<c:otherwise>
											<a href="/admin/gift/block/${item.id }"
												style="text-decoration: none;" class="status delivered"><i
												class='bx bxs-trash-alt'></i>Mở</a>
										</c:otherwise>
									</c:choose>
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