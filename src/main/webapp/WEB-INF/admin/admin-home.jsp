<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 26/11/2021
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FashionWeb - Admin home</title>

    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.png"/>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/bootstrap.css">

    <link rel="stylesheet" href="assets/vendors/iconly/bold.css">

    <link rel="stylesheet" href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="assets/vendors/fontawesome/all.min.css">
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
            <h3>Dashboard</h3>
        </div>
        <div class="page-content">
            <section class="row">
                <div class="col-12 col-lg-9">
                    <div class="row">
                        <div class="col-6 col-lg-3 col-md-6">
                            <div class="card">
                                <div class="card-body px-3 py-4-5">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="stats-icon purple">
                                                <i class="iconly-boldShow"></i>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <h6 class="text-muted font-semibold">Tổng số sản phẩm</h6>
                                            <h6 class="font-extrabold mb-0">${soLuongSP}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6 col-lg-3 col-md-6">
                            <div class="card">
                                <div class="card-body px-3 py-4-5">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="stats-icon blue">
                                                <i class="iconly-boldProfile"></i>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <h6 class="text-muted font-semibold">Tổng số thương hiệu</h6>
                                            <h6 class="font-extrabold mb-0">${soLuongTH}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6 col-lg-3 col-md-6">
                            <div class="card">
                                <div class="card-body px-3 py-4-5">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="stats-icon green">
                                                <i class="iconly-boldAdd-User"></i>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <h6 class="text-muted font-semibold">Tổng số tài khoản</h6>
                                            <h6 class="font-extrabold mb-0">${soLuongUser}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6 col-lg-3 col-md-6">
                            <div class="card">
                                <div class="card-body px-3 py-4-5">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="stats-icon red">
                                                <i class="iconly-boldBookmark"></i>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <h6 class="text-muted font-semibold">Đơn hàng cao nhất</h6>
                                            <h6 class="font-extrabold mb-0">
                                                <fmt:formatNumber type="number"
                                                                  maxFractionDigits="0" value="${donHangMaxCost} "/>
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Statistic</h4>
                                </div>
                                <div class="card-body">
                                    <div id="chart-profile-visit"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-xl-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Bình luận hôm nay</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-lg">
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Comment</th>
                                                <th>Product</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.listBinhLuanHomNay}" var="o">
                                            <tr>
                                                <td class="col-3">
                                                    <div class="d-flex align-items-center">
                                                        <div class="avatar avatar-md">
                                                            <img src="assets/images/faces/5.jpg">
                                                        </div>
                                                        <p class="font-bold ms-3 mb-0">${o.getHoTenKH(listAllUser)}</p>
                                                    </div>
                                                </td>
                                                <td class="col-auto">
                                                    <p class=" mb-0">${o.noiDung}</p>
                                                </td>
                                                <td>
                                                    <a href="#">
                                                        <img style="height: 100px; width: 100px;" id="hinhSP-1" src="${o.getAnhSP(listSanPham)}" data-bs-target="#Gallerycarousel"
                                                             data-bs-slide-to="0">

                                                    </a>
                                                </td>
                                            </tr>
                                            </c:forEach>

<%--                                            <tr>--%>
<%--                                                <td class="col-3">--%>
<%--                                                    <div class="d-flex align-items-center">--%>
<%--                                                        <div class="avatar avatar-md">--%>
<%--                                                            <img src="assets/images/faces/2.jpg">--%>
<%--                                                        </div>--%>
<%--                                                        <p class="font-bold ms-3 mb-0">Si Ganteng</p>--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
<%--                                                <td class="col-auto">--%>
<%--                                                    <p class=" mb-0">Wow amazing design! Can you make another tutorial for this design?</p>--%>
<%--                                                </td>--%>
<%--                                                <td>--%>
<%--                                                    <a href="#">--%>
<%--                                                        <img style="height: 100px; width: 100px;" id="hinhSP-1" src="https://images.unsplash.com/photo-1633008808000-ce86bff6c1ed?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyN3x8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60" data-bs-target="#Gallerycarousel"--%>
<%--                                                             data-bs-slide-to="0">--%>
<%--                                                    </a>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-3">
                    <div class="card">
                        <div class="card-body py-4 px-5">
                            <div class="d-flex align-items-center">
                                <div class="avatar avatar-xl">
                                    <img src="assets/images/faces/1.jpg" alt="Face 1">
                                </div>
                                <div class="ms-3 name">
                                    <h5 class="font-bold">${sessionScope.loginedUser.hoTen}</h5>
                                    <h6 class="text-muted mb-0">@${sessionScope.loginedUser.userName}</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <h4>Danh sách nhân viên</h4>
                        </div>
                        <div class="card-content pb-4">
                            <c:forEach items="${requestScope.listUser}" var="o">
                            <div class="recent-message d-flex px-4 py-3">
                                <div class="avatar avatar-lg">
                                    <img src="assets/images/faces/4.jpg">
                                </div>
                                <div class="name ms-4">
                                    <h5 class="mb-1">${o.hoTen}</h5>
                                    <h6 class="text-muted mb-0">@${o.userName}</h6>
                                </div>
                            </div>
                            </c:forEach>
