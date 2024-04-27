select
    b1_0.id,
    b1_0.book_name,
    b1_0.count,
    b1_0.description,
    b1_0.img,
    b1_0.isbn,
    b1_0.public_date
from
    book b1_0
        join
    author_books a1_0
    on b1_0.id=a1_0.books_id
        join
    genres_books g1_0
    on b1_0.id=g1_0.books_id
where
    a1_0.authors_id=2
  and g1_0.genres_id=17