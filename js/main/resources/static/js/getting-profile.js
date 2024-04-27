"use strict";
window.onload = function () {
    console.log("11111111111");
    getUserTs();
};
function setUserTs(data) {
    return "\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n                    <span class=\"user-dtls\"></span>\n                    <hr class=\"info-details-hr\">\n    ";
}
function getUserTs() {
    $.ajax("/profile/api", {
        cache: false,
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
    })
        .done(function (data) {
        console.log(data);
        setUserTs(data);
    });
}
