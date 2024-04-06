function cover(){
    $('body,html').css('overflow', 'hidden')
    $('#cover').append(`
        <div class="cover"></div>
        <div class="cover-content justify-content-center">
            <div class="cover-content-fill"></div>
        </div>
    `)
}

function removeCover(){
    $('.cover').remove()
    $('.cover-content').remove()
    $('.cover-content-fill').remove()
    $('body,html').css('overflow', '')
}