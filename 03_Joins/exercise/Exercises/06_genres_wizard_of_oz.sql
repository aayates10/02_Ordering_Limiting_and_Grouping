-- 6. The genres of "The Wizard of Oz" sorted in alphabetical order (A-Z).
-- (3 rows)
SELECT
genre.genre_name
FROM
movie
JOIN
movie_genre on movie.movie_id = movie_genre.movie_id
JOIN
genre on movie_genre.genre_id = genre.genre_id
WHERE
title LIKE 'The Wizard of Oz'
ORDER BY
genre_name ASC

