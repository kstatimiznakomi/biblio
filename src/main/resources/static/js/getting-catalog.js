window.onload = function () {
    let pageNumber = document.URL.substring(document.URL.lastIndexOf('/') + 1);
    getContent(pageNumber);
}

var content = {}

function getContent(pageNumber){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/catalog/api/' + pageNumber,
        data: false,
        success: (response) => {
            content = response.content
            content.map(function (obj){
                setContent(obj)
            })
        },
        failure: (response) => {
            alert(response)
        }
    });
    getAllAuthors();
    getAllGenres();
    getAllPublishers();
}

function setContent(obj){
    $('#content').append(`
    <div class="book-bg">
        <ul class="book" style="margin-left: 0px; margin-right: 0px;">
            <img class="book-img" src="${obj.img}">
            <li style="margin-right: 0px;">
                <div class="name-book justify-content-between">
                    <a href="book/${obj.id}">${obj.bookName}</a>
                    <span>Количество: ${obj.count}</span>
                </div>
                <div >бронь</div>
            </li>
        </ul>
    </div>
    `);
}

function getAllGenres(){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/genres/api',
        data: false,
        success: (response) => {
            content = response
            content.map(function (obj){
                setGenres(obj)
            })
        },
        failure: (response) => {
            alert(response)
        }
    });
}

function getAllAuthors(){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/author/api',
        data: false,
        success: (response) => {
            response.map(function (obj){
                setAuthors(obj)
            })
        },
        failure: (response) => {
            alert(response)
        }
    });
}

function getAllPublishers(){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/publishers/api',
        data: false,
        success: (response) => {
            response.map(function (obj){
                setPublishers(obj)
            })
        },
        failure: (response) => {
            alert(response)
        }
    });
}

function setAuthors(obj){
    $('#auth').append(`
        <option value="${obj.id}"> ${obj.authorLastName} ${obj.authorName}</option>
`)
}

function setGenres(obj){
    $('#genre').append(`
        <option value="${obj.id}"> ${obj.genreName}</option>
`)
}

function setPublishers(obj){
    $('#publ').append(`
        <option value="${obj.id}"> ${obj.publisherName}</option>
`)
}