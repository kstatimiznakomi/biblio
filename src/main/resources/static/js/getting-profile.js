


window.onload = function () {
    getUser()
    getNotes()
}

function getNotes() {
    $.ajax({
        url: '/profile/get-notes',
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        data: false
    })
        .done(function (data){
            data.map(function (note){
                    setBooks(note)
                }
            )
        })
}

function setBooks(data) {
    let diffDys = moment(data.dateReturn).diff(new Date(), 'days')
    let diffHrs = moment(data.dateReturn).diff(new Date(), 'hours') % diffDys

    $('#notes').append(`
            <div class="res-book-bg align-items-center">
                <div class="row w-100 align-items-center">
                    <div class="col-2">
                        <img class="book-img-prof" width="90px" src="${data.book.img}">
                    </div>
                    <div class="col-6">
                        <a href="/book/${data.book.id}">${data.book.bookName}</a>
                    </div>
                    <div class="col-4">
                        <span class="noselect">Осталось: ${diffDys} дней, ${diffHrs} часов</span>
                    </div>
                </div>
            </div>
    `)
}

function setUser(data) {
    $('#info-details').append(`
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.lastname}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls ">${data.name}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.surname}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.username}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls ">${data.email}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.phone}</span>
                    <hr class="info-details-hr">
    `)
}

function getUser(){
    $.ajax({
        cache: false,
        url: '/profile/api',
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        data: false
    })
        .done(function (data){
            setUser(data)
        })
}