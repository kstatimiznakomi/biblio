function setUsers(user) {
    $('#users').append(`
                <tr>
                        <td>
                            <a class="btn btn-warning" data-bs-toggle="modal"
                            data-bs-target=$('#editModal' + ${user.id}") data-row="${user}">
                            <i class="bi bi-pencil"></i>
                            </a>
                            <a class="btn btn-danger" disabled="disabled"
                            onclick="isDelete(${user.id})"><i class="bi bi-trash"></i></a>
                        </td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.status}</td>
                        <td>${user.role}</td>
                        <td>${user.lastname}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.address}</td>
                    </tr>
`)
}

function getUsersList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/admin/users/api',
        data: false,
        failure: (error) => {
            console.log(error)
        }
    })
        .done((response) => {
            console.log(response)
            response.map((user) => {
                    setUsers(user)
                }
            )
        })
}