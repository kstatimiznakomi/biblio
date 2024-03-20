
window.onload = function () {
    if (new URL(window.location.href).searchParams.has("searchText")){
        $('#searchText')[0].value = new URL(window.location.href).searchParams.get("searchText")
    }
    Params()
}
$('#search-button')[0].addEventListener('click', (e) => {
    clearSearchObj()
    e.preventDefault()
    $('#search-div')[0].querySelectorAll("input").forEach(item => item.value !== "" ? searchObj[item.id] = item.value : '')
    createUrl(searchObj)
    history.pushState(searchObj, 'Поиск', url)
    deleteContent()
    search()
})

searchObj = {}
let url = 'http://localhost:8080/catalog/search?'

function createUrl(obj) {
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

function deleteContent() {
    var books = document.querySelectorAll('.book-bg');
    books.forEach(book => {
        book.remove()
    })
}

function search(){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        url: 'http://localhost:8080/catalog/api/search',
        data: searchObj,
        success: (response)=> {
            response.content.map(function (obj){
                setContent(obj)
            })
        }
    });
}

