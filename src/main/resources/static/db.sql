CREATE TABLE teams_bo1(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE ,
  rating DOUBLE(10,3) NOT NULL DEFAULT 1500,
  deviation DOUBLE(7,3) NOT NULL DEFAULT 350,
  volatility DOUBLE(10,6) NOT NULL DEFAULT 0.06,
  count INT(11) NOT NULL DEFAULT 0
);

CREATE TABLE teams_bo3(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  rating DOUBLE(10,3) NOT NULL DEFAULT 1500,
  deviation DOUBLE(7,3) NOT NULL DEFAULT 350,
  volatility DOUBLE(10,6) NOT NULL DEFAULT 0.06,
  count INT(11) NOT NULL DEFAULT 0,
  FOREIGN KEY (id) REFERENCES teams_bo1(id)
);



