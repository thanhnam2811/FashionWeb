use QuanLiShop
go

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
------ User ------
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
	if not exists(SELECT name
                 FROM master.sys.server_principals
                 WHERE name = @username)
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






	




