TRUNCATE appointment_review RESTART IDENTITY CASCADE;
TRUNCATE appointment RESTART IDENTITY CASCADE;
TRUNCATE article RESTART IDENTITY CASCADE;

TRUNCATE specialist_specialization RESTART IDENTITY CASCADE;
TRUNCATE specialization RESTART IDENTITY CASCADE;

TRUNCATE users RESTART IDENTITY CASCADE;
TRUNCATE role RESTART IDENTITY CASCADE;

INSERT INTO role(name)
VALUES ('SPECIALIST'),
       ('ADMIN'),
       ('PATIENT');

INSERT INTO users(name, email, password, phone_number, role_id)
VALUES ('user_1', 'email_1', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_1', 1),
       ('user_2', 'email_2', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_2', 2),
       ('user_3', 'email_3', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_3', 3);

INSERT INTO specialization(name)
VALUES ('test_specialization_1'),
       ('test_specialization_2'),
       ('test_specialization_3');

INSERT INTO specialist_specialization(specialist_id, specialization_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO appointment(patient_id, specialist_id, date_time, canceled)
VALUES (3, 1, now(), false);

INSERT INTO appointment_review(appointment_id, date_time, review_text)
VALUES (1, now(), 'Text review text');

INSERT INTO article(specialist_id, date_time, title, article_text)
VALUES (1, now(), 'Test article title', 'Test article text');