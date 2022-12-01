DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  orderId VARCHAR(250) NOT NULL,
  price int(10) NOT NULL,
  quantity int(10) NOT NULL,
  side VARCHAR(250) NOT NULL
);

INSERT INTO orders  VALUES
  (1,'A647334','100','2', 'Buy');
INSERT INTO orders  VALUES
  (2,'A647335','150','1', 'Buy');
INSERT INTO orders  VALUES
  (3,'A647336','190','3', 'Buy');
INSERT INTO orders  VALUES
  (4,'A647337','100','2', 'Buy');
INSERT INTO orders  VALUES
  (5,'A647338','101','3', 'Sell');
INSERT INTO orders  VALUES
  (6,'A647339','100','4', 'Sell');
INSERT INTO orders  VALUES
  (7,'A647340','120','3', 'Sell');
INSERT INTO orders  VALUES
  (8,'A647341','150','2', 'Sell');
INSERT INTO orders  VALUES
  (9,'A647342','110','3', 'Buy');
INSERT INTO orders  VALUES
  (10,'A647343','100','2', 'Sell');