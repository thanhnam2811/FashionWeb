-- CREATE DATABASE --

create database QuanLiShop
go

use QuanLiShop
go

create table ThuongHieu
(
	maTH int identity primary key,
	tenTH nvarchar(26) not null,
	emailTH nvarchar(50),
	hinhTH varchar(max),
);

create table LoaiSP
(
	maLoaiSP int identity primary key,
	tenLoaiSP nvarchar(50) not null
);

create table SanPham
(
	maSP int identity primary key,
	tenSP nvarchar(50) not null,
	maTH int null,
	maLoaiSP int not null,
	hinhSP varchar(max) not null,
	giaSP float not null check (giaSP >= 0),
	soLuongSP int not null check( soLuongSP >= 0 ),
	ngayDangBan date default getdate()
)

create table Users
(
	maKH int identity primary key,
	hoTen nvarchar(50) not null,
	sdt varchar(10),
	ngaySinh date,
	diaChi nvarchar(50),
	userName varchar(20) not null unique,
	password varchar(max) not null,
	roleID int null default 1,
)

create table DonHang
(
	maDH int identity primary key,
	maKH int null,
	tenNguoiNhan nvarchar(200) not null,
	diaChi nvarchar(max) not null,
	sdt nvarchar(max) not null,
	ngayMua date not null default getdate(),
	tongTien float not null default 0,
	maDV int null,
)

create table VanChuyen
(
	maDV int identity primary key,
	tenDV nvarchar(50) not null,
	email nvarchar(50) not null,
	SDT varchar(10) not null,
	diaChi nvarchar(50) not null,
);

create table ChiTietDonHang
(
	ID int identity primary key,
	maDH int null,
	maSP int null,
	soLuongSP int not null check (soLuongSP >= 0),
	thanhTien float null check (thanhTien >= 0) default 0,
	unique(maDH, maSP)
)


create table BinhLuan
(
	maCMT int identity primary key,
	maKH int not null,
	maSP int not null,
	noiDung nvarchar(max),
	ngayDang datetime default getdate(),
)

create table Role
(
	roleID int identity primary key,
	name varchar(10) not null unique,
)

create table ChiTietGioHang
(
	ID int identity primary key,
	maKH int not null,
	maSP int not null,
	soLuongSP int not null check (soLuongSP >= 0),
	thanhTien float null check (thanhTien >=0) default 0,
	unique(maSP, maKH),
)

-- CREATE RELATIONSHIP --

alter table SanPham
	add Constraint FK_SanPham_ThuongHieu
	foreign key(maTH) references ThuongHieu(maTH)
	on delete set null -- Thương hiệu bị xóa -> đặt maTH = null
	on update no action

alter table SanPham
	add Constraint FK_Sanpham_LoaiSP
	foreign key(maLoaiSP) references LoaiSP(maLoaiSP)
	on delete cascade -- Loại sản phẩm bị xóa -> xóa cả sản phẩm thuộc loại đó
	on update no action

alter table DonHang
	add constraint FK_DonHang_Users
	foreign key(maKH) references Users(maKH)
	on delete set null -- set null mã KH trên Đơn Hàng của những KH bị xóa TK
	on update no action

alter table chiTietDonHang
	add constraint FK_chiTietDH_DH
	foreign key(maDH) references DonHang(maDH)
	on delete set null -- set null mã ĐH trên chi tiết đơn hàng của những Đơn Hàng bị xóa
	on update no action
 
alter table chiTietDonHang
	add constraint FK_chiTietDH_SP
	foreign key(maSP) references SanPham(maSP)
	on delete set null
	on update no action

alter table DonHang
	add constraint FK_DonHang_VanChuyen
	foreign key(maDV) references VanChuyen(maDV)
	on delete set null -- set null maDV trên những đơn hàng của những DV Vận chuyển bị xóa
	on update no action

alter table BinhLuan
	add constraint FK_BinhLuan_Users
	foreign key(maKH) references Users(maKH)
	on delete cascade -- Xóa những bình luận của những khách hàng bị xóa tk
	on update no action 

