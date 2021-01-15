DROP TABLE IF EXISTS T_USERS;
  
CREATE TABLE T_USERS (
  id INT PRIMARY KEY,
  license VARCHAR(250) NOT NULL,
  tps INT DEFAULT 1
);

INSERT INTO T_USERS (id, license, tps) VALUES
  (1, 'Advanced', 100),
  (2, 'Standard', 10),
  (3, 'Trial', 1);