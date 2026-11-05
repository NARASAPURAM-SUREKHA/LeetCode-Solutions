# Write your MySQL query statement below
SELECT email from Person Group by email having COUNT(email)>1;