select
    j1_0.id,
    j1_0.book_id,
    j1_0.date_return,
    j1_0.date_take,
    j1_0.reader_ticket_id,
    j1_0.status
from
    journal_notes j1_0
where
    j1_0.reader_ticket_id=1
  and j1_0.book_id=6 and j1_0.status='Открытый'