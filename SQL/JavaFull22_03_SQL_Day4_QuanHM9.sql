/*Exercise Sql basic*/
/*Exercise 1*/
ALTER DATABASE Exercise_SQL_Basic COLLATE Latin1_General_100_CI_AI_SC_UTF8
USE Exercise_SQL_Basic;
CREATE TABLE LoaiSP(
    MaLoaiSP VARCHAR(100) PRIMARY KEY,
    TenLoaiSP VARCHAR(255)
);
CREATE TABLE SanPham(
    MaSP VARCHAR(100) PRIMARY KEY,
    TenSP VARCHAR(255),
    MaLoaiSP VARCHAR(100) FOREIGN KEY REFERENCES LOAISP(MaLoaiSP),
    GiaBan INT,
);
CREATE TABLE NhanVien(
    MaNV VARCHAR(100) PRIMARY KEY,
    HoTenNV VARCHAR(255),
    GioiTinh VARCHAR(10),
    QueQuan VARCHAR(100),
    Tuoi INT
);
CREATE TABLE BanHang(
    MaNV VARCHAR(100) FOREIGN KEY REFERENCES NHANVIEN(MaNV),
    MaSP VARCHAR(100) FOREIGN KEY REFERENCES SANPHAM(MaSP),
    SoLuongDaBan INT
);
/*Question 1*/
INSERT INTO NhanVien(MaNV, HoTenNV, GioiTinh, QueQuan, Tuoi) VALUES ('NV01', 'Nguyễn Chí Phèo', 'Nam', 'Quảng Trị', 18);
/*Setting up before question 2*/
INSERT INTO NhanVien(MaNV, HoTenNV, GioiTinh, QueQuan, Tuoi) VALUES ('NV02', 'Thị Nở', 'Nữ', 'Kon Tum', 40);
/*Question 2*/
DELETE FROM NhanVien WHERE NhanVien.QueQuan = 'Kon Tum' AND NhanVien.GioiTinh = 'Nữ';
/*Setting up before question 3*/
INSERT INTO LoaiSP(MaLoaiSP, TenLoaiSP) VALUES
('TYPE001', 'Loại gì đó'),
('TYPE002', 'Bột giặc'),
('TYPE003', 'Mỹ phẩm'),
('TYPE004', 'Loại sản phẩm 1'),
('TYPE005', 'Loại sản phẩm 2'),
('TYPE006', 'Loại sản phẩm 3'),
('TYPE007', 'Loại sản phẩm 4'),
('TYPE008', 'Kem dưỡng da');
INSERT INTO SanPham(MaSP, TenSP, MaLoaiSP, GiaBan) VALUES
('SP001','Sản phẩm gì đó', 'TYPE001', 20000),
('SP002','Đá', 'TYPE001', 10000000),
('SP003','OMO', 'TYPE002', 57000),
('SP004','Sản phẩm 1', 'TYPE008', 10000),
('SP005','Sản phẩm 2', 'TYPE004', 100000),
('SP006','Sản phẩm 3', 'TYPE006', 50000),
('SP007','Sản phẩm 4', 'TYPE005', 0);
/*Question 3*/
UPDATE SanPham  SET GiaBan *= 2 WHERE SanPham.MaLoaiSP = 'TYPE001';
/*Question 4*/
SELECT * FROM NhanVien;
/*Question 5*/
SELECT * FROM NhanVien n WHERE n.Tuoi >= 23 AND n.QueQuan = 'Bình Định';
/*Setting up before question 6*/
INSERT INTO NhanVien(MaNV, HoTenNV, GioiTinh, QueQuan, Tuoi) VALUES 
('NV03', 'Nhân viên 2', 'Nam', 'Thanh Hóa', 45),
('NV04', 'Nhân viên 3', 'Nữ', 'Hà Nội', 25),
('NV05', 'Nhân viên 4', 'Nữ', 'TP.HCM', 31),
('NV06', 'Nhân viên 5', 'Nam', 'Cà Mau', 24),
('NV07', 'Nhân viên 6', 'Nữ', 'Phú Yên', 28),
('NV08', 'Nhân viên 7', 'Nam', 'Nam Định', 23),
('NV09', 'Nhân viên 8', 'Nam', 'Quảng Ngãi', 21);
INSERT INTO BanHang(MaNV, MaSP, SoLuongDaBan) VALUES 
('NV03', 'SP001', 3),
('NV03', 'SP002', 3),
('NV03', 'SP003', 3),
('NV03', 'SP004', 3),
('NV03', 'SP002', 3),
('NV03', 'SP001', 3),
('NV03', 'SP002', 3);
/*Question 6*/
SELECT p.MaSP FROM SanPham p RIGHT JOIN BanHang s ON p.MaSP = s.MaSP WHERE s.SoLuongDaBan > 0;
/*Question 7*/
SELECT * FROM NhanVien n WHERE n.HoTenNV LIKE 'Nguyễn%';
/*Question 8*/
SELECT * FROM NhanVien n WHERE n.HoTenNV LIKE '%Hoa';
/*Question 9*/
SELECT * FROM SanPham p WHERE LEN(p.TenSP) >= 12;
/*Question 10*/
SELECT * FROM SanPham p LEFT JOIN LoaiSP ls ON p.MaLoaiSP = ls.MaLoaiSP WHERE ls.TenLoaiSP = 'Mỹ Phẩm';
/*Question 11*/
SELECT * FROM SanPham p LEFT JOIN BanHang s ON p.MaSP = s.MaSP WHERE p.GiaBan < 20000 OR s.SoLuongDaBan = 0;
/*Question 12*/
SELECT s.MaNV, n.HoTenNV
FROM BanHang s 
RIGHT JOIN SanPham p ON s.MaSP = p.MaSP
INNER JOIN NhanVien n ON n.MaNV = s.MaNV
GROUP BY s.MaNV, p.TenSP, n.HoTenNV
HAVING p.TenSP = 'OMO' 
AND s.MaNV NOT IN 
  (
      SELECT DISTINCT bh.MaNV FROM BanHang bh 
      JOIN SanPham sp ON bh.MaSP = sp.MaSP
      WHERE sp.TenSP != 'OMO'
  )
