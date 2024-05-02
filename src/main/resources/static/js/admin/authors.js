function setAuthors(author){
    $('#authors').append(`
                <tr>
                    <td>
                        <a id="${author.id}" class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target=$('#editModal' + ${author.id}") data-row="${author}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           onclick="isDelete(author, ${author.id})"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${author.id}</td>
                    <td>${author.authorLastName}</td>
                    <td>${author.authorName}</td>
                    <td>${author.authorSurname}</td>
                </tr>
`)
}

function getAuthorsList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/author/api',
        data: false,
        failure: (error) => {
            console.log(error)
        }
    })
        .done((response) => {
            response.map((author) => setAuthors(author))
        })
}