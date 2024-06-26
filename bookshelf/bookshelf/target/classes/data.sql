
DROP TABLE IF EXISTS ALLBOOKS;
DROP TABLE IF EXISTS QUOTES;

CREATE TABLE IF NOT EXISTS ALLBOOKS (
                                     ID LONG AUTO_INCREMENT PRIMARY KEY,
                                     TITLE VARCHAR(255) NOT NULL,
                                     AUTHOR VARCHAR(255) NOT NULL,
                                     PAGES_TOTAL INT NOT NULL,
                                     PAGE_CURRENT INT,
                                     BOOK_URL VARCHAR(255) NOT NULL
);





INSERT INTO ALLBOOKS (TITLE, AUTHOR, PAGES_TOTAL, PAGE_CURRENT, BOOK_URL) VALUES
                                                          ('Harry Potter', 'J.K. Rowling', 400, 0, 'images/12.png'),
                                                          ('Lord of the Rings', 'J.R.R. Tolkien', 800, 0, 'images/18.png'),
                                                          ('Hitchhikers Guide to the Galaxy', 'Douglas Adams', 200, 199, 'images/21.png'),
                                                          ('The Hunger Games 2', 'Suzanne Collins', 330, 54, 'images/2.png'),
                                                            ('The Hunger Games', 'Suzanne Collins', 300, 14,'images/7.png');



CREATE TABLE IF NOT EXISTS QUOTES(
                                     ID LONG AUTO_INCREMENT PRIMARY KEY,
                                     QUOTE VARCHAR(255) NOT NULL,
                                     ALLBOOKS_ID LONG NOT NULL ,
                                     FOREIGN KEY (ALLBOOKS_ID) REFERENCES ALLBOOKS (ID)

);

INSERT INTO QUOTES (QUOTE,ALLBOOKS_ID) VALUES
                                           ('I swear to do only mischief', 1),
                                            ('Happiness can be found even in the darkest of times if only one remembers to turn on the light',1),
                                            ('Even the smallest person can change the course of the future',2);

