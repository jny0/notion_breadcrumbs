DROP TABLE IF EXISTS pages;
CREATE TABLE pages (
       id BIGINT NOT NULL AUTO_INCREMENT,
       title VARCHAR(255) NOT NULL,
       content TEXT,
       parent_id BIGINT,
       PRIMARY KEY (id),
       FOREIGN KEY (parent_id) REFERENCES pages(id)
);