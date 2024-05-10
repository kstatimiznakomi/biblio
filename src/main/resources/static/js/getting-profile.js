window.onload = function () {
    if (window.location.href.includes("edit")) {
        getUserToEdit()
        isEmptyFunc()
    }
    else{
        getUser()
        getNotes()
    }
}
let bookIds = []
let listOfEmpty = []

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
            if (bookIds.length > 0) sendBookList()
        })
}

function renderNote(data, diffDys, diffHrs) {
    $('#notes').append(`
            <div role="button" onclick="modalBookInfo(${data.book.id})" class="res-book-bg align-items-center">
                <div class="row w-100 align-items-center">
                    <div class="col-2">
                        <img class="book-img-prof" width="90px" src="${data.book.img}">
                    </div>
                    <div class="col-6">
                        <span>${data.book.bookName}</span>
                    </div>
                    <div class="col-4">
                        <span class="noselect">Осталось: ${diffDys} дней, ${diffHrs} часов</span>
                    </div>
                </div>
            </div>
        `)
}

function setBooks(data) {
    let diffDys = moment(data.dateReturn).diff(new Date(), 'days')
    let diffHrs = moment(data.dateReturn).diff(new Date(), 'hours') % diffDys
    if (diffDys <= 0){
        if (diffHrs <= 0){
            getBooksList(data.book.id)
        } else renderNote(data, diffDys, diffHrs)
    } else renderNote(data, diffDys, diffHrs)
}

function refetch() {
    $.ajax({
        url: '/profile/get-notes',
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        data: false
    })
        .done((data) => {
            data === {}
                ?
                $('#notes').append(`
                            <h1>Здесь нету книг</h1>
                        `)
                : renderNote(data, diffDys, diffHrs)
        })
}

function getBooksList(bookId){
    bookIds.push(bookId)
}

function sendBookList() {
    $.ajax({
        url: '/books/complete/' + bookIds,
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        data: false
    })
}

function getUserToEdit(){
    $.ajax({
        cache: false,
        url: '/profile/api',
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        data: false
    })
        .done(function (response) {
            userGot = {}
            setUserEdit(response)
            userGot = response
        })
}

let userGot = {}
let userSend = {}

function isEmptyInput(value){
    isEmptyFunc()
    value === '' || listOfEmpty.length ?
        $('#user-edit-btn').prop('disabled', true)
        :
        $('#user-edit-btn').prop('disabled', false)
}

function setUserEdit(data) {
    $('#edit-user').prepend(`
        <input type="text" oninput="isEmptyInput(this.value)" value="${data.lastname}" class="user" placeholder="Фамилия" id="lastname" required>
        <input type="text" oninput="isEmptyInput(this.value)" value="${data.name}" class="user" placeholder="Имя" id="name" required>
        <input type="text" oninput="isEmptyInput(this.value)" value="${data.surname}" class="user" placeholder="Отчество" id="surname" required>
        <input type="text" oninput="isEmptyInput(this.value)" value="${data.username}" class="user" placeholder="Имя пользователя" id="username" required>
        <hr class="separator">
        <input type="email" oninput="isEmptyInput(this.value)" value="${data.email}" class="user" placeholder="Почта" id="email" required>
        <input type="number" oninput="isEmptyInput(this.value)" value="${data.phone}" class="user" placeholder="Номер телефона" id="phone" required>
    `)
}

const fillUserSend = () => {
    userSend = {}
    $('#edit-user')[0]
        .querySelectorAll("input")
        .forEach(item => userSend[item.id] = item.value)
}

function errorSpan(text, id){
    return `<label for="${id}" class="error">${text}</label>`
}



$('#user-edit-btn')[0].addEventListener('click', (e) => {
    $("label").remove()
    e.preventDefault()
    cover()

    fillUserSend()

    const jsonUser = JSON.stringify(userSend)
    isEmptyFunc()
    if (!isEmpty) {
        delete userSend["seq_password"]

        $.ajax({
            cache: false,
            url: '/profile/put',
            type: "PUT",
            contentType: 'application/json',
            dataType: "json",
            data: jsonUser,
            error: function (response){
                console.log("error")
                removeCover()
                response.responseJSON.errors.map(item => {
                    $(item.field).title = item.field
                    //$('#edit-user').append(errorSpan(item.defaultMessage, item.field))

                    $( errorSpan(item.defaultMessage, item.field) ).insertBefore(document.getElementById(item.field))
                    //$('#edit-user').append(errorSpan(item.defaultMessage, item.field))
                })
            }
        })
            .done((response) => {
                removeCover()
                alert(response.msg)
                removeUserData()
                getUserToEdit()
            })
    }
})

const removeUserData = () => {
    let inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.remove()
    })
}

let isEmpty = false

const isEmptyFunc = () => {
    fillUserSend()
    listOfEmpty = []
    for (let item in userSend) {
        if (userSend[item] === "") {
            listOfEmpty.push(item)
        }
    }
    if (listOfEmpty.length > 0) {
        isEmpty = true
    }
}

function setUser(data) {
    /*let clone = document.getElementsByTagName("template")[0]
        .content.cloneNode(true);

    $('#info-details').append(clone)*/
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
        .done(function (response){
            setUser(response)
            userGot = response
        })
}