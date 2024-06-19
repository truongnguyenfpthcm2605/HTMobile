<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/taglib/tablig.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản Phẩm</title>
    <%@include file="/taglib/bstrap4.3.jsp" %>
    <%@include file="/taglib/admincss.jsp" %>
</head>
<body>
<div class="containers">
    <jsp:include page="menu.jsp"/>
    <!-- ========================= Main ==================== -->
    <div class="main">
        <h2 style="margin: 20px; font-weight: 600;">Sản Phẩm</h2>
        <div class="container">
            <div class="topbar">
                <div class="toggle">
                    <i style="font-size: 1.75rem" class='bx bx-list-ul'></i>
                </div>
            </div>

            <form action="/admin/product/update" class="row"
                  style="margin-top: 10px;" enctype="multipart/form-data"
                  method="post">
                <div class="col-md-12" style="margin-bottom: 15px">
                    <c:choose>
                        <c:when test="${not empty p }">
                            <img id="previewImage"
                                 src="${pageContext.request.contextPath}/img/products/${sessionScope.p.imgproduct}"
                                 alt=""
                                 style="width: 200px; height: 200px; border-radius: 10px;">
                            <input type="hidden" name="id" value="${p.id }">
                        </c:when>
                        <c:otherwise>
                            <img id="previewImage"
                                 src="${pageContext.request.contextPath}/img/anhmacdinh.jpg"
                                 alt=""
                                 style="width: 200px; height: 200px; border-radius: 10px;">
                        </c:otherwise>
                    </c:choose>
                    <br> <input id="imageInput" type="file" class="form-control" style="width: 30%"
                                accept="image/jpg, image/png" name="imgproduct"
                                onchange="previewFile(event)">
                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label>Tên sản phẩm *</label> <input type="text"
                                                             class="form-control" value="${p.title }" name="title"
                                                             required="required"/>
                    </div>
                    <div class="form-group">
                        <label>Giá gốc *</label> <input type="number" min="1000000"
                                                        class="form-control" value="${p.pricecost }" name="pricecost"
                                                        required="required"/>

                    </div>
                    <div class="form-group">
                        <label>Giá *</label> <input type="number" min="1000000"
                                                    class="form-control" value="${p.price }" name="price"
                                                    required="required"/>

                    </div>
                    <div class="form-group">
                        <label>Số lượng *</label> <input type="number" min="1"
                                                         class="form-control" value="${p.quanityfinal }"
                                                         name="quanityfinal" required="required"/>

                    </div>
                    <div class="form-group">
                        <label>CPU *</label> <input type="text" class="form-control"
                                                    value="${p.chip }" name="chip" required="required"/>

                    </div>
                    <div class="form-group">
                        <label>Mô tả *</label>
                        <textarea id="" cols="30" rows="5" class="form-control"
                                  name="description" required="required">${p.description }</textarea>

                    </div>


                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Loại Sản Phẩm *</label> <br> <select name="category"
                                                                    class="form-control">
                        <c:forEach items="${listCategory}" var="cate">
                            <option value="${cate.id}"  ${p.category.id==cate.id?'selected':''} >${cate.title }</option>
                        </c:forEach>
                    </select>
                    </div>

                    <div class="form-group">
                        <label>Ram *</label> <br> <select name="ram"
                                                          class="form-control">
                        <c:forEach items="${listRam}" var="ram">
                            <option value="${ram}" ${p.ram == ram? 'selected':'' }>${ram}GB</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="form-group">
                        <label>Memory *</label> <br> <select name="rom"
                                                             class="form-control">
                        <c:forEach items="${listRom}" var="rom">
                            <option value="${rom}  ${p.rom==rom ?'selected':''}">${rom}GB</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="form-group">
                        <label>Pin *</label> <input type="number" min="1000" max="10000"
                                                    class="form-control" value="${p.pin }" name="pin"
                                                    required="required"/>

                    </div>
                    <div class="form-group">
                        <label>Camera *</label> <input type="number" step="any" min="10"
                                                       max="200" class="form-control" value="${p.camera }"
                                                       name="camera" required="required"/>

                    </div>
                    <div class="form-group">
                        <label>Screen *</label> <input type="number" step="any" min="1"
                                                       max="100" class="form-control" value="${p.screen }"
                                                       name="screen" required="required"/>

                    </div>

                    <div class="form-group">
                        <label>Hình ảnh mô tả *</label> <input type="file" required
                                                               class="form-control" multiple name="thumnail">
                    </div>


                    <div class="form-group">
                        <a class=" btn btn-light" href="admin/product/"
                                style="width: 40%; margin: 10px 10px; border-radius: 5px;">
                            Trở Lại
                        </a>
                        <button type="submit" class=" btn btn-dark"
                               style="width: 40%; margin: 10px 10px; border-radius: 5px;">Lưu</button>
                    </div>

                </div>
            </form>

            <p style="color: red;">${messageFile }</p>


        </div>
    </div>
</div>
<%@include file="/taglib/adminjs.jsp" %>
<script>
    function previewFile(event) {
        let input = event.target;
        let reader = new FileReader();

        reader.onload = function () {
            let image = document.getElementById('previewImage');
            image.src = reader.result;
        };

        if (input.files && input.files[0]) {
            reader.readAsDataURL(input.files[0]);
        } else {
            let image = document.getElementById('previewImage');
            image.src = "/img/anhmacdinh.jpg";
        }
    }
</script>
</body>
</html>