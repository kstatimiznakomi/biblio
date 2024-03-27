select
    book.id,
    book.book_name,
    book.count
from
    book
         join
    author_books b2_0
    on book.id=b2_0.books_id
         join
    author b_3
    on b_3.id = b2_0.authors_id
where b_3.id = 2
