<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="header-v4">
    <!-- Header desktop -->
    <div class="container-menu-desktop">
        <!-- Topbar -->
        <div class="top-bar">
            <div class="content-topbar flex-sb-m h-full container">
                <div class="left-top-bar">
                    Giao hàng miễn phí cho đơn đặt hàng tiêu chuẩn trên 10 triệu VNĐ
                </div>

                <div class="right-top-bar flex-w h-full">
                    <c:if test="${sessionScope.loginedUser==null}">
                        <a href="${pageContext.request.contextPath}/signIn" class="flex-c-m trans-04 p-lr-25">
                            Đăng nhập
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.loginedUser!=null}">
                        <c:if test="${sessionScope.loginedUser.roleID==2|| sessionScope.loginedUser.roleID==3}">
                            <a href="${pageContext.request.contextPath}/admin" class="flex-c-m trans-04 p-lr-24">
                                Admin Home
                            </a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/userinfo" class="flex-c-m trans-04 p-lr-24">
                            My account
                        </a>
                        <a href="${pageContext.request.contextPath}/signOut" class="flex-c-m trans-04 p-lr-24">
                            Đăng xuất
                        </a>
                    </c:if>


                </div>

                <div class="wrap-menu-desktop how-shadow1">
                    <nav class="limiter-menu-desktop container">

                        <!-- Logo desktop -->
                        <a href="${pageContext.request.contextPath}/home" class="logo">
                            <img src="images/icons/Logo-NTTT.png" alt="IMG-LOGO">
                        </a>

                        <!-- Menu desktop for user -->
<%--                        <c:if test="${sessionScope.loginedUser.roleID==1 || sessionScope.loginedUser.roleID==null}">--%>
                            <div class="menu-desktop">
                                <ul class="main-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/home">Home</a>
                                    </li>

                                    <li>
                                        <a href="${pageContext.request.contextPath}/product">Shop</a>
                                    </li>

                                    <li>
                                        <a href="${pageContext.request.contextPath}/about">About</a>
                                    </li>

                                    <li>
                                        <a href="${pageContext.request.contextPath}/contact">Contact</a>
                                    </li>

                                </ul>
                            </div>
<%--                        </c:if>--%>

<%--                        <!-- Menu desktop for staff -->--%>
<%--                        <c:if test="${sessionScope.loginedUser.roleID==2}">--%>
<%--                            <div class="menu-desktop">--%>
<%--                                <ul class="main-menu">--%>
<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/home">Home</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/product">Shop</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/productManagement">Product Manager</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/Statistic">Statistic</a>--%>
<%--                                    </li>--%>

<%--                                </ul>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
<%--                        <!-- Menu desktop for admin -->--%>
<%--                        <c:if test="${sessionScope.loginedUser.roleID==3}">--%>
<%--                            <div class="menu-desktop">--%>
<%--                                <ul class="main-menu">--%>
<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/home">Home</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/product">Shop</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/productManagement">Product Manager</a>--%>
<%--                                    </li>--%>

<%--                                    <li >--%>
<%--                                        <a href="${pageContext.request.contextPath}/brandManagement">Brand Manager</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/UserManagement">User Manager</a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a href="${pageContext.request.contextPath}/Statistic">Statistic</a>--%>
<%--                                    </li>--%>

