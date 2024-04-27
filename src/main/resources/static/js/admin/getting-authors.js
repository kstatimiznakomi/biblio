window.onload = () => {
    console.log("imhere")
    getAuthorsList()
}



function setAuthors(obj){
    $('#authors').append(`
                <tr>
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target="$(#editModal) ${obj.id}" data-row="${obj.author}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           onclick="isDelete(${obj.id})"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${obj.id}</td>
                    <td>${obj.authorLastName}</td>
                    <td>${obj.authorName}</td>
                    <td>${obj.authorSurname}</td>
                </tr>
`)
}

$('#editModal')

function getAuthorsList() {
    console.log("imhere")
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
            console.log(response)
            response.map((author) => {
                setAuthors(author)
                }
            )
        })
}