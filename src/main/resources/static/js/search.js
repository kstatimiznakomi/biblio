    var searchText = document.getElementById('search-button')
    searchText.addEventListener('click', () => {
        var string = document.getElementsByTagName("input")[0].value;
        if (string !== "") search(string);
        else console.log("введите название книги");
    });

function search(name){

}

