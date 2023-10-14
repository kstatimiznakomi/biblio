let author = document.getElementsByTagName("select")[0]
let genre = document.getElementsByTagName("select")[1]
author.addEventListener('change', () => {
    let authorId = document.getElementsByTagName("select")[0].value
    let genreId = document.getElementsByTagName("select")[1].value
    filter(authorId, genreId)
    console.log("ID автора: " + authorId + " ID жанра: " + genreId);
});

function filter(authorId, genreId){
    $.ajax({
        type: "GET",
        contentType: false,
        dataType: "json",
        url: '/filter/by-author-genre'
    })
}
