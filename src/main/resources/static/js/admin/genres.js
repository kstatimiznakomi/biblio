function setGenres(genre){
    $('#genres').append(`
                <tr>
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target=$('#editModal' + ${genre.id}") data-row="${genre}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <button class="btn btn-danger"
                           onclick="deleteGenre(${genre.id})"><i class="bi bi-trash"></i></button>
                    </td>
                    <td>${genre.id}</td>
                    <td>${genre.genreName}</td>
                </tr>
`)
}

function deleteGenre(genreId){
    let isConf = confirm("Удалить запись?");
    if (isConf) {
        //document.location.replace("/admin/genres/" + genreId + "/delete");
        $.ajax({
            type: "DELETE",
            contentType: 'application/json',
            dataType: "text",
            url: '/admin/genres/api/' + genreId + '/delete',
            success: () => {
                //alert("Успешно")
                reloadGenresList()
            },
            failure: (response) => {
                alert(response)
            }
        })
    }
}

function reloadGenresList(){
    $('#genres').html("") //clear genres table
    getGenresList()
}

function getGenresList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "text",
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

const submitGenreAddBtn = document.getElementById("submit-genre-add-btn")
const genreAddForm = document.getElementById('genre-name-input-add').querySelectorAll("input")

submitGenreAddBtn.addEventListener('click', () => {
    var genreObj = {}
    genreAddForm.forEach(item => genreObj[item.id] = item.value)
    const genre = JSON.stringify(genreObj)
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        dataType: "text",
        url: '/admin/genres/api/add',
        data: genre,
        success: () => {
            alert("Успешно")
        },
        failure: (response) => {
            alert(response)
        }
    });
})