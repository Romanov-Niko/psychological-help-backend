DELETE FROM users;
DELETE FROM role;

INSERT INTO role(id, name)
VALUES (1, 'SPECIALIST'),
       (2, 'ADMIN'),
       (3, 'PATIENT');

INSERT INTO users(id, name, email, password, phone_number, role_id, specialist)
VALUES (1, 'user_1', 'email_1', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_1', 1, true),
       (2, 'user_2', 'email_2', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_2', 2, false),
       (3, 'user_3', 'email_3', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', 'phone_number_3', 3, false);