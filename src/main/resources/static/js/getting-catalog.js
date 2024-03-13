window.onload = function () {
    console.log(document.URL)
    let pageNumber = document.URL.substring(document.URL.lastIndexOf('/') + 1);
    console.log(pageNumber)
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