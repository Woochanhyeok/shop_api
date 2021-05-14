# thepirates

설치 및 환경설정 가이드
=====================
* IntelliJ IDEA
* Java 8
* Spring Boot version 2.4.5
* spring.dependency-management version 1.0.11
* 라이브러리
  - Lombok
  - Spring Web
* Test
  - JUnit5
* API Test
  - Postman
* 설치
  - https://github.com/Woochanhyeok/thepirates.git
* DB
  - h2
    1. h2 콘솔 실행
    2. JDBC URL에 jdbc:h2:~/shopinfo 입력하고 연결 (shopinfo.mv.db  DB파일 생성)
    3. 한 번 연결해서 DB 파일 생성 후에는 JDBC URL에 jdbc:h2:tcp://localhost/~/shopinfo 입력하고 연결 (tcp 연결)

***
테이블 생성 SQL
==============
shop 테이블 생성
---------------
<pre><code>
CREATE TABLE shop (
shop_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
owner VARCHAR(10) NOT NULL,
description VARCHAR(100) NOT NULL,
level INT NOT NULL,
address VARCHAR(100) NOT NULL,
phone VARCHAR(20) NOT NULL
);</code></pre>

business_time 테이블 생성
------------------------
<pre><code>CREATE TABLE business_time (
business_time_id INT NOT NULL AUTO_INCREMENT,
day VARCHAR(10) NOT NULL,
open VARCHAR(10) NOT NULL,
close VARCHAR(10) NOT NULL
);</code></pre>

***



API 사용 가이드
==============
점포 추가 API
---------------
* localhost:8080/register/shop
  - POST 방식
* 요청
<pre><code>
{
"name": "인어수산",
"owner": "장인어",
"description": "인천소래포구 종합어시장 갑각류센터 인어수산",
"level": 2,
"address": "인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1 층 1호",
"phone": "010-1111-2222",
"businessTimes": [
{
"day": "Monday",
"open": "13:00",
"close": "23:00"
},
{
"day": "Tuesday",
"open": "13:00",
"close": "23:00"
},
{
"day": "Wednesday",
"open": "09:00",
"close": "18:00"
},
{
"day": "Thursday",
"open": "09:00",
"close": "23:00"
},
{
"day": "Friday",
"open": "09:00",
"close": "23:00"
}
]
}
</code></pre>
<pre><code>
{
"name": "해적수산",
"owner": "박해적",
"description": "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집",
"level": 1,
"address": "서울 동작구 노량진동 13-8 노량진수산시장 활어 001",
"phone": "010-1234-1234",,
"businessTimes": [
{
"day": "Monday",
"open": "09:00",
"close": "24:00"
},
{
"day": "Tuesday",
"open": "09:00",
"close": "24:00"
},
{
"day": "Wednesday",
"open": "09:00",
"close": "24:00"
},
{
"day": "Thursday",
"open": "09:00",
"close": "24:00"
},
{
"day": "Friday",
"open": "09:00",
"close": "24:00"
}
]
}
</code></pre>
* 응답
<pre><code>
등록된 점포의 id
</code></pre>

휴무일 등록 API
---------------
* localhost:8080/register/holiday
  - POST 방식
* 요청
<pre><code>
{
"id": 1,
"holidays": [
"2021-05-15",
"2021-05-16"
]
}
</code></pre>
* 응답
<pre><code>
성공 : holidays 길이
실패 : 0
</code></pre>

점포 목록 조회 API
-----------------
* localhost:8080/get/list
  - GET 방식
* 응답
<pre><code>
[
{
"name": "해적수산",
"description": "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집",
"level": 1,
"businessStatus": "OPEN"
},
{
"name": "인어수산",
"description": "인천소래포구 종합어시장 갑각류센터 인어수산",
"level": 2,
"businessStatus": "HOLIDAY"
}
]
</code></pre>

점포 상세 정보 조회 API
---------------
* localhost:8080/get/info
  - GET 방식
* 요청
<pre><code>
{
"id" : 1
}
</code></pre>
* 응답
<pre><code>
점포의 상세 정보(점포 id, 점포명, 점포 설명, 등급, 주소, 전화번호, 조회 일자 기준 영업 시간 3일치)
</code></pre>

점포 삭제 API
-------------
* localhost:8080/delete
  - DELETE 방식
* 요청
<pre><code>
{
"id" : 1
}
</code></pre>
* 응답
<pre><code>
삭제된 점포의 id
</code></pre>
