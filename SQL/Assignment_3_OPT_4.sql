/*Assignment 3_OPT_4*/
USE DMS
INSERT INTO ACT VALUES (1,'Data Analyze')
INSERT INTO ACT VALUES (2,'DataBase Organization')
INSERT INTO ACT VALUES (3,'Backend Procedures')
INSERT INTO ACT VALUES (4,'Backend QA')
INSERT INTO ACT VALUES (5,'Frontend Procedures')
INSERT INTO ACT VALUES (6,'Frontend QA')
INSERT INTO ACT VALUES (7,'Final Test')
INSERT INTO ACT VALUES (8,'Demo Release')
INSERT INTO DEPT VALUES ('D01','Data Dev',NULL,'MR1',NULL)
INSERT INTO DEPT VALUES ('D02','Backend Dev',NULL,'MR2',NULL)
INSERT INTO DEPT VALUES ('D03','Frontend Dev',NULL,'MR2',NULL)
INSERT INTO DEPT VALUES ('D04','QA-Dept',NULL,'MR3',NULL)
INSERT INTO DEPT VALUES ('D05','HR',NULL,'MR4',NULL)
INSERT INTO EMP VALUES ('DATA01','Nguyen','A','D01','Data Science',1000,200,1)
INSERT INTO EMP VALUES ('DATA02','Nguyen','B','D01','Data Science',1200,300,2)
INSERT INTO EMP VALUES ('DATA03','Nguyen','C','D01','Data Science',1400,400,3)
INSERT INTO EMP VALUES ('DATA04','Tran','D','D01','Data Science',1500,500,4)
INSERT INTO EMP VALUES ('BFDV01','Tran','E','D01','FullStack Dev',1100,200,1)
INSERT INTO EMP VALUES ('BFDV02','Tran','F','D01','FullStack Dev',1200,300,2)
INSERT INTO EMP VALUES ('BFDV03','Le','G','D01','FullStack Dev',1300,400,3)
INSERT INTO EMP VALUES ('BFDV04','Le','H','D01','FullStack Dev',1400,500,4)
INSERT INTO EMP VALUES ('BFDV05','Le','I','D01','FullStack Dev',1500,300,1)
INSERT INTO EMP VALUES ('BFDV06','Hoang','J','D01','FullStack Dev',1600,400,2)
INSERT INTO EMP VALUES ('BFDV07','Hoang','K','D01','FullStack Dev',1700,500,3)
INSERT INTO EMP VALUES ('BFDV08','Hoang','L','D01','FullStack Dev',1800,300,4)
INSERT INTO EMP VALUES ('BFDV09','Pham','M','D01','FullStack Dev',1900,400,1)
INSERT INTO EMP VALUES ('BFDV10','Pham','N','D01','FullStack Dev',2000,500,2)
INSERT INTO EMP VALUES ('QADA01','Pham','O','D01','QA - Tester',1000,300,1)
INSERT INTO EMP VALUES ('QADA02','Vo','P','D01','QA - Tester',1000,400,2)
INSERT INTO EMP VALUES ('QADA03','Vo','Q','D01','QA - Tester',1000,200,3)
INSERT INTO EMP VALUES ('QADA04','Vo','R','D01','QA - Tester',1000,100,4)
INSERT INTO EMP VALUES ('HR01','Doan','S','D01','Human Resource',1000,200,3)
INSERT INTO EMPPROJACT VALUES ('DATA01','PRO01',1)
INSERT INTO EMPPROJACT VALUES ('DATA02','PRO01',1)
INSERT INTO EMPPROJACT VALUES ('BFDV01','PRO01',3)
INSERT INTO EMPPROJACT VALUES ('BFDV02','PRO01',3)
INSERT INTO EMPPROJACT VALUES ('BFDV03','PRO01',5)
INSERT INTO EMPPROJACT VALUES ('BFDV04','PRO01',5)
INSERT INTO EMPPROJACT VALUES ('QADA01','PRO01',7)
INSERT INTO EMPPROJACT VALUES ('QADA02','PRO01',7)
INSERT INTO EMPPROJACT VALUES ('DATA03','PRO02',1)
INSERT INTO EMPPROJACT VALUES ('DATA04','PRO02',1)
INSERT INTO EMPPROJACT VALUES ('BFDV05','PRO02',3)
INSERT INTO EMPPROJACT VALUES ('BFDV06','PRO02',3)
INSERT INTO EMPPROJACT VALUES ('BFDV07','PRO02',5)
INSERT INTO EMPPROJACT VALUES ('BFDV08','PRO02',5)
INSERT INTO EMPPROJACT VALUES ('QADA03','PRO02',7)
INSERT INTO EMPPROJACT VALUES ('QADA04','PRO02',7)
INSERT INTO EMPPROJACT VALUES ('DATA02','PRO03',1)
INSERT INTO EMPPROJACT VALUES ('DATA03','PRO03',1)
INSERT INTO EMPPROJACT VALUES ('BFDV04','PRO03',3)
INSERT INTO EMPPROJACT VALUES ('BFDV05','PRO03',3)
INSERT INTO EMPPROJACT VALUES ('BFDV05','PRO03',5)
INSERT INTO EMPPROJACT VALUES ('BFDV06','PRO03',5)
INSERT INTO EMPPROJACT VALUES ('QADA02','PRO03',7)
INSERT INTO EMPPROJACT VALUES ('QADA03','PRO03',7)
INSERT INTO EMPMAJOR VALUES ('DATA01','J01','Computer Science')
INSERT INTO EMPMAJOR VALUES ('DATA03','J01','Computer Science')
INSERT INTO EMPMAJOR VALUES ('DATA01','J03','Project Analyze')
INSERT INTO EMPMAJOR VALUES ('DATA03','J03','Project Analyze')
INSERT INTO EMPMAJOR VALUES ('QADA01','J02','MAT')
INSERT INTO EMPMAJOR VALUES ('QADA03','J02','MAT')
INSERT INTO EMPMAJOR VALUES ('QADA01','J04','Q-MAT')
INSERT INTO EMPMAJOR VALUES ('QADA03','J04','Q-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV01','J02','MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV01','J04','Q-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV01','J06','K-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV01','J08','Z-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV03','J02','MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV03','J04','Q-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV03','J06','K-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV03','J08','Z-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV05','J02','MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV05','J04','Q-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV05','J06','K-MAT')
INSERT INTO EMPMAJOR VALUES ('BFDV05','J08','Z-MAT')

