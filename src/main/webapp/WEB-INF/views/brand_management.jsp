<%@ page import="beans.SanPham" %>
<%@ page import="beans.ThuongHieu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Shoping Cart</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="js/main.js">
    <!--===============================================================================================-->
</head>

<body class="animsition">

<!-- Header Admin -->
<jsp:include page="_header.jsp"/>

<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="index.html" class="stext-109 cl8 hov-cl1 trans-04">
            Admin
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4">
				Brand management
			</span>
    </div>
</div>

<!-- brand -->
<section class="bg0 p-t-23 p-b-140">
    <div class="container">
        <div class="p-b-10">
            <h3 class="ltext-103 cl5">
                Brand Management
            </h3>
        </div>

        <div class="flex-w flex-sb-m p-b-52">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" onclick="popupClose();">
                    Quản lý thương hiệu
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"  onclick="popupOpen();">
                    Thêm thương hiệu
                </button>
            </div>
        </div>
    </div>

    <div class="row isotope-grid m-lr-auto p-l-100">
        <div class=" isotope-item listProduct p-r-50 p-b-30" id ="showlist">
            <!-- Block2 -->
            <div class="block2">
                <div class="m-lr-auto m-lr-0-xl wrap-table-brand-management">
                    <table class="table-brand-management">
                        <!--SanPham(maSP, tenSP, maLoaiSP, maTH, hinhSP, giaSP, ngayDangBan)-->
                        <tr class="table_head">
                            <th class="column-1">LoGo</th>
                            <th class="column-2" >Mã Thương Hiệu</th>
                            <th class="column-3">Tên</th>
                            <th class="column-4">Email</th>
                            <th class="column-7">Cập nhật </th>
                            <th class="column-8">Hủy Hợp tác</th>
                        </tr>
                        <c:forEach items="${listTH}" var="o">
                            <tr class="table_row">
                                <td class="column-1">
                                    <div class="how-itemcart1">
                                        <img src="${o.hinhTH}" alt="IMG">
                                    </div>
                                </td>
                                <td class="column-2">${o.maTH}</td>
                                <td class="column-3">${o.tenTH}</td>
                                <td class="column-4">${o.emailTH}</td>
                                <td class="column-7">
                                    <div class="flex-c-m stext-101 cl2 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                        <form action="${pageContext.request.contextPath}/callUpdateBrand?id=${o.maTH}" method="post">
                                            <button type="submit">Sửa</button>
                                        </form>
                                    </div>
                                </td>
                                <td class="column-8">
                                    <div class="flex-c-m stext-101 cl2 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                        <form action="${pageContext.request.contextPath}/brandControll?action=delete&delmaTH=${o.maTH}" method="post">
                                            <button type="submit">Xóa</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <div class=" isotope-item listProduct createProduct ">
            <!-- Block2 -->
            <div class="block2" id="create" style="display:none;">
                <!-- Create new product -->
                <div class="m-lr-auto bor10 p-lr-40 p-t-30 p-b-40 m-r-40 m-lr-0-xl p-lr-15-sm" >
                    <form action="${pageContext.request.contextPath}/brandControll?action=insert" method="post" >
                        <h4 class="mtext-109 cl2 p-b-30">
                            Create Brand
                        </h4>

                        <%--<div class="flex-w flex-t p-tb-34">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Mã Thương Hiệu
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="maTH"
                                           placeholder="mã thương hiệu" disabled>
                                </div>
                            </div>
                        </div>--%>

                        <div class="flex-w flex-t p-tb-34">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Tên Thương Hiệu
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="tenTH"
                                           placeholder="Tên thương hiệu">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Hình Thương Hiệu (URL):
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="hinhTH"
                                           placeholder="ex: image/Adidas/1.jpg">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-tb-34">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Email
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="emailTH">
                                </div>
                            </div>
                        </div>

                        <button type="submit" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                            Create product
                        </button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</section>

<!-- Footer -->
<jsp:include page="_footer.jsp"/>

<!-- Back to top -->
<jsp:include page="_backToTop.jsp"/>
<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function () {
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function () {
        $(this).css('position', 'relative');
        $(this).css('overflow', 'hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function () {
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="js/main.js"></script>
<!--===============================================================================================-->
<script src="vendor/isotope/isotope.pkgd.min.js"></script>
<script>
    function popupOpen() {

        document.getElementById("showlist").style.display = "none";
        document.getElementById("create").style.display = "block";
        document.getElementById("modify").style.display = "none";

    }

    // Popup Close

    function popupClose() {

        document.getElementById("showlist").style.display = "block";
        document.getElementById("create").style.display = "none";
        document.getElementById("modify").style.display = "none";

    }

</script>
</body>

</html>
