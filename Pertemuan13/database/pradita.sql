use pradita;

delete from order_detail where code <> '';
delete from `order` where code <> '';
delete from item where code <> '';

insert into item(code, name, quantity, price) values('IM001', 'Indomie Goreng', 30, 4100);
insert into item(code, name, quantity, price) values('IM002', 'Indomie Kuah', 20, 4000);

insert into `order`(code, note) values('1000000001', 'Penjualan Pertama');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('1000000001', 1, 'IM001', 'Indomie Goreng', 1, 4100);
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('1000000001', 2, 'IM002', 'Indomie Kuah', 2, 4000);
    
insert into `order`(code, note) values('1000000002', 'Penjualan Kedua');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('1000000002', 1, 'IM001', 'Indomie Goreng', 2, 4100);
    
insert into `order`(code, note) values('1000000003', 'Penjualan Ketiga');
insert into order_detail(code, line, itemcode, name, quantity, price) 
  values('1000000003', 1, 'IM002', 'Indomie Kuah', 4, 4000);

select t1.code, t1.date, t1.note, t2.line, t2.itemcode, t2.name, t2.quantity, t2.price, 
  (t2.quantity * t2.price) linetotal
  from `order` t1, order_detail t2 where t1.code = t2.code;

