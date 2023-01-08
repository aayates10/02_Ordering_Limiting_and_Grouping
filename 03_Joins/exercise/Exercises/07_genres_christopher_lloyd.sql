-- 7. The genres of movies that Christopher Lloyd has appeared in, sorted alphabetically.
-- (8 rows) Hint: DISTINCT will prevent duplicate values in your query results.
SELECT Distinct
genre_name
FROM
movie
JOIN
movie_genre on movie.movie_id = movie_genre.movie_id
JOIN
genre on movie_genre.genre_id = genre.genre_id
JOIN
movie_actor on movie.movie_id = movie_actor.movie_id
JOIN
person on person.person_id = movie_actor.actor_id
WHERE
person_name LIKE 'Christopher Lloyd'
ORDER BY
genre_name ASC

