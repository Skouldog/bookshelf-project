CREATE TABLE IF NOT EXISTS ALLBOOKS (
                                     ID LONG AUTO_INCREMENT PRIMARY KEY,
                                     TITLE VARCHAR(255) NOT NULL,
                                     AUTHOR VARCHAR(255) NOT NULL,
                                     PAGES_TOTAL INT NOT NULL,
                                     PAGE_CURRENT INT
);





INSERT INTO ALLBOOKS (TITLE, AUTHOR, PAGES_TOTAL, PAGE_CURRENT) VALUES
                                                          ('Harry Potter', 'J.K. Rowling', 400, 0),
                                                          ('Lord of the Rings', 'J.R.R. Tolkien', 800, 0),
                                                          ('Hitchhikers Guide to the Galaxy', 'Douglas Adams', 200, 199),
                                                          ('The Hunger Games 2', 'Suzanne Collins', 330, 54),
                                                            ('The Hunger Games', 'Suzanne Collins', 300, 14);



CREATE TABLE IF NOT EXISTS QUOTES(
                                     ID LONG AUTO_INCREMENT PRIMARY KEY,
                                     QUOTE VARCHAR(255) NOT NULL,
                                     ALLBOOKS_ID LONG NOT NULL ,
                                     FOREIGN KEY (ALLBOOKS_ID) REFERENCES ALLBOOKS (ID)

);
