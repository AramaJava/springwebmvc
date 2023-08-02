-- 1. Create database first_db
--CREATE DATABASE first_db
--    WITH
--    OWNER = postgres
--    ENCODING = 'UTF8';

-- 2. Create person table
create table person (
                        id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name varchar(30) NOT NULL,
                        surname varchar(40) NOT NULL,
                        age int NOT NULL, check (age > 16),
                        email varchar(40) NOT NULL UNIQUE

);
