function setGenres(genre){
    $('#genres').append(`
                <tr>
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target=$('#editModal' + ${genre.id}") data-row="${genre}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           onclick="isDelete(${genre.id})"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${genre.id}</td>
                    <td>${genre.genreName}</td>
                </tr>
`)
}

function getGenresList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/genres/api',
        data: false,
        failure: (error) => {
            console.log(error)
        }
    })
        .done((response) => {
            response.map((genre) => {
                    setGenres(genre)
                }
            )
        })
}