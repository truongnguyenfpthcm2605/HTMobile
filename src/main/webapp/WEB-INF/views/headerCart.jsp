<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglib/tablig.jsp"%>

<!-- Header Section Begin -->
<header class="header-section">
	<div class="header-top">
		<div class="container">
			<div class="ht-left">
				<div class="mail-service">
					<i class=" fa fa-envelope"></i> htmobile@gmail.com
				</div>
				<div class="phone-service">
					<i class=" fa fa-phone"></i> 0999999999
				</div>
			</div>
			<div class="ht-right">
				<c:choose>
					<c:when test="${ not empty  sessionScope.account}">
							 <a href="/information/views" class="login-panel"><i class="fa fa-user"></i>Welcome, ${sessionScope.account.getFullname()} </a>
					</c:when>
					<c:otherwise>
							 <a href="/login" class="login-panel"><i class="fa fa-user"></i>Đăng Nhập</a>
					</c:otherwise>
				</c:choose>
				<div class="top-social">
					<a href="?lang=vi"  style="text-decoration: none; font-weight: bold;">
						<img alt="" src="/img/flag-2.jpg">
					VN
					</a>
					
					<a href="?lang=en"  style="text-decoration: none; font-weight: bold;">
					<img alt="" src="/img/flag-1.jpg">
					English</a>
				</div>
				<div class="top-social">
					<a href="#"><i class="ti-facebook"></i></a> <a href="#"><i
						class="ti-twitter-alt"></i></a> <a href="#"><i class="ti-linkedin"></i></a>
					<a href="#"><i class="ti-pinterest"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="inner-header">
			<div class="row">
				<div class="col-lg-2 col-md-2">
					<div class="logo"  style="padding: 0">
						<a href="/index"> <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="">
						</a>
					</div>
				</div>
				<div class="col-lg-7 col-md-7">
					<form action="/product/search" method="post" class="container">
						<input type="text" value="${keywords}"  style="width: 80%;
                        box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
                        border-radius: 10px;line-height: 40px; margin: 0; border: solid 1px orange; outline: none" placeholder="Bạn đang tìm gì?" name="search">
						<button type="submit" class="btn btn-warning" style="width: 15%; margin-left: 5px;
                         height: 40px"><i class="fa-solid fa-magnifying-glass"></i></button>
					</form>
				</div>
				<div class="col-lg-3 text-left col-md-3">
					<ul class="nav-right">
						<li class="heart-icon"><a href="/favorite"> <i
								class="icon_heart_alt"></i> <span>1</span>
						</a></li>
						<li class="cart-icon"><a href="/cart/views"> <i
								class="icon_bag_alt"></i> <span>${numberCartItem }</span>
						</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
		<div class="nav-item">
		<div class="container">

			<nav class="nav-menu mobile-menu" style="text-align: center;">
				<ul>
					<li ><a href="/index"> <s:message code="lo.mn.home"></s:message></a> </li>
					<li><a href="/product/views"> <s:message code="lo.mn.product"></s:message></a></li>
					<li><a href="https://chat.openai.com/">GPT</a></li>
					<li><a href="/newfeed/views"> <s:message code="lo.mn.about"></s:message></a></li>
					<li><a href="/contact/views"> <s:message code="lo.mn.contact"></s:message></a></li>
					<li><a href="#"> <s:message code="lo.mn.setting"></s:message></a>
						<ul class="dropdown">
							<li><a href="/register"> <s:message code="lo.mn.register"></s:message>  </a></li>
							<li><a href="/login"> <s:message code="lo.mn.login"></s:message> </a></li>
							<li><a href="/rememberPass"> <s:message code="lo.mn.changepass"></s:message> </a></li>

							<c:if test="${ not empty  sessionScope.account}">
								<li><a href="/logout"> <s:message code="lo.mn.logout"></s:message> </a></li>
								<li><a href="/history"> <s:message code="lo.mn.history"></s:message> </a></li>

							</c:if>
						</ul></li>
					<c:if test="${ sessionScope.account.role}">
						<li><a href="/admin/overview"> <s:message code="lo.mn.manager"></s:message></a></li>
					</c:if>
				</ul>
			</nav>
			<div id="mobile-menu-wrap"></div>
		</div>
	</div>
</header>
<script>
		$(document).ready(function() {
			$("a[href*=lang]").on("click", function() {
				var param = $(this).attr("href");
				$.ajax({
					url : "/home/index" + param,
					success : function() {
						location.reload();
					}
				});
				return false;
			})
		})
	</script>
<!-- Header End -->