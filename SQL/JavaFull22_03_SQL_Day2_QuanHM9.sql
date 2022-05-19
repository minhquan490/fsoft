/*Assignment 2_Opt3: Movie Management - Q1*/
/*CREATE DATABASE 'name of database'*/
USE Movie_Management /*Name of database*/;
CREATE TABLE Movie(
    movie_id INT PRIMARY KEY identity(1,1),
    movie_name VARCHAR(255) not null,
    duration INT,
    genre INT,
    director VARCHAR(255),
    amount_of_money INT,
    comment TEXT,
    CONSTRAINT chk_duration CHECK(duration >= 60),
    CONSTRAINT chk_genre CHECK(genre > 0 AND genre < 9)
);
CREATE TABLE Actor(
    actor_id INT PRIMARY KEY IDENTITY(1,1),
    actor_name VARCHAR(255),
    age TINYINT,
    average_movie_salary INT,
    nationnally VARCHAR(100)
);
CREATE TABLE Actedln(
    movie_id INT FOREIGN KEY REFERENCES Movie(movie_id),
    actor_id INT FOREIGN KEY REFERENCES Actor(actor_id)
);

/*Assignment 2_Opt3: Movie Management - Q2-a*/
ALTER TABLE Movie ADD Image_Link VARCHAR(255) UNIQUE
/*Setting up before Q2-b*/
CREATE TABLE Genre(
    genre_id INT PRIMARY KEY IDENTITY(1,1),
    genre_name VARCHAR(50) NOT NULL
);
ALTER TABLE Movie 
ADD CONSTRAINT fk_genre 
FOREIGN KEY (genre) 
REFERENCES Genre(genre_id);
INSERT INTO dbo.Genre(genre_name) VALUES ('Action'), ('Adventure'), ('Comedy'), ('Crime(gangster)'), ('Dramas'), ('Horror'), ('Musical/dance'), ('War');
/*Assignment 2_Opt3: Movie Management - Q2-b*/
INSERT INTO dbo.Actor(actor_name, age, average_movie_salary, nationnally) 
VALUES ('actor-1', 38, 2000, 'national-a'), ('actor-2', 39, 1000, 'national-b'), ('actor-3', 40, 1500, 'national-c'), ('actor-4', 50, 2000, 'national-a'), ('actor-err', 56, 2500, 'national-c');
INSERT INTO dbo.Movie(movie_name, duration, genre, director, amount_of_money, comment, Image_Link) 
VALUES ('movive-1', 72, 1, 'none', 3000, 'good','/a'), ('movive-2', 61, 1, 'none', 3000, 'bad', '/b'), ('movive-3', 61, 1, 'none', 3000, 'no-comment','/c'), ('movive-4', 90, 1, 'none', 3000,'terrible','/d'), ('movive-5', 80, 1, 'none', 3000,'ok','/e');
INSERT INTO dbo.Actedln(movie_id, actor_id) 
VALUES (1,1), (1,2), (1,3), (1,4), (1,5), (2,1), (2,5), (3,4), (4,1), (4,4), (4,3), (5,3), (5,5), (5,1);
UPDATE dbo.Actor SET actor_name = 'actor-5' WHERE actor_id = 5;
/*Assignment 2_Opt3: Movie Management - Q3-c*/
SELECT * FROM [dbo].[Actor] WHERE Actor.age > 50;
/*Assignment 2_Opt3: Movie Management - Q3-d*/
SELECT dbo.Actor.actor_name, dbo.Actor.average_movie_salary FROM dbo.Actor ORDER BY dbo.Actor.average_movie_salary DESC
/*Assignment 2_Opt3: Movie Management - Q3-e*/
SELECT movie.movie_name 
FROM  dbo.Movie movie
INNER JOIN dbo.Actedln acted ON movie.movie_id = acted.movie_id
INNER JOIN dbo.Actor actor ON acted.actor_id = actor.actor_id
WHERE actor.actor_id IN (
    SELECT ac.actor_id 
    FROM Actedln AI 
    JOIN Actor ac 
    ON AI.actor_id = ac.actor_id 
    WHERE ac.actor_name = 'actor-1'
    );
/*Assignment 2_Opt3: Movie Management - Q3-f*/ 
SELECT m.movie_name
FROM dbo.Movie m 
INNER JOIN dbo.Actedln ai ON ai.movie_id = m.movie_id
INNER JOIN dbo.Actor a ON ai.actor_id = a.actor_id
INNER JOIN dbo.Genre g ON g.genre_id = m.genre
WHERE g.genre_name = 'action' 
GROUP BY m.movie_name 
HAVING COUNT(ai.actor_id) > 3