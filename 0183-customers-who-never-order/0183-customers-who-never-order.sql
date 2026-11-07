# Write your MySQL query statement below
Select c.name as Customers
From Customers c Left Join Orders o on c.id = o.customerId where o.id IS NULL;