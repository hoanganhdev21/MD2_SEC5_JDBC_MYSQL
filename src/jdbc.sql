create database fresher_book_management;
use fresher_book_management;
create table Book(
    id int primary key auto_increment,
    name varchar(100) not null unique ,
    price float,
    title varchar(200),
    author varchar(50),
    book_status bit
);
DELIMITER &&
create procedure get_all_book()
BEGIN
    select * from Book;
end &&
DELIMITER &&
create procedure create_book(
    book_name varchar(100),
    book_price float,
    book_title varchar(200),
    book_author varchar(50),
    status bit
)
BEGIN
    insert into Book(name, price, title, author, book_status)
        values (book_name,book_price,book_title,book_author,status);
end &&
DELIMITER &&
create procedure update_book(
    book_id int,
    book_name varchar(100),
    book_price float,
    book_title varchar(200),
    book_author varchar(50),
    status bit
)
BEGIN
    update Book
        set name=book_name,
            price = book_price,
            title = book_title,
            author = book_author,
            book_status = status
    where id = book_id;
end &&
DELIMITER &&
create procedure delete_book(book_id int)
BEGIN
    delete from Book where id = book_id;
end &&
DELIMITER &&
create procedure get_book_by_id(book_id int,out cnt_book int)
BEGIN
    set cnt_book = (select count(id) from Book where id = book_id);
end &&
DELIMITER &&
create procedure get_by_id(book_id int)
BEGIN
    select * from Book where id = book_id;
end &&