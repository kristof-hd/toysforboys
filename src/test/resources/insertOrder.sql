insert into orders(orderDate, requiredDate, shippedDate, comments, customerId, status, version) 
values ('2000-01-01', '2001-01-01', NULL, NULL, (select id from customers where name='test'), 'PROCESSING', 1);
insert into productlines(name, description, version) values ('test', 'test', 1);
insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version) 
values('test', '1:100', 'test', 1, 1, 1, (select id from productlines where name='test'), 1);
insert into orderdetails(orderId, productId, quantityOrdered, priceEach) values ((select id from orders where orderDate='2000-01-01'), (select id from products where name='test'), 10, 1);