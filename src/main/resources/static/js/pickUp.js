// jQuery 불러오기
$(function loadJQuery() {
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.charset = "utf-8";
    oScript.src = "http://code.jquery.com/jquery-1.6.2.min.js";
    document.getElementsByTagName("head")[0].appendChild(oScript);
})


// 주소 검색기능
jQuery.noConflict(
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
)


// flatpicker 요일 지정하기
flatpickr("#myDateInput", {
    disable: [
        // 주말 제외 (토, 일)
        function(date) {
            // return true to disable
            return (date.getDay() === 0 || date.getDay() === 6);
        }
    ],
    dateFormat: "Y-m-d" // 날짜 형식 설정
});

// myDateInput의 값이 변경되었을 때 처리할 함수
document.getElementById("myDateInput").addEventListener("change", function() {
    var dateInput = document.getElementById("myDateInput").value; // myDateInput의 값 가져오기
    var date = new Date(dateInput); // 입력된 날짜를 JavaScript의 Date 객체로 변환
    var daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"]; // 요일 배열
    var dayOfWeek = daysOfWeek[date.getDay()]; // Date 객체에서 요일 값을 가져와서 배열에서 해당 요일을 찾음
    document.getElementById("text_insertDayOfWeek").textContent = "(" + dayOfWeek + ")"; // 요일 값을 text_insertDayOfWeek에 출력
});


// 이미지 첨부
const upload = document.querySelector('.img_pickup_upload');
upload.addEventListener('change', getImageFiles);

var inputFileList = new Array();     // 이미지 파일을 담아놓을 배열 (업로드 버튼 누를 때 서버에 전송할 데이터)

function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    var filesArr = Array.prototype.slice.call(files);
    const imagePreview = document.querySelector('#img_pickup_preview');
    const docFrag = new DocumentFragment();

    // 업로드 된 파일 유효성 체크
    if (filesArr.length > 6) {
        alert("이미지는 최대 6개까지 업로드 가능합니다.");
        $('input[name=images]').val();
        return;
    }


    // 파일 타입 검사
    [...files].forEach(file => {
        if (!file.type.match("image/.*")) {
            alert('이미지 파일만 업로드가 가능합니다.');
            return
        }

        // 파일 갯수 검사
        if ([...files].length < 7) {
            uploadFiles.push(file);
            const reader = new FileReader();
            reader.onload = (e) => {
                const preview = createElement(e, file);
                imagePreview.appendChild(preview);
            };
            reader.readAsDataURL(file);
        }
    });

    filesArr.forEach(function(f) {
        inputFileList.push(f);    // 이미지 파일을 배열에 담는다.
    });

}

// 첨부 이미지 preview
function createElement(e, file) {
    const delBtn = document.createElement("input");
    const li = document.createElement('li');
    const img = document.createElement('img');

    img.setAttribute('src', e.target.result);
    img.setAttribute('data-file', file.name);
    li.appendChild(img);
    delBtn.className = 'delBtn';
    delBtn.type = "button";
    delBtn.value = "X";
    li.id = "img_list";
    li.appendChild(delBtn);

    $(document).ready(function() {
        $(delBtn).click(function () {
            $(li).remove();
        })
    });
    return li;
}


// 다음페이지 이동시, 입력값 빈칸 확인
function chBox() {
    // 예약정보 값 불러오기
    const pu_name = $("#pickup_name").val();
    const pu_tel = $("#pickup_tel").val();
    const pu_zip = $("#pickup_zip_code").val();
    const pu_address1 = $("#pickup_address1").val();
    const pu_address2 = $("#pickup_address2").val();
    const pu_address3 = $("#pickup_address3").val();
    const pu_address4 = $("#pickup_address4").val();
    const pu_day = $("#myDateInput").val();
    let pu_img;
    // 이미지 유무 확인 후 Y,N 입력
    if ($(".img_pickup_upload")[0].files.length > 0) {
        pu_img = "Y";
    } else {
        pu_img = "N";
    }
    const user_id = $("#pickup_userID").val();
    const house_no = $("#pickup_house").val();
    const pu_elevator = $("#pickup_elevator").val();
    const text_memo = $(".text_memo").val();

    let formData = new FormData();  // 폼 객체

    for (let i = 0; i < inputFileList.length; i++) {
        formData.append("images", inputFileList[i]);  // 배열에서 이미지들을 꺼내 폼 객체에 담는다.
    }

    const address = {
        "user_id": user_id,
        "pu_address_name": pu_name,
        "pu_address_tel": pu_tel,
        "pu_address_zip": pu_zip,
        "pu_address1": pu_address1,
        "pu_address2": pu_address2,
        "pu_address3": pu_address3,
        "pu_address4": pu_address4
    };

   const info = {
        "user_id": user_id,
        "house_no": house_no,
        "pu_elevator": pu_elevator,
        "pu_day": pu_day,
        "pu_img": pu_img,
        "text_memo": text_memo
   };


    // 필수정보 입력 확인
    if (pu_name === "" || pu_tel === "" || pu_zip === "" || pu_address1 === "" || pu_address2 === "" ||
        pu_address3 === "" || pu_address4 === "" || pu_day === "") {
        alert("필수 정보를 입력해주세요")
        return false;
    } else if ($("#checkBox_colPersonInfo").is(":checked") === false) {
        $("#checkBox_colPersonInfo").focus();
        alert("약관에 동의해주세요");
        return false;
    } else {
        location.href = "/pickup2"
        $.ajax({
            url:"pickupSave.do",
            type:"post",
            data: address,
            success: function() {
                $.ajax({
                    url: "pickupSave2.do",
                    type: "post",
                    data: info,
                    success: function() {
                        $.ajax({
                            type: 'post',
                            url: 'pickupImg.do',
                            data: formData,
                            contentType: false,
                            processData: false,
                            enctype: 'multipart/form-data',
                            cache: false,
                            success: location.href = "/pickup2",
                            error: function (xhr, status, error) {
                                alert("오류가 발생했습니다.\n" + error);
                            }
                        })
                    },
                    error: function (xhr, status, error) {
                        alert("오류가 발생했습니다.\n" + error);
                    }
                })
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다.\n" + error);
            }
        })
    }

}





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


// pickup 세번째 페이지 입력정보 저장
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



