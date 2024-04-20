var pages = []

const offset = 5
let start = 1
let end = 0

var toFirst = [`<li><span class="page-digits-span">1</span><li>`]
var btween = [`<li><span class="page-digits-span">...</span><li>`]

function toLast(ttlPgs){
    return `<li><span class="page-digits-span">${ttlPgs}</span><li>`
}

function getMaxMin(crntPg, ttlPgs){
    end = ttlPgs
    if(crntPg - offset > 1){
        start = crntPg - offset
    }
    if (crntPg + offset <= ttlPgs){
        end = crntPg + offset
    }
}

function startEnd(ttlPgs){
    pages.unshift(toFirst)
    pages.push(toLast(ttlPgs))
}

function getPagesCatalog(crntPg, ttlPgs){
    pages.length = 0
    getMaxMin(crntPg, ttlPgs)
    for (let i = start; i <= end; i++) {
        i === crntPg ? pages.push(`<li><span class="page-digits-span">${i}</span><li>`)
            : pages.push(`<li><a class="page-digits-bg-link" href="${i}">${i}</a><li>`)
    }
    if (pages.length >= 5) startEnd()
    return pages.toString().replaceAll(",", "")
}

let urlSearch = 'http://localhost:8080/catalog/search?'

function getSearchPagerr(crntPg, ttlPgs){
    pages.length = 0
    let urlPager = new URLSearchParams('?' +
        new URLSearchParams(window.location.href)
            .toString()
            .split('%3F')[1]
    )
    getMaxMin(crntPg, ttlPgs)
    for (let i = start; i <= ttlPgs; i++) {
        urlPager.set("page", i)
        i === crntPg ? pages.push(`<li><span class="page-digits-span">${crntPg}</span><li>`)
            : pages.push(`<li><a class="page-digits-bg-link" href="${urlSearch + urlPager}">${i}</a><li>`)
    }
    if (pages.length >= 5) startEnd(ttlPgs)
    return pages.toString().replaceAll(",", "")
}