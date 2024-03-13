const author = document.getElementsByTagName("select")[0]
const genre = document.getElementsByTagName("select")[1]
const publisher = document.getElementsByTagName("select")[2]


author.addEventListener('change', () => {
    let authorId = document.getElementsByTagName("select")[0].value
    if (authorId === '0') document.getElementById("authorId").value = '';
    else document.getElementById("authorId").value = authorId;
});
genre.addEventListener('change', () => {
    let genreId = document.getElementsByTagName("select")[1].value
    if (genreId === '0') document.getElementById("genreId").value = '';
    else document.getElementById("genreId").value = genreId;
});
publisher.addEventListener('change', () => {
    let publisherId = document.getElementsByTagName("select")[2].value
    if (publisherId === '0') document.getElementById("publisherId").value = '';
    else document.getElementById("publisherId").value = publisherId;
});