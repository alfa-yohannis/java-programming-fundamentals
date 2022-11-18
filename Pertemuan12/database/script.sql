use pradita;

delete from order_detail where code <> '';
delete from `order` where code <> '';
delete from item where code <> '';

insert into item(code, name, price, quantity) values('AB001', 'Indomie Goreng', 4100, 20);
insert into item(code, name, price, quantity) values('AB002', 'Indomie Kuah', 4000, 10);

insert into `order`(code) values('O0001');
insert into order_detail(code, line, itemcode, name, price, quantity) 
	values('O0001', 1, 'AB001', 'Indomie Goreng', 4100, 1);
insert into order_detail(code, line, itemcode, name, price, quantity) 
	values('O0001', 2, 'AB002', 'Indomie Kuah', 4000, 2);

select * from item;
select t1.code, t1.date, t2.line, t2.itemcode, t2.name, 
	t2.price, t2.quantity, (t2.price * t2.quantity) total
	from `order` t1, order_detail t2 where t1.code = t2.code