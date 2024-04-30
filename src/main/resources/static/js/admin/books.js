function setBooks(book){
    $('#books').append(`
                <tr >
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target=$('#editModal' + ${book.id}") data-row="${book}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           onclick="isDelete(${book.id})"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${book.id}</td>
                    <td>${book.bookName}</td>
                    <td>
                        <div style="height: 100px; overflow:auto;">${book.description}</div>
                    </td>
                    <td>
                        <img class="book-img-prof" width="100px" src="${book.img}">
                    </td>
                    <td>${book.isbn}</td>
                    <td>${book.publicDate}</td>
                    <td>${book.count}</td>
                </tr>
`)
}

function getBooksList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/books/api',
        data: false,
        failure: (error) => {
            console.log(error)
        }
    })
        .done((response) => {
            response.map((author) => {
                    setBooks(author)
                }
            )
        })
}