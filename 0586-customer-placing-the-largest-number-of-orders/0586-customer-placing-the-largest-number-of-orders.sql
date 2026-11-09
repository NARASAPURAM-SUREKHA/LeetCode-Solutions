# Write your MySQL query statement below
SELECT customer_number
From Orders
group by customer_number order by COUNT(*) DESC limit 1;