SELECT e.emp_no, e.first_name, e.last_name FROM EMP e WHERE e.emp_no IN
(
    SELECT emj.emp_no FROM EMPPROJACT emj
)

SELECT e.emp_no, e.first_name, e.last_name FROM EMP e 
LEFT JOIN EMPMAJOR em ON e.emp_no = em.emp_no
WHERE em.major_name = 'MAT' AND em.major_name = 'Computer Science'

/*Solution 1: Using CTE*/
WITH AVG_SALARY_DEPT
AS
(
    SELECT d.dept_no, (SUM(e.salary) + SUM(e.bonus))/COUNT(e.emp_no) AS DEPT_AVG_SAL FROM DEPT d 
    FULL JOIN EMP e ON d.dept_no = e.dept_no
    GROUP BY d.dept_no, e.dept_no
    HAVING (SUM(e.salary) + SUM(e.bonus))/COUNT(e.emp_no) IS NOT NULL
)
SELECT e.emp_no, e.last_name, e.first_name, e.salary, e.dept_no, asd.DEPT_AVG_SAL FROM EMP e 
LEFT JOIN AVG_SALARY_DEPT asd ON e.dept_no = asd.dept_no
/*Solution 2: Using JOIN*/
SELECT e.emp_no, e.last_name, e.first_name, e.salary, e.dept_no, asd.DEPT_AVG_SAL FROM EMP e 
LEFT JOIN 
(
    SELECT d.dept_no, (SUM(e.salary) + SUM(e.bonus))/COUNT(e.emp_no) AS DEPT_AVG_SAL FROM DEPT d 
    FULL JOIN EMP e ON d.dept_no = e.dept_no
    GROUP BY d.dept_no, e.dept_no
    HAVING (SUM(e.salary) + SUM(e.bonus))/COUNT(e.emp_no) IS NOT NULL
) AS asd ON e.dept_no = asd.dept_no

WITH VAG_E_DEPT
AS 
(
    SELECT d.dept_no, CAST(SUM(e.ed_level) as decimal(4,0))/CAST(COUNT(e.emp_no) as decimal(4,0)) VAG_E FROM DEPT d 
    LEFT JOIN EMP e ON e.dept_no = d.dept_no
    GROUP BY d.dept_no
    HAVING SUM(e.ed_level)/COUNT(e.emp_no) IS NOT NULL
)
SELECT e.emp_no, e.last_name, e.first_name FROM EMP e 
LEFT JOIN VAG_E_DEPT ved ON ved.dept_no = e.dept_no
WHERE CAST(e.ed_level as decimal(4,2)) > ved.VAG_E
/*g*/
GO
DECLARE @low INT;
DECLARE @high INT;
DECLARE @mid INT;
DECLARE @highIdex INT;
DECLARE @payrollLow INT;
DECLARE @payrollHigh INT;
DECLARE @payroll INT;
DECLARE @idConceptHigh VARCHAR(50);
DECLARE @idConceptLow VARCHAR(50);
SET @low = 1;
SELECT @high = COUNT(e.emp_No) FROM EMP e;
SET @mid = CAST(@low + (@high - @low)/2 as int);
SET @highIdex = 4;
SET @idConceptLow = 'BFDV0';
SET @idConceptHigh = 'QADA';
WHILE (@low <= @mid AND @high >= @mid)
BEGIN

    IF @mid = @low AND @mid = @high
    BEGIN
        SET @idConceptLow = 'BFDV';
    END;

    IF (@highIdex = 1 AND @high = 15)
        BEGIN
            SET @idConceptHigh = 'HR0';
            SET @highIdex = 1;
        END;

    IF (@highIdex = 1 AND @high = 14)
        BEGIN
            SET @highIdex = 4;
            SET @idConceptHigh = 'DATA0';
        END;

    IF @low != @mid + 1
        BEGIN
            SELECT @payrollLow = e.Salary + e.bonus FROM EMP e WHERE e.emp_no = @idConceptLow + CAST(@low as varchar);
            SELECT @payrollHigh = e.Salary + e.bonus FROM EMP e WHERE e.emp_no = @idConceptHigh + CAST(@highIdex as varchar);
        END;

    IF @payrollHigh > @payrollLow
        SET @payroll = @payrollHigh;
    ELSE
        SET @payroll = @payrollLow;

    IF @highIdex != 1
    BEGIN
        SET @highIdex = @highIdex - 1;
    END;

    SET @low = @low + 1;
    SET @high = @high - 1;
END;
SELECT e.emp_no, e.last_name, e.first_name , e.salary + e.bonus AS TotalSalary FROM EMP e WHERE (e.salary + e.bonus = @payroll);
GO
/*h*/
