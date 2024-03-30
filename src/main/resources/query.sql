select
    b1_0.id,
    b1_0.book_name,
    b1_0.count,
    b1_0.description,
    b1_0.img,
    b1_0.isbn,
    b1_0.public_date
from
    book b1_0 offset 0 rows fetch first 5 rows only