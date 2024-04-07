var pages = []
let pagesL = ""

function getPagesCatalog(crntPg, ttlPgs){
    const offset = 5
    let start = 1
    let end = ttlPgs
    if(crntPg - offset > 1) start = crntPg - offset
    if (crntPg + offset <= ttlPgs) end = crntPg + offset

    /*for (let i = start; i < end; i++){
        i === crntPg ? pagesL + "<li><span class=" + "page-digits-span" + ">" + i + "</span><li>"
            : pagesL += "<li><a class=" + '"' + "page-digits-bg-link" + '"' + " " + "href=" + '#' + ">" + i + "</a><li>"
    }*/

    for (let i = start; i < end; i++){
        i === crntPg ? pages.push(`<li><span class="page-digits-span">${i}</span><li>`)
            : pages.push(`<li><a class="page-digits-bg-link" href="#">${i}</a><li>`)
    }
    console.log(pages.join(","))
    return pages.toString().replace(",", "")
}