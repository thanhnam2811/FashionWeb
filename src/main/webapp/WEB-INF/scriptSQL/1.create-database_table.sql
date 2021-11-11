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




