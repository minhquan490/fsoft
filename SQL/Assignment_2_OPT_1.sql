/*Assignment 2 - Opt 1: Fresher Training Management*/
/*Question a*/
/*CREATE DATABASE Fresher_Training_Management;*/
USE Fresher_Training_Management;
CREATE TABLE Trainee(
    TraineeID INT PRIMARY KEY IDENTITY(1,1),
    Full_Name VARCHAR(100) NOT NULL,
    Birth_Date DATE NOT NULL,
    Gender VARCHAR(10) CHECK(Gender = 'Male' OR Gender = 'Female') NOT NULL,
    ET_IQ INT CHECK(ET_IQ >= 0 AND ET_IQ <= 20) NOT NULL,
    ET_Gmath INT CHECK(ET_Gmath >= 0 AND ET_Gmath <= 20) NOT NULL,
    ET_English INT CHECK(ET_English >= 0 AND ET_English <= 50) NOT NULL,
    Training_Class INT,
    Evaluation_Notes TEXT
);
INSERT INTO Trainee(Full_Name, Birth_Date, Gender, ET_IQ, ET_Gmath, ET_English, Training_Class, Evaluation_Notes) VALUES 
('Trainer 1','1998-10-01','Male',11,20,18,1,'Note 10'),
('Trainer 2','1998-09-01','Male',12,19,24,2,'Note 9'),
('Trainer 3','1998-08-01','Male',13,18,27,3,'Note 8'),
('Trainer 4','1998-07-01','Male',14,17,22,4,'Note 7'),
('Trainer 5','1998-06-01','Female',15,16,20,5,'Note 6'),
('Trainer 6','1998-05-01','Female',16,15,50,2,'Note 5'),
('Trainer 7','1998-04-01','Female',17,14,37,3,'Note 4'),
('Trainer 8','1998-03-01','Female',18,13,38,4,'Note 3'),
('Trainer 9','1998-02-01','Male',19,12,39,1,'Note 2'),
('Trainer 10','1998-01-01','Male',20,11,40,2,'Note 1');
/*Question b*/
ALTER TABLE Trainee ADD Fsoft_Account VARCHAR(50);
DECLARE @i INTEGER;
SET @i = 1;
WHILE @i <= 10
BEGIN
    UPDATE Trainee SET Fsoft_Account = ('Fa' + CAST(@i AS varchar) + '@fsoft.com.vn') WHERE TraineeID = @i;
    SET @i = @i + 1;
END;
ALTER TABLE Trainee ADD CONSTRAINT UC_Fsoft_Account UNIQUE (Fsoft_Account);
/*Question c*/
GO
CREATE VIEW Trainee_View
AS 
(
    SELECT t.TraineeID, t.Full_Name, t.Birth_Date, t.Gender, t.ET_IQ, t.ET_Gmath, t.ET_English
    FROM Trainee t 
    WHERE (t.ET_IQ + t.ET_Gmath >= 20) AND (t.ET_IQ >= 8) AND (t.ET_Gmath >= 8) AND (t.ET_English >= 18)
)
GO
/*Question d*/
SELECT t.TraineeID, t.Full_Name, CONVERT(int, SUBSTRING(CONVERT(varchar, t.Birth_Date), 6, 2)) AS Birth_Month, t.Gender, t.ET_IQ, t.ET_Gmath, t.ET_English
FROM Trainee t 
WHERE (t.ET_IQ + t.ET_Gmath >= 20) AND (t.ET_IQ >= 8) AND (t.ET_Gmath >= 8) AND (t.ET_English >= 18)
GROUP BY t.TraineeID, t.Full_Name, t.Gender, t.ET_IQ, t.ET_Gmath, t.ET_English, t.Birth_Date
ORDER BY Birth_Month ASC
GO
DECLARE @numOfCol INT;
DECLARE @left VARCHAR(100);
DECLARE @right VARCHAR(100);
DECLARE @i INT;
SET @i = 1;
SELECT @numOfCol = COUNT(t.TraineeID) FROM Trainee t;
WHILE @i < @numOfCol
BEGIN
    SELECT @left = t.Full_Name FROM Trainee t WHERE t.TraineeID = @i;
    SELECT @right = t.Full_Name FROM Trainee t WHERE t.TraineeID = @i + 1;
    IF LEN(@left) < LEN(@right)
        BEGIN
            SET @left = CONCAT(@left, ',', @right);
            SELECT @right = value FROM string_split(@left,',') WHERE value != @right;
            SELECT @left = value FROM string_split(@left,',') WHERE value != @right;
        END;
    SET @i = @i + 1;
END;
SELECT t.Full_Name, t.Birth_Date, t.Gender FROM Trainee t WHERE t.Full_Name = @left;
GO