    $('#search-button')[0].addEventListener('click', (e) => {
        clearSearchObj()
        e.preventDefault()
        $('#search-div')[0].querySelectorAll("input").forEach(item => item.value !== "" ? searchObj[item.id] = item.value : '')
        search()
    })
    searchObj = {}

    function clearSearchObj(){
        for(let item in searchObj){
            delete searchObj[item];
        }
    }

function search(){
    let paramsDTO = JSON.stringify(searchObj)
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: '/search/api',
        data: paramsDTO,
    });
}

