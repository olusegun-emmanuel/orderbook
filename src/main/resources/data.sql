DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  orderId VARCHAR(250) NOT NULL,
  price int(10) NOT NULL,
  quantity int(10) NOT NULL,
  side VARCHAR(10) NOT NULL,
  status VARCHAR(30) NOT NULL
);

INSERT INTO orders  VALUES
  (1,'A647334','100','2','Buy','New');
INSERT INTO orders  VALUES
  (2,'A647335','200','1','Buy','New');
INSERT INTO orders  VALUES
  (3,'A647336','300','3','Buy','New');
INSERT INTO orders  VALUES
  (4,'A647337','500','2','Sell','New');
INSERT INTO orders  VALUES
  (5,'A647338','600','3','Sell','New');
INSERT INTO orders  VALUES
  (6,'A647339','700','4','Sell','New');
