DROP DATABASE IF EXISTS easytutor;
CREATE DATABASE easytutor;
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
  PRIMARY KEY (test_id)
);

CREATE TABLE questions (
  question_id INT AUTO_INCREMENT,
  name        VARCHAR(100),
  header      VARCHAR(100),
  PRIMARY KEY (question_id)
);

CREATE TABLE answers (
  answer_id INT,
  content   VARCHAR(200),
  PRIMARY KEY (answer_id)
);

CREATE TABLE users_atutor (
  user_atutor_id INT,
  name           VARCHAR(100),
  PRIMARY KEY (user_atutor_id)
);

CREATE TABLE tests_questions (
  test_id        BINARY(16),
  question_id    INT,
  user_atutor_id INT,
  answer_id      INT,
  PRIMARY KEY (test_id, question_id, user_atutor_id, answer_id),
  CONSTRAINT FK_TESTS_QUESTIONS_TEST_ID FOREIGN KEY (test_id) REFERENCES tests (test_id),
  CONSTRAINT FK_TESTS_QUESTIONS_ANSWER_ID FOREIGN KEY (answer_id) REFERENCES answers (answer_id),
  CONSTRAINT FK_TESTS_QUESTIONS_USER_ATUTOR_ID FOREIGN KEY (user_atutor_id) REFERENCES users_atutor (user_atutor_id),
  CONSTRAINT FK_TESTS_QUESTIONS_QUESTION_ID2 FOREIGN KEY (question_id) REFERENCES questions (question_id)
);

CREATE TABLE questions_answers (
  question_id INT NOT NULL,
  answer_id   INT NOT NULL,
  PRIMARY KEY (question_id, answer_id),
  CONSTRAINT FK_QUESTIONS_ANSWERS_QUESTION_ID FOREIGN KEY (question_id) REFERENCES questions (question_id),
  CONSTRAINT FK_QUESTIONS_ANSWERS_ANSWER_ID FOREIGN KEY (answer_id) REFERENCES answers (answer_id)
);


