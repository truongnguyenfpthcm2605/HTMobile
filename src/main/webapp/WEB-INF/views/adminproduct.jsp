<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản Lý Sản Phẩm</title>
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
				<div class="row" style="margin: 10px">
					<h2 class="col-md-8">Quản Lý Sản Phẩm</h2>
					<a href="/admin/product/add" class="btn btn-dark col-md-4"
						><i class='bx bxs-file-plus'></i>Thêm
						Sản Phẩm</a>
				</div>
				<div class="row" style="margin-bottom: 20px">
					<div class=" col-md-6">
						<form action="/admin/product/search" class="row" method="post">
							<input type="text" class="form-control col-md-7" name="search"
								value="${search }" placeholder="Tên sản phẩm">
							<button class="btn btn-dark col-md-4" style="margin-left: 5px">Tìm</button>
						</form>
					</div>

					<div class="col-md-2">
						<form action="/admin/product/month" method="post">
							<select name="months" class="btn btn-secondary"
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
					<div class="col-md-2">

						<form action="/admin/product/sort" method="post">
							<select name="sortProduct" class="btn btn-secondary"
								onchange="this.form.submit()">
								<option value="title" ${key eq 'title' ? 'selected' : ''}>Tên
									Sản Phẩm</option>
								<option value="price" ${key eq 'price' ? 'selected' : ''}>Giá</option>
								<option value="ram" ${key eq 'ram' ? 'selected' : ''}>Ram</option>
								<option value="rom" ${key eq 'rom' ? 'selected' : ''}>Bộ
									Nhớ</option>
								<option value="pin" ${key eq 'pin' ? 'selected' : ''}>Pin</option>
								<option value="camera" ${key eq 'camera' ? 'selected' : ''}>Camera</option>
								<option value="screen" ${key eq 'screen' ? 'selected' : ''}>Màn
									Hình</option>
								<option value="createday"
									${key eq 'createday' ? 'selected' : ''}>Ngày Đăng</option>
								<option value="quanityfinal"
									${key eq 'quanityfinal' ? 'selected' : ''}>Số Lượng
									Tồn Kho</option>
								<option value="quantitysold"
									${key eq 'quantitysold' ? 'selected' : ''}>Số Lượng Đã
									bán</option>
							</select>
						</form>
					</div>
					<div class="col-md-2">
						<form action="/admin/product/category" method="post">
							<select class="form-control" name="category"
								onchange="this.form.submit()">
								<c:forEach items="${listCategory}" var="category">
									<option value="${category.id}"
										${cate== category.id?'selected':''}>${category.title}</option>
								</c:forEach>
							</select>
						</form>
					</div>
				</div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Stt</th>
							<th scope="col">Mã Sản Phẩm</th>
							<th scope="col">Hình Sản Phẩm</th>
							<th scope="col">Tên Sản Phẩm</th>
							<th scope="col">Ngày Tạo</th>
							<th scope="col">Giá</th>
							<th scope="col">Tồn Kho</th>
							<th scope="col">Đã Bán</th>
							<th scope="col">Thao tác</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="item" items="${ListProduct }" varStatus="index">
							<tr>
								<td>${index.count }</td>
								<td>${item.id }</td>
								<td><img alt=''
									src="<c:url value='/img/products/${item.imgproduct }'></c:url>"
									style="width: 100px; height: 100px; border-radius: 10px">
								</td>
								<td>${item.title }</td>
								<td><fmt:formatDate value="${item.createday}"
										pattern="HH:mm yyyy-MM-dd" /></td>
								<td><fmt:formatNumber value="${item.price }"
										type="currency" currencyCode="VND" /></td>
								<td>${item.quanityfinal}</td>
								<td>${item.quantitysold }</td>

								<td><a href="/admin/product/view/${item.id }"
									style="text-decoration: none;" class="status pending"><i
										class='bx bx-edit-alt'></i> Cập Nhật</a> </td>
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