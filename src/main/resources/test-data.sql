DELETE FROM appointment_review;
DELETE FROM appointment;
DELETE FROM article;

DELETE FROM specialist_specialization;
DELETE FROM specialization;

DELETE FROM users;
DELETE FROM role;

ALTER SEQUENCE role_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE specialization_id_seq RESTART WITH 1;
ALTER SEQUENCE appointment_id_seq RESTART WITH 1;
ALTER SEQUENCE appointment_review_id_seq RESTART WITH 1;
ALTER SEQUENCE article_id_seq RESTART WITH 1;

INSERT INTO role(id, name)
VALUES (1, 'SPECIALIST'),
       (2, 'ADMIN'),
       (3, 'PATIENT');

INSERT INTO users(id, name, email, password, phone_number, role_id, specialist)
VALUES (1, 'user_1', 'email_1', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_1', 1, true),
       (2, 'user_2', 'email_2', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_2', 2, false),
       (3, 'user_3', 'email_3', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_3', 3, false);

INSERT INTO specialization(id, name)
VALUES (1, 'test_specialization_1'),
       (2, 'test_specialization_2'),
       (3, 'test_specialization_3');

INSERT INTO specialist_specialization(specialist_id, specialization_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO appointment(id, patient_id, specialist_id, date_time, canceled)
VALUES (1, 3, 1, now(), false);

INSERT INTO appointment_review(id, appointment_id, date_time, review_text)
VALUES (1, 1, now(), 'Text review text');

INSERT INTO article(id, specialist_id, date_time, title, article_text)
VALUES (1, 1, now(), 'Test article title', 'Test article text');