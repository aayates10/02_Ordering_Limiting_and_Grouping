-- 12. Create a "Bill Murray Collection" in the collection table. For the movies that have Bill Murray in them, set their collection ID to the "Bill Murray Collection". (1 row, 6 rows)

Insert INTO collection (collection_name)
VALUES ('Bill Murray Collection');

UPDATE movie
SET collection_id =  (SELECT collection_id FROM collection where collection_name = 'Bill Murray Collection')
WHERE movie_id IN
(SELECT movie_actor.movie_id FROM movie_actor JOIN person on movie_actor.actor_id= person.person_id WHERE person.person_name = 'Bill Murray');


--WHERE movie_id IN (SELECT movie_id FROM movie _actor WHERE actor_id  = 1532)AND collection.collection_name;
--(Select title FROM movie WHERE movie_id IN (SELECT movie_id FROM movie _actor WHERE actor_id  = 1532)AND collection_id)