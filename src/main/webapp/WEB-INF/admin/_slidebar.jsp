<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 26/11/2021
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="sidebar" class="active">
    <div class="sidebar-wrapper active">
        <div class="sidebar-header">
            <div class="d-flex justify-content-between">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/admin"><img src="assets/images/logo/logo.png" alt="Logo" srcset=""></a>
                </div>
                <div class="toggler">
                    <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                </div>
            </div>
        </div>
        <div class="sidebar-menu">
            <ul class="menu">
                <li class="sidebar-title">Menu</li>

                <li class="sidebar-item ${requestScope.pageName.contains("Dashboard") ? "active" : ""}">
                    <a href="${pageContext.request.contextPath}/admin" class='sidebar-link'>
                        <i class="bi bi-grid-fill"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="sidebar-title">Management</li>
                <li class="sidebar-item ${requestScope.pageName.contains("Product") ? "active" : ""}">
                    <a href="${pageContext.request.contextPath}/manage-product" class='sidebar-link'>
                        <i class="bi bi-basket-fill" aria-hidden="true"></i>
                        <span>Product</span>
                    </a>
                </li>
                <li class="sidebar-item ${requestScope.pageName.contains("Brand") ? "active" : ""}">
                    <a href="${pageContext.request.contextPath}/brandManagement" class='sidebar-link'>
                        <i class="bi bi-life-preserver"></i>
                        <span>Brand</span>
                    </a>
                </li>
                <li class="sidebar-item ${requestScope.pageName.contains("Comment") ? "active" : ""}">
                    <a href="${pageContext.request.contextPath}/manage-comment" class='sidebar-link'>
                        <i class="bi bi-chat-dots-fill"></i>
                        <span>Comment</span>
                    </a>
                </li>
                <li class="sidebar-item  has-sub ${requestScope.pageName.contains("Account") ? "active" : ""}">
                    <a href="#" class='sidebar-link'>
                        <i class="bi bi-person-badge-fill"></i>
                        <span>Account</span>
                    </a>
                    <ul class="submenu ${requestScope.pageName.contains("Account") ? "active" : ""}">
                        <li class="submenu-item  ${requestScope.pageName.contains("User") ? "active" : ""}">
                            <a href="${pageContext.request.contextPath}/manage-user">User</a>
                        </li>
                        <li class="submenu-item  ${requestScope.pageName.contains("Staff & Admin") ? "active" : ""}">
                            <a href="${pageContext.request.contextPath}/manage-staff-admin">Staff & Admin</a>
                        </li>
                    </ul>
                </li>
                <li class="sidebar-title">Action</li>
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/signOut" class='sidebar-link'>
                        <i class="bi bi-x-octagon-fill" aria-hidden="true"></i>
                        <span>Log out</span>
                    </a>
                </li>
            </ul>
        </div>
        <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
    </div>
</div>
