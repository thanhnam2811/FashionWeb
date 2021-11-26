<%@ page import="beans.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <title>User Management</title>
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
    <!--===============================================================================================-->
</head>

<body class="animsition">

<!-- Header Admin -->
<jsp:include page="_header.jsp"/>


<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="index.jsp" class="stext-109 cl8 hov-cl1 trans-04">
            Admin
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4">
				User management
			</span>
    </div>
</div>

<!-- Product -->
<section class="bg0 p-t-23 p-b-140">
    <div class="container">
        <div class="p-b-10">
            <h3 class="ltext-103 cl5">
                User Management
            </h3>
        </div>

        <div class="flex-w flex-sb-m p-b-52">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter=".listUser">
                    Quản lý tài khoản
                </button>

                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".createUser">
                    Thêm tài khoản
                </button>
            </div>
        </div>
    </div>

    <div class="row isotope-grid m-lr-auto p-l-100">
        <div class=" isotope-item listUser p-r-50 p-b-30">
            <!-- Block2 -->
            <div class="block2">
                <div class="m-lr-auto m-lr-0-xl wrap-table-user-management">
                    <table class="table-product-management">           <!-- Lỗi class -->
                        <!--SanPham(maSP, tenSP, maLoaiSP, maTH, hinhSP, giaSP, ngayDangBan)-->
                        <!--Users(maKH, hoTen,  sdt, ngaySinh,  diaChi,  userName,  password, roleID)-->
                        <tr class="table_head">
                            <th class="column-1">Họ tên</th>
                            <th class="column-2">Số điện thoại</th>
                            <th class="column-3">NgàySinh</th>
                            <th class="column-4">Địa chỉ</th>
                            <th class="column-5">Username</th>
                            <th class="column-6">Quyền</th>
                            <th class="column-7">Sửa</th>
                            <th class="column-8">Xóa</th>
                        </tr>
                        <c:forEach items="${listUser}" var="o">
                            <tr class="table_row">
                                <td class="column-1">${o.hoTen}</td>
                                <td class="column-2">${o.sdt}</td>
                                <td class="column-3">${o.ngaySinh}</td>
                                <td class="column-4">${o.diaChi}</td>
                                <td class="column-5">${o.userName}</td>
                                <td class="column-6">${o.roleID}</td>
                                <td class="column-7">
                                    <a <c:if test="${o.roleID == 1}">style="display: none" </c:if> href="${pageContext.request.contextPath}/editUser?maKH=${o.maKH}">
                                        <div class="flex-c-m stext-101 cl2 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                            Sửa
                                        </div>
                                    </a>
                                </td>
                                <td class="column-8">
                                    <a href="${pageContext.request.contextPath}/deleteUser?maKH=${o.maKH}">
                                        <div class="flex-c-m stext-101 cl2 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                            Xóa
                                        </div>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <div class=" isotope-item listUser createUser ">
            <!-- Block2 -->
            <div class="block2">
                <!-- Create new product -->
                <div class="m-lr-auto bor10 p-lr-40 p-t-30 p-b-40 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <form action="UserManagement" method="post">
                        <h4 class="mtext-109 cl2 p-b-30">
                            Create User
                        </h4>
                        <div class="flex-w flex-t p-tb-34">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Họ tên:
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="newUserName"
                                           placeholder="Họ tên">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110">
									Số điện thoại:
								</span>
                            </div>
                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="numberPhone"
                                           placeholder="Số điện thoại">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span style="display: block" class="stext-110 cl2">
									Ngày sinh:
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 size-101">
                                    <input style="display: block" class="stext-111 cl2 plh3 size-111 p-lr-15" type="date"
                                           name="dateOfBirth">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Địa chỉ:
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="address"
                                           placeholder="ex: Bình Định">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Username
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="username"
                                           placeholder="Username">
                                </div>
                            </div>
                        </div>
                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Password
								</span>
                            </div>

                            <div class="size-101">
                                <div class="bor8 bg0 m-b-12">
                                    <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="password" name="password"
                                           placeholder="Password">
                                </div>
                            </div>
                        </div>

                        <div class="flex-w flex-t p-t-1 p-b-33">
                            <div class="size-208">
								<span class="stext-110 cl2">
									Quyền:
								</span>
                            </div>

                            <select class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9 size-101" name="roleID">--%>
                                    <c:forEach items="${listRole}" var="lsR">
                                       <option value="${lsR.roleID}">${lsR.name}</option>
                                     </c:forEach>
                            </select>
                        </div>

                        <button type="submit"
                                class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                            Create user
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

<!-- Modal1 -->
<div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
    <div class="overlay-modal1 js-hide-modal1"></div>

    <div class="container">
        <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
            <button class="how-pos3 hov3 trans-04 js-hide-modal1">
                <img src="images/icons/icon-close.png" alt="CLOSE">
            </button>
            <!-- Edit product -->
            <div class="m-lr-auto bor10 p-lr-40 p-t-30 p-b-40 m-r-40 m-lr-0-xl p-lr-15-sm">
                <form action="editProduct">
                    <h4 class="mtext-109 cl2 p-b-30">
                        Edit User
                    </h4>
                    <div class="flex-w flex-t p-tb-34">
                        <div class="size-208">
								<span class="stext-110 cl2">
									Họ tên:
								</span>
                        </div>

                        <div class="size-101">
                            <div class="bor8 bg0 size-101">
                                <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="userName"
                                       placeholder="Họ tên">
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-1 p-b-33">
                        <div class="size-208">
								<span class="stext-110">
									Số điện thoại:
								</span>
                        </div>
                        <div class="size-101">
                            <div class="bor8 bg0 size-101">
                                <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="numberPhone"
                                       placeholder="Số điện thoại">
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-1 p-b-33">
                        <div class="size-208">
								<span class="stext-110">
									Ngày sinh:
								</span>
                        </div>

                        <div class="size-101">
                            <div class="bor8 bg0 size-101">
                                <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="date"
                                       name="dateOfBirth">
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-1 p-b-33">
                        <div class="size-208">
								<span class="stext-110 cl2">
									Địa chỉ:
								</span>
                        </div>

                        <div class="size-101">
                            <div class="bor8 bg0 m-b-12">
                                <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="address"
                                       placeholder="ex: Bình Định">
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-1 p-b-33">
                        <div class="size-208">
								<span class="stext-110 ">
									Username
								</span>
                        </div>

                        <div class="size-101">
                            <div class="bor8 bg0 m-b-12">
                                <input class="stext-111 cl2 plh3 size-111 p-lr-15" type="text" name="username"
                                       placeholder="Username">
                            </div>
                        </div>
                    </div>

                    <div class="flex-w flex-t p-t-1 p-b-33">
                        <div class="size-208">
								<span class="stext-110">
									Quyền:
								</span>
                        </div>

                        <select class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9 size-101" name="roleType">--%>
                            <c:forEach items="${listRole}" var="lsR">
                                <option value="${lsR.roleID}">${lsR.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit"
                            class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                        Submit
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

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
</body>

</html>