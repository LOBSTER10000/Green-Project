let content;


$(document).ready(function () {
    $.ajax({
        url: '/pagination', // 데이터를 가져올 URL_
        type: 'post',
        dataType: "json",
        success: function (data) { // 데이터를 가져온 후 실행할 콜백 함수
            console.log(data); // 데이터 확인용
            content = data;
            console.log(data);
            getData(1);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
});


// 페이징 처리
function pageLink(currentPage, totalPage, funName) {
    // pageLink 설정하기(?)
    let pageUrl = "";
    let pageLimit = 5;
    let startPage = parseInt((currentPage - 1) / pageLimit) * pageLimit + 1;
    let endPage = startPage - 1 + pageLimit;

    if (totalPage < endPage) {
        endPage = totalPage;
    }

    let nextPage = endPage + 1;

    //page test
    console.log(currentPage, "curPage,", startPage, "startPage,", endPage, "endPage,", nextPage, "nextPage");

    <!-- href="javascript:showNumber()" 링크 클릭시 함수실행을 시켜줌-->

    //맨 첫 페이지
    if (currentPage > 1 && pageLimit < currentPage) {
        pageUrl += "<a class='page first' href='javascript:" + funName + "(1);'><li className=\"page-item\"></a>";
    }
    //이전 페이지
    if (currentPage > pageLimit) {
        pageUrl += " <a class='page prev' href='javascript:" + funName + "(" + (startPage == 1 ? 1 : startPage - 1) + ");'><li className=\"page-item\"></a>";
    }
    //~pageLimit 맞게 페이지 수 보여줌
    for (var i = startPage; i <= endPage; i++) {
        //현재페이지면 진하게 표시
        if (i == currentPage) {
            pageUrl += " <a href='#'><strong>" + i + "</strong></a>"
        } else {
            pageUrl += " <a href='javascript:" + funName + "(" + i + ");'> " + i + " </a>";
        }
    }

    //다음 페이지
    if (nextPage <= totalPage) {
        pageUrl += "<a class='page next' href='javascript:" + funName + "(" + (nextPage < totalPage ? nextPage : totalPage) + ");'><li className=\"page-item\"></a>";
    }
    //맨 마지막 페이지
    if (currentPage < totalPage && nextPage < totalPage) {
        pageUrl += "<a class='page last' href='javascript:" + funName + "(" + totalPage + ");'><li className=\"page-item\"></a>";
    }
    //pageUrl_ test
    console.log(pageUrl);
    // 현재 페이지 번호를 업데이트합니다.

    $('#pagingul').html(pageUrl);
    // cur를 붙여야하나 아니면 pageUrl 을 붙여야하나 해봐야함
    return pageUrl;


}

function getData(num) {
    // 한페이지에 10개를 보여주기 위한 함수 설정
    let itemsPerPage = 10;  // 한 페이지에 보여줄 데이터의 수
    let currentPage = num;    // 현재 페이지 번호
    let startIndex = (currentPage - 1) * itemsPerPage;  // 시작 데이터 인덱스
    let endIndex = startIndex + itemsPerPage;          // 끝 데이터 인덱스
    // page =1 이면  0~9 >> 10~19 >> ...

    let userTable = $("#t_body"); //t_body 변수 지정
    // 이전 데이터를 모두 지우고

    //페이징처리 ----
    let totalPage = Math.ceil(content.length / itemsPerPage);  // 총 페이지 수
    // pageLink(현재페이지, 전체페이지, 호출할 함수이름)  // --------- 해결해야함 !!
    let htmlStr = pageLink(currentPage, totalPage, "getData");
    // common.js - pageLink
    $("#div_paginate").html(htmlStr);  // --------- 해결해야함 !!

    $('#t_body').empty();
    // 데이터를 테이블에 추가
    $.each(content, function (i, item) {
        if (i >= startIndex && i < endIndex) {
            userTable.append(
                '<tr>' +
                '<td><a href="/admin/modify?user_id=' + item.user_id + '">' + item.user_id + '</a></td>' +
                '<td>' + item.user_pass + '</td>' +
                '<td>' + item.user_name + '</td>' +
                '<td>' + item.user_email + '</td>' +
                '<td>' + item.user_tel + '</td>' +
                '<td>' + item.zip_code + '</td>' +
                '<td>' + item.address1 + '</td>' +
                '<td>' + item.address2 + '</td>' +
                '<td>' + item.address3 + '</td>' +
                '<td>' + item.address4 + '</td>' +
                '<td>' + item.user_point + '</td>' +
                '</tr>'
            );
        }
    });

} // data 10개씩 끊어오는 함수

