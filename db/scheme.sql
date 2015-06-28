DROP DATABASE IF EXISTS easytutor;
CREATE DATABASE easytutor
  CHARACTER SET utf8;
USE easytutor;
DROP TABLE IF EXISTS tests_questions;
DROP TABLE IF EXISTS tests;
DROP TABLE IF EXISTS questions_answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS users_atutor;

CREATE TABLE tests (
  test_id BINARY(16),
  name    VARCHAR(100),
  discipline_name VARCHAR(300),
  groups VARCHAR(10),
  course INT,
  PRIMARY KEY (test_id)
);

CREATE TABLE questions (
  name   VARCHAR(100),
  header VARCHAR(100),
  PRIMARY KEY (name)
);

CREATE TABLE answers (
  content VARCHAR(200),
  PRIMARY KEY (content)
);

CREATE TABLE users_atutor (
  name VARCHAR(100),
  PRIMARY KEY (name)
);

CREATE TABLE questions_answers (
  question_name  VARCHAR(100),
  answer_content VARCHAR(200),
  test_id BINARY(16),
  PRIMARY KEY (question_name, answer_content, test_id),
  CONSTRAINT FK_QUESTIONS_ANSWERS_QUESTION_ID FOREIGN KEY (question_name) REFERENCES questions (name),
  CONSTRAINT FK_QUESTIONS_ANSWERS_ANSWER_ID FOREIGN KEY (answer_content) REFERENCES answers (content)
);

CREATE TABLE tests_questions (
  test_id        BINARY(16),
  question_name  VARCHAR(100),
  user_atutor_id VARCHAR(100),
  answer_content VARCHAR(200),
  PRIMARY KEY (test_id, question_name, user_atutor_id, answer_content),
  CONSTRAINT FK_TESTS_QUESTIONS_TEST_ID FOREIGN KEY (test_id) REFERENCES tests (test_id),
  CONSTRAINT FK_TESTS_QUESTIONS_ANSWER_ID FOREIGN KEY (answer_content) REFERENCES answers (content),
  CONSTRAINT FK_TESTS_QUESTIONS_USER_ATUTOR_ID FOREIGN KEY (user_atutor_id) REFERENCES users_atutor (name),
  CONSTRAINT FK_TESTS_QUESTIONS_QUESTION_ID FOREIGN KEY (question_name) REFERENCES questions (name)
);



