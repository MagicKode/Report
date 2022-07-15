CREATE TABLE IF NOT EXISTS reportInfo
(
    report_info_id SERIAL PRIMARY KEY,
    status_type    INTEGER NOT NULL,
    report_title   VARCHAR(128) NOT NULL,
    path_file      VARCHAR(128) NOT NULL,
    created_at     TIMESTAMP DEFAULT NOW(),
    updated_at     TIMESTAMP DEFAULT NULL
);
