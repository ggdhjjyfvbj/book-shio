window.onscroll = function() {
    var header = document.getElementById("header2");
    if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        header.style.top = "0";
    } else {
        header.style.top = "-120px"
    }
}