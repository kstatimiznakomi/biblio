/////////////////////////// main
window.onload = function () {
    if (window.location.href.includes("page")) {
        Params();
        ParamsFromSearch();
        fillParamsFromUrl();
        search()
    }
    else {
        let pageNumber = document.URL.substring(document.URL.lastIndexOf('/') + 1);
        Params();
        getContent(pageNumber);
    }
}

let bookExist = true;
let signedUser = false;
searchObj = {}

function userCheck(){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/signed-user',
        data: false,
        success: (response) => {
            response === true ? signedUser = true : signedUser = false
        },
        failure: (response) => {
            alert(response)
        }
    });
}

function Params(){
    getAllAuthors();
    getAllGenres();
    getAllPublishers();
}

function getContent(pageNumber){
    userCheck();
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/catalog/api/' + pageNumber,
        data: false,
        success: (response) => {
            response.content.map(function (obj){
                setContent(obj)
            })
            getMainPager(response)
        },
        failure: (response) => {
            alert(response)
        }
    });
}

function setContent(obj){
    obj.count > 0 ? bookExist = true : bookExist = false;
    $('#content').append(`
    <div class="book-bg mt-2 col-3 transition-all">
        <ul class="book transition-all" style="margin-left: 0px; margin-right: 0px;">
            <li style="margin-right: 0px;">
                <div class="name-book">
                    <img class="book-img mt-3" src="${obj.img}">
                    <div class="col-11 overflow-hidden book-nm">
                        <a href="book/${obj.id}">${obj.bookName}</a>
                    </div>
                    <span>Количество: ${obj.count}</span>
                ${signedUser === true ? 
                        bookExist === true ? reserveOpen(obj) : reserveBooksNull()
                : notSigned()}
            </li>
        </ul>
    </div>
    `);
}

function reserveOpen(obj){
    return `
    <div class="btn-book">
        <a href="/books/add/${obj.id}" class="btn__menu mb-3">Забронировать</a>
    </div>
    `;
}

function notSigned(){
    return `
    <div class="btn-book-locked">
        <span class="btn__menu">Войдите, чтобы бронировать книги</span>
    </div>
    `;
}

function reserveBooksNull(){
    return `
    <div class="btn-book-locked mb-3">
        <span class="btn__menu">Книги нет в наличии</span>
    </div>
    `;
}

function getAllGenres(){
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/genres/api',
        data: false,
        success: (response) => {
            response.map(function (obj){
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

function hasEmptyParams(){
    return window.location.href.includes("bookName") === false &&
        window.location.href.includes("publisherId") === false &&
        window.location.href.includes("authorId") === false &&
        window.location.href.includes("genreId") === false &&
        window.location.href.includes("publicDate") === false
}

///////////////////////////////////// for search

$('#search-button')[0].addEventListener('click', (e) => {
    e.preventDefault()
    clearSearchObj()
    fillSearchObj()
    createUrl()
    history.pushState(searchObj, 'Поиск', url)
    deleteContent()
    search()
})

function fillSearchObj(){
    $('#search-div')[0].querySelectorAll("input").forEach(item => item.value !== "" ? searchObj[item.id] = item.value : '')
}

function fillParamsFromUrl(){
    clearSearchObj()
    let url = new URLSearchParams(window.location.href)
    let newUrl = url.toString().split('%3F')[1]
    let rightStrUrl = '?' + newUrl
    let correct = new URLSearchParams(rightStrUrl)
    searchObj = Object.fromEntries(correct.entries())
}

function fillParams(){
    let url = new URLSearchParams(window.location.href)
    let newUrl = url.toString().split('%3F')[1]
    let rightStrUrl = '?' + newUrl
    let correct = new URLSearchParams(rightStrUrl)
    $('#searchText').value = correct.get("searchText")
    $('#publishDate').value = correct.get("publishDate")
}

function ParamsFromSearch(){
    $('#auth')[0].value = 1;
    $('#searchText')[0].value = new URL(window.location.href).searchParams.get("searchText")
    $('#publishDate')[0].value = new URL(window.location.href).searchParams.get("publishDate")
    $('#genre')[0].value = new URL(window.location.href).searchParams.get("genreId")
    $('#publ')[0].value = new URL(window.location.href).searchParams.get("publisherId")
}
function getPagesLess(response){
    $('#pager').append(`
    <div class="pages" align="center">
    <span>Всего элементов: ${response.totalElements}</span>
</div>
    `)
}

function getPagesEmpty(response){
    $('#pager').append(`
    <div class="pages" align="center">
    <span>По данному запросу не было найдено книг :(</span>
</div>
    `)
}

function getMainPager(response){
    $('#pager').append(`
    <div class="pages" align="center">
    <span>Всего элементов: ${response.totalElements} - Страница ${response.number + 1} из ${response.totalPages}</span>
    &nbsp;
    <div>
        <ul class="mt-1 paging justify-content-center">
            
        </ul>
    </div>
</div>
    `)
    document.querySelector('.paging').innerHTML = getPagesCatalog(response.number + 1, response.totalPages)
}

function getSearchPager(response){
    $('#pager').append(`
    <div class="pages" align="center">
    <span>Всего элементов: ${response.totalElements} - Страница ${response.number + 1} из ${response.totalPages}</span>
    &nbsp;
    <div>
        <ul class="mt-1 paging justify-content-center">
            
        </ul>
    </div>
</div>
    `)
    document.querySelector('.paging').innerHTML = getSearchPagerr(response.number + 1, response.totalPages)
}

function getPagerSearch(){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: '/catalog/api/search',
        data: searchObj,
        success: (response)=> {
            getSearchPager(response)
            fillParams()
            if (response.totalElements < 5){
                getPagesEmpty(response)
            }
        }
    });
}

function search() {
    userCheck()
    cover()
    // preloader y
    // paginator half
    // login y

    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: '/catalog/api/search',
        data: searchObj,
        success: (response) => {
            response.content.map((obj) => {
                setContent(obj)
            })
            removeCover()
            fillParams()
            deletePager()
            if (response.totalElements < 5) {
                response.totalElements === 0 ? getPagesEmpty() : getPagesLess(response)
            }
            else getSearchPager(response)
        }
    });

}
let url = 'http://localhost:8080/catalog/search?'

function createUrl() {
    url = 'http://localhost:8080/catalog/search?'
    for (let item in searchObj) {
        url += item + '=' + searchObj[item] + '&'
    }
    url = url.slice(0, -1)
}

function clearSearchObj() {
    for (let item in searchObj) {
        delete searchObj[item];
    }
}

function deletePager() {
    var pages = document.querySelectorAll('.pages');
    pages.forEach(page => {
        page.remove()
    })
}

function deleteContent() {
    var books = document.querySelectorAll('.book-bg');
    books.forEach(book => {
        book.remove()
    })
}