/*CREATE DATABASE Extra_Exercises_SQL_Basic*/
USE Extra_Exercises_SQL_Basic;
CREATE TABLE Khach_Hang(
    MaKH VARCHAR(50) PRIMARY KEY,
    TenKH VARCHAR(100) NOT NUll,
    Email VARCHAR(50) NOT NULL,
    SoDT INT NOT NULL,
    DiaChi VARCHAR(150) NOT NULL
);
CREATE TABLE Thanh_Toan(
    MaTT VARCHAR(5) PRIMARY KEY,
    PhuongThucThanhToan VARCHAR(30) NOT NULL
);
CREATE TABLE Don_Hang(
    MaDH VARCHAR(20) PRIMARY KEY,
    MaKH VARCHAR(50) FOREIGN KEY REFERENCES Khach_Hang(MaKH) ON DELETE CASCADE NOT NULL,
    MaTT VARCHAR(5) FOREIGN KEY REFERENCES Thanh_Toan(MaTT) ON DELETE CASCADE NOT NULL,
    NgayDat DATE NOT NULL
);
CREATE TABLE DM_San_Pham(
    MaDM VARCHAR(10) PRIMARY KEY,
    TenDanhMuc VARCHAR(100) NOT NULL,
    MoTa TEXT NOT NULL
);
CREATE TABLE San_Pham(
    MaSP VARCHAR(6) PRIMARY KEY, 
    MaDM VARCHAR(10) FOREIGN KEY REFERENCES DM_San_Pham(MaDM) ON DELETE CASCADE NOT NULL,
    TenSP VARCHAR(266) NOT NULL,
    GiaTien INT NOT NULL,
    SoLuong INT NOT NULL,
    XuatXu VARCHAR(50)
);
CREATE TABLE Chi_Tiet_Don_Hang(
    MaDH VARCHAR(20) FOREIGN KEY REFERENCES Don_Hang(MaDH) ON DELETE CASCADE NOT NULL,
    MaSP VARCHAR(6) FOREIGN KEY REFERENCES San_Pham(MaSP) ON DELETE CASCADE NOT NULL,
    SoLuong INT NOT NULL,
    TongTien INT NOT NULL
);
INSERT INTO Khach_Hang(MaKH, TenKH, Email, SoDT, DiaChi) VALUES
('KH001','Tran Van An','antv@gmail.com',0905123564,'Lang Son'),
('KH002','Phan Phuoc','phuocp@gmail.com',0932568984,'Da Nang'),
('KH003','Tran Huu Anh','anhth@gmail.com',0901865232,'Ha Noi');
INSERT INTO DM_San_Pham(MaDM, TenDanhMuc, MoTa) VALUES
('DM01','Thoi Trang Nu','vay, ao danh cho nu'),
('DM02','Thoi Trang Nam','quan danh cho nam'),
('DM03','Trang suc','danh cho nu va nam');
INSERT INTO San_Pham(MaSP, MaDM, TenSP, SoLuong, GiaTien, XuatXu) VALUES
('SP001','DM01','Dam Maxi',200,195000,'VN'),
('SP002','DM01','Tui Da My',50,3000000,'HK'),
('SP003','DM02','Lac tay Uc',300,300000,'HQ');
INSERT INTO Thanh_Toan(MaTT, PhuongThucThanhToan) VALUES
('TT01','Visa'),
('TT02','Master Card'),
('TT03','JCB');
INSERT INTO Don_Hang(MaDH, MaKH, MaTT, NgayDat) VALUES
('DH001','KH002','TT01','2014-10-20'),
('DH002','KH002','TT01','2015-05-15'),
('DH003','KH001','TT03','2015-04-20');
INSERT INTO Chi_Tiet_Don_Hang(MaDH, MaSP, SoLuong, TongTien) VALUES
('DH001','SP002', 3, 56000),
('DH003','SP001', 10, 27444),
('DH002','SP002', 10, 67144);
/*Câu 1*/
SELECT p.MaSP, p.TenSP, dm.TenDanhMuc, p.GiaTien, p.SoLuong, p.XuatXu FROM San_Pham p LEFT JOIN DM_San_Pham dm ON p.MaDM = dm.MaDM
/*Câu 2*/
DELETE c FROM Khach_Hang c WHERE c.DiaChi = 'Lang Son'
/*Câu 3*/
UPDATE San_Pham SET XuatXu = 'Viet Name' WHERE XuatXu = 'VN'
/*Câu 4*/
SELECT p.MaSP, dm.TenDanhMuc, p.TenSP, p.GiaTien, p.SoLuong, p.XuatXu FROM San_Pham p INNER JOIN DM_San_Pham dm ON p.MaDM = dm.MaDM
WHERE (p.SoLuong > 50 AND dm.TenDanhMuc = 'Thoi trang nu') OR (p.SoLuong > 100 AND dm.TenDanhMuc = 'Thoi trang nam')
/*Câu 5*/
SELECT * FROM Khach_Hang c WHERE c.TenKH LIKE 'A%' AND LEN(c.TenKH) = 5
/*Câu 6*/
SELECT p.MaSP, dm.TenDanhMuc, p.TenSP, p.GiaTien, p.SoLuong, p.XuatXu FROM San_Pham p INNER JOIN DM_San_Pham dm ON dm.MaDM = p.MaDM
GROUP BY p.MaSP, dm.TenDanhMuc, p.TenSP, p.GiaTien, p.SoLuong, p.XuatXu
ORDER BY p.TenSP DESC, p.SoLuong ASC
/*Câu 7*/
SELECT c.TenKH, COUNT(ctdh.MaSP) ProductIsOrdered, ctdh.SoLuong FROM Chi_Tiet_Don_Hang ctdh 
INNER JOIN Don_Hang o ON o.MaDH = ctdh.MaDH
INNER JOIN Khach_Hang c ON c.MaKH = o.MaKH
WHERE ctdh.SoLuong > 5
GROUP BY c.TenKH, ctdh.SoLuong
/*Câu 8*/
SELECT DISTINCT c.TenKH FROM Khach_Hang c 
/*Câu 9*/
SELECT c.MaKH, c.TenKH, p.TenSP, ctdh.SoLuong, o.NgayDat, p.GiaTien, ctdh.TongTien
FROM Chi_Tiet_Don_Hang ctdh
INNER JOIN Don_Hang o ON ctdh.MaDH = o.MaDH
INNER JOIN San_Pham p ON p.MaSP = ctdh.MaSP
INNER JOIN Khach_Hang c ON c.MaKH = o.MaKH
WHERE c.MaKH IN 
(SELECT dh.MaKH FROM Don_Hang dh)
/*Câu 10*/
SELECT c.MaKH, c.TenKH, o.MaDH, p.TenSP, od.SoLuong, od.TongTien
FROM Khach_Hang c 
FULL JOIN Don_Hang o ON c.MaKH = o.MaKH
FULL JOIN Chi_Tiet_Don_Hang od ON od.MaDH = o.MaDH
FULL JOIN San_Pham p ON p.MaSP = od.MaSP
WHERE c.MaKH IN
(
    SELECT dh.MaKH FROM Don_Hang dh
)
OR c.MaKH NOT IN 
(
    SELECT dh.MaKH FROM Don_Hang dh
)
/*Câu 11*/
SELECT c.MaKH, c.TenKH FROM Khach_Hang c 
JOIN Don_Hang o ON c.MaKH = o.MaKH
JOIN Thanh_Toan pay ON pay.MaTT = o.MaTT
WHERE pay.PhuongThucThanhToan = 'Visa' OR pay.PhuongThucThanhToan = 'JCB'
/*Câu 12*/
SELECT c.MaKH, c.TenKH
FROM Khach_Hang c 
LEFT JOIN Don_Hang o ON c.MaKH = o.MaKH
GROUP BY c.MaKH, c.TenKH, o.MaKH
HAVING o.MaKH IS NULL
/*Câu 13*/
SELECT o.MaKH, c.TenKH, p.TenSP, ctdh.SoLuong, p.GiaTien, pay.PhuongThucThanhToan, o.NgayDat, ctdh.TongTien FROM Don_Hang o 
INNER JOIN Khach_Hang c ON c.MaKH = o.MaKH
INNER JOIN Chi_Tiet_Don_Hang ctdh ON o.MaDH = ctdh.MaDH
INNER JOIN San_Pham p ON p.MaSP = ctdh.MaSP
INNER JOIN Thanh_Toan pay ON pay.MaTT = o.MaTT
GROUP BY o.MaKH, c.TenKH, p.TenSP, p.GiaTien, pay.PhuongThucThanhToan, c.DiaChi, ctdh.SoLuong, o.NgayDat, ctdh.TongTien
HAVING COUNT(o.MaKH) = 1 AND c.DiaChi = 'Da Nang'