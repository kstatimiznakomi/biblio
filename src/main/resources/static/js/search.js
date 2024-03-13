    $('#search-button')[0].addEventListener('click', (e) => {
        clearSearchObj()
        e.preventDefault()
        $('#search-div')[0].querySelectorAll("input").forEach(item => item.value !== "" ? searchObj[item.id] = item.value : '')
        console.log(searchObj)
        urlCreate()
        search()
    })
    searchObj = {}
    let url = "http://localhost:8080/search?"

    function clearSearchObj(){
        for(let item in searchObj){
            delete searchObj[item];
        }
    }

    function urlCreate(){
        url = "http://localhost:8080/search?"
        for (let item in searchObj){
            url += item + '=' + searchObj[item] + '&'
        }
        console.log(url)
    }

function search(){
    let searchJson = JSON.stringify(searchObj)
    console.log(searchJson)
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: '/search/' + name + '/1',
        data: {
            searchJson
        },
    });
}

