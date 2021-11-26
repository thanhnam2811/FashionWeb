use QuanLiShop
go

-- create ViewerRole
use QuanLiShop
create role ViewerRole
grant SELECT, EXECUTE to ViewerRole

-- create user guess with read-only rules
if not exists(SELECT name
              FROM master.sys.server_principals
              WHERE name = 'guess')
    create login guess with password = 'guess'
else
    alter login guess with password = 'guess'
create user guess for login guess
exec sp_addrolemember ViewerRole, guess

-- LoaiSP
insert into LoaiSP(tenLoaiSP)
values (N'Quần')
insert into LoaiSP(tenLoaiSP)
values (N'Áo')
insert into LoaiSP(tenLoaiSP)
values (N'Giày')

-- ThuongHieu
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('Adidas', N'adidas@gmail.com',N'Image/LOGO/adidas.png')
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('Nike', N'nike@gmail.com',N'Image/LOGO/nike.png')
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('Biti''s', N'bitis@gmail.com',N'Image/LOGO/biti''s.png')
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('POLO', N'polo@gmail.com',N'Image/LOGO/polo.png')
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('puma', N'puma@gmail.com',N'Image/LOGO/puma.png')
insert into ThuongHieu(tenTH, emailTH, hinhTH) values('Gucci', N'Gucci@gmail.com',N'Image/LOGO/gucci.png')

-- VanChuyen
insert into VanChuyen(tenDV, email, SDT, diaChi)
values ('J&T Express', N'J&T@gmail.com', '0911111111', N'TP.HCM')
insert into VanChuyen(tenDV, email, SDT, diaChi)
values ('GiaoHangNhanh', N'GHN@gmail.com', '0911111112', N'TP.HCM')
insert into VanChuyen(tenDV, email, SDT, diaChi)
values ('ViettelPost', N'ViettelPost@gmail.com', '0911111113', N'TP.HCM')

-- Role
-- User Role --
SET IDENTITY_INSERT Role ON
insert into Role(roleID, name)
values (1, 'UserRole')
use QuanLiShop
create role UserRole
grant SELECT, EXECUTE to UserRole
grant INSERT, DELETE on ChiTietGioHang to UserRole
grant INSERT on ChiTietDonHang to UserRole
revoke UPDATE(hoTen, sdt, ngaySinh, diaChi, [password]) on Users to UserRole
grant INSERT on DonHang to UserRole

-- Staff Role --
insert into Role(roleID, name)
values (2, 'StaffRole')
use QuanLiShop
create role StaffRole
grant SELECT, EXECUTE to StaffRole
grant INSERT, DELETE on BinhLuan to StaffRole
grant UPDATE(hoTen, sdt, ngaySinh, diaChi, [password]) on Users to StaffRole
grant INSERT, DELETE on LoaiSP to StaffRole

-- Admin Role --
insert into Role(roleID, name)
values (3, 'AdminRole')
use QuanLiShop
create role AdminRole
grant SELECT, INSERT, DELETE, EXECUTE to AdminRole WITH GRANT OPTION
SET IDENTITY_INSERT Role OFF

-- Users
insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password)
values (N'Thái Thành Nam', '0999999999', '2001-11-28', N'Bến Tre', 'nam', 'nam')
insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password)
values (N'Cao Hoài Tấn', '0999999999', '2001-09-02', N'Bình Định', 'tan', 'tan')
insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password, roleID)
values (N'Nguyễn Ngọc Trung', '0999999999', '2001-02-14', N'Phú Yên', 'trung', 'trung', 2)
insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password, roleID)
values (N'Nguyễn Phúc Thanh Toàn', '0999999999', '2001-01-01', N'Tây Ninh', 'toan', 'toan', 2)
insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password, roleID)
values (N'Admin', '0999999999', '2001-01-01', N'TP.HCM', 'admin', 'admin', 3)

