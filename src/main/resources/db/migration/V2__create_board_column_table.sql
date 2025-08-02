CREATE TABLE board_column (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              kind VARCHAR(50) NOT NULL,
                              `order` INT NOT NULL,
                              board_id BIGINT,
                              FOREIGN KEY (board_id) REFERENCES board(id)
);
