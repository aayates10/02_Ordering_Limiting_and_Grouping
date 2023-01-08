-- 4. The titles and taglines of all the movies that are in the Fantasy genre. 
-- Order the results by title (A-Z).
-- (81 rows)
SELECT
title, tagline
FROM
movie
JOIN
movie_genre on movie.movie_id = movie_genre.movie_id
JOIN
genre on movie_genre.genre_id = genre.genre_id
WHERE
genre_name LIKE 'Fantasy'
ORDER BY
title ASC





