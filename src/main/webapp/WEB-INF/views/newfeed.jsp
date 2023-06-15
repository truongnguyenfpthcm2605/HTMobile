<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@include file="/taglib/tablig.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tin Tức</title>
     <%@include file="/taglib/css.jsp" %>

</head>
<body>
		<jsp:include page="header.jsp" />
		 <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i>Trang Chủ</a>
                        <span>Tin Tức</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section Begin -->

    <!-- Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-8 order-2 order-lg-1">
                    <div class="blog-sidebar">
                        <div class="search-form">
                            <h4>Tìm Kiếm</h4>
                            <form action="#">
                                <input type="text" placeholder="Bạn đang tìm gì?">
                                <button type="submit"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                        <div class="blog-catagory">
                            <h4>Thể Loại</h4>
                            <ul>
                                <li><a href="#">Tin trong nước</a></li>
                                <li><a href="#">Tin công nghệ </a></li>
                                <li><a href="#">Tin thể thao</a></li>
                                <li><a href="#">Tin tức HOT</a></li>
                            </ul>
                        </div>
                        <div class="recent-post">
                            <h4>Bài Đăng Gần Đây</h4>
                            <div class="recent-blog">
                                <a href="#" class="rb-item">
                                    <div class="rb-pic">
                                        <img src="${pageContext.request.contextPath}/img/blog/recent-1.jpg" alt="">
                                    </div>
                                    <div class="rb-text">
                                        <h6>Tại sao chúng ta phải cài Win cho MacBook?</h6>
                                        <p>Tin HOT <span>15/05/2023</span></p>
                                    </div>
                                </a>
                                <a href="#" class="rb-item">
                                    <div class="rb-pic">
                                        <img src="${pageContext.request.contextPath}/img/blog/recent-2.jpg" alt="">
                                    </div>
                                    <div class="rb-text">
                                        <h6>Tại sao chúng ta phải cài Win cho MacBook?</h6>
                                        <p>Tin HOT <span>15/05/2023</span></p>
                                    </div>
                                </a>
                                <a href="#" class="rb-item">
                                    <div class="rb-pic">
                                        <img src="${pageContext.request.contextPath}/img/blog/recent-3.jpg" alt="">
                                    </div>
                                    <div class="rb-text">
                                        <h6>Tại sao chúng ta phải cài Win cho MacBook?</h6>
                                        <p>Tin HOT <span>15/05/2023</span></p>
                                    </div>
                                </a>
                                <a href="#" class="rb-item">
                                    <div class="rb-pic">
                                        <img src="${pageContext.request.contextPath}/img/blog/recent-4.jpg" alt="">
                                    </div>
                                    <div class="rb-text">
                                        <h6>Tại sao chúng ta phải cài Win cho MacBook?</h6>
                                        <p>Tin HOT <span>15/05/2023</span></p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="blog-tags">
                            <h4>Tìm kiếm nhanh</h4>
                            <div class="tag-item">
                                <a href="#">Iphone</a>
                                <a href="#">Samsung</a>
                                <a href="#">Tai nghe</a>
                                <a href="#">Iphone 15</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 order-1 order-lg-2">
                    <div class="row">
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-1.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>Điều cần lưu ý trước khi cài Win cho MacBook.</h4>
                                    </a>
                                    <p>Tin công nghệ <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-2.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>Cách cài Win 10 cho MacBook thông qua Boot Camp.</h4>
                                    </a>
                                    <p>Tin HOT <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-3.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>VTV Giải Trí: Ứng dụng xem phim, TV Show trực tuyến.</h4>
                                    </a>
                                    <p>Tin Trong Nước <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-4.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>Những tính năng nổi bật của VTV Giải Trí.</h4>
                                    </a>
                                    <p>Tin HOT <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-5.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>Phát sóng các chương trình giải trí của Việt Nam và quốc tế</h4>
                                    </a>
                                    <p>Tin nổi bật <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="blog-item">
                                <div class="bi-pic">
                                    <img src="${pageContext.request.contextPath}/img/blog/blog-6.jpg" alt="">
                                </div>
                                <div class="bi-text">
                                    <a href="./blog-details.html">
                                        <h4>Cách tải VTV Giải Trí trên điện thoại Android</h4>
                                    </a>
                                    <p>Tin HOT <span>15/05/2023</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="loading-more">
                                <i class="icon_loading"></i>
                                <a href="#">
                                    Xem Thêm
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <!-- Partner Logo Section Begin -->
    <div class="partner-logo">
        <div class="container">
            <div class="logo-carousel owl-carousel">
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="${pageContext.request.contextPath}/img/logo-carousel/logo-1.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="${pageContext.request.contextPath}/img/logo-carousel/logo-2.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="${pageContext.request.contextPath}/img/logo-carousel/logo-3.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="${pageContext.request.contextPath}/img/logo-carousel/logo-4.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="${pageContext.request.contextPath}/img/logo-carousel/logo-5.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Partner Logo Section End -->
		
		<jsp:include page="footer.jsp" />
			<%@include file="/taglib/js.jsp" %>
</body>
</html>