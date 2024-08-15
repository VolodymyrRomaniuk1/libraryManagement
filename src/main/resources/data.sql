insert into books(title, author, amount) values ('Book 1', 'Mark Twain', 3);
insert into books(title, author, amount) values ('Book 2', 'Mark Twain', 0);
insert into books(title, author, amount) values ('Book 3', 'Mark Twain', 15);
insert into books(title, author, amount) values ('Book 4', 'Ernest Hemingway', 5);
insert into books(title, author, amount) values ('Book 5', 'Ernest Hemingway', 4);
insert into books(title, author, amount) values ('Book 6', 'William Shakespeare', 2);

insert into members(name, membership_date) values ('John Doe', '2024-06-15');
insert into members(name, membership_date) values ('Maria Brown', '2024-07-14');
insert into members(name, membership_date) values ('Anthony Carson', '2024-07-20');

insert into borrows(book_id, member_id, borrow_date) values (1, 2, '2024-07-15');
insert into borrows(book_id, member_id, borrow_date) values (1, 2, '2024-07-15');
insert into borrows(book_id, member_id, borrow_date) values (1, 2, '2024-07-16');
insert into borrows(book_id, member_id, borrow_date) values (4, 2, '2024-07-17');
insert into borrows(book_id, member_id, borrow_date) values (4, 1, '2024-07-15');
insert into borrows(book_id, member_id, borrow_date) values (5, 1, '2024-07-15');
insert into borrows(book_id, member_id, borrow_date) values (2, 3, '2024-07-22');