UNION ALL
SELECT s.MaNV, n.HoTenNV FROM BanHang s 
INNER JOIN NhanVien n ON n.MaNV = s.MaNV
WHERE s.MaSP IS NULL
GROUP BY s.MaNV, n.HoTenNV
/*Question 13*/
SELECT n.MaNV FROM BanHang s 
JOIN (SELECT bh.MaNV, COUNT(*) AS TotalProductIsSaled FROM BanHang bh GROUP BY bh.MaNV) 
AS x ON s.MaNV = x.MaNV
RIGHT JOIN NhanVien n 
ON n.MaNV = s.MaNV
WHERE x.TotalProductIsSaled IS NULL AND n.QueQuan = 'Gia Lai';
/*Question 14*/
SELECT p.MaSP, p.TenSP, p.GiaBan, t.TenLoaiSP FROM SanPham p INNER JOIN LoaiSP t ON t.MaLoaiSP = p.MaLoaiSP WHERE p.GiaBan IS NOT NULL AND p.GiaBan > 0;
/*QUestion 15*/
SELECT n.MaNV, n.HoTenNV, n.GioiTinh, n.QueQuan, p.MaSP, p.TenSP, t.TenLoaiSP, x.TotalProductIsSaled AS SoLuongDaBan
FROM BanHang s
JOIN (SELECT bh.MaNV, COUNT(*) AS TotalProductIsSaled FROM BanHang bh GROUP BY bh.MaNV)
AS x 
ON s.MaNV = x.MaNV
RIGHT JOIN NhanVien n 
ON n.MaNV = s.MaNV
RIGHT JOIN SanPham p
ON p.MaSP = s.MaSP
FULL JOIN LoaiSP t
ON p.MaLoaiSP = t.MaLoaiSP
WHERE x.TotalProductIsSaled IS NOT NULL;
/*Question 16*/
SELECT p.MaLoaiSP, t.TenLoaiSP FROM BanHang s 
JOIN (SELECT bh.MaSP, COUNT(*) AS ProductIsSaled FROM BanHang bh GROUP BY bh.MaSP) AS x 
ON s.MaSP = x.MaSP
RIGHT JOIN SanPham p 
ON p.MaSP = s.MaSP
FULL JOIN LoaiSP t 
ON t.MaLoaiSP = p.MaLoaiSp
WHERE x.ProductIsSaled IS NOT NULL
GROUP BY p.MaLoaiSP, t.TenLoaiSP;
/*Question 17*/
SELECT DISTINCT n.HoTenNV FROM NhanVien n;
/*Question 18*/
SELECT n.MaNV, n.HoTenNV, ISNULL(n.QueQuan, 'Cõi trên xuống') AS QueQuan FROM NhanVien n;
/*Question 19*/
SELECT n.MaNV, n.HoTenNV, n.QueQuan, n.GioiTinh, n.Tuoi FROM NhanVien n INNER JOIN (
    SELECT nv.MaNV, MAX(nv.Tuoi) AS MaxAge FROM NhanVien nv GROUP BY nv.MaNV
) AS x ON n.MaNV = x.MaNV ORDER BY n.MaNV DESC OFFSET 0 ROWS FETCH FIRST 5 ROWS ONLY;
/*Question 20*/
SELECT * FROM NhanVien n WHERE n.Tuoi >= 25 AND n.Tuoi <= 35;

