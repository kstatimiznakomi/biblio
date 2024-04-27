select
    b1_0.id,
    b1_0.book_name,
    b1_0.count,
    b1_0.description,
    b1_0.img,
    b1_0.isbn,
    b1_0.public_date,
    b1_0.publisher_id
from
    book b1_0
        join
    author_books a1_0
    on b1_0.id=a1_0.books_id
where
    a1_0.authors_id=16 offset 0 rows fetch first 5 rows only