-- SanPham
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Sky Jordan 1', 2, 3, N'Image/Nike/SkyJordan1.jpg', 1659000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Nike Air Force 1 Crater', 2, 3, N'Image/Nike/NikeAirForce1Crater.jpg', 3239000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Nike Air Force 1 Fontanka', 2, 3, N'Image/Nike/NikeAirForce1Fontanka.jpg', 3239000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'QUẦN WINTERIZED FUTURE ICONS ADIDAS SPORTSWEAR', 1, 1, N'Image/Adidas/Quan1.jpg', 1500000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'SWEAT PANTS SHATTERED TREFOIL ADICOLOR', 1, 1, N'Image/Adidas/Quan2.jpg', 1900000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'TRACK PANTS FIREBIRD PRIMEBLUE CLASSICS ADICOLOR', 1, 1, N'Image/Adidas/Quan3.jpg', 1900000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'QUẦN SWEATPANTS CẠP CAO HYPERGLAM ​', 1, 1, N'Image/Adidas/Quan4.jpg', 1400000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'TRACK PANTS DISRUPTED ICON ADICOLOR CLASSICS', 1, 1, N'Image/Adidas/Quan5.jpg', 2100000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values ('TRACK PANTS', 1, 1, N'Image/Adidas/Quan6.jpg', 1530000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values ('Air Jordan XXXVI SE Luka', 2, 3, N'Image/Nike/giay1.jpg', 5439000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values ('Jordan 1 Mid SE', 2, 3, N'Image/Nike/giay2.jpg', 1789000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values ('Jordan 1 Mid SE', 2, 3, N'Image/Nike/3.jpg', 1789000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'GIÀY ADVANTAGE THE SIMPSONS', 1, 3, N'Image/Adidas/1.jpg', 1800000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'GIÀY ADVANTAGE', 1, 3, N'Image/Adidas/2.jpg', 1800000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'GIÀY CLUBCOURT', 1, 3, N'Image/Adidas/3.jpg', 1900000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Biti’s Hunter X Z Collection InGreenZ', 3, 3, N'Image/Bitis/1.jpg', 1199000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Biti’s Hunter Core Z Collection Earth', 3, 3, N'Image/Bitis/2.jpg', 699000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Biti’s Hunter Core Z Collection Stone', 3, 3, N'Image/Bitis/3.jpg', 699000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values ('POLO M2ATP2051001', 4, 2, N'Image/POLO/1.jpg', 350000, 5, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun tay ngắn năng động Graffiti Overfit', 5, 2, N'Image/puma/1.jpg', 2290000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun tay ngắn cổ tròn logo cá tính', 5, 2, N'Image/puma/2.jpg', 1590000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun tay ngắn cổ tròn thời trang', 5, 2, N'Image/puma/3.jpg', 1690000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun tay ngắn phối họa tiết cây cọ', 5, 2, N'Image/puma/4.jpg', 1990000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nữ Nike Court Victory', 2, 2, N'Image/Nike/1.jpg', 590000, 20, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nam Nike Dri FIT Academy', 2, 2, N'Image/Nike/2.jpg', 1390000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo Liverpool', 2, 2, N'Image/Nike/4.jpg', 2599000, 10, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nam Nike Xám', 2, 2, N'Image/Nike/5.jpg', 390000, 40, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nữ Nike Sport Sweat', 2, 2, N'Image/Nike/6.jpg', 750000, 20, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nữ POLO Demin cổ V', 4, 2, N'Image/POLO/2.jpg', 450000, 14, getdate())
insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan)
values (N'Áo thun nữ POLO Pres Leeve', 4, 2, N'Image/POLO/3.jpg', 990000, 45, getdate())

-- ChiTietGioHang
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (1, 10, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (1, 12, 3)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (1, 14, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (1, 16, 2)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (1, 18, 1)

insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (2, 11, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (2, 13, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (2, 17, 4)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (2, 19, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (2, 9, 2)

insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (3, 1, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (3, 3, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (3, 7, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (3, 9, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (3, 11, 2)

insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (4, 2, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (4, 4, 2)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (4, 6, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (4, 8, 1)
insert into ChiTietGioHang(maKH, maSP, soLuongSP)
values (4, 10, 2)

-- DonHang
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (1, 1, 'Nam', 'Ba Tri', '091771024')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (1, 1, 'Nam', 'Ba Tri', '091771024')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (2, 3, 'Nam', 'Ba Tri', '091771024')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (2, 1, 'Nam', 'Ba Tri', '091771024')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (3, 1, 'Nam', 'Ba Tri', '091771024')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (3, 2, 'Nam', 'Ba Tri', '091771024')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (4, 3, 'Nam', 'Ba Tri', '091771024')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt)
values (4, 1, 'Nam', 'Ba Tri', '091771024')

-- ChiTietDonHang

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (1, 10, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (1, 2, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (2, 12, 3)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (2, 2, 3)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (3, 11, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (3, 1, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (4, 13, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (4, 3, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (5, 1, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (5, 10, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (6, 3, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (6, 7, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (7, 2, 1)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (7, 5, 1)

insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (8, 4, 2)
insert into ChiTietDonHang(maDH, maSP, soLuongSP)
values (8, 14, 2)

-- BinhLuan
insert into BinhLuan(maKH, maSP, noiDung)
values (1, 1, N'Sản phẩm quá tuyệt vời')
insert into BinhLuan(maKH, maSP, noiDung)
values (1, 2, N'Sản phẩm quá tuyệt vời')

insert into BinhLuan(maKH, maSP, noiDung)
values (2, 3, N'Sản phẩm quá tuyệt vời')
insert into BinhLuan(maKH, maSP, noiDung)
values (2, 4, N'Sản phẩm quá tuyệt vời')

insert into BinhLuan(maKH, maSP, noiDung)
values (3, 5, N'Sản phẩm quá tuyệt vời')
insert into BinhLuan(maKH, maSP, noiDung)
values (3, 6, N'Sản phẩm quá tuyệt vời')

insert into BinhLuan(maKH, maSP, noiDung)
values (4, 7, N'Sản phẩm quá tuyệt vời')
insert into BinhLuan(maKH, maSP, noiDung)
values (4, 8, N'Sản phẩm quá tuyệt vời')
