-- 5. The titles and release dates of all the movies that are in the Comedy genre. 
-- Order the results by release date, earliest to latest. 
-- (220 rows)

SELECT
title, release_date
FROM
movie
JOIN
movie_genre on movie.movie_id = movie_genre.movie_id
JOIN
genre on movie_genre.genre_id = genre.genre_id
WHERE
genre_name LIKE 'Comedy'
ORDER BY
release_date ASC
