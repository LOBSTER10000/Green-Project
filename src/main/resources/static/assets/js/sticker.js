
/* 헤더 스크롤 이동시 고정 및 색 변경 */
let header = document.querySelector('.top-header-area');

// 현재 페이지 URL에서 경로명만 추출
let pathname = window.location.pathname;

// 경로명이 '/' 또는 '/index'인 경우 스타일 적용
if (pathname === '/' || pathname === '/index') {
    window.addEventListener('scroll', () => {
        if (window.scrollY > 0) {
            header.style.backgroundColor = '#fff';
            header.querySelectorAll('a').forEach(a => {
                a.style.color = '#333';
            });
            header.style.opacity = '0.9';
        } else {
            header.style.backgroundColor = 'transparent';
            header.querySelectorAll('a').forEach(a => {
                a.style.color = '#fff';
            });
            header.style.opacity = '1';
        }
    });
} else { // 그 외의 경우 검정색 글씨와 투명도 1로 유지
    header.querySelectorAll('a').forEach(a => {
        a.style.color = '#000';
    });
    header.style.backgroundColor = '#fff';
    header.style.opacity = '0.9';
}