create table person (
                        id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name varchar(30) NOT NULL UNIQUE,
                        surname varchar(40) NOT NULL UNIQUE,
                        age int NOT NULL,
                        email varchar(40) NOT NULL UNIQUE

);
