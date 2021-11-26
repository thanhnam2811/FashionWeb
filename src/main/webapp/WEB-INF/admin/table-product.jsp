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


    <script>
        function editProduct(maSP) {
            var _idSP = document.getElementById('_idSP');
            _idSP.value = document.getElementById('idSP-' + maSP).textContent.trim();
            var _ten = document.getElementById('_tenSP');
            _ten.value = document.getElementById('tenSP-' + maSP).textContent.trim().replace('Tên: ', '');
            var _loaiSP = document.getElementById('_loaiSP');
            _loaiSP.value = document.getElementById('loaiSP-' + maSP).textContent.trim().replace('Loại: ', '');
            var _maTH = document.getElementById('_maTH');
            _maTH.value = document.getElementById('maTH-' + maSP).textContent.trim().replace('Thương hiệu: ', '');
            var _hinhSP = document.getElementById('_hinhSP');
            _hinhSP.value = document.getElementById('hinhSP-' + maSP).src.trim();
            var _giaSP = document.getElementById('_giaSP');
            _giaSP.value = document.getElementById('giaSP-' + maSP).textContent.trim();
            var _soLuongSP = document.getElementById('_soLuongSP');
            _soLuongSP.value = document.getElementById('soLuongSP-' + maSP).textContent.trim();

            var _dateString = document.getElementById('ngayBan-' + maSP).textContent.trim().replace('Ngày bán: ', '').split('/');
            var _ngayBan = document.getElementById('_ngayBan');
            _ngayBan.value = _dateString[2] + '-' + _dateString[1] + '-' + _dateString[0]; // yyyy-mm-dd
        }
    </script>
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
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Quản lý</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td id='idSP-1'>1</td>
                                        <td>
                                            <a href="#">
                                                <img style="height: 150px; width: 130px;" id="hinhSP-1"
                                                     src="https://images.unsplash.com/photo-1633008808000-ce86bff6c1ed?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyN3x8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                     data-bs-target="#Gallerycarousel"
                                                     data-bs-slide-to="0">
                                            </a>
                                        </td>
                                        <td>
                                            <p id='tenSP-1'>Tên: Nike Air</p>
                                            <p id='loaiSP-1'>Loại: Quần</p>
                                            <p id='maTH-1'>Thương hiệu: Nike</p>
                                            <p id='ngayBan-1'>Ngày bán: 26/11/2021</p>
                                        </td>
                                        <td id='giaSP-1'>
                                            1000000
                                        </td>
                                        <td id='soLuongSP-1'>
                                            5
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-primary" onclick="editProduct(1)">Sửa</a>
                                            <a href="#" class="btn btn-danger">Xóa</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-12 col-lg-3">

                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title" id="_formTitle">Create & Edit Form</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form form-vertical">
                                            <div class="form-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="first-name-vertical">ID</label>
                                                            <input type="text" id="_idSP" class="form-control"
                                                                   name="_idSP" disabled>
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
                                                            <select class="form-select" id="_loaiSP">
                                                                <option>Quần</option>
                                                                <option>Áo</option>
                                                                <option>Giày</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="email-id-vertical">Thương hiệu</label>
                                                            <select class="form-select" id="_maTH">
                                                                <option>Adidas</option>
                                                                <option>Nike</option>
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
                                                        <button type="submit" class="btn btn-primary me-1 mb-1">Submit
                                                        </button>
                                                        <button type="reset" class="btn btn-light-secondary me-1 mb-1">
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
