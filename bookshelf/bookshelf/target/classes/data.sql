CREATE TABLE IF NOT EXISTS books (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     pages INT NOT NULL,
                                     currentPage INT
);


INSERT INTO books (title, author, pages, currentPage) VALUES
                                                          ('Harry Potter', 'J.K. Rowling', 399, 0),
                                                          ('Lord of the Rings', 'J.R.R. Tolkien', 800, 0),
                                                          ('Hitchhikers Guide to the Galaxy', 'Douglas Adams', 200, 199),
                                                            ('The Hunger Games', 'Suzanne Collins', 300, 14);