use QuanLiShop
go

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