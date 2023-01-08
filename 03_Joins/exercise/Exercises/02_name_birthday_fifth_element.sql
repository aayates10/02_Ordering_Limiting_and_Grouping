-- 2. The names and birthdays of actors in "The Fifth Element"
-- Order the results alphabetically (A-Z) by name.
-- (15 rows)
SELECT 
person_name,birthday
FROM
person
JOIN
movie_actor on movie_actor.actor_id = person.person_id
JOIN
movie on movie.movie_id = movie_actor.movie_id
WHERE
title LIKE 'The Fifth Element'
ORDER BY
person_name ASC

