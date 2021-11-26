<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 26/11/2021
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FashionWeb - Manage staff and admin</title>

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
        function editInfo(maKH) {
            var _username = document.getElementById('_username');
            _username.value = document.getElementById('username-' + maKH).textContent.trim();
            document.getElementById('_username').disabled = true;

            var _hoTen = document.getElementById('_hoTen');
            _hoTen.value = document.getElementById('hoTen-' + maKH).textContent.trim().replace('Tên: ', '');

            var _sdt = document.getElementById('_sdt');
            _sdt.value = document.getElementById('sdt-' + maKH).textContent.trim().replace('Sđt: ', '');

            var _diaChi = document.getElementById('_diaChi');
            _diaChi.value = document.getElementById('diaChi-' + maKH).textContent.trim().replace('Địa chỉ: ', '');

            var _dateString = document.getElementById('ngaySinh-' + maKH).textContent.trim().replace('Ngày sinh: ', '').split('/');
            var _ngaySinh = document.getElementById('_ngaySinh');
            _ngaySinh.value = _dateString[2] + '-' + _dateString[1] + '-' + _dateString[0]; // yyyy-mm-dd
        }

    </script>
</head>

<body>
<div id="app">

    <!-- Slide bar -->
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
                        <h3>Staff & Admin management</h3>
                        <p class="text-subtitle text-muted">Quản lý nhân viên và admin</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="admin-home.html">Management</a></li>
                                <li class="breadcrumb-item" aria-current="page">Account</li>
                                <li class="breadcrumb-item active" aria-current="page">Staff & Admin</li>
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
                                <h4 class="card-title">Staff & Admin Datatable</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped" id="table1">
                                    <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Thông tin</th>
                                        <th>Quyền</th>
                                        <th>Quản lý</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td id='username-1'>
                                            nam
                                        </td>
                                        <td>
                                            <p id='hoTen-1'>Tên: Thái Thành Nam</p>
                                            <p id='sdt-1'>Sđt: 0981771024</p>
                                            <p id='ngaySinh-1'>Ngày sinh: 28/11/2001</p>
                                            <p id='diaChi-1'>Địa chỉ: Bến Tre</p>
                                        </td>
                                        <td id='roleID-1'>
                                            Nhân viên
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-primary" onclick="editInfo(1)">Sửa</a>
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
                                                            <label for="first-name-vertical">Username</label>
                                                            <input type="text" id="_username" class="form-control" name="_username">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="first-name-vertical">Họ và tên</label>
                                                            <input type="text" id="_hoTen" class="form-control" name="_hoTen" placeholder="Họ và tên">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Số điện thoại</label>
                                                            <div class="input-group mb-3">
                                                                <input type="tel" id="_sdt" class="form-control" name="_sdt" pattern="[0-9]{10}" required placeholder="Số điện thoại 10 số">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Ngày sinh</label>
                                                            <div class="input-group mb-3">
                                                                <input type="date" id="_ngaySinh" class="form-control" name="_ngaySinh">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Địa chỉ</label>
                                                            <div class="input-group mb-3">
                                                                <textarea type="text" id="_diaChi" class="form-control" name="_diaChi" pattern="[0-9]{10}" required placeholder="Địa chỉ"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12 d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1">Submit</button>
                                                        <button type="reset" class="btn btn-light-secondary me-1 mb-1"  onclick="document.getElementById('_username').disabled = false;">
                                                            Reset</button>
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
                    <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a href="http://ahmadsaugi.com">A. Saugi</a></p>
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
