DELETE FROM appointment_review;
DELETE FROM appointment;
DELETE FROM article;

DELETE FROM admin;
DELETE FROM patient;
DELETE FROM specialist;
DELETE FROM users;

DELETE FROM specialist_specialization;
DELETE FROM specialist;
DELETE FROM specialization;

ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE admin_id_seq RESTART WITH 1;
ALTER SEQUENCE patient_id_seq RESTART WITH 1;
ALTER SEQUENCE specialist_id_seq RESTART WITH 1;
ALTER SEQUENCE specialization_id_seq RESTART WITH 1;
ALTER SEQUENCE appointment_id_seq RESTART WITH 1;
ALTER SEQUENCE appointment_review_id_seq RESTART WITH 1;
ALTER SEQUENCE article_id_seq RESTART WITH 1;

INSERT INTO users(id, name, email, phone_number)
VALUES (1, 'user_1', 'email_1', 'phone_number_1'),
       (2, 'user_2', 'email_2', 'phone_number_2'),
       (3, 'user_3', 'email_3', 'phone_number_3');

INSERT INTO admin(id, user_id, access_level)
VALUES (1, 1, 'ALL');

INSERT INTO patient(id, user_id)
VALUES (1, 2);

INSERT INTO specialist(id, user_id, verified)
VALUES (1, 2, true);

INSERT INTO specialization(id, name)
VALUES (1, 'test_specialization_1'),
       (2, 'test_specialization_2'),
       (3, 'test_specialization_3');

INSERT INTO specialist_specialization(specialist_id, specialization_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO appointment(id, patient_id, specialist_id, date_time, canceled)
VALUES (1, 1, 1, now(), false);

INSERT INTO appointment_review(id, appointment_id, date_time, review_text)
VALUES (1, 1, now(), 'Text review text');

INSERT INTO article(id, specialist_id, date_time, title, article_text)
VALUES (1, 1, now(), 'Test article title', 'Test article text');