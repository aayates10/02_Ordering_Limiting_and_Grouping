-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)
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
person_name LIKE 'Robert De Niro'AND release_date > '2010-12-31'

ORDER BY
genre_name ASC
