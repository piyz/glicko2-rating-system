CREATE TABLE teams(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  rating DOUBLE(7,3) NOT NULL ,
  deviation DOUBLE(6,3) NOT NULL ,
  volatility DOUBLE(8,6) NOT NULL,
  count INT(11) NOT NULL
);

CREATE TABLE teams_bo3(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  rating DOUBLE(7,3) NOT NULL ,
  deviation DOUBLE(6,3) NOT NULL ,
  volatility DOUBLE(8,6) NOT NULL,
  count INT(11) NOT NULL,
  FOREIGN KEY (id) REFERENCES teams(id)
);



