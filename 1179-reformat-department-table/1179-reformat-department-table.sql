# Write your MySQL query statement below
SELECT
    id,
    MAX(case when month = 'Jan' THEN revenue END)as Jan_Revenue,
    MAX(case when month = 'Feb' THEN revenue END)as Feb_Revenue,
    MAX(case when month = 'Mar' THEN revenue END)as Mar_Revenue,
    MAX(case when month = 'Apr' THEN revenue END)as Apr_Revenue,
    MAX(case when month = 'May' THEN revenue END)as May_Revenue,
    MAX(case when  month = 'Jun' THEN revenue END)as Jun_Revenue,
    MAX(case when month = 'Jul' THEN revenue END) as Jul_Revenue,
    MAX(case when month = 'Aug' THEN revenue END)as Aug_Revenue,
    MAX(case when month = 'Sep' THEN revenue END)as Sep_Revenue,
    MAX(case when month = 'Oct' THEN revenue END) as Oct_Revenue,
    MAX(case when month= 'Nov' THEN revenue END) as Nov_Revenue,
    MAX(case when month = 'Dec' THEN revenue END)as Dec_Revenue
FROM Department
group by id;
