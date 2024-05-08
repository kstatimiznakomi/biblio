function cover(){
    $('body,html').css('overflow', 'hidden')
    $('#head').removeClass("sticky-top")
    $('#cover').append(`
        <div class="cover transition-all"></div>
        <div class="cover-content transition-all justify-content-center ">
            <div class="cover-content-fill transition-all"></div>
        </div>
    `)
}

function modalBookInfo(bookId){
    cover()
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: '/catalog/book/' + bookId,
        data: false,
        success: (response) => {
            removeCover()
                $('body,html').css('overflow', 'hidden')
            $('#head').removeClass("sticky-top")
                $('#cover').append(`
                <div class="cover transition-all"></div>
                    <div onclick="removeCover()" class="cover-content transition-all align-items-center justify-content-center">
                        <div class="book-info d-flex transition-all">
                            <div>
                                <img src="${response.img}"/>
                            </div>
                            <div>
                                <h3>${response.bookName}</h3>
                                <h3>Описание: ${response.description}</h3>
                                <h3>Количество: ${response.count}</h3>
                            </div>
                        </div>
                    </div>
                `)
        },
        failure: (response) => {
            alert(response)
        }
    });

}

function removeCover(){
    $('.cover').remove()
    $('.cover-content').remove()
    $('.cover-content-fill').remove()
    $('body,html').css('overflow', '')
    $('#head').addClass("sticky-top")
}