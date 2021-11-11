use QuanLiShop
go

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