<%--                                </ul>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>


                        <!-- Icon header -->
                        <c:if test="${sessionScope.loginedUser.roleID==1}">
                            <div class="wrap-icon-header flex-w flex-r-m">

                                <c:if test="${sessionScope.loginedUser!=null}">
                                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart"
                                         data-notify="${listChiTietGioHang.size()}">
                                        <i class="zmdi zmdi-shopping-cart"></i>
                                    </div>

                                    <a href="#" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
                                       data-notify="0">
                                        <i class="zmdi zmdi-favorite-outline"></i>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>
                    </nav>
                </div>
            </div>






            <!-- Modal Search -->
            <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
                <div class="container-search-header">
                    <button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
                        <img src="images/icons/icon-close2.png" alt="CLOSE">
                    </button>

                    <form class="wrap-search-header flex-w p-l-15">
                        <button class="flex-c-m trans-04">
                            <i class="zmdi zmdi-search"></i>
                        </button>
                        <input class="plh3" type="text" name="search" placeholder="Search...">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Header Mobile -->
    <div class="wrap-header-mobile">
        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="${pageContext.request.contextPath}/home"><img src="images/icons/Logo-NTTT.png" alt="IMG-LOGO"></a>
        </div>

        <!-- Icon header -->
        <c:if test="${sessionScope.loginedUser.roleID==1}">
        <div class="wrap-icon-header flex-w flex-r-m m-r-15">
            <c:if test="${sessionScope.loginedUser!=null}">
            <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart"
                 data-notify="${listChiTietGioHang.size()}">
                <i class="zmdi zmdi-shopping-cart"></i>
            </div>

            <a href="#" class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
               data-notify="0">
                <i class="zmdi zmdi-favorite-outline"></i>
            </a>
            </c:if>
        </div>
        </c:if>

        <!-- Button show menu -->
        <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
                        <span class="hamburger-box">
					<span class="hamburger-inner"></span>
                        </span>
        </div>
    </div>

    <!-- Menu Mobile -->
    <div class="menu-mobile">
        <ul class="topbar-mobile">
            <li>
                <div class="left-top-bar">
                    Giao hàng miễn phí cho đơn đặt hàng tiêu chuẩn trên 10 triệu VNĐ
                </div>
            </li>

            <li>
                <div class="right-top-bar flex-w ">
                    <c:if test="${sessionScope.loginedUser==null}">
                        <a href="${pageContext.request.contextPath}/signIn" class="flex-c-m p-lr-10 trans-04">
                            Đăng nhập
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.loginedUser!=null}">
                        <a href="${pageContext.request.contextPath}/userinfo" class="flex-c-m p-lr-10 trans-04">
                                My account
                        </a>
                        <a href="${pageContext.request.contextPath}/signOut" class="flex-c-m p-lr-10 trans-04">
                                Đăng xuất
                        </a>
                    </c:if>
                    </div>
            </li>
        </ul>
        <c:if test="${sessionScope.loginedUser.roleID==1 || sessionScope.loginedUser.roleID==null}">
        <ul class="main-menu-m">
            <li>
                <a href="${pageContext.request.contextPath}/home">Home</a>

            </li>

            <li>
                <a href="${pageContext.request.contextPath}/product">Shop</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/cart" class="label1 rs1" data-label1="hot">Features</a>
            </li>


            <li>
                <a href="${pageContext.request.contextPath}/about">About</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/contact">Contact</a>
            </li>

        </ul>
        </c:if>

        <!-- Menu desktop for staff -->


                <c:if test="${sessionScope.loginedUser.roleID==2}">
                <ul class="main-menu-m">
                    <li>
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/product">Shop</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/productManagement">Product Manager</a>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/Statistic">Statistic</a>
                    </li>

                </ul>

        </c:if>

        <c:if test="${sessionScope.loginedUser.roleID==3}">
            <ul class="main-menu-m">
                <li>
                    <a href="${pageContext.request.contextPath}/home">Home</a>

                </li>

                <li>
                        <a href="${pageContext.request.contextPath}/product">Shop</a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/productManagement">Product Manager</a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/brandManagement">Brand Manager</a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/UserManagement">User Manager</a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/Statistic">Statistic</a>
                </li>

            </ul>
        </c:if>

    </div>
</header>
<!-- Cart -->
<div class="wrap-header-cart js-panel-cart">
    <div class="s-full js-hide-cart"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
                <span class="mtext-103 cl2">
					Your Cart
				</span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full">
                <c:set var="tongTien" value="0"/>
                <c:forEach items="${requestScope.listChiTietGioHang}" var="o">
                    <c:set var="tongTien" value="${tongTien + o.thanhTien}"/>
                    <li class="header-cart-item flex-w flex-t m-b-12">
                        <div class="header-cart-item-img">
                            <img src="${o.sanPham.hinhSP }" alt="IMG">
                        </div>

                        <div class="header-cart-item-txt p-t-8">
                            <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                    ${o.sanPham.tenSP }
                            </a>

                            <span class="header-cart-item-info ">
								${o.sanPham.soLuongSP} x  <fmt:formatNumber type="number"
                                                                            maxFractionDigits="0" value="${o.sanPham.giaSP}"/>vnđ
							</span>
                        </div>
                    </li>
                </c:forEach>

            </ul>

            <div class="w-full">
                <div class="header-cart-total w-full p-tb-40">
                    Total: <fmt:formatNumber type="number"
                                             maxFractionDigits="0" value="${tongTien}"/>vnđ
                </div>

                <div class="header-cart-buttons flex-w w-full">
                    <a href="${pageContext.request.contextPath}/cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                        View Cart
                    </a>

                    <a href="${pageContext.request.contextPath}/cart"
                       class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                        Check Out
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
