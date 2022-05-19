/*Assignment 3_Opt1: Employee Management System*/
/*CREATE DATABASE Employee_Management_System*/
USE Employee_Management_System;
CREATE TABLE T_Status(
    StatusCode INT PRIMARY KEY IDENTITY(0,1),
    StatusName VARCHAR(50) NOT NULL
);
CREATE TABLE Department(
    DeptNo INT PRIMARY KEY IDENTITY(1,1),
    DeptName VARCHAR(100) NOT NULL,
    Note TEXT
);
CREATE TABLE Employee(
    EmpNo VARCHAR(50) PRIMARY KEY,
    EmpName VARCHAR(100) NOT NULL,
    BirthDate DATE NOT NULL,
    DeptNo INT FOREIGN KEY REFERENCES Department(DeptNo) ON DELETE SET NULL,
    MgrNo INT NOT NULL,
    StartDate DATE,
    Salary DECIMAL(14,4) NOT NULL,
    EmpLevel INT CHECK(EmpLevel >= 1 AND EmpLevel <= 7) NOT NULL,
    EmpStatus INT FOREIGN KEY REFERENCES T_Status(StatusCode) ON DELETE SET NULL,
    Note TEXT
);
CREATE TABLE Skill(
    SKillNo INT PRIMARY KEY IDENTITY(1,1),
    SkillName VARCHAR(50) NOT NULL,
    Note TEXT
);
CREATE TABLE Emp_Skill(
    SkillNo INT FOREIGN KEY REFERENCES Skill(SkillNo) ON UPDATE CASCADE NOT NULL,
    EmpNo VARCHAR(50) FOREIGN KEY REFERENCES Employee(EmpNo) ON UPDATE CASCADE NOT NULL,
    SkillLevel INT CHECK(SkillLevel >= 1 AND SkillLevel <= 3) NOT NULL,
    RegDate DATE NOT NULL,
    ESDescription TEXT,
    CONSTRAINT PK_Emp_Skill PRIMARY KEY (SkillNo, EmpNo)
);

ALTER TABLE Employee ADD Email VARCHAR(100) UNIQUE NOT NULL;
ALTER TABLE Employee ADD CONSTRAINT DF_EmpStatus DEFAULT 0 FOR EmpStatus;
ALTER TABLE Employee ADD CONSTRAINT DF_MgrNo DEFAULT 0 FOR MgrNo;
ALTER TABLE Emp_Skill DROP COLUMN ESDescription;

GO
DECLARE @i INT;
DECLARE @statusName VARCHAR(50);
DECLARE @deptName VARCHAR(100);
DECLARE @skillName VARCHAR(50);
DECLARE @n INT;
DECLARE @s INT;
SET @i = 0;
WHILE @i <= 7
BEGIN
    IF @i = 0
    BEGIN
        SET @statusName = 'Working';
        SET @deptName = 'Department 1';
        SET @skillName = 'Java';
        SET @n = @i + 1;
        SET @s = @i;
    END;

    IF @i = 1
    BEGIN
        SET @statusName = 'Unpaid leave';
        SET @deptName = 'Department 2';
        SET @skillName = 'C++';
        SET @n = @i + 1;
        SET @s = @i;
    END;

    IF @i = 2
    BEGIN
        SET @statusName = 'Out';
        SET @deptName = 'Department 3';
        SET @skillName = 'C#';
        SET @n = @i + 1;
        SET @s = @i;
    END;

    IF @i <= 2
    BEGIN
        INSERT INTO T_Status(StatusName) VALUES (@StatusName);
        INSERT INTO Department(DeptName) VALUES (@deptName);
        INSERT INTO Skill(SkillName) VALUES (@skillName);
    END;

    INSERT INTO Employee(EmpNo, EmpName, BirthDate, DeptNo, MgrNo, StartDate, Salary, EmpLevel, EmpStatus, Email) VALUES 
    ('E00' + CAST(@i + 1 as varchar), 'Employee' + ' ' + CAST(@i + 1 as varchar), DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 15300), '1980-01-01'), FLOOR(RAND()*(@n-1+1))+1, FLOOR(RAND()*(3-0+1))+0, DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 8030), '2000-01-01'), RAND()*(40000000-8000000)+8000000, FLOOR(RAND()*(7-1+1))+1, FLOOR(RAND()*(@s-0+1))+0, 'e00' + CAST(@i + 1 as varchar) + '@gmail.com');

    INSERT INTO Emp_Skill(SkillNo, EmpNo, SkillLevel, RegDate) VALUES
    (FLOOR(RAND()*(@n-1+1))+1, 'E00' + CAST(@i + 1 as varchar), FLOOR(RAND()*(3-1+1))+1, DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 8030), '2000-01-01'));
    SET @i = @i + 1;
END;
GO
DECLARE @now DATE;
SET @now = CAST(GETDATE() AS date);
SELECT e.EmpName, e.Email, d.DeptName FROM Employee e 
INNER JOIN Department d ON e.DeptNo = d.DeptNo
WHERE e.EmpStatus = 0 AND DATEDIFF(month, e.StartDate, @now) >= 6;
GO
SELECT e.EmpName FROM Employee e 
LEFT JOIN Emp_Skill es ON e.EmpNo = es.EmpNo
RIGHT JOIN Skill s ON es.SkillNo = s.SKillNo
WHERE s.SkillName = 'C++' AND s.SkillName = 'C#';
WITH Departments_Have_Many_Employee
AS
(
    SELECT d.DeptNo, d.DeptName, COUNT(e.DeptNo) TotalEmp FROM Employee e
    RIGHT JOIN Department d ON d.DeptNo = e.DeptNo
    GROUP BY d.DeptName, d.DeptNo
    HAVING COUNT(e.DeptNo) >= 2
)
SELECT d.DeptName, e.EmpName FROM Department d 
LEFT JOIN Departments_Have_Many_Employee de ON d.DeptNo = de.DeptNo
RIGHT JOIN Employee e ON d.DeptNo = e.DeptNo
GROUP BY d.DeptName, e.EmpName
SELECT e.EmpName, e.Email, COUNT(es.SkillNo) TotalSkill FROM Emp_Skill es
RIGHT JOIN Employee e ON e.EmpNo = es.EmpNo
GROUP BY es.EmpNo, e.EmpName, e.Email;
GO
CREATE VIEW Employee_View
AS
(
    SELECT e.EmpName, s.SkillName, d.DeptName FROM Employee e
    INNER JOIN Emp_Skill es ON e.EmpNo = es.EmpNo
    LEFT JOIN Skill s ON es.SkillNo = s.SKillNo
    INNER JOIN Department d ON e.DeptNo = d.DeptNo
    GROUP BY e.EmpName, s.SkillName, d.DeptName 
);