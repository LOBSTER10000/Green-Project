


// 카테고리 버튼 이벤트
$(document).ready(function(){
    $(".btn_recycleCategory_1st").each(function(index) {
        $(this).attr('menu-index', index);
    }).click(function(){
        // 클릭된 <div>의 menu-index 값을 index 변수에 할당한다
        const index = $(this).attr('menu-index');
        // 클릭한 <div>에 clicked_menu 클래스 추가
        $('.btn_recycleCategory_1st[menu-index=' + index + ']').addClass('clicked_menu');
        // 그 외 <div>는 clicked_menu 클래스 삭제
        $('.btn_recycleCategory_1st[menu-index!=' + index + ']').removeClass('clicked_menu');
    });
});

$(document).ready(function(){
    $(".btn_recycleCategory_2st").each(function(index2) {
        $(this).attr('menu-index2', index2);
    }).click(function(){
        // 클릭된 <div>의 menu-index 값을 index 변수에 할당한다
        const index2 = $(this).attr('menu-index2');
        // 클릭한 <div>에 clicked_menu 클래스 추가
        $('.btn_recycleCategory_2st[menu-index2=' + index2 + ']').addClass('clicked_menu');
        // 그 외 <div>는 clicked_menu 클래스 삭제
        $('.btn_recycleCategory_2st[menu-index2!=' + index2 + ']').removeClass('clicked_menu');
    });
});


// pickup 두번째 페이지 입력정보 저장
function pickupSave2() {
    const category_no = 1;
    const pu_category_no = $(".count_category").val();

    console.log("카테고리 갯수>>>>>>>>>>>>>"+count_category);

    // 세션 스토리지에 임시 저장
    sessionStorage.setItem("category_no", category_no);
    sessionStorage.setItem("pu_category_no", pu_category_no);

    // saveVal = "저장";
    alert("임시 저장되었습니다")

    // $.ajax({
    //     url:"pickupSave.do",
    //     type:"post",
    //     data:{"user_id":user_id, "pu_name":pu_name, "pu_tel":pu_tel,
    //     "pu_zip":pu_zip,"pu_address1":pu_address1,"pu_address2":pu_address2,
    //     "pu_address3":pu_address3,"pu_address4":pu_address4,"house_no":house_no,"pu_elevator":pu_elevator,
    //     "pu_day":pu_day, "pu_img":pu_img, "text_memo":text_memo},
    //     success: "",
    //     error: function() {
    //         alert('error')
    //     }
    // })
}