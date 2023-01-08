-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre. 
-- Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)

SELECT
title,release_date,length_minutes
FROM
movie
JOIN
movie_genre on movie.movie_id = movie_genre.movie_id
JOIN
genre on movie_genre.genre_id = genre.genre_id
WHERE
genre_name LIKE 'Action'
ORDER BY
length_minutes DESC, release_date DESC

FETCH FIRST 5 ROWS ONLY

