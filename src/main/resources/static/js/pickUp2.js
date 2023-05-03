// jQuery 불러오기
$(function loadJQuery() {
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.charset = "utf-8";
    oScript.src = "http://code.jquery.com/jquery-1.6.2.min.js";
    document.getElementsByTagName("head")[0].appendChild(oScript);
})


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

// 1차 카테고리 버튼
const firstCategoryButtons = document.querySelectorAll(".btn_recycleCategory_1st");

// 1차 카테고리 버튼 이벤트
firstCategoryButtons.forEach(button => {
    button.addEventListener('click', () => {
        const id = button.id;   // 클릭된 버튼의 id 값을 가져옴
        let options;    // options를 통해 선택된 1차 카테고리에 상응하는 2차 카테고리를 붙임

        if(id ==='img_washing') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="일반세탁기">
                <input type="button" class="btn_recycleCategory_2st" value="드럼세탁기">
                <input type="button" class="btn_recycleCategory_2st" value="탈수기">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/washing-machine.png');

        } else if (id === 'img_refrigerator') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="가정용냉장고">
                <input type="button" class="btn_recycleCategory_2st" value="김치냉장고">
                <input type="button" class="btn_recycleCategory_2st" value="와인냉장고">
                <input type="button" class="btn_recycleCategory_2st" value="업소용냉장고">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/refrigerator.png');

        } else if (id === 'img_television') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="텔레비전(CRT)">
                <input type="button" class="btn_recycleCategory_2st" value="텔레비전(LCD,PDP)">
                <input type="button" class="btn_recycleCategory_2st" value="프로젝션 TV">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/television.png');

        } else if (id === 'img_airConditioner') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="에어컨실내기">
                <input type="button" class="btn_recycleCategory_2st" value="에어컨실외기">
                <input type="button" class="btn_recycleCategory_2st" value="일체형에어컨">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/air-conditioner.png');

        } else if (id === 'img_solar') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="태양광패널">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/solar-cell.png');

        } else if (id === 'img_etc') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="전자레인지">
                <input type="button" class="btn_recycleCategory_2st" value="오븐">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/oven.png');

        } else if (id === 'img_computer') {
            options = `
                <input type="button" class="btn_recycleCategory_2st" value="컴퓨터본체">
            `;
            $("#btn_recycleCategory").html(options);

            // 이미지 태그의 src 값을 변경한다
            $('#img_recycle_preview').attr('src', '/img/pickup/computer.png');

        }
        // 2차 카테고리 버튼
        const secondCategoryButtons = document.querySelectorAll(".btn_recycleCategory_2st");

        // 2차 카테고리 버튼 이벤트
        secondCategoryButtons.forEach((button, index2) => {
            button.setAttribute('menu-index2', index2);
            button.addEventListener('click', () => {
                const index2 = button.getAttribute('menu-index2');
                $('.btn_recycleCategory_2st[menu-index2=' + index2 + ']').addClass('clicked_menu');
                $('.btn_recycleCategory_2st[menu-index2!=' + index2 + ']').removeClass('clicked_menu');
            });
        });

        secondCategoryButtons.forEach(button => button.addEventListener('click', addItem));

        // 추가 버튼 클릭 시 실행될 함수
        function addItem() {
            const categoryVal = event.target.value;
            const previewCategories = document.querySelectorAll('.text_detailCategory_preview');
            let categoryExists = false;

            // 이미 존재하는 값을 확인
            previewCategories.forEach(function(previewCategory) {
                if (previewCategory.innerText === categoryVal) {
                    categoryExists = true;
                }
            });

            // 존재하지 않는 경우에만 추가
            if (!categoryExists) {
                const newButtonHtml = `
                  <tr style="height: 65px; border-bottom: 2px solid #d5d2d2;">
                    <td class="col-4" style="border-right: 2px solid #d5d2d2; font-family: NanumBarunGothic, serif;">
                      <div class="text_detailCategory_preview">${categoryVal}</div>
                    </td>
                    <td class="col-4" style="border-right: 2px solid #d5d2d2; text-align: center; font-family: NanumBarunGothic, serif;">
                      <button type="button" class="minus" onclick="fnCalcCnt('minus');">
                        <div style="width: 100%; height: 100%; position: absolute; top: -20px;">-</div>
                      </button>
                      <input type="text" class="count_category" name="item_cnt" title="수량 입력" value="1" maxlength="1">
                      <button type="button" class="plus" onclick="fnCalcCnt('plus');">
                        <div style="width: 100%; height: 100%; position: absolute; top: -20px;">+</div>
                      </button>
                    </td>
                    <td class="col-4">
                      <div class="button_inputCategory_delete">
                        <input type="button" onclick="categoryDel()" value="X" style="color: red; border: none; font-size: 28px;">
                      </div>
                    </td>
                  </tr>
                `;
                $("#preview_row").append(newButtonHtml);
            }
        }

    });
});


// 카테고리 수량 조절 이벤트
function fnCalcCnt(action) {
    const row = event.target.closest('tr'); // 클릭된 버튼이 속한 행 가져오기
    const countInput = row.querySelector('.count_category'); // 행에서 count_category input 찾기
    let count = parseInt(countInput.value); // 현재 count 값 가져오기

    if (action === 'plus') {
        count++;
    } else if (action === 'minus') {
        if (count > 1) { // count 값이 1 이상일 때만 감소
            count--;
        }
    }

    countInput.value = count; // 변경된 count 값을 count_category input에 적용
}

// 카테고리 삭제 이벤트
function categoryDel() {
    $(event.target).closest('tr').remove();  // 현재 클릭된 삭제 버튼이 속한 행을 제거
}

// 다음 페이지 이동시, 수량 입력여부 확인
function chBox() {
    var previewRow = document.getElementById("preview_row");
    var closestRowWithChildNodes = previewRow.closest('tbody:has(>*)');
    if (closestRowWithChildNodes != null) {
        location.href = "/pickup3";
    } else {
        alert("카테고리를 선택해주세요.");
        return false;
    }
}

