let author = document.getElementsByTagName("select")[0]
let genre = document.getElementsByTagName("select")[1]
let publisher = document.getElementsByTagName("select")[2]
author.addEventListener('change', () => {
    preFilter()
});
genre.addEventListener('change', () => {
    preFilter()
});
publisher.addEventListener('change', () => {
    preFilter()
});

function preFilter(){
    let authorId = document.getElementsByTagName("select")[0].value
    let genreId = document.getElementsByTagName("select")[1].value
    let publisherId = document.getElementsByTagName("select")[2].value
    filter(authorId, genreId, publisherId)
    console.log("ID автора: " + authorId + " ID жанра: " + genreId + " ID издателя: " + publisherId);
}

function filter(authorId, genreId, publisherId){
    $.ajax({
        type: "GET",
        contentType: false,
        dataType: "json",
        url: '/filter/by-author-genre-publisher',
        data: {
            'author': authorId,
            'genre': genreId,
            'publisher': publisherId
        },
    });
}
