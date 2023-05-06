document.addEventListener('DOMContentLoaded', function() {
    const box = document.querySelector('#delete');

    box.addEventListener("click", function(e){
        if(!confirm('정말로 아이디를 삭제하시겠습니까?(삭제된 아이디는 복구하기 힘듭니다)')){
            e.preventDefault();
            alert("취소 되었습니다");
        }
        else{
            alert("삭제 되었습니다.");
        }

    });
});



document.addEventListener('DOMContentLoaded', function(){
    // 이미지 미리보기를 위한 이벤트 등록
    document.getElementById('profile_upload').addEventListener('change', function() {
        // 파일 선택 창에서 선택한 파일 객체 가져오기
        const file = this.files[0];

        // FileReader 객체 생성
        const reader = new FileReader();

        // 파일 읽기 완료 후 실행되는 콜백 함수 등록
        reader.onload = function() {
            // 이미지 미리보기를 위한 img 태그 가져오기
            const img = document.getElementById('img');
            const img2 = document.getElementById('pro_img');
            // img 태그의 src 속성 설정하여 이미지 미리보기
            img.src = reader.result;
            img2.src = reader.result;
        }

        // 파일 읽기 시작
        reader.readAsDataURL(file);
    });
});








