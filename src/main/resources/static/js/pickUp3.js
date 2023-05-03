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