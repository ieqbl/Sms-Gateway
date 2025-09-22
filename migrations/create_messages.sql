CREATE TABLE IF NOT EXISTS migrations
(
    id SERIAL PRIMARY KEY,
    filename TEXT UNIQUE NOT NULL,
    applied_at TIMESTAMP DEFAULT NOW()
);


CREATE TABLE IF NOT EXISTS config
(
    id SERIAL PRIMARY KEY,
    config_driver TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS sms_logs
(
    id SERIAL PRIMARY KEY,
    from_address VARCHAR(255) NOT NULL,
    to_address VARCHAR(255) NOT NULL,
    Driver VARCHAR(255) not null,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    retry int default 0,
    driver_response Text
);

CREATE TABLE IF NOT EXISTS messages
(
    id SERIAL PRIMARY KEY,
    phone VARCHAR(255),
    content TEXT
);

