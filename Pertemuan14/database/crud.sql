# first
select * from `order` t1 where t1.code = (select min(code)  from `order` t2) limit 1;

# previous
select * from `order` t1 where t1.code = (select max(code)  from `order` t2 where t2.code < 'TA00000002') limit 1;

# next
select * from `order` t1 where t1.code = (select min(code)  from `order` t2 where t2.code > 'TA00000002') limit 1;

# last
select * from `order` t1 where t1.code = (select max(code)  from `order` t2) limit 1;
select line, itemcode, name, quantity, price, (quantity * price) total from `order_detail` t1 where t1.code = (select max(code)  from `order` t2);

#get the last code
SELECT Max(code) code FROM `order`