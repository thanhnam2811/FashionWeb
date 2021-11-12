<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Thông tin tài khoản</title>
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
	<link rel="icon" type="image/x-icon" href="assets/img/Logo.PNG" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
			crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link
			href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:500,700"
			rel="stylesheet" type="text/css" />
	<link
			href="https://fonts.googleapis.com/css?family=Muli:400,400i,800,800i"
			rel="stylesheet" type="text/css" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link rel="stylesheet" type="text/css" href="css/account.css">

</head>

<body id="page-top">

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
	 id="sideNav">
	<a class="navbar-brand js-scroll-trigger" href="#page-top"> <span
			class="d-block d-lg-none">NTTT SHOP</span> <span
			class="d-none d-lg-block"><img
			class="img-fluid img-profile rounded-circle mx-auto mb-2"
			src="images/user.jpg" alt="..." /></span>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
									href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
									href="#about">Thông tin tài khoản</a></li>
			<c:if test="${sessionScope.loginedUser.roleID!=2 && sessionScope.loginedUser.roleID!=3 }">
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
									href="#experience">Lịch sử mua hàng</a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
									href="#education">Thay đổi thông tin tài khoản</a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger"
									href="#skills">Thay đổi mật khẩu</a></li>
		</ul>
	</div>
</nav>
<!-- Page Content-->
<div class="container-fluid p-0">
	<!-- About-->
	<section class="resume-section" id="about">
		<div class="resume-section-content">
			<h1 class="mb-0">
				Thông tin <span class="text-primary"> tài khoản</span>
			</h1>
			<div class="subheading mb-5">
				N3T SHOP: <a href="mailto:name@email.com"> n3t@gmail.com</a>
			</div>
			<section>

				<!-- <h2>Đăng ký tài khoản</h2> -->
				<!-- -->
				<label>Họ và tên: </label> <input type="text" readonly="readonly"
												  name="hoten" value="${info.hoTen }"> <br> <label>Số
				điện thoại:</label> <input type="text" readonly="readonly" name="sdt"
										   value="${info.sdt }"> <br> <label>Ngày tháng
				năm sinh:</label> <input type="date" readonly="readonly" name="namsinh"
										 value="${info.ngaySinh }"> <br> <label>Địa
				chỉ:</label> <input type="text" readonly="readonly" name="diachi"
									value="${info.diaChi }">


			</section>

		</div>
	</section>
	<hr class="m-0" />
	<!-- Experience-->
	<c:if test="${sessionScope.loginedUser.roleID!=2 && sessionScope.loginedUser.roleID!=3}">
	<section class="resume-section" id="experience">
		<div class="resume-section-content">
			<h2 class="mb-5">Lịch sử mua hàng</h2>

			<div
					class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<c:forEach items="${listDH}" var="p">
				<div class="flex-grow-1">
					<h5>Mã đơn hàng: ${p.maDH } &emsp;  Ngày mua:${p.ngayMua } &emsp; Tổng
						tiền: <fmt:formatNumber maxFractionDigits="0" value="${p.tongTien}"/>đ</h5>
					<h5>Người nhận: ${p.tenNguoiNhan } &emsp;  Địa chỉ:${p.diaChi } &emsp; SDT: ${p.sdt}</h5>

					<table border="1">
						<tr>
							<th>Sản phẩm</th>
							<th>Đơn giá</th>
							<th>Số lượng</th>
							<th>Thành tiền</th>
						</tr>
						<c:forEach items="${listCTDH}" var="o">
							<c:if test="${o.maDH == p.maDH}">
								<tr>
									<td>${o.gettenSP(listSP)}</td>
									<td><fmt:formatNumber maxFractionDigits="0" value="${o.getgiaSP(listSP)}"/>đ</td>
									<td>${o.soLuongSP }</td>
									<td><fmt:formatNumber maxFractionDigits="0" value="${o.thanhTien}"/>đ</td>
								</tr>
							</c:if>

						</c:forEach>

					</table>
					<br>
					<br>
					</c:forEach>
				</div>
			</div>
			</div>
	</section>
	</c:if>
	<hr class="m-0" />
	<!-- Education-->
	<section class="resume-section" id="education">
		<div class="resume-section-content">
			<h2 class="mb-5">Chỉnh sửa thông tin cá nhân</h2>
			<div
					class="d-flex flex-column flex-md-row justify-content-between mb-5">
				<section>
					<form action="edituserinfo" method="post">
						<!-- -->
						<label>Họ và tên:</label>
						<input type="text" required="" value="${info.hoTen }" name="hoten">
						<br>
						<label>Số điện thoại:</label>
						<input type="text" required="" value="${info.sdt }" name="sdt">
						<br>
						<label>Ngày tháng năm sinh:</label>
						<input type="date" required="" name="namsinh" value="${info.ngaySinh }"> <br>
						<label>Địa chỉ:</label>
						<input type="text" required="" value="${info.diaChi }" name="diachi">
						<br>
						<input id="submit"  type="submit" name="submit" value="Cập nhật">
					</form>
				</section>

			</div>
		</div>
	</section>
	<hr class="m-0" />
	<!-- Skills-->
	<section class="resume-section" id="skills">
		<div class="resume-section-content">
			<h2 class="mb-5">Thay đổi mật khẩu</h2>
			<form action="edituserpassword" onsubmit="return checkPassword()"
				  method="post">
				<label>Mật khẩu cũ:</label> <input type="password" required=""
												   placeholder="OldPassword"  name="oldpassword" />
				<br>
				<p style="color:red;">${mess }</p>
				<label>Mật khẩu mới:</label> <input type="password" required=""
													placeholder="NewPassword" id="newpassword" name="newpassword" />
				<br> <label>Nhập lại mật khẩu:</label> <input type="password" required=""
															  placeholder="ConfirmPassword" id="cpassword" name="cpassword" />
				<br>
				<span style="color: rgb(238, 17, 17);" id="message_error"></span>
				<input id="submit" type="submit" name="submit" value="Cập nhật">
			</form>

		</div>
	</section>
	<!-- Bootstrap core JS-->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Third party plugin JS-->
	<script
			src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/account.js"></script>
	<script type="text/javascript">
		function checkPassword() {
			var fpw = document.getElementById("newpassword").value;
			var spw = document.getElementById("cpassword").value;

			if (fpw != spw) {
				document.getElementById('message_error').innerHTML = "<span >Mật khẩu chưa khớp</span>";
				return false;
			}
			return true;
		}
	</script>
</div>
</body>
</html>