ALTER TABLE users ADD email VARCHAR(255) UNIQUE NULL;
ALTER TABLE users ADD country_code VARCHAR(255) NULL;
ALTER TABLE users ADD phone_number VARCHAR(255) NULL;
ALTER TABLE users ADD authy_id BIGINT NULL;