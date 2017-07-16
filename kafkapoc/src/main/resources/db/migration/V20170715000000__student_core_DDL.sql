 create table student(
	 id bigserial NOT NULL,
	 name character varying,
	 gender character varying,
	 subject character varying,
	 marks integer NOT NULL,
	 CONSTRAINT pk_student PRIMARY KEY(id) 
 );
 
 CREATE SEQUENCE seq_student START WITH 1 INCREMENT BY 1;
 ALTER TABLE student ALTER COLUMN id SET DEFAULT nextval('seq_student');