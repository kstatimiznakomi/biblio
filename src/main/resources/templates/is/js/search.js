    var searchText = document.getElementById('search-button')
    searchText.addEventListener('click', () => {
        var string = document.getElementById("search-in").value;
        //if (string !== "") search(string);
        //else console.log("введите название книги");
    });

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

