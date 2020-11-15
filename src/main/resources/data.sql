DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO persons (first_name, last_name) VALUES
  ('Amazon', 'Man'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija');