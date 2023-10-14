select
    b1_0.genres_id,
    b1_1.id,
    b1_1.book_name,
    b1_1.count,
    b1_1.description,
    b1_1.img,
    b1_1.isbn,
    b1_1.public_date
from
    genres_books b1_0
        join
    book b1_1
    on b1_1.id=b1_0.books_id
where
    b1_0.genres_id=1