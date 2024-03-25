select
    b2_0.books_id
from
    book b1_0,
    author a1_0
        left join
    author_books b2_0
    on a1_0.id=b2_0.author_id
where
    b2_0.books_id=2