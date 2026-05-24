# 음식점 주문 관리 시스템 (Restaurant Order Management)

Spring Boot 기반의 **음식점 주문 관리 웹 애플리케이션**입니다.

사용자 역할을 **관리자(ADMIN) / 점주(SELLER) / 고객(CLIENT)** 으로 분리하여
각 역할에 맞는 기능과 접근 권한을 설계했습니다.

---

# 프로젝트 개요

* 음식점 주문 흐름을 실제 서비스 구조에 가깝게 구현
* Spring Security 기반 권한 제어
* 관리자 / 점주 / 고객 역할별 기능 분리
* 주문 및 매출 관리 기능 구현
* MyBatis 기반 데이터 처리 및 계층 구조 설계

---

# 기술 스택

## Backend

* Java 17
* Spring Boot
* Spring Security
* MyBatis

## Database

* MySQL

## Frontend

* Thymeleaf
* HTML / CSS

## Build Tool

* Maven

---

# 실행 환경

* Java 17
* MySQL 8.x
* Maven

---

# 실행 방법

## 1. DB 생성

```sql
CREATE DATABASE order_db;
```

---

## 2. application.yaml 환경 설정

`src/main/resources/application.yaml`

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

환경변수 또는 직접 값을 입력하여 설정합니다.

예시:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/order_db
    username: root
    password: 1234
```

---

## 3. 애플리케이션 실행

애플리케이션 실행 후 아래 주소로 접속합니다.

```text
http://localhost:9787/auth/
```

최초 실행 시 관리자 계정의 초기 비밀번호가 콘솔에 1회 출력됩니다.

---

# 프로젝트 구조

```text
com.astb.order
├─ config
│  └─ SecurityConfig.java
├─ controller
│  ├─ AdminController.java
│  ├─ AuthController.java
│  ├─ ClientController.java
│  └─ SellerController.java
├─ domain
│  ├─ menu
│  ├─ order
│  ├─ orderitem
│  ├─ store
│  └─ user
├─ dto
│  ├─ AdminStoreDTO.java
│  ├─ OrderListDTO.java
│  └─ SellerSalesDTO.java
├─ service
│  ├─ AdminService.java
│  ├─ MenuService.java
│  ├─ OrderService.java
│  ├─ SellerService.java
│  ├─ StoreService.java
│  └─ UserService.java
└─ resources
   ├─ application.yaml
   ├─ mapper
   │  ├─ MenuMapper.xml
   │  ├─ OrderMapper.xml
   │  ├─ OrderItemMapper.xml
   │  ├─ SellerMapper.xml
   │  ├─ StoreMapper.xml
   │  └─ UserMapper.xml
   ├─ static
   │  └─ css
   │     └─ app.css
   └─ templates
      ├─ admin
      ├─ auth
      ├─ client
      └─ seller
```

---

# 권한 및 역할 구조

| 역할     | 설명        |
| ------ | --------- |
| ADMIN  | 전체 서비스 관리 |
| SELLER | 음식점 점주    |
| CLIENT | 일반 고객     |

Spring Security를 활용하여 URL 단위 접근 권한을 제어했습니다.

또한 비활성화된 계정은 로그인 자체를 제한하도록 구현했습니다.

---

# 주요 기능

## 공통 기능

* 로그인 / 회원가입
* Spring Security 기반 권한 분기
* 계정 활성 / 비활성 관리
* 비활성 계정 로그인 차단

---

## 관리자 (ADMIN)

* 회원 목록 조회
* 회원 활성 / 비활성 관리
* 가게 전체 목록 조회
* 가게별 총 매출 집계
* 가게별 메뉴 관리 페이지 접근

---

## 점주 (SELLER)

* 가게 등록
* 메뉴 등록 / 수정 / 삭제
* 메뉴 판매중지 처리
* 가게 총 매출 조회
* 메뉴별 매출 통계 조회
* 주문 목록 조회
* 주문 상세 내역 조회

---

## 고객 (CLIENT)

* 메뉴 주문
* 주문 내역 조회
* 주문 상세 조회

---

# DB 설계

* 사용자(`user_t`)
* 가게(`store_t`)
* 메뉴(`menu_t`)
* 주문(`order_t`)
* 주문 상세(`order_item_t`)

주문 시점의 메뉴 정보를 별도로 저장하여
이후 메뉴 가격 및 이름이 변경되더라도 주문 데이터의 정합성을 유지하도록 설계했습니다.

---

# 설계 포인트

* 역할별 책임 분리
* 권한 기반 접근 제어
* 계층형 구조(controller / service / mapper) 적용
* 주문 데이터 정합성 고려
* 관리자와 점주의 조회 목적 차이를 고려한 기능 분리

---

# 프로젝트 목적

실제 서비스 운영 흐름과 역할 분리를 고려한
백엔드 설계 및 권한 제어 경험을 목표로 진행한 프로젝트입니다.
