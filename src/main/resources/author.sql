select
    b1_0.id,
    b1_0.book_name,
    b1_0.img,
    b1_0.count
from
    book b1_0
        join
    author_books a1_0
    on b1_0.id=a1_0.books_id
where
    a1_0.authors_id=2