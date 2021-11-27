<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 26/11/2021
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FashionWeb - Manage product</title>

    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.png"/>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
</head>

<body>
<div id="app">
    <!-- Slidebar -->
    <jsp:include page="_slidebar.jsp"/>
    <div id="main">
        <header class="mb-3">
            <a href="#" class="burger-btn d-block d-xl-none">
                <i class="bi bi-justify fs-3"></i>
            </a>
        </header>

        <div class="page-heading">
            <div class="page-title">
                <div class="row">
                    <div class="col-12 col-md-6 order-md-1 order-last">
                        <h3>Product management</h3>
                        <p class="text-subtitle text-muted">Quản lý sản phẩm</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="admin-home.html">Management</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Product</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <section class="section">
                <div class="card">
                    <section class="row">
                        <div class="col-12 col-lg-9">
                            <div class="card-header">
                                <h4 class="card-title">Product Datatable</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped" id="table1">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Sản phẩm</th>
                                        <th>Thông tin</th>
                                        <th>Giá (vnđ)</th>
                                        <th>Số lượng</th>
                                        <th>Quản lý</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.listSP}" var="sp">
                                        <tr>
                                            <td>${sp.maSP}</td>
                                            <td>
                                                <a href="#">
                                                    <img style="height: 150px; width: 130px;"
                                                         src="${sp.hinhSP}"
                                                         data-bs-target="#Gallerycarousel"
                                                         data-bs-slide-to="0">
                                                </a>
                                            </td>
                                            <td>
                                                <p>Tên: ${sp.tenSP}</p>
                                                <p>Loại: ${sp.getTenLoaiSP(requestScope.listLoaiSP)}</p>
                                                <p>Thương hiệu: ${sp.getTenThuongHieu(requestScope.listTH)}</p>
                                                <p>
                                                    Ngày đăng bán: <fmt:formatDate value="${sp.ngayDangBan}"
                                                                                   pattern="dd/MM/yyyy"/>
                                                </p>
                                            </td>
                                            <td>
                                                    ${sp.giaSP}
                                            </td>
                                            <td>
                                                    ${sp.soLuongSP}
                                            </td>
                                            <td>
                                                <a href="#" class="btn btn-primary"
                                                   onclick="
                                                           document.getElementById('_formTitle').textContent = 'EDIT PRODUCT: ID = ${sp.maSP}';
                                                           document.getElementById('_maSP').value = ${sp.maSP};
                                                           document.getElementById('_tenSP').value = '${sp.tenSP}';
                                                           document.getElementById('_maLoaiSP').value = ${sp.maLoaiSP};
                                                           document.getElementById('_maTH').value = ${sp.maTH};
                                                           document.getElementById('_hinhSP').value = '${sp.hinhSP}';
                                                           document.getElementById('_giaSP').value = ${sp.giaSP};
                                                           document.getElementById('_soLuongSP').value = ${sp.soLuongSP};
                                                           document.getElementById('_ngayBan').value = '${sp.ngayDangBan}';
                                                           document.getElementById('_btnReset').textContent = 'Cancel';
                                                           ">
                                                    Sửa
                                                </a>
                                                <a href="${pageContext.request.contextPath}/deleteProduct?maSP=${sp.maSP}"
                                                   class="btn btn-danger">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-12 col-lg-3">

                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title" id="_formTitle">CREATE NEW PRODUCT</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-vertical" action="manage-product" method="post">
                                            <div class="form-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="first-name-vertical">ID</label>
                                                            <input type="text" id="_maSP" class="form-control"
                                                                   name="_maSP" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="first-name-vertical">Tên</label>
                                                            <input type="text" id="_tenSP" class="form-control"
                                                                   name="_tenSP" placeholder="Tên">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="email-id-vertical">Loại</label>
                                                            <select class="form-select" id="_maLoaiSP" name="_maLoaiSP">
                                                                <c:forEach items="${requestScope.listLoaiSP}" var="lsp">
                                                                    <option value="${lsp.maLoaiSP}">
                                                                            ${lsp.tenLoaiSP}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="email-id-vertical">Thương hiệu</label>
                                                            <select class="form-select" id="_maTH" name="_maTH">
                                                                <c:forEach items="${requestScope.listTH}" var="th">
                                                                    <option value="${th.maTH}">
                                                                            ${th.tenTH}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Hình (URL)</label>
                                                            <!-- <input class="form-control" type="file" id="formFile"> -->
                                                            <input type="text" id="_hinhSP" class="form-control"
                                                                   name="_hinhSP" placeholder="URL">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Giá</label>
                                                            <div class="input-group mb-3">
                                                                <input type="number" id="_giaSP" class="form-control"
                                                                       name="_giaSP" placeholder="Giá">
                                                                <span class="input-group-text"
                                                                      id="basic-addon2">vnđ</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Số lượng</label>
                                                            <input type="number" id="_soLuongSP" class="form-control"
                                                                   name="_soLuongSP" placeholder="Số lượng">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Ngày bán</label>
                                                            <input type="date" id="_ngayBan" class="form-control"
                                                                   name="_ngayBan">
                                                        </div>
                                                    </div>
                                                    <div class="col-12 d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1">
                                                            Submit
                                                        </button>
                                                        <button type="reset" class="btn btn-light-secondary me-1 mb-1" id="_btnReset"
                                                                onclick="
                                                                        document.getElementById('_formTitle').textContent = 'CREATE NEW USER';
                                                                        document.getElementById('_btnReset').textContent = 'Reset';
                                                                ">
                                                            Reset
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

            </section>
        </div>

        <footer>
            <div class="footer clearfix mb-0 text-muted">
                <div class="float-start">
                    <p>2021 &copy; Mazer</p>
                </div>
                <div class="float-end">
                    <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a
                            href="http://ahmadsaugi.com">A. Saugi</a></p>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
<script>
    // Simple Datatable
    let table1 = document.querySelector('#table1');
    let dataTable = new simpleDatatables.DataTable(table1);
</script>

<script src="assets/js/mazer.js"></script>
</body>

</html>
