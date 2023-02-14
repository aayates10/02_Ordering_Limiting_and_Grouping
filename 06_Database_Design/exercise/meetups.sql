DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS member;


CREATE TABLE member(
member_id serial NOT NULL,
last_name varchar(100)	NOT NULL,
first_name varchar(100) NOT NULL,
member_email varchar (100) NOT NUll,
phone_number varchar (100) NULL,
date_of_birth  varchar(100) NOT NULL,
email_reminder varchar (100) NULL, 
	CONSTRAINT PK_member PRIMARY KEY (member_id)
);

CREATE TABLE groups (
group_id serial NOT NULL,
group_name varchar(100) NOT NULL,
member_id int NOT NULL,
	CONSTRAINT PK_group PRIMARY KEY (group_id),
	CONSTRAINT FK_group_member FOREIGN KEY (member_id) REFERENCES member (member_id)
	
);

CREATE TABLE event(
event_id serial NOT NULL,
event_name varchar NOT NULL,
description varchar NOT NULL,
start_date_time timestamp NOT NULL,
duration int NOT NULL,
group_id int NOT NULL,
member_id int NOT NULL,
CONSTRAINT PK_event PRIMARY KEY (event_id),
CONSTRAINT FK_event_group FOREIGN KEY (group_id)REFERENCES groups (group_id)	
);

INSERT INTO member (last_name,first_name,member_email,date_of_birth,email_reminder)
VALUES
('Henry','James','jahen@aol.com','2002-9-1','yes'),
('Burch','Damion', 'damion@aol.com','2001-1-2','yes'),
('Williams','Paul','pauly@aol.com','2004-1-2','yes'),
('Brown','Kevin','kevkev@aol.com','1995-1-2','no'),
('Yates','Aaron','Aaron@aol.com','1992-1-2','yes'),
('Duck','Donald','Duckparty@aol.com','1997-2-1','yes'),
('Tesla','Rico','Tesla@aol.com','2001-2-4','yes'),
('Jordan','James','Chrisco@aol.com','2000-2-1','yes');
SELECT * FROM member;

INSERT INTO groups (group_name,member_id)
VALUES
('gold', (Select member_id from member where member_email = 'damion@aol.com' )),
('Silver', (Select member_id from member where member_email = 'Duckparty@aol.com')),
('Bronz', (Select member_id from member where member_email = 'Chrisco@aol.com'));

-- INSERT INTO event (event_name,description,start_date_time,duration,group_id)
-- VALUES
-- ('Showcase City','showcase of all citys in Jamestown','2023-12-01 13:10:11 (Select group_id from groups where group_name = 'gold')
-- ('Twilight','showcase of all students available','2023-2-2 13:12:11' (Select group_id from groups where group_name = 'Silver || gold')
-- ('The beat','showcase of all hip hop songs',"4-23-2023","10:11:11",(Select group_id from groups where group_name = 'gold')




