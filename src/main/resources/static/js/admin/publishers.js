function setPublishers(publisher){
    $('#publishers').append(`
                <tr >
                    <td>
                        <a class="btn btn-warning" data-bs-toggle="modal"
                           data-bs-target=$('#editModal' + ${publisher.id}") data-row="${publisher}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a class="btn btn-danger"
                           onclick="isDelete(${publisher.id})"><i class="bi bi-trash"></i></a>
                    </td>
                    <td>${publisher.id}</td>
                    <td>${publisher.publisherName}</td>
                </tr>
`)
}

function getPublishersList() {
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/publishers/api',
        data: false,
        failure: (error) => {
            console.log(error)
        }
    })
        .done((response) => {
            response.map((publisher) => {
                    setPublishers(publisher)
                }
            )
        })
}