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

CREATE TABLE users_atutor (
  name VARCHAR(100),
  PRIMARY KEY (name)
);

CREATE TABLE tests (
  test_id BINARY(16),
  name    VARCHAR(100),
  discipline_name VARCHAR(300),
  groups VARCHAR(10),
  course INT,
  submission_time DATETIME,
  atutor_user_id VARCHAR(100),
  PRIMARY KEY (test_id),
  CONSTRAINT FK_TESTS_ATUTOR_USER_ID FOREIGN KEY (atutor_user_id) REFERENCES users_atutor(name)
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
  answer_content VARCHAR(200),
  PRIMARY KEY (test_id, question_name, answer_content),
  CONSTRAINT FK_TESTS_QUESTIONS_TEST_ID FOREIGN KEY (test_id) REFERENCES tests (test_id),
  CONSTRAINT FK_TESTS_QUESTIONS_ANSWER_ID FOREIGN KEY (answer_content) REFERENCES answers (content),
  CONSTRAINT FK_TESTS_QUESTIONS_QUESTION_ID FOREIGN KEY (question_name) REFERENCES questions (name)
);

CREATE TABLE tests_results(
  test_id BINARY(16),
  max INT,
  current DOUBLE,
  PRIMARY KEY (test_id),
  CONSTRAINT FK_TESTS_RESULTS_TEST_ID FOREIGN KEY (test_id) REFERENCES tests(test_id)
);

CREATE TABLE users(
  name VARCHAR(30) PRIMARY KEY,
  password TEXT,
  enabled BOOL
);

CREATE TABLE users_roles(
  user_role_id INT AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(20),
  user_name VARCHAR(30),
  CONSTRAINT FK_USERS_ROLES_ROLE FOREIGN KEY (user_name) REFERENCES users(name)
);


