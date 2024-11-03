CREATE DATABASE  IF NOT EXISTS `pradita` ;
USE `pradita`;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `code` varchar(5) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `quantity` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `code` varchar(5) NOT NULL,
  `note` text DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `code` varchar(5) NOT NULL,
  `line` int NOT NULL,
  `itemcode` varchar(5) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `quantity` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`line`,`code`),
  KEY `itemcode_idx` (`itemcode`),
  KEY `code_idx` (`code`),
  CONSTRAINT `code` FOREIGN KEY (`code`) REFERENCES `order` (`code`),
  CONSTRAINT `itemcode` FOREIGN KEY (`itemcode`) REFERENCES `item` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

delete from order_detail where code <> '';
delete from `order` where code <> '';
delete from item where code <> '';

insert into item(code, name, quantity, price) values('IM001', 'Indomie Goreng', 30, 4100);
insert into item(code, name, quantity, price) values('IM002', 'Indomie Kuah', 20, 4000);

insert into `order`(code, note) values('10001', 'Penjualan Pertama');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('10001', 1, 'IM001', 'Indomie Goreng', 1, 4100);
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('10001', 2, 'IM002', 'Indomie Kuah', 2, 4000);
    
insert into `order`(code, note) values('10002', 'Penjualan Kedua');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('10002', 1, 'IM001', 'Indomie Goreng', 2, 4100);
    
insert into `order`(code, note) values('10003', 'Penjualan Ketiga');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('10003', 1, 'IM002', 'Indomie Kuah', 4, 4000);

select t1.code, t1.date, t1.note, t2.line, t2.itemcode, t2.name, t2.quantity, t2.price, 
  (t2.quantity * t2.price) linetotal
  from `order` t1, order_detail t2 where t1.code = t2.code;


