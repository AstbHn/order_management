# 음식점 주문 관리 시스템 (Restaurant Order Management)

Spring Boot 기반의 **음식점 주문 관리 웹 애플리케이션**입니다.  
사용자 역할을 **관리자 / 점주 / 고객**으로 분리하여,  
각 역할에 맞는 기능과 접근 권한을 설계했습니다.

---

## 프로젝트 개요

- 음식점 주문 흐름을 실제 서비스 구조에 가깝게 구현
- Spring Security를 활용한 권한 기반 접근 제어
- 관리자·점주·고객 역할별 기능 분리
- 주문, 매출, 메뉴 관리까지 포함한 실무형 프로젝트

---

## 실행 방법

1. 애플리케이션을 실행합니다.
2. 최초 실행 시 콘솔에 출력되는 관리자 비밀번호를 확인합니다.
   - 관리자 비밀번호는 초기 실행 시 **1회만** 출력됩니다.
3. 브라우저에서 아래 주소로 접속합니다.

   http://localhost:9787/auth/

---
## 프로젝트 구조
```
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
└─ service
   ├─ AdminService.java
   ├─ MenuService.java
   ├─ OrderService.java
   ├─ SellerService.java
   ├─ StoreService.java
   └─ UserService.java
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

## 기술 스택

- **Backend**
  - Java 17
  - Spring Boot
  - Spring Security
  - MyBatis
- **Database**
  - MySQL
- **Frontend**
  - Thymeleaf
  - HTML / CSS
- **Build Tool**
  - Maven

---

## 권한 및 역할 구조

| 역할 | 설명 |
|---|---|
| ADMIN | 전체 서비스 관리 |
| SELLER | 음식점 점주 |
| CLIENT | 일반 고객 |

Spring Security를 통해 URL 단위로 접근 권한을 제어하며,  
비활성화된 계정은 로그인 자체가 차단됩니다.

---

## 주요 기능

### 공통 기능
- 로그인 / 회원가입
- Spring Security 기반 권한 분기
- 계정 활성 / 비활성 관리  
  (관리자 제어 + 비활성 계정 로그인 차단)

---

### 관리자 (ADMIN)
- 회원 목록 조회
- 회원 활성 / 비활성 관리
- 가게 전체 목록 조회
- 가게별 총 매출 집계
- 가게별 메뉴 관리 페이지 접근

---

### 점주 (SELLER)
- 가게 등록
- 메뉴 등록 / 수정 / 삭제 / 판매중지
- 가게 총 매출 확인
- 메뉴별 매출 통계 조회
- 주문 리스트 조회
- 주문 상세 내역 확인

---

### 고객 (CLIENT)
- 메뉴 주문
- 주문 내역 조회
- 주문 상세 내역 확인

---

## DB 설계 개요

- 사용자(`user_t`)
- 가게(`store_t`)
- 메뉴(`menu_t`)
- 주문(`order_t`)
- 주문 상세(`order_item_t`)

주문 시점의 메뉴 정보는 스냅샷 형태로 저장하여  
메뉴 변경 시에도 주문 내역의 정합성을 유지하도록 설계했습니다.

---

## 설계 포인트

- 역할별 책임 분리 (관리자 / 점주 / 고객)
- 관리자에게는 **주문 상세가 아닌 매출 요약 중심 제공**
- 점주에게는 **실제 운영에 필요한 주문 상세 제공**
- 기능 과잉을 피하고, 실무 관점에서 필요한 기능만 구현

---

## 📎 프로젝트 목적 
**실제 서비스 운영 흐름과 역할 분리를 고려한 백엔드 설계 경험**을 목표로 합니다.

