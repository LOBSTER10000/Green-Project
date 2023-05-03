// jQuery 불러오기
$(function loadJQuery() {
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.charset = "utf-8";
    oScript.src = "http://code.jquery.com/jquery-1.6.2.min.js";
    document.getElementsByTagName("head")[0].appendChild(oScript);
})

function pickupReservation() {
    $.ajax({
        url:"pickupRealSave.do",
        type:"get",
        success: function() {
            alert("수거 예약되었습니다.")
            location="/index"
        },
        error: function() {
            alert('error')
        }
    })
}