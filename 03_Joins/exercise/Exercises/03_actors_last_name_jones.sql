-- 3. For all actors with the last name of "Jones", display the actor's name and movie titles they appeared in. 
-- Order the results by the actor names (A-Z) and then by movie title (A-Z). 
-- (48 rows)

SELECT 
person_name, movie.title
FROM
person
JOIN
movie_actor on movie_actor.actor_id = person.person_id
JOIN
movie on movie.movie_id = movie_actor.movie_id
WHERE
person_name LIKE ('% Jones')
GROUP BY
movie.title,person_name
ORDER BY
person_name ASC, title ASC
