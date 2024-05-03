window.onload = function () {
    if (window.location.href.includes("edit")) {
        getUserToEdit()
    }
    else{
        getUser()
        getNotes()
    }
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
            console.log(data)
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

function sendUser(){

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
let userToCheck = {}

function setUserEdit(data){
    $('#edit-user').prepend(`
                        <input type="text" value="${data.lastname}" class="user" placeholder="Фамилия" id="lastname" required>
                        <input type="text" value="${data.name}" class="user" placeholder="Имя" id="name" required>
                        <input type="text" value="${data.surname}" class="user" placeholder="Отчество" id="surname" required>
                        <input type="text" value="${data.username}" class="user" placeholder="Имя пользователя" id="username" required>
                        <hr class="separator">
                        <input type="password" class="user" placeholder="Пароль" id="password" required>
                        <input type="password" class="user" id="seq_password" placeholder="Подтверждение пароля" required>
                        <input type="email" value="${data.email}" class="user" placeholder="Почта" id="email" required>
                        <input type="number" value="${data.phone}" class="user" placeholder="Номер телефона" id="phone" required>
                        
    `)
}

$('#user-edit-btn')[0].addEventListener('click', (e) => {
    userSend = {}
    e.preventDefault()

    $('#edit-user')[0]
        .querySelectorAll("input")
        .forEach(item => userSend[item.id] = item.value)
    userToCheck = userSend
    console.log("userCheck: " + userToCheck)

    const jsonUser = JSON.stringify(userSend)
    isEmptyFunc()
    if (!isEmpty) {
        if (userSend["password"] !== userSend["seq_password"]) alert("Пароли не совпадают!")
        else{
            delete userSend["seq_password"]
            if (userToCheck !== userGot){
                $.ajax({
                    cache: false,
                    url: '/register/put',
                    type: "PUT",
                    contentType: 'application/json',
                    dataType: "json",
                    data: jsonUser,
                    success: alert("Данные успешно изменены!")
                })
            } else alert("Измените данные, чтобы сохранить")
        }
    } else alert("Данные не должы быть пусты!")
})

let isEmpty = false
isEmptyFunc = () => {
    for (let item in userSend) {
        if (userSend[item] === "") {
            alert("Пароль или данные пользователя не могут быть пустыми!")
            isEmpty = true
            return
        }
    }
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
        .done(function (response){
            setUser(response)
            userGot = response
        })
}