## SpringBoot-Project-GREENTOPIA 그린토피아
> 스프링부트 + Thymeleaf를 이용한 무상 수거 예약 사이트


[![Contributors][contributors-shield]][contributors-url]
<img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/4b115380-3fd5-4dc7-b39c-3a0fa5c505cb">


>  일상생활 속에서 "누구나" "어디서든" 환경보존에 동참하기 위해 보다 편리하게 접근 장벽을 낮추는 효율적인 무상수거 시스템입니다

## 💻 프로젝트 설명

 * 주로 어떤 일들을 제공하는가? 
 
   (1) 환경보존에 동참하기 위해 효율적인 무상수거 예약 기능
   
   (2) 발급받은 그린포인트를 이용한 포인트 교환 기능
   
   (3) 고객들과의 대화를 위해 1:1 대화를 비롯한 카카오톡 채널 오픈

 * 왜 프로젝트를 만들게 되었는가? 
 
   (1) 국내 전자 쓰레기 배출량은 해마다 5.5%씩 증가인데 폐가전 재활용률은 EU의 총 43%에 불과함
   
   (2) 사람들에게 인지도를 높여, 무상수거에 대한 진입장벽을 낮추기 위해서 제작
 
 * 어떠한 효과를 기대할 수 있는가? 
  
    (1) 환경보호 -> 대기, 수질, 토양 등 환경 문제 유발 방지
  
    (2) 에너지 절약 -> 자원회수를 통해 재활용 함으로써 에너지 절약 실현
  
    (3) 경제적 이득 -> 연계된 기업들의 수익 및 일자리 창출
  
    (4) 자원 재활용 촉진 -> 자원의 효율적 회수 및 재활용
  
    (5) 대중인식 개선 -> 인식개선과 지속 가능한 생활 습관을 장려 가능
   
## ⏲개발기간
 * 2023년 3월 24일 ~ 2023년 5월 9일
 
## 👨‍👩‍👧‍👦멤버구성
* 팀장 이동규(LOBSTER10000) : 회원가입&로그인, 포인트교환, 마이페이지&어드민페이지, 포인트지급, 웹배포 등 
* 팀원 김준호(thxb) : 수거예약, ERD 제작
* 팀원 이경선(SEONCANDO) : 채팅, 검색, 메인페이지
* 팀원 차민지(dlqhdddd) : 메인페이지, 페이징, 검색
* 팀원 이보영(gpdms) : 회원관리, 메인페이지(푸터)
* 팀원 전예원(Yewonj927) : 게시판&댓글(CRUD)

## ⚙개발환경
 * Java : <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/> Java 11
 * IDE : <img src="https://img.shields.io/badge/intellij idea-000000?style=flat-square&logo=intellij idea&logoColor=white"/> Intellij 2023-01-01
 * Framework : <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> Spring boot (2.7.6)
 * Database : <img src="https://img.shields.io/badge/mysql-4479A1?style=flat-square&logo=mysql&logoColor=white"/> Mysql (workbench 8.0)
 * ORM : <img src="https://img.shields.io/badge/Mybatis-F5A805?style=flat-square&logo=Mybatis&logoColor=white"/> Mybatis 3.0.0
 * Server : <img src="https://img.shields.io/badge/AWS Lightsail-F58105?style=flat-square&logo=AWS Lightsail&logoColor=white"/> AWS Lightsail

