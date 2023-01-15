TRUNCATE appointment_review RESTART IDENTITY CASCADE;
TRUNCATE appointment RESTART IDENTITY CASCADE;
TRUNCATE article RESTART IDENTITY CASCADE;

TRUNCATE specialist_specialization RESTART IDENTITY CASCADE;
TRUNCATE specialization RESTART IDENTITY CASCADE;

TRUNCATE users RESTART IDENTITY CASCADE;
TRUNCATE role RESTART IDENTITY CASCADE;
TRUNCATE password_recovery RESTART IDENTITY CASCADE;
TRUNCATE files RESTART IDENTITY CASCADE;

INSERT INTO files(data, name, type)
VALUES (lo_import('/images/people/human1.jpg'), 'human1', 'jpg'),
       (lo_import('/images/people/human2.jpg'), 'human2', 'jpg'),
       (lo_import('/images/people/human3.jpg'), 'human3', 'jpg'),
       (lo_import('/images/people/human4.jpg'), 'human4', 'jpg'),
       (lo_import('/images/articles/article1.jpg'), 'article1', 'jpg'),
       (lo_import('/images/articles/article2.jpg'), 'article2', 'jpg'),
       (lo_import('/images/articles/article3.jpg'), 'article3', 'jpg'),
       (lo_import('/images/articles/article4.jpg'), 'article4', 'jpg'),
       (lo_import('/images/articles/article5.jpg'), 'article5', 'jpg'),
       (lo_import('/images/articles/article6.jpg'), 'article6', 'jpg');

INSERT INTO role(name)
VALUES ('SPECIALIST'),
       ('ADMIN'),
       ('PATIENT');

INSERT INTO users(name, email, password, phone_number, role_id, image)
VALUES ('Андрій Мірошник', 'email_1', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', '380573957296', 1, 1),
       ('Олійник Артем', 'email_2', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', '380573395735', 2, 2),
       ('Романов Микола', 'email_3', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', '38057499385', 3, 3),
       ('Витошко Олена', 'email_4', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', '38043097234', 3, 4),
       ('Ігнатенко Микола', 'artem98962@gmail.com', '$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO', '38045873495', 3, null);

INSERT INTO specialization(name)
VALUES ('Психофізіологія'),
       ('Корекційна психологія'),
       ('Медична психологія');

INSERT INTO specialist_specialization(specialist_id, specialization_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO appointment(patient_id, specialist_id, date_time, free, canceled)
VALUES (3, 1, now(), true, false),
       (4, 1, now()+ interval '1 hour', true, false),
       (5, 1, now()+ interval '1 day', true, false),
       (5, 1, now()+ interval '2 days 30 minutes', true, false);

INSERT INTO appointment_review(appointment_id, date_time, review_text)
VALUES (1, now(), 'Гарна робота!');

INSERT INTO article(specialist_id, date_time, title, preview_text, article_text, image)
VALUES (1, now(), 'Що таке панічна атака?', 'Панічна атака - це короткий епізод сильного занепокоєння, що викликає фізичні відчуття страху, такі як прискорене серцебиття, задишку або запаморочення.', 'Test article text', 5),
       (1, now(), 'Синдром професійного вигорання: що робити, якщо ти «перегорів»?', 'Чи часто з вами трапляється таке, що вам зовсім не хочеться йти на роботу, ви відчуваєте постійну втому, безнадійність та розбитість? ', 'Test article text', 6),
       (1, now(), 'Що таке (ПТСР) посттравматичний стресовий розлад?', 'Посттравматичний стресовий розлад (ПТСР) - це тривожний розлад, спричинений дуже стресовими, лякаючими або неприємними подіями.', 'Test article text', 7),
       (1, now(), 'Як позбутися звички гризти нігті?', 'Про причини хвороби, коли гризуть нігті та про те, як позбутися шкідливої звички гризти нігті розповідаємо у статті.', 'Test article text', 8),
       (1, now(), 'Вид харчового розладу: орторексія', 'Здорове харчування може призвести до значного покращення здоровя.', 'Test article text', 9),
       (1, now(), 'Як протистояти булінгу?', 'Більшість з нас стикається з булінгом у певні моменти свого життя, наприклад з булінгом в школі або булінгом на роботі.', 'Test article text', 10);

INSERT INTO password_recovery(user_id, message)
VALUES (4, 'aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee');



