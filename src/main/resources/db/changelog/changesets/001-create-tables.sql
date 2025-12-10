CREATE TABLE IF NOT EXISTS projects
(
    id                      BIGSERIAL PRIMARY KEY,
    author_telegram_user_id BIGINT       NOT NULL,
    github_repository_url   VARCHAR(255) NOT NULL,
    programming_language    VARCHAR(50)  NOT NULL,
    roadmap_project         VARCHAR(50)  NOT NULL,
    added_timestamp         BIGINT       NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_projects_author_telegram_user_id
    ON projects (author_telegram_user_id);

CREATE UNIQUE INDEX IF NOT EXISTS idx_projects_github_repository_url_unique
    ON projects (github_repository_url);