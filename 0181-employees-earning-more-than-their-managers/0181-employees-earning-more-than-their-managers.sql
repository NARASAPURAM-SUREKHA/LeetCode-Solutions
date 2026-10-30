# Write your MySQL query statement below
SELECT e.name AS Employee from Employee e
JOIN Employee m on e.managerId = m.id
where e.salary > m.salary;