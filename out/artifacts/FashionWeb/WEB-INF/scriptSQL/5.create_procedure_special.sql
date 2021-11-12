use QuanLiShop
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