<%--                            <div class="recent-message d-flex px-4 py-3">--%>
<%--                                <div class="avatar avatar-lg">--%>
<%--                                    <img src="assets/images/faces/5.jpg">--%>
<%--                                </div>--%>
<%--                                <div class="name ms-4">--%>
<%--                                    <h5 class="mb-1">Dean Winchester</h5>--%>
<%--                                    <h6 class="text-muted mb-0">@imdean</h6>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="recent-message d-flex px-4 py-3">--%>
<%--                                <div class="avatar avatar-lg">--%>
<%--                                    <img src="assets/images/faces/1.jpg">--%>
<%--                                </div>--%>
<%--                                <div class="name ms-4">--%>
<%--                                    <h5 class="mb-1">John Dodol</h5>--%>
<%--                                    <h6 class="text-muted mb-0">@dodoljohn</h6>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <h4>Biểu đồ loại sản phẩm</h4>
                        </div>
                        <div class="card-body">
                            <div id="chart-visitors-profile"></div>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <footer>
            <div class="footer clearfix mb-0 text-muted">
                <div class="float-start">
                    <p>Copyright &copy;<script>document.write(new Date().getFullYear());</script>Mazer <span class="text-danger"><i class="bi bi-heart"></i></span> by <a href="http://ahmadsaugi.com">A. Saugi</a></p>

                </div>
                <div class="float-end">
                    <p>Redesign <span class="text-danger"><i class="bi bi-heart"></i></span> by Tấn & Nam & Trung & Toàn</p>
                </div>
            </div>
        </footer>
    </div>
</div>

<script src="assets/vendors/apexcharts/apexcharts.js"></script>
<script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>


<script type="text/javascript">
    var optionsProfileVisit = {
        annotations: {
            position: 'back'
        },
        dataLabels: {
            enabled:false
        },
        chart: {
            type: 'bar',
            height: 300
        },
        fill: {
            opacity:1
        },
        plotOptions: {
        },
        series: [{
            name: 'sales',
            data: [
                <c:forEach items="${requestScope.doanhThu}" var="o"> ${o.tongTien},</c:forEach>
            ]
        }],
        colors: '#435ebe',
        xaxis: {
            categories: [
                <c:forEach items="${requestScope.doanhThu}" var="o"> getMonthString(${o.thang}),</c:forEach>

            ],
        },
    }
    function getMonthString(thang){
       var month =["Jan","Feb","Mar","Apr","May","Jun","Jul", "Aug","Sep","Oct","Nov","Dec"];

       return month[thang - 1];

    }
    let optionsVisitorsProfile  = {
        series: [
            <c:forEach items="${requestScope.listSoLuongSPByLoaiSP}" var="o"> ${o.soLuongSP}/ ${tongSP},</c:forEach>
        ],
        labels: ['Quần', 'Áo','Giày'],
        colors: ['#435ebe','#55c6e8','#FF33FF'],
        chart: {
            type: 'donut',
            width: '100%',
            height:'350px'
        },
        legend: {
            position: 'bottom'
        },
        plotOptions: {
            pie: {
                donut: {
                    size: '30%'
                }
            }
        }
    }

    var optionsEurope = {
        series: [{
            name: 'series1',
            data: [310, 800, 600, 430, 540, 340, 605, 805,430, 540, 340, 605]
        }],
        chart: {
            height: 80,
            type: 'area',
            toolbar: {
                show:false,
            },
        },
        colors: ['#5350e9'],
        stroke: {
            width: 2,
        },
        grid: {
            show:false,
        },
        dataLabels: {
            enabled: false
        },
        xaxis: {
            type: 'datetime',
            categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z","2018-09-19T07:30:00.000Z","2018-09-19T08:30:00.000Z","2018-09-19T09:30:00.000Z","2018-09-19T10:30:00.000Z","2018-09-19T11:30:00.000Z"],
            axisBorder: {
                show:false
            },
            axisTicks: {
                show:false
            },
            labels: {
                show:false,
            }
        },
        show:false,
        yaxis: {
            labels: {
                show:false,
            },
        },
        tooltip: {
            x: {
                format: 'dd/MM/yy HH:mm'
            },
        },
    };

    let optionsAmerica = {
        ...optionsEurope,
        colors: ['#008b75'],
    }
    let optionsIndonesia = {
        ...optionsEurope,
        colors: ['#dc3545'],
    }



    var chartProfileVisit = new ApexCharts(document.querySelector("#chart-profile-visit"), optionsProfileVisit);
    var chartVisitorsProfile = new ApexCharts(document.getElementById('chart-visitors-profile'), optionsVisitorsProfile)
    var chartEurope = new ApexCharts(document.querySelector("#chart-europe"), optionsEurope);
    var chartAmerica = new ApexCharts(document.querySelector("#chart-america"), optionsAmerica);
    var chartIndonesia = new ApexCharts(document.querySelector("#chart-indonesia"), optionsIndonesia);

    chartIndonesia.render();
    chartAmerica.render();
    chartEurope.render();
    chartProfileVisit.render();
    chartVisitorsProfile.render()
</script>
<%--<script src="assets/js/pages/dashboard.js"></script>--%>
<script src="assets/js/mazer.js"></script>

</body>

</html>
