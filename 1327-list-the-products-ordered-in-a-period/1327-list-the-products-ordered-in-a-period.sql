# Write your MySQL query statement below
SELECT p.product_name,sum(o.unit) AS unit
FROM Products p
JOIN Orders o ON p.product_id =o.product_id
Where o.order_date >='2020-02-01'and o.order_date<'2020-03-01'
group by p.product_id,p.product_name having sum(o.unit)>=100;