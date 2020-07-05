USE `bookReviewRS`;
INSERT IGNORE INTO USER(email,username,password)  VALUES('1@gmail.com','testuser1','testuser1');
INSERT IGNORE INTO USER(email,username,password)  VALUES('2@gmail.com','testuser2','testuser2');
INSERT IGNORE INTO USER(email,username,password)  VALUES('3@gmail.com','testuser3','testuser3');
INSERT IGNORE INTO USER(email,username,password)  VALUES('4@gmail.com','testuser4','testuser4');
INSERT IGNORE INTO USER(email,username,password)  VALUES('5@gmail.com','testuser5','testuser5');
INSERT IGNORE INTO USER(email,username,password)  VALUES('6@gmail.com','testuser6','testuser6');
INSERT IGNORE INTO USER(email,username,password)  VALUES('test@gmail.com', '1234567890','test');
INSERT IGNORE INTO USER(email,username,password)  VALUES('test1@gmail.com', '9365937493','test1');
INSERT IGNORE INTO USER(email,username,password)  VALUES('a2@gmail.com','projecta2','a2');
INSERT IGNORE INTO USER(email,username,password)  VALUES('jreynolds25@student.gsu.edu','jreynolds25','Charisma143$$$');

INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9781523504459','1,000 Books to Read Before You Die: A Life-Changing List','James Mustich');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9780439136358','Harry Potter and the Prisoner of Azkaban','J.K. Rowling');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9780060883287','One Hundred Years of Solitude (P.S.)','Gabriel Garcia Marquez');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9789353242060','Gone with the Wind','Margaret Mitchell');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9780224017893','Missing Person','Patrick Modiano');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9780439023481','The Hunger Games','Suzanne Collins');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9781492180746','The Mom Test','Rob Fitzpatrick');
INSERT IGNORE INTO BOOK(isbn,title,author) VALUES('9781593278175', 'The Manga Guide To Microprocessors', 'Michio Shibuya');

INSERT IGNORE INTO SEARCHISTORY(userID,isbn) VALUES('1','9780439136358');
INSERT IGNORE INTO SEARCHISTORY(userID,isbn) VALUES('1','9789353242060');
INSERT IGNORE INTO SEARCHISTORY(userID,isbn) VALUES('2','9780224017893');
INSERT IGNORE INTO SEARCHISTORY(userID,isbn) VALUES('4','9780439136358');



