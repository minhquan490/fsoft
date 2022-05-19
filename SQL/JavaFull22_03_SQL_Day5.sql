/*Assignment 3_Op2: Order Management*/
/*CREATE DATABASE Order_Management COLLATE Latin1_General_100_CI_AI_SC_UTF8*/
USE Order_Management;
/*Question 1*/
CREATE TABLE San_Pham(
    Ma_SP INT PRIMARY KEY IDENTITY(1,1),
    Ten_SP VARCHAR(100) COLLATE Latin1_General_100_CI_AI_SC_UTF8,
    Don_Gia INT
);
CREATE TABLE Khach_Hang(
    Ma_KH INT PRIMARY KEY IDENTITY(1,1),
    Ten_KH VARCHAR(100) COLLATE Latin1_General_100_CI_AI_SC_UTF8,
    Phone_No INT,
    Ghi_Chu VARCHAR(100) COLLATE Latin1_General_100_CI_AI_SC_UTF8
);
CREATE TABLE Don_Hang(
    Ma_DH INT PRIMARY KEY IDENTITY(1,1),
    Ngay_DH DATE,
    Ma_SP INT FOREIGN KEY REFERENCES San_Pham(Ma_SP),
    So_Luong INT,
    Ma_KH INT FOREIGN KEY REFERENCES Khach_Hang(Ma_KH)
);
INSERT INTO San_Pham(Ten_SP, Don_Gia) VALUES ('Sản phẩm 1', 10000);
INSERT INTO Khach_Hang(Ten_KH, Phone_No, Ghi_Chu) VALUES ('Khách Hàng 1', 0123456789, 'ghi chú 1');
INSERT INTO Don_Hang(Ngay_DH, Ma_SP, So_Luong, Ma_KH) VALUES ('2022-05-01', 1, 13, 1);
GO
CREATE VIEW [Don_Hang_View] 
AS SELECT c.Ten_KH, o.Ngay_DH, p.Ten_SP, o.So_Luong, o.So_Luong * p.Don_Gia AS Thanh_Tien
FROM Don_Hang o
INNER JOIN Khach_Hang c ON o.Ma_KH = c.Ma_KH
INNER JOIN San_Pham p ON p.Ma_SP = o.Ma_SP;
GO
DROP TABLE Don_Hang;
DROP TABLE San_Pham;
DROP TABLE Khach_Hang;
DROP VIEW Don_Hang_View;
/*Question 2*/
CREATE TABLE Department(
    Department_Number INT PRIMARY KEY IDENTITY(1,1),
    Department_Name VARCHAR(200) NOT NULL
);
CREATE TABLE Employee_Table(
    Employee_Number INT PRIMARY KEY IDENTITY(1,1),
    Employee_Name VARCHAR(100) NOT NULL,
    Department_Number INT FOREIGN KEY REFERENCES Department(Department_Number)
);
CREATE TABLE Skill(
    Skill_Number INT PRIMARY KEY IDENTITY(1,1),
    Skill_Name VARCHAR(50) NOT NULL
);
CREATE TABLE Employee_Skill_Table(
    Employee_Number INT FOREIGN KEY REFERENCES Employee_Table(Employee_Number),
    Skill_Code INT FOREIGN KEY REFERENCES Skill(Skill_Number),
    Date_Registered DATE NOT NULL
);
/*1*/
INSERT INTO Department(Department_Name) VALUES 
('Phòng ban 1'),
('Phòng ban 2'),
('Phòng ban 3');
INSERT INTO Skill(Skill_Name) VALUES
('Java'),
('C++'),
('C#'),
('Python');
INSERT INTO Employee_Table(Employee_Name, Department_Number) VALUES
('Employee 1', 1),
('Employee 2', 2),
('Employee 3', 3),
('Employee 4', 1);
INSERT INTO Employee_Skill_Table(Employee_Number, Skill_Code, Date_Registered) VALUES
(1,1,'2010-04-03'),
(2,1,'2010-10-03'),
(2,2,'2010-04-07'),
(3,3,'2021-12-12'),
(3,4,'2017-02-01'),
(3,1,'2022-01-04'),
(4,3,'2022-04-30');
/*2*/
/*Using join*/
SELECT e.Employee_Name, s.Skill_Name FROM Employee_Table e 
RIGHT JOIN Employee_Skill_Table es ON e.Employee_Number = es.Employee_Number
RIGHT JOIN Skill s ON s.Skill_Number = es.Skill_Code
WHERE s.Skill_Name = 'Java';
/*Using subqueries*/
SELECT e.Employee_Name
FROM Employee_Table e, Employee_Skill_Table es
WHERE es.Employee_Number IN 
(SELECT est.Employee_Number 
FROM Employee_Skill_Table est
WHERE est.Employee_Number = e.Employee_Number)
AND es.Skill_Code IN 
(SELECT sk.Skill_Number FROM Skill sk WHERE sk.Skill_Name = 'Java')
AND e.Employee_Number = es.Employee_Number;
/*3*/
SELECT d.Department_Name, e.Employee_Name
FROM Department d
INNER JOIN Employee_Table e ON e.Department_Number = d.Department_Number
GROUP BY d.Department_Name, e.Employee_Name
HAVING COUNT(e.Department_Number) >= 3
ORDER BY d.Department_Name ASC
/*4*/
SELECT e.Employee_Name, COUNT(es.Skill_Code) Total_Skill
FROM Employee_Table e, Employee_Skill_Table es
WHERE es.Employee_Number IN 
(SELECT est.Employee_Number 
FROM Employee_Skill_Table est
WHERE est.Employee_Number = e.Employee_Number)
AND es.Skill_Code IN 
(SELECT sk.Skill_Number FROM Skill sk)
AND e.Employee_Number = es.Employee_Number
GROUP BY e.Employee_Name
HAVING COUNT(es.Skill_Code) > 1
/*5*/
GO
CREATE VIEW Employee_View(Employee_Number, Employee_Name, Department_Name) AS
SELECT e.Employee_Number, e.Employee_Name, d.Department_Name
FROM Employee_Table e
INNER JOIN Department d ON e.Department_Number = d.Department_Number
RIGHT JOIN Employee_Skill_Table es ON es.Employee_Number = e.Employee_Number
GROUP BY e.Employee_Number, e.Employee_Name, d.Department_Name
HAVING COUNT(es.Skill_Code) > 1
GO