<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 26/11/2021
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FashionWeb - Manage Brand</title>

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
        function editBrand(maTH) {
            var _maTH = document.getElementById('_maTH');
            _maTH.value = document.getElementById('maTH-' + maTH).textContent.trim();
            var _tenTH = document.getElementById('_tenTH');
            _tenTH.value = document.getElementById('tenTH-' + maTH).textContent.trim().replace('Tên: ', '');
            var _logoTH = document.getElementById('_logoTH');
            _logoTH.value = document.getElementById('logoTH-' + maTH).src.trim();
            var _emailTH = document.getElementById('_emailTH');
            _emailTH.value = document.getElementById('emailTH-' + maTH).textContent.trim().replace('Email: ', '');
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
                        <h3>Brand management</h3>
                        <p class="text-subtitle text-muted">Quản lý thương hiệu</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="admin-home.html">Management</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Brand</li>
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
                                <h4 class="card-title">Brand Datatable</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped" id="table1">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Logo</th>
                                        <th>Thông tin</th>
                                        <th>Quản lý</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td id='maTH-1'>
                                            1
                                        </td>
                                        <td>
                                            <a href="#">
                                                <img style="height: 150px; width: 130px;" id="logoTH-1" src="https://images.unsplash.com/photo-1633008808000-ce86bff6c1ed?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyN3x8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" data-bs-target="#Gallerycarousel"
                                                     data-bs-slide-to="0">
                                            </a>
                                        </td>
                                        <td>
                                            <p id='tenTH-1'>Tên: Adidas</p>
                                            <p id='emailTH-1'>Email: Adidas@gmail.com</p>
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-primary" onclick="editBrand(1)">Sửa</a>
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
                                                            <input type="text" id="_maTH" class="form-control" name="_maTH" disabled>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="first-name-vertical">Tên</label>
                                                            <input type="text" id="_tenTH" class="form-control" name="_tenTH" placeholder="Tên">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Logo (URL)</label>
                                                            <!-- <input class="form-control" type="file" id="_logoTH"> -->
                                                            <input type="text" id="_logoTH" class="form-control" name="_logoTH" placeholder="URL">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <label for="contact-info-vertical">Email</label>
                                                            <div class="input-group mb-3">
                                                                <input type="text" id="_emailTH" class="form-control" name="_emailTH" placeholder="brand@gmail.com">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12 d-flex justify-content-end">
                                                        <button type="submit" class="btn btn-primary me-1 mb-1">Submit</button>
                                                        <button type="reset" class="btn btn-light-secondary me-1 mb-1">Reset</button>
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