## 📺 화면 구성
 <div>
   <table>
    <tbody>
     <tr>
     <td align="center">
       메인페이지
      </td>
      <td align="center">
       로그인&회원가입
      </td>
     </tr>
     <tr>
      <td>
       <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/4b115380-3fd5-4dc7-b39c-3a0fa5c505cb">
     </td>
      <td>
       <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/56905a2a-00f9-45f6-aa81-5d7b8b215cad">
      </td>
     </tr>
     <tr>
      <td align="center">수거 예약</td>
      <td align="center">포인트 교환</td>
     </tr>
     <tr>
      <td>
       <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/d66f572f-b6e4-4176-aa3d-fdaa8b9928de">
      </td>
       <td>
         <img width="100%" height="30%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/e4bec78c-4e90-481e-af54-2f98654aa996">
      </td>
     </tr>
      <tr>
      <td align="center">마이페이지</td>
      <td align="center">관리자페이지</td>
     </tr>
     <tr>
      <td>
       <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/b10f113f-eed2-4301-b2e9-445c57fc7d4a">
      </td>
       <td>
        <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/e5de00e0-9e82-4a8f-bb2c-d5170ec2be9b">
      </td>
     </tr>
     <tr>
      <td align="center">게시판</td>
      <td align="center">포인트 지급</td>
     </tr>
     <tr>
       <td>
        <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/f601db8a-9a3e-4217-a333-bc54d72df8b9">
      </td>
       <td>
      <img width="100%" src="https://github.com/LOBSTER10000/Green-Project/assets/111476138/2b80d5b2-ce25-46fe-829c-bea1c5de6d9d">
      </td>
     </tr>
    </tbody>
 </table>
</div>
 
## 📌주요기능
  🟩 <strong>무상수거 예약</strong>
  
    * 수거 날짜 선택, 주소 입력 가능
    * 정확한 물품 확인을 위한 사진 업로드 기능
  
  🟩 <strong>그린포인트 교환</strong>
  
    * 수거로 지급받은 그린포인트를 활용한 교환시스템
    * 교환 내역을 통한 메일 발송
  
  🟩 <strong>채팅 기능</strong>
  
    * 카카오 채널을 비롯한 1:1 채팅 기능 2가지 구현
  
  🟩 <strong>공지사항&QNA</strong>
  
    * Admin 계정으로만 CRUD가 가능한 공지사항
    * User 계정으로 CRUD가 가능한 QNA
    
 🟩 <strong>관리자페이지</strong>
 
    * Ajax를 이용한 데이터 페이징, 검색 기능
    * 각 데이터들 수정,삭제 기능
    
 🟩 <strong>예약 관리</strong>
    
    * 포인트 지급
    * 예약 삭제 및 관리 기능 추가

## 📃 참고
   * 폐가전무상수거 : https://15990903.or.kr/portal/main/main.do
   * 그린포인트(종료) : https://greenpoint.knps.or.kr/main.do
  
<!-- ## 설치 방법

OS X & 리눅스:

```sh
npm install my-crazy-module --save
```

윈도우:

```sh
edit autoexec.bat
```

## 사용 예제

스크린 샷과 코드 예제를 통해 사용 방법을 자세히 설명합니다.

_더 많은 예제와 사용법은 [Wiki][wiki]를 참고하세요._

## 개발 환경 설정

모든 개발 의존성 설치 방법과 자동 테스트 슈트 실행 방법을 운영체제 별로 작성합니다.

```sh
make install
npm test
```

## 업데이트 내역

* 0.2.1
    * 수정: 문서 업데이트 (모듈 코드 동일)
* 0.2.0
    * 수정: `setDefaultXYZ()` 메서드 제거
    * 추가: `init()` 메서드 추가
* 0.1.1
    * 버그 수정: `baz()` 메서드 호출 시 부팅되지 않는 현상 (@컨트리뷰터 감사합니다!)
* 0.1.0
    * 첫 출시
    * 수정: `foo()` 메서드 네이밍을 `bar()`로 수정
* 0.0.1
    * 작업 진행 중

## 정보

이름 – [@트위터 주소](https://twitter.com/dbader_org) – 이메일주소@example.com

XYZ 라이센스를 준수하며 ``LICENSE``에서 자세한 정보를 확인할 수 있습니다.

[https://github.com/yourname/github-link](https://github.com/dbader/)

## 기여 방법

1. (<https://github.com/yourname/yourproject/fork>)을 포크합니다.
2. (`git checkout -b feature/fooBar`) 명령어로 새 브랜치를 만드세요.
3. (`git commit -am 'Add some fooBar'`) 명령어로 커밋하세요.
4. (`git push origin feature/fooBar`) 명령어로 브랜치에 푸시하세요. 
5. 풀리퀘스트를 보내주세요.

<!-- Markdown link & img dfn's -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/LOBSTER10000/Green-Project/graphs/contributors
 -->
