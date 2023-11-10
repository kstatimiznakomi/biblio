const author = document.getElementsByTagName("select")[0]
const genre = document.getElementsByTagName("select")[1]
const publisher = document.getElementsByTagName("select")[2]
const dateButton = document.getElementById("date")
author.addEventListener('change', () => {
    let authorId = document.getElementsByTagName("select")[0].value
    document.location.replace("/search/author/" + authorId + "/1");
});
genre.addEventListener('change', () => {
    let genreId = document.getElementsByTagName("select")[1].value

});
publisher.addEventListener('change', () => {
    let publisherId = document.getElementsByTagName("select")[2].value
    document.location.replace("/search/publisher/" + publisherId + "/1");
});

dateButton.addEventListener('change', () => {
    const date = document.getElementById("date").value
    const auth = document.getElementsByTagName("select")[0].value
    document.location.replace("/search/date/" + date + "/1");
})