function setGenres(genre){
    $('#genres').append(`
                <tr>
                    <td>
                        <button class="btn btn-warning" onclick="editGenre(${genre.id})">
                            <i class="bi bi-pencil"></i>
                        </button>
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
        $.ajax({
            type: "DELETE",
            contentType: 'application/json',
            dataType: "text",
            url: '/admin/genres/api/' + genreId + '/delete',
            success: () => {
                reloadGenresList()
            },
            failure: (response) => {
                alert(response)
            }
        })
    }
}

function editGenre(genreId) {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/genres/api/' + genreId,
        data:false,
        success: (response) => {
            console.log(response)
            $('#modal').append(`
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">
                                <h5 class="modal-title">Редактирование жанра <div ${response.genreName}/></h5>
                                <button type="button" class="btn-close" aria-label="Закрыть" onclick="closeModal()"></button>
                            </div>
                            <div class="modal-body" id="genre-name-input-edit">
                                <div class="mb-3">
                                    <label for="genreName" class="form-label">Название жанра</label>
                                    <input name="genreName" type="text" value="${response.genreName}"
                                           class="form-control" id="genreName" placeholder="Введите название жанра">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" onclick="closeModal()">Закрыть</button>
                                <button type="submit" class="btn btn-primary" onClick="saveGenre(${response.id})">Сохранить изменения</button>
                            </div>
                        </form>
                    </div>
                </div>
    `).modal('show')
        }
    })
}

function saveGenre(genreId) {
    const genreEditForm = document.getElementById('genre-name-input-edit').querySelectorAll("input")
    var genreObj = {}
    genreEditForm.forEach(item => genreObj[item.id] = item.value)
    const genre = JSON.stringify(genreObj)
    $.ajax({
        type: "PUT",
        contentType: 'application/json',
        dataType: "json",
        url: '/admin/genres/api/' + genreId + '/edit',
        data: genre,
        success: () => {
            alert("Успешно")
            closeModal()
        },
        failure: (response) => {
            alert(response)
        }
    })
}

function closeModal() {
    $('#modal').modal('hide')
    $('#modal').html("")
}

function reloadGenresList(){
    $('#genres').html("") //clear genres table
    getGenresList()
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