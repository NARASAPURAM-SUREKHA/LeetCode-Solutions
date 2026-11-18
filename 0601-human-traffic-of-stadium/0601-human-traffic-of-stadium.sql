# Write your MySQL query statement below
WITH CTE AS (
    SELECT *,id-ROW_NUMBER() over (order by id) as grp
    From Stadium where people >= 100
)
SELECT id, visit_date, people
FROM CTE
Where grp in( Select grp from CTE group by grp having count(*) >=3)
ORDER BY visit_date;