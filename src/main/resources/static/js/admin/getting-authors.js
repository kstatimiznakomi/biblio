window.onload = function () {
    getAuthorsList()
}

function setAuthors(obj){
    $('#authors').append(`
                <tr>
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           th:attr="data-bs-target=${'#editModal' + obj.author.id}" data-row="${obj.author}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           th:onclick="'isDelete(' + ${obj.id} + ')'"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${obj.author.id}</td>
                    <td>${obj.author.authorLastName}</td>
                    <td>${obj.author.authorName}</td>
                    <td>${obj.author.authorSurname}</td>
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
    })
        .done(function (data){
            data.map(function (author){
                setAuthors(author)
                }
            )
        })
}