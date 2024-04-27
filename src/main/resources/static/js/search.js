




function search(name){
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: '/search/' + name + '/1',
        data: {
            'bookName': name
        },
    });
}