alter table BinhLuan
	add constraint FK_Comment_SanPham
	foreign key(maSP) references SanPham(maSP)
	on delete cascade -- Xóa những bình luận của những khách hàng bị xóa tk
	on update no action 

alter table Users
	add constraint FK_Users_Role
	foreign key(roleID) references Role(roleID)
	on delete set null -- set null role của những role bị xóa
	on update no action

alter table chiTietGioHang
	add constraint FK_chiTietGH_Users
	foreign key(maKH) references Users(maKH)
	on delete cascade -- xóa giỏ hàng của những người bị xóa tk
	on update no action

alter table chiTietGioHang
	add constraint FK_chiTietGH_SP
	foreign key(maSP) references SanPham(maSP)
	on delete cascade -- xóa chi tiêst giỏ hàng của những sp bị xóa
	on update no action

-- CREATE TRIGGER --

-----------------------------CHITIETDONHANG------------------------------
-- Tính tổng tiền của đơn hàng = tổng giá tiền của chi tiết đơn hàng khi insert hoặc cập nhật lại chitietdonhang
drop trigger if exists tinhTongTien
go
create trigger tinhTongTien on chiTietDonHang
after insert, update as
declare @maDH int, @tongTien float, @thanhTien float, @id int
select @maDH = new.maDH, @thanhTien = sp.giaSP * new.soLuongSP, @id = new.id
from inserted new, SanPham sp
where new.maSP = sp.maSP
begin
	update ChiTietDonHang
	set thanhTien = @thanhTien
	where id = @id

	select @tongTien = sum(thanhTien) from chiTietDonHang where maDH = @maDH
	update DonHang
	set tongTien = @tongTien
	where maDH = @maDH
end
go

-- Cập nhật lại tổng tiền của đơn hàng khi delete 1 chitietdonhang 
drop trigger if exists tinhTongTienOnDelete
go
create trigger tinhTongTienOnDelete on chiTietDonHang
after delete as
declare @maDH int, @tongTien float
select @maDH = old.maDH
from deleted old
begin 
	select @tongTien = sum(thanhTien) from chiTietDonHang where maDH = @maDH
	update DonHang
	set tongTien = @tongTien
	where maDH = @maDH
end
go

-- Không cho phép số lượng đặt hàng của 1 sp/ dh > số lượng tồn kho
drop trigger if exists check_SL_DatHang
go
create trigger check_SL_DatHang on chiTietDonHang
after insert
AS
declare @ne_sl int, @tonkho int, @maSP int
select  @ne_sl = ne.soLuongSP, @tonkho = sp.soLuongSP, @maSP = sp.maSP
from inserted ne, SanPham sp
where ne.maSP = sp.maSP
begin
	if( @ne_sl > @tonkho)
	Begin
	print(N'Cửa hàng chỉ còn ' +  CAST(@tonkho AS NVARCHAR(100)) + N' sp, không thể mua ' + CAST(@ne_sl AS NVARCHAR(100)) + ' sp');
	ROLLBACK;
	End
	else
		update SanPham
		set soLuongSP = @tonkho - @ne_sl
		where maSP = @maSP;
end
GO

--------------CHITIETGIOHANG-----------------------------------------------------

-- Trigger tính giá tiền = SL*(đơn giá của SP) trong bảng chitietGH sau khi insert or update
drop trigger if exists TinhTienChiTietGH
go
create trigger TinhTienChiTietGH on chiTietGioHang
after insert, update as
declare @tongTien float, @id int
select @tongTien = sp.giaSP * ins.soLuongSP, @id = ins.id
from SanPham sp, inserted ins
where sp.maSP = ins.maSP
Begin
	update chiTietGioHang
	set thanhTien = @tongTien
	where id = @id
end
go

-- Không cho phép số lượng của 1 sp trong giỏ  > số lượng tồn kho
drop trigger if exists check_SL_SPofGH
go
create trigger check_SL_SPofGH on chiTietGioHang
after insert, update
AS
declare @ne_sl int, @tonkho int
select  @ne_sl = ne.soLuongSP, @tonkho = sp.soLuongSP
from inserted ne inner join SanPham sp on ne.maSP = sp.maSP
if( @ne_sl > @tonkho)
Begin
	print('So luong san pham cua cua hang khong du de dap ung');
	ROLLBACK;
