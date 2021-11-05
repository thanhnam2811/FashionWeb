use QuanLiShop
go

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