CREATE TABLE IF NOT EXISTS role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(64)                  NOT NULL,
    email        VARCHAR(64)                  NOT NULL,
    password     VARCHAR(64)                  NOT NULL,
    phone_number VARCHAR(64)                  NOT NULL,
    role_id      INTEGER REFERENCES role (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS application
(
    id       SERIAL PRIMARY KEY,
    verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS specialization
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS specialist_specialization
(
    specialist_id     INTEGER REFERENCES users (id)          NOT NULL,
    specialization_id INTEGER REFERENCES specialization (id) NOT NULL,
    PRIMARY KEY (specialist_id, specialization_id)
);

CREATE TABLE IF NOT EXISTS appointment
(
    id            SERIAL PRIMARY KEY,
    patient_id    INTEGER REFERENCES users (id) NOT NULL,
    specialist_id INTEGER REFERENCES users (id) NOT NULL,
    date_time     TIMESTAMP                     NOT NULL,
    canceled      BOOLEAN                       NOT NULL
);

CREATE TABLE IF NOT EXISTS appointment_review
(
    id             SERIAL PRIMARY KEY,
    appointment_id INTEGER REFERENCES appointment (id) NOT NULL,
    date_time      TIMESTAMP                           NOT NULL,
    review_text    TEXT                                NOT NULL
);

CREATE TABLE IF NOT EXISTS article
(
    id            SERIAL PRIMARY KEY,
    specialist_id INTEGER REFERENCES users (id) NOT NULL,
    date_time     TIMESTAMP                     NOT NULL,
    title         VARCHAR(256)                  NOT NULL,
    preview_text  VARCHAR(256)                  NOT NULL,
    article_text  TEXT                          NOT NULL
);