/*Exercise 2*/
/*Setting up before question 1*/
ALTER TABLE SanPham ADD SoLuong INT;
UPDATE SanPham SET SanPham.SoLuong = 3 WHERE SanPham.MaSP = 'SP001';
UPDATE SanPham SET SanPham.SoLuong = 5 WHERE SanPham.MaSP = 'SP002';
UPDATE SanPham SET SanPham.SoLuong = 7 WHERE SanPham.MaSP = 'SP003';
UPDATE SanPham SET SanPham.SoLuong = 9 WHERE SanPham.MaSP = 'SP004';
UPDATE SanPham SET SanPham.SoLuong = 2 WHERE SanPham.MaSP = 'SP005';
UPDATE SanPham SET SanPham.SoLuong = 28 WHERE SanPham.MaSP = 'SP006';
UPDATE SanPham SET SanPham.SoLuong = 18 WHERE SanPham.MaSP = 'SP007';
/*Question 1*/
SELECT p.MaLoaiSP, p.SoLuong FROM SanPham p;
/*Question 2*/
SELECT n.MaNV, n.HoTenNV, s.MaSP FROM NhanVien n INNER JOIN BanHang s ON s.MaNV = n.MaNV GROUP BY n.MaNV, n.HoTenNV, s.MaSP HAVING COUNT(*) IS NOT NULL;
/*Question 3*/
SELECT n.MaNV, n.HoTenNV, s.MaSP FROM NhanVien n INNER JOIN BanHang s ON s.MaNV = n.MaNV;
/*Question 4*/
SELECT p.MaSP, p.TenSP, t.MaLoaiSP, t.TenLoaiSP FROM SanPham p INNER JOIN LoaiSP t ON t.MaLoaiSP = p.MaLoaiSP;
/*Question 5*/
SELECT n.MaNV, n.HoTenNV, x.ProductIsSaled AS SanPhamDaBan FROM BanHang s 
JOIN(SELECT bh.MaSP, COUNT(*) AS ProductIsSaled FROM BanHang bh GROUP BY bh.MaSP) AS x
ON s.MaSP = x.MaSP
LEFT JOIN NhanVien n ON n.MaNV = s.MaNV
WHERE x.ProductIsSaled > 10;
/*Question 6*/
SELECT n.MaNV, n.HoTenNV, x.ProductIsSaled AS SanPhamDaBan FROM BanHang s 
JOIN(
    SELECT bh.MaSP, COUNT(*) AS ProductIsSaled FROM BanHang bh 
    JOIN SanPham sp ON sp.MaSP = bh.MaSP 
    JOIN LoaiSP lp ON lp.MaLoaiSP = sp.MaLoaiSP
    WHERE lp.TenLoaiSP = 'Kem dưỡng da'
    GROUP BY bh.MaSP
    ) AS x
ON s.MaSP = x.MaSP
LEFT JOIN NhanVien n ON n.MaNV = s.MaNV
WHERE x.ProductIsSaled > 10;
/*Question 7*/
SELECT n.GioiTinh, n.QueQuan, n.Tuoi FROM NhanVien n GROUP BY n.GioiTinh, n.QueQuan, n.Tuoi;
/*Question 8*/
SELECT s.MaNV, p.TenSP, t.TenLoaiSP FROM BanHang s 
JOIN (SELECT bh.MaSP, COUNT(*) AS ProductSaled FROM BanHang bh GROUP BY bh.MaSP) AS x 
ON x.MaSP = s.MaSP
JOIN SanPham p ON p.MaSP = s.MaSP
JOIN LoaiSP t ON p.MaLoaiSP = t.MaLoaiSP
WHERE x.ProductSaled IS NOT NULL
GROUP BY s.MaNV, p.TenSP, t.TenLoaiSP;
/*Question 9*/
SELECT COUNT(DISTINCT s.MaNV) AS SoLuongNhanVien FROM BanHang s 
JOIN SanPham p ON p.MaSP = s.MaSP
WHERE p.MaSP = 'SP001' GROUP BY s.MaNV;
/*Question 10*/
SELECT COUNT(DISTINCT s.MaNV) AS SoLuongNhanVien FROM BanHang s
JOIN SanPham p ON p.MaSP = s.MaSP
RIGHT JOIN LoaiSP t ON t.MaLoaiSP = p.MaLoaiSP
WHERE t.TenLoaiSP = 'Bột giặc' GROUP BY s.MaNV;
