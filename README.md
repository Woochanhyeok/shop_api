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
  - git clone https://github.com/Woochanhyeok/shop_api.git
* DB
  - h2
    1. h2 콘솔 실행
    2. JDBC URL에 **jdbc:h2:~/shopinfo** 입력하고 연결 (shopinfo.mv.db  DB파일 생성)
    3. 한 번 연결해서 DB 파일(shopinfo.mv.db) 생성 후에 재연결할 경우 JDBC URL에 **jdbc:h2:tcp://localhost/~/shopinfo** 입력하고 연결 (tcp 연결)

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
점포  API
---------------
* localhost:8080/register/shop
  - POST 방식
* 요청
<pre><code>
{
"name": "shop1",
"owner": "우찬혁",
"description": "첫번째 점포",
"level": 2,
"address": "경기도 남양주시",
"phone": "010-1234-1234",
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
"name": "shop2",
"owner": "찬혁",
"description": "두번째 점포",
"level": 1,
"address": "경기도 남양주시",
"phone": "010-1234-1234",
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
"name": "shop1",
"description": "첫번째 점포",
"level": 1,
"businessStatus": "OPEN"
},
{
"name": "shop2",
"description": "두번째 점포",
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