End
GO

drop trigger if exists after_insert_update_Users
go
create trigger after_insert_update_Users on Users
after insert
as
declare @username varchar(20), @password varchar(max), @roleName varchar(10), @sql nvarchar(max)
select @username = userName, @password = password, @roleName = Role.name
from Users, Role
where Users.roleID = Role.roleID
begin
	-- create login
	if not exists(SELECT name FROM master.sys.server_principals WHERE name = @username)
	    begin
	    set @sql='create login ' + @username + ' with password = ''' + @password + ''''
	    exec (@sql)
	    end
	else
		begin
	    set @sql='alter login ' + @username + ' with password = ''' + @password + ''''
	    exec (@sql)
	    end 
	-- create user
	set @sql='create user ' + @username + ' for login ' + @username
	exec (@sql)
	-- add to role
	set @sql='sp_addrolemember ' + @roleName + ',' + @username
	exec (@sql)
end

go

drop trigger if exists after_delete_Users
go
create trigger after_delete_Users on Users
    after delete
    As

    declare @username varchar(20), @sql varchar(MAX)
    select @username = de.userName
    from deleted de

begin
    --Xóa login
    set @sql= 'DROP LOGIN ' + @username
    exec (@sql)
    --Xóa user
    set @sql= 'DROP USER ' + @username
    exec (@sql)

end
go

-- INSERT DATA --

-- create ViewerRole
use QuanLiShop
create role ViewerRole
grant SELECT, EXECUTE to ViewerRole

-- create user guess with read-only rules
if not exists(SELECT name FROM master.sys.server_principals WHERE name = 'guess')
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
grant UPDATE(hoTen, sdt, ngaySinh, diaChi, [password]) on Users to UserRole
grant INSERT on DonHang to UserRole

-- Staff Role --
insert into Role(roleID, name)
values (2, 'StaffRole')

use QuanLiShop
create role StaffRole
grant SELECT, EXECUTE to StaffRole
grant INSERT, DELETE on BinhLuan to StaffRole
grant INSERT, DELETE on SanPham to StaffRole
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
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (1, 1, 'Nam', 'Ba Tri', '091771024', '2021-11-11')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (1, 1, 'Nam', 'Ba Tri', '091771024', '2021-10-10')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (2, 3, 'Nam', 'Ba Tri', '091771024', '2021-09-09')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (2, 1, 'Nam', 'Ba Tri', '091771024', '2021-08-08')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (3, 1, 'Nam', 'Ba Tri', '091771024', '2021-07-07')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (3, 2, 'Nam', 'Ba Tri', '091771024', '2021-06-06')

insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (4, 3, 'Nam', 'Ba Tri', '091771024', '2021-05-05')
insert into DonHang(maKH, maDV, tenNguoiNhan, diaChi, sdt, ngayMua)
values (4, 1, 'Nam', 'Ba Tri', '091771024', '2021-04-04')

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

-- CREATE PROCEDURE --

---------- ROLE ----------
	-- load --
drop procedure if exists load_role 
go
create procedure load_role
as
	select * from role
go
	-- insert --
drop procedure if exists insert_role 
go
create procedure insert_role 
	@name varchar(10)
as
	insert into Role(name)
		values (@name)
go

	-- update --
drop procedure if exists update_role 
go
create procedure update_role 
	@id int,
	@name varchar(10)
as
	update Role 
	set name = @name
	where roleID = @id
go

	-- delete --
drop procedure if exists delete_role 
go
create procedure delete_role 
	@id int
as
	delete Role 
	where roleID = @id
go


---------- Users ----------
	-- load --
drop procedure if exists load_Users 
go
create procedure load_Users
as
	select * from Users
go

	-- insert --
drop procedure if exists insert_Users 
go
create procedure insert_Users 
	@hoTen nvarchar(50),
	@sdt varchar(10),
	@ngaySinh date,
	@diaChi nvarchar(50),
	@userName varchar(20),
	@password varchar(20)
as
	insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password) 
		values(@hoten, @sdt, @ngaySinh, @diaChi, @userName, @password)
go

	-- update --
drop procedure if exists update_Users 
go
create procedure update_Users
	@maKH int,
	@hoTen nvarchar(50),
	@sdt varchar(10),
	@ngaySinh date,
	@diaChi nvarchar(50),
	@userName varchar(20),
	@password varchar(20)
as
	update Users
	set
		hoTen = @hoTen,
		sdt = @sdt,
		ngaySinh = @ngaySinh,
		diaChi = @diaChi,
		userName = @userName,
		password = @password
	where maKh = @maKH
go

	-- delete --
drop procedure if exists delete_Users
go
create procedure delete_Users
	@maKH int
as
	delete Users
	where maKH = @maKH
go


---------- BinhLuan ----------
	-- load --
drop procedure if exists load_BinhLuan
go
create procedure load_BinhLuan
as
	select * from BinhLuan
go

	-- insert --
drop procedure if exists insert_BinhLuan
go
create procedure insert_BinhLuan
	@maKH int,
	@maSP int,
	@noiDung nvarchar(max)
as
	insert into BinhLuan(maKH, maSP, noiDung) 
		values(@maKH, @maSP, @noiDung)
go

	-- update --
drop procedure if exists update_BinhLuan
go
create procedure update_BinhLuan
	@maCMT int,
	@noiDung nvarchar(max)
as
	update BinhLuan
	set
		noiDung = @noiDung
	where maCMT = @maCMT
go

	-- delete --
drop procedure if exists delete_BinhLuan
go
create procedure delete_BinhLuan
	@maCMT int
as
	delete BinhLuan
	where maCMT = @maCMT
go


---------- VanChuyen ----------
	-- load --
drop procedure if exists load_VanChuyen
go
create procedure load_VanChuyen
as
	select * from VanChuyen
go

	-- insert --
drop procedure if exists insert_vanchuyen 
go
create procedure insert_vanchuyen 
	@tenDV varchar(50),
	@email varchar(50),
	@SDT varchar(10),
	@diachi varchar(50)
as
	insert into VanChuyen(tenDV,email,SDT,diaChi)
		values (@tenDV,@email,@SDT,@diachi)
go

	-- update --
drop procedure if exists update_vanchuyen
go
create procedure update_vanchuyen
	@id int,
	@tenDV varchar(50),
	@email varchar(50),
	@SDT varchar(10),
	@diachi varchar(50)
as
	update VanChuyen 
	set tenDV = @tenDV, email = @email, SDT = @SDT, diaChi = @diachi
	where maDV = @id
go	

	-- delete --
drop procedure if exists delete_vanchuyen 
go
create procedure delete_vanchuyen 
	@id int
as
	delete VanChuyen 
	where maDV = @id
go


---------- DonHang ----------
	-- load --
drop procedure if exists load_DonHang
go
create procedure load_DonHang
as
	select * from DonHang
go

	-- insert --
drop procedure if exists insert_donhang 
go
create procedure insert_donhang 
	@maKH int,
	@tenNguoiNhan nvarchar(200),
	@diaChi nvarchar(MAX),
	@sdt nvarchar(MAX),
	@tongTien float,
	@maDV int
as
	insert into DonHang(maKH, tenNguoiNhan, diaChi,sdt,tongTien, maDV)
		values (@maKH, @tenNguoiNhan, @diaChi, @sdt, @tongTien, @maDV)
go

	-- update -- 
drop procedure if exists update_donhang
go
create procedure update_donhang
	@id int,
	@maKH int,
	@ngayMua datetime,
	@tongTien float,
	@maDV int
as
	update DonHang 
	set maKH = @maKH, ngayMua = @ngayMua, tongTien = @tongTien, maDV = @maDV
	where maDH = @id
go

	-- delete --
drop procedure if exists delete_donhang
go
create procedure delete_donhang
	@id int
as
	delete DonHang
	where maDH = @id
go


---------- ChiTietDonHang ----------
	-- load --
drop procedure if exists load_ChiTietDonHang
go
create procedure load_ChiTietDonHang
as
	select * from ChiTietDonHang
go

	-- insert --
drop procedure if exists insert_chiTietDonHang 
go
create procedure insert_chiTietDonHang  
	@maDH int,
	@maSP int,
	@soLuongSP int,
	@thanhTien float
as
	insert into ChiTietDonHang(maDH,maSP,soLuongSP,thanhTien)
		values (@maDH,@maSP,@soLuongSP,@thanhTien)
go

	-- update --
drop procedure if exists update_chiTietDonHang
go
create procedure update_chiTietDonHang 
	@id int,
	@maDH int,
	@maSP int,
	@soLuongSP int,
	@thanhTien float
as
	update ChiTietDonHang 
	set maDH = @maDH, maSP = @maSP, soLuongSP = @soLuongSP, thanhTien = @thanhTien
	where ID = @id
go

	-- delete --
drop procedure if exists delete_chiTietDonHang 
go
create procedure delete_chiTietDonHang
	@id int
as
	delete ChiTietDonHang 
	where ID = @id
go


---------- ChiTietGioHang ----------
	-- load --
drop procedure if exists load_ChiTietGioHang
go
create procedure load_ChiTietGioHang
as
	select * from ChiTietGioHang
go

	-- insert --
drop procedure if exists insert_chiTietGioHang 
go
create procedure insert_chiTietGioHang   
	@maKH int,
	@maSP int,
	@soLuongSP int
as
	insert into ChiTietGioHang(maKH,maSP,soLuongSP)
		values (@maKH,@maSP,@soLuongSP)
go

	-- update --
drop procedure if exists update_chiTietGioHang 
go
create procedure update_chiTietGioHang  
	@id int,
	@maKH int,
	@maSP int,
	@soLuongSP int
as
	update ChiTietGioHang 
	set maKH = @maKH, maSP = @maSP, soLuongSP = @soLuongSP
	where ID = @id
go

	-- delete --
drop procedure if exists delete_chiTietGioHang 
go
create procedure delete_chiTietGioHang
	@id int
as
	delete ChiTietGioHang 
	where ID = @id
go


---------- SanPham ----------
	-- load --
drop procedure if exists load_SanPham
go
create procedure load_SanPham
as
	select * from SanPham
go

	-- insert --
drop procedure if exists insert_SanPham
go
create procedure insert_SanPham
	@tenSP nvarchar(50), 
	@maTH int, @maLoaiSP int, 
	@hinhSP varchar(max),
	@giaSP float, 
	@soLuongSP int, 
	@ngayDangBan  date
as
	insert into SanPham(tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan) 
		values(@tenSP, @maTH, @maLoaiSP, @hinhSP, @giaSP, @soLuongSP, @ngayDangBan)
go

	-- update --
drop procedure if exists update_SanPham
go
create Procedure update_SanPham
	@maSP int, 
	@tenSP nvarchar(50), 
	@maTH int, 
	@maLoaiSP int, 
	@hinhSP varchar(max),
	@giaSP float, 
	@soLuongSP int, 
	@ngayDangBan  date
as
	update SanPham 
	set 
		tenSP = @tenSP, 
		maTH = @maTH, 
		maLoaiSP = @maLoaiSP,
		hinhSP = @hinhSP,
		giaSP = @giaSP, 
		soLuongSP = @soLuongSP, 
		ngayDangBan = @ngayDangBan
	where maSP = @maSP
go

	-- delete --
drop procedure if exists delete_SanPham
go
create procedure delete_SanPham
	@maSP int
as
	delete from SanPham 
	where maSP = @maSP
go

-------- ThuongHieu ----------
	-- load --
drop procedure if exists load_ThuongHieu
go
create procedure load_ThuongHieu
as
	select * from ThuongHieu
go

	-- insert --
drop procedure if exists insert_ThuongHieu
go
create Procedure insert_ThuongHieu
	@tenTH nvarchar(26), 
	@emailTH nvarchar(50), 
	@hinhTH varchar(max)
as
	insert into ThuongHieu (tenTH, emailTH, hinhTH) 
		values (@tenTH, @emailTH, @hinhTH)
go

	-- update --
drop procedure if exists update_ThuongHieu
go
create Procedure update_ThuongHieu
@maTH int, @tenTH nvarchar(26), @emailTH nvarchar(50), @hinhTH varchar(max)
as
	update ThuongHieu
	set  
		tenTH = @tenTH, 
		emailTH = @emailTH, 
		hinhTH = @hinhTH
	where maTH = @maTH
go

	-- delete --
drop procedure if exists delete_ThuongHieu
go
create Procedure delete_ThuongHieu
@maTH int
as
	delete from ThuongHieu 
	where maTH = @maTH 
go

-------- LoaiSP ----------
	-- load --
drop procedure if exists load_LoaiSP
go
create procedure load_LoaiSP
as
	select * from LoaiSP
go


	-- insert --
drop procedure if exists insert_LoaiSP
go
create Procedure insert_LoaiSP
@tenLoaiSP nvarchar(50)
as
	insert into LoaiSP(tenLoaiSP)
		values(@tenLoaiSP)
go

	-- update --
drop procedure if exists update_LoaiSP
go
create Procedure update_LoaiSP
	@maLoaiSP int, 
	@tenLoaiSP nvarchar(50)
as
	update LoaiSP
	set  tenLoaiSP = @tenLoaiSP
	where maLoaiSP = @maLoaiSP
go

	-- delete --
drop procedure if exists delete_LoaiSP
go
create Procedure delete_LoaiSP
	@maLoaiSP int
as
	delete from LoaiSP 
	where maLoaiSP = @maLoaiSP
go

---------- SanPham ----------
	-- load SanPham by maLoaiSP --
drop procedure if exists load_SanPham_by_maLoaiSP
go
create procedure load_SanPham_by_maLoaiSP
	@maLoaiSP int
as
	select * 
	from SanPham 
	where maLoaiSP = @maLoaiSP
go

	-- load SanPham by maSP --
drop procedure if exists load_SanPham_by_maSP
go
create procedure load_SanPham_by_maSP
	@maSP int
as
	select * 
	from SanPham 
	where maSP = @maSP
go

---Load DonHang theo maKH----------
drop procedure if exists load_DonHangbyMaKH
go
create procedure load_DonHangbyMaKH
@maKH int null
AS
BEGIN
	if(@maKH is null)
		select * from DonHang
	else
		select * from DonHang where maKH = @maKH
END;
GO

-- Load list Sản phẩm theo mã loại sản phẩm
drop procedure if exists load_ListSanPhambymaLoaiSP
go
create procedure load_ListSanPhambymaLoaiSP
@maLoaiSP int null
AS
BEGIN
	if(@maLoaiSP is null)
		select * from SanPham
	else
		select * from SanPham where maLoaiSP = @maLoaiSP
END;
GO

-- Load loại Sản phẩm theo mã sản phẩm ---
drop procedure if exists load_LoaiSanPhambymaSP
go
create procedure load_LoaiSanPhambymaSP
@maSP int null
AS
BEGIN
		select SanPham.maLoaiSP,tenLoaiSP 
		from SanPham  join LoaiSP on SanPham.maLoaiSP=LoaiSP.maLoaiSP
		where  SanPham.maSP=@maSP
END;
GO

-- Load list Bình Luận theo mã sản phẩm --
drop procedure if exists load_ListBinhLuanPhambymaSP
go
create procedure load_ListBinhLuanPhambymaSP
@maSP int null
AS
BEGIN
	select maCMT,users.hoTen,maSP,noiDung,ngayDang
	from users,binhluan 
	where users.maKH = binhluan.maKH and binhluan.maSP=@maSP
END;
GO


-- Load thương hiệu theo mã sản phẩm--
drop procedure if exists load_ThuongHieuPhambymaSP
go
create procedure load_ThuongHieuPhambymaSP
@maSP int null
AS
BEGIN
	select ThuongHieu.maTH,tenTH,emailTH,hinhTH 
	from ThuongHieu inner join SanPham on ThuongHieu.maTH=SanPham.maTH
	where SanPham.maSP=@maSP
END;
GO

-- update Num Product In GioHang --
drop procedure if exists update_NumProductInGioHang 
go
create procedure update_NumProductInGioHang  
	@id int,
	@soLuongSP int

as
	update ChiTietGioHang 
	set soLuongSP = @soLuongSP
	where ID = @id
go

-- find user --
drop procedure if exists find_User
go
create procedure find_User
	@username varchar(20),
	@password varchar(max)
as
	select *
	from Users
	where
		Users.userName = @username and 
		Users.[password] = @password;
go

-- Load info user theo maKH--
drop procedure if exists load_UserInfo_bymaKH
go
create procedure load_UserInfo_bymaKH
	@maKH int
as
	select * from Users
	where Users.maKH=@maKH
go

--load list Đơn hàng theo maKH--
drop procedure if exists load_listDonHang_bymaKH
go
create procedure load_listDonHang_bymaKH
	@maKH int
as
		select DonHang.maDH,DonHang.maKH,DonHang.tenNguoiNhan,DonHang.diaChi,DonHang.sdt ,DonHang.ngayMua,DonHang.tongTien,DonHang.maDV
        from DonHang join Users on DonHang.maKH=Users.maKH
        where  DonHang.maKH=@maKH
go

-- Load list Chi tiết đơn hàng theo maKH--
drop procedure if exists load_listChiTietDonHang_bymaKH
go
create procedure load_listChiTietDonHang_bymaKH
	@maKH int
as
	select ChiTietDonHang.ID,ChiTietDonHang.maDH,ChiTietDonHang.maSP,ChiTietDonHang.soLuongSP,ChiTietDonHang.thanhTien
           from ChiTietDonHang join DonHang on ChiTietDonHang.maDH=DonHang.maDH
           where  DonHang.maKH=@maKH
go

-- Edit thông tin User (Không thay đổi pass) theo maKH --
drop procedure if exists update_UserInfo_bymaKH
go
create procedure update_UserInfo_bymaKH
	@maKH int,
	@hoTen nvarchar(50),
	@sdt varchar(10),
	@ngaySinh date,
	@diaChi nvarchar(50)
as
	update Users
	set
		Users.hoTen = @hoTen,
		Users.sdt = @sdt,
		Users.ngaySinh = @ngaySinh,
		Users.diaChi = @diaChi
	where Users.maKH = @maKH
go

------------------------------------------
drop procedure if exists searchSP
go
create procedure searchSP
@search nvarchar(50)
AS
Begin
	select *
	from SanPham
	where SanPham.tenSP like @search
End
Go

--------------------------------------------------------
drop procedure if exists count_listSP
go
create procedure count_listSP
@search nvarchar(50)
AS
Begin
	select COUNT(*) as sl from SanPham where SanPham.tenSP like @search
End
Go

----------------------------------------------------------
drop procedure if exists sortSP_bygiaSP
go
create procedure sortSP_bygiaSP
@sortType nvarchar(10),
@min nvarchar(20),
@max nvarchar(20)
AS
BEGIN
	IF(@sortType = 'DESC')
		select * from SanPham where giaSP >= @min and giaSP <= @max order by giaSP DESC
	ELSE	
		select * from SanPham where giaSP >= @min and giaSP <= @max order by giaSP
END
go

---------------------------------------------------
drop procedure if exists sortSP_byngayDangBan
go
CREATE PROCEDURE sortSP_byngayDangBan
@min nvarchar(20),
@max nvarchar(20)
AS
BEGIN
	SELECT * 
	FROM SanPham
	WHERE giaSP >= @min and giaSP <= @max
	ORDER BY ngayDangBan DESC
END
Go

-----------------------------------------------
drop procedure if exists getMaxPrice
go
create procedure getMaxPrice
AS
Begin
	select max(giaSP) from SanPham
END
Go

----------------------------------------------------
drop procedure if exists load_SPBanNhieu
go
create procedure load_SPBanNhieu
AS 
Begin
		select top(10) maSP,tenSP, maTH, maLoaiSP, hinhSP, giaSP, soLuongSP, ngayDangBan
	from SanPham as b, (select maSP as sp, count(soLuongSP) as SL
					from ChiTietDonHang 
					group by maSP) as A
	where b.maSP = A.sp and soLuongSP > 0
	order by SL desc
end
go

-----------------------------------------------------
drop procedure if exists load_ThuongHieu_byMaTH
go
create procedure load_ThuongHieu_byMaTH
@maTH nvarchar(10)
AS
BEGIN
	select * from ThuongHieu where maTH = @maTH
END
go

-----------------
drop procedure if exists getChiTietGioHangByMaKH
go
create procedure getChiTietGioHangByMaKH
	@maKH int
as
BEGIN
	Select ChiTietGioHang.ID, ChiTietGioHang.maSP,ChiTietGioHang.soLuongSP, tenSP,giaSP,hinhSP,ChiTietGioHang.thanhTien
                From ChiTietGioHang, SanPham, Users
                Where ChiTietGioHang.maKH = Users.maKH  and ChiTietGioHang.maSP = SanPham.maSP
                and Users.maKH =@maKH;
END;
go

--
drop procedure if exists deleteGioHangBymaKH
go
create procedure deleteGioHangBymaKH
	@maKH int
as

BEGIN
	delete ChiTietGioHang 
    where maKH = @maKH;
END;
go

--
drop procedure if exists insertUserAdmin
go
create procedure insertUserAdmin
	@hoTen nvarchar(50),
	@sdt varchar(10),
	@ngaySinh date,
	@diaChi nvarchar(50),
	@username varchar(20),
	@password varchar(MAX),
	@roleID int
as
BEGIN
	insert into Users(hoTen, sdt, ngaySinh, diaChi, username, password,roleID)
                values(@hoTen,@sdt,@ngaySinh,@diaChi,@username,@password,@roleID)
END;
go


drop procedure if exists Admin_editUser
go
create procedure Admin_editUser
	@hoTen nvarchar(50),
	@sdt varchar(10),
	@ngaySinh date,
	@diaChi nvarchar(50),
	@username varchar(20),
	@roleID int,
	@maKH int
as
BEGIN
	update Users
	set
		hoTen = @hoTen,
		sdt = @sdt,
		ngaySinh = @ngaySinh,
		diaChi = @diaChi,
		userName = @userName,
		roleID = @roleID
	where maKh = @maKH
END;
go

--
drop procedure if exists getDoanhThuTheoNgay
go
create procedure getDoanhThuTheoNgay
as
BEGIN
	Select ngayMua, Sum(tongTien)as tongTien
      From DonHang
      Group by ngayMua
END;
go

--
drop procedure if exists  EditUserInfo_password
go
create procedure EditUserInfo_password
    @username varchar(20),
    @new_password varchar(MAX),
    @old_password varchar(MAX)
as
begin

    declare @sql varchar(max),@oldpass varchar(MAX)
    select @oldpass = password
    from Users
    where userName = @username;
    --Check password cũ
    if @oldpass = @old_password
        begin
            set @sql = 'alter login ' + @username + ' with password = ''' + @new_password + ''' OLD_PASSWORD = ''' + @old_password + ''''
            exec (@sql)

            Update Users
            set password = @new_password
            where username= @username
              and password = @old_password
        end
end
go


-- find user --
drop procedure if exists findUserByUserName
go
create procedure findUserByUserName
    @username varchar(20)
as
select *
from Users
where
        Users.userName = @username
go

-- CREATE FUNCTION --
	
-- Tính tổng tiền --
drop FUNCTION if EXISTS funcTongTienInCart
go
Create Function funcTongTienInCart
(@maKH int)
returns float
as
begin
declare @tongTien float

set @tongTien = (Select Sum(thanhTien)as TongTien
				From ChiTietGioHang
				Where ChiTietGioHang.maKH = @maKH 
				Group by ChiTietGioHang.maKH)
return @tongTien
end;
go

--getMaDHMaxOfMaKH--
drop FUNCTION if EXISTS getMaDHMaxOfMaKH
go
CREATE FUNCTION getMaDHMaxOfMaKH
( @maKH INT )
RETURNS INT
AS
BEGIN
DECLARE @maDH INT;
SET @maDH = (select MAX(maDH) as maDH
			from DonHang
			group by maKH
			having maKH=@maKH)
RETURN @maDH;
END;
go