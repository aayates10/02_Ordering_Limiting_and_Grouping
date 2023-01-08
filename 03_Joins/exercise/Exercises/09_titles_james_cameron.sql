-- 9. The titles of movies directed by James Cameron, sorted alphabetically.
-- (6 rows)
Select
title
FROM
movie
JOIN 
person on movie.director_id = person.person_id
WHERE
person_name LIKE 'James Cameron'
ORDER BY
title ASC
