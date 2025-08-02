CREATE TABLE card (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      blocked BOOLEAN DEFAULT FALSE,
                      block_reason VARCHAR(255),
                      blocks_amount INT DEFAULT 0,
                      board_column_id BIGINT,
                      FOREIGN KEY (board_column_id) REFERENCES board_column(id)
);
