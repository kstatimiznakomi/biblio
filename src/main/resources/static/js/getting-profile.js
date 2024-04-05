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
            console.log(data)
        })
}

function setUser(data){
    $('#info-details').append(
        `
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.lastname}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.name}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.surname}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.username}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.email}</span>
                    <hr class="info-details-hr">
                    <span class="user-dtls">${data.phone}</span>
                    <hr class="info-details-hr">
    `
    )
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