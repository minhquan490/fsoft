/*Assignment 2_Opt4: Adventure Works Database*/
use Adventure_Works_2008;
/*Exercise 1: Querying and Filtering Data*/
/*Question 1:*/
SELECT p.ProductID, p.Name, p.Color, p.ListPrice FROM Production.Product p 
/*Question 2:*/
SELECT p.ProductID, p.Name, p.Color, p.ListPrice FROM Production.Product p WHERE p.ListPrice > 0
/*Question 3:*/
SELECT p.ProductID, p.Name, p.Color, p.ListPrice FROM Production.Product p WHERE p.Color IS NULL
/*Question 4:*/
SELECT p.ProductID, p.Name, p.Color, p.ListPrice FROM Production.Product p WHERE p.Color IS NOT NULL
/*Question 5:*/
SELECT p.ProductID, p.Name, p.Color, p.ListPrice FROM Production.Product p WHERE p.Color IS NOT NULL AND p.ListPrice > 0
/*Question 6:*/
SELECT p.Name + ':' + p.Color AS [Name And Color] FROM Production.Product p WHERE p.Color IS NOT NULL
/*Question 7:*/
SELECT 'NAME: ' + p.Name + ' -- ' + 'COLOR: ' + p.Color AS [Name And Color] FROM Production.Product p WHERE p.Color IS NOT NULL
/*Question 8:*/
SELECT p.ProductID, p.Name FROM Production.Product p WHERE p.ProductID >= 400 AND p.ProductID <= 500
/*Question 9:*/
SELECT p.ProductID, p.Name, p.Color FROM Production.Product p WHERE p.Color IN ('black', 'blue')
/*Question 10:*/
SELECT p.Name, p.ListPrice FROM Production.Product p WHERE p.Name LIKE 'S%' ORDER BY p.ListPrice ASC
/*Question 11:*/
SELECT p.Name, p.ListPrice FROM Production.Product p WHERE p.Name LIKE 'S%' OR p.Name LIKE 'A%' ORDER BY p.Name ASC
/*Question 12:*/
SELECT p.Name, p.ListPrice FROM Production.Product p WHERE p.Name LIKE 'SPO[^K]%' ORDER BY p.Name ASC
/*Question 13:*/
SELECT DISTINCT p.Color FROM Production.Product p
/*Question 14:*/
SELECT DISTINCT p.Color, ps.ProductSubcategoryID FROM Production.ProductSubcategory ps INNER JOIN Production.Product p ON p.ProductSubcategoryID = ps.ProductSubcategoryID WHERE p.Color IS NOT NULL ORDER BY ps.ProductSubcategoryID ASC
/*Question 15:*/
SELECT ProductSubCategoryID
    , LEFT([Name],35) AS [Name]
    , Color, ListPrice
FROM Production.Product
WHERE ProductSubCategoryID = 1   
    AND Color IN ('Red','Black')
    OR ListPrice BETWEEN 1000 AND 2000 
ORDER BY ProductID
/*Question 16:*/
SELECT p.Name, ISNULL(p.Color,'Unknown'), p.ListPrice FROM Production.Product p 

/*Exercise 2: Grouping and Summarizing Data*/
/*Question 1:*/
SELECT * FROM Production.Product 
/*Answer 504 product*/
/*Question 2:*/
SELECT COUNT(p.ProductSubcategoryID) AS HasSubCategoryID FROM Production.Product p WHERE p.ProductSubcategoryID IS NOT NULL
/*Question 3:*/
SELECT p.ProductSubcategoryID, COUNT(p.ProductID) AS CountedProducts FROM Production.Product p GROUP BY p.ProductSubcategoryID
/*Question 4:*/
SELECT COUNT(*) AS NoSubCat FROM Production.Product p WHERE p.ProductSubcategoryID IS NULL GROUP BY p.ProductSubcategoryID
SELECT COUNT(*) AS NoSubCat FROM Production.Product P  GROUP BY ProductSubcategoryID HAVING P.ProductSubcategoryID IS NULL
/*Question 5:*/
SELECT pi.ProductID, SUM(pi.Quantity) AS TheSum FROM Production.ProductInventory pi GROUP BY pi.ProductID
/*Question 6:*/
SELECT pi.ProductID, SUM(pi.Quantity) AS TheSum FROM Production.ProductInventory pi WHERE pi.LocationID = 40 GROUP BY pi.ProductID HAVING SUM(pi.Quantity) < 100
/*Question 7:*/
SELECT pi.Shelf, pi.ProductID, SUM(pi.Quantity) AS TheSum FROM Production.ProductInventory pi WHERE pi.LocationID = 40 GROUP BY pi.ProductID, pi.Shelf HAVING SUM(pi.Quantity) < 100
/*Question 8:*/
SELECT AVG(pi.Quantity) AS TheAvg FROM Production.ProductInventory pi WHERE pi.LocationID = 10
/*Question 9:*/
SELECT pi.ProductID, pi.Shelf, AVG(pi.Quantity) AS TheAvg FROM Production.ProductInventory pi WHERE pi.LocationID = 10 GROUP BY CUBE (pi.ProductID, pi.Shelf) HAVING pi.Shelf != 'N/A' ORDER BY pi.Shelf ASC
/*Question 10:*/
SELECT p.Color, p.Class, COUNT(*) AS TheCount, AVG(p.ListPrice) AS AvgPrice FROM Production.Product p WHERE p.Color NOT IN ('Multi', 'White', 'Grey') GROUP BY GROUPING SETS(p.Color, p.Class) HAVING p.Class IS NOT NULL OR p.Color IS NOT NULL
/*Question 11:*/
SELECT ProductSubcategoryID
      , COUNT(Name) as Counted
FROM Production.Product
GROUP BY CUBE(ProductSubcategoryID)