
DROP TABLE IF EXISTS tests;
CREATE TABLE tests(
    test_id VARCHAR(50),
    name VARCHAR(100),
    PRIMARY KEY (test_id)
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions(
    question_id INT AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY (question_id)
);

DROP TABLE IF EXISTS tests_questions;
CREATE TABLE tests_questions(
    test_id VARCHAR(50),
    question_id INT,
    PRIMARY KEY (test_id, question_id),
    CONSTRAINT FK_TESTS_QUESTIONS_TEST_ID FOREIGN KEY (test_id) REFERENCES tests(test_id),
    CONSTRAINT FK_TESTS_QUESTIONS_QUESTION_ID2 FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

