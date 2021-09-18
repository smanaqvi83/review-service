DROP TABLE IF EXISTS reviews;

CREATE TABLE reviews (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              product_id VARCHAR(100) NOT NULL,
                              review_score INT(10) NOT NULL);

INSERT INTO reviews (product_id, review_score) VALUES
                                                             ('BB5476', 5),
                                                             ('BB5476', 4),
                                                             ('BB5476', 3);