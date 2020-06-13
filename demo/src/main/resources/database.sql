CREATE DATABASE Student_Management;

CREATE TABLE IF NOT EXISTS Student_Management.student
(
    id BIGINT auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    age int,
    gender bool,
    email varchar(50) not null,
    address varchar(255)
);

CREATE TABLE IF NOT EXISTS Student_Management.subject
(
    id BIGINT auto_increment primary key,
    name varchar(50) not null,
    code varchar(50) not null,
    credit_point int not null
);

CREATE TABLE IF NOT EXISTS Student_Management.score
(
    id BIGINT auto_increment primary key,
    student_id BIGINT not null,
    subject_id BIGINT not null,
    score int not null,
    CONSTRAINT FK_StudentScore FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT FK_SubjectScore FOREIGN KEY (subject_id) REFERENCES subject(id)
)
