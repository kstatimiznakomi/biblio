function cover(){
    $('body,html').css('overflow', 'hidden')
    $('#cover').append(`
        <div class="cover transition-all"></div>
        <div class="cover-content transition-all justify-content-center">
            <div class="cover-content-fill transition-all"></div>
        </div>
    `)
}

function removeCover(){
    $('.cover').remove()
    $('.cover-content').remove()
    $('.cover-content-fill').remove()
    $('body,html').css('overflow', '')
}