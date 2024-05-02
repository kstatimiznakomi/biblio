window.onload = () => {
    if (window.location.href.search("/admin/authors") > 0) getAuthorsList()
    if (window.location.href.search("/admin/books") > 0) getBooksList()
    if (window.location.href.search("/admin/publishers") > 0) getPublishersList()
    if (window.location.href.search("/admin/genres") > 0) getGenresList()
    if (window.location.href.search("/admin/users") > 0) getUsersList()
    setListeners()
}

function isEdit(point, objId) {
    console.log("/admin/" + point + "/" + objId + "/edit")
    let isConf = confirm("Удалить запись?");
    if (isConf) {
        document.location.replace("/admin/"+ object + "/" + objId + "/edit");
    }
}

function isDelete(point, objId) {
    console.log("/admin/"+ point + "/" + objId + "/delete")
    let isConf = confirm("Удалить запись?");
    if (isConf) {
        document.location.replace("/admin/"+ object + "/" + objId + "/delete");
    }
}

function setListeners() {
    
}
