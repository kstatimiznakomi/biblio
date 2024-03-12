
const regBtn = document.getElementById('reg-btn')
const form = document.getElementById('form').querySelectorAll("input")

regBtn.addEventListener('click', () => {
    var userObj = {};
    form.forEach(item => userObj[item.id] = item.value);
    if (userObj["seq_password"] === userObj["password"]){
        delete userObj["seq_password"]
        const user = JSON.stringify(userObj)
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            dataType: "json",
            url: '/register/new',
            data: user,
        });
    }
    else alert("пароли не совпадают")
});

