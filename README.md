# JPA를 활용한 웹 애플리케이션(PetHub) 리팩토링

## 프로젝트 개요

이 프로젝트는 기존 팀 프로젝트에서 사용했던 MyBatis를 JPA로 전환하여 다시 구현한 개인 프로젝트입니다. (기존 프로젝트 : https://github.com/Komo1284/pethub)

타임리프를 이용한 HTML 파일은 그대로 유지하면서 백엔드 코드를 처음부터 새로 작성하여 JPA 학습과 더불어 여러 가지 개선을 도모하였습니다. 

이 README 파일에서는 프로젝트의 주요 기능, 개선 사항, 문제 해결 방법 등을 다룹니다.

### 주요 기능



1. 회원 관리 
   - 멤버 업데이트 시 오류 메시지를 담아 수정 페이지로 포워드하고, 자바스크립트를 사용하여 수정이 완료되었을 때 메시지를 표시하고 페이지를 이동하도록 구현.
2. 쿠폰 관리
   - Spring @Scheduled를 사용하여 쿠폰 만료일이 지나면 자동으로 데이터를 삭제.
3. 상품 등록 및 관리
   - 상품 등록 시, 타임리프에서 enum 값을 전달하여 카테고리 및 타입 선택.
   - 선택하지 않은 경우 서비스 단에서 메시지를 담아 같은 페이지로 포워드하고 alert로 메시지를 표시.
4. 게시판 기능
   - Spring Data JPA와 QueryDSL을 사용하여 게시판 기능 구현.
   - 동적 쿼리를 사용한 페이징 및 검색 기능 구현.
   - 중복된 코드를 줄이기 위해 boardType을 정의하여 하나의 메서드와 HTML 파일로 통합. 
5. 장바구니 기능 개선
   - 잘못 설계된 DB를 수정하여 결제 시 장바구니를 비우고 주문 목록으로 이동하게 구현.

### 개선 사항 및 문제 해결

1. 불필요한 테이블 삭제 및 반정규화를 통한 성능개선
<img src="/DB/ERD.png">
2. 멤버 업데이트 오류 처리 개선
    - 방식: 수정 페이지로 포워드하여 오류 메시지 alert.
    - 사항: 자바스크립트를 이용해 수정 완료 메시지를 표시하고 페이지 이동.
2. 쿠폰 자동 삭제 기능 추가
   - Spring @Scheduled를 사용하여 쿠폰 만료 시 자동으로 데이터를 삭제하도록 관리.
3. JPA 변경 감지 기능 활용
   - 관리자 등급을 일반 회원으로 강등 시 정상적으로 수정되지 않는 문제를 JPA의 변경 감지(dirty checking)를 이용해 해결.
   - 해결 방법: 트랜잭션 내에서만 변경 감지가 일어나므로 해당 메서드에 @Transactional을 적용.
4. 상품 등록 시 메시지 처리 개선
   - 문제: 선택하지 않은 경우 메시지를 포워드 방식으로 전달할 때 데이터가 정상적으로 전달되지 않음.
   - 해결 방법: 리다이렉트 방식으로 변경하고, get 메서드에서 requestParam으로 메시지를 받아 alert로 출력.
5. 게시판 기능 개선
   - QueryDSL을 이용해 동적 쿼리로 페이징 및 검색 기능 구현.
   - 중복된 코드를 줄이기 위해 boardType을 정의하여 하나의 메서드와 HTML 파일로 통합.
6. 장바구니 기능 개선
   - 결제 시 장바구니를 비우고 주문 목록으로 이동하도록 DB 설계를 수정하고 기능 구현.
7. QueryDSL DTO 맵핑 문제 해결
   - 문제: 주문 목록 조회 시 orderItem 리스트를 DTO에 정확하게 맵핑하지 못함.
   - 해결 방법: DTO를 사용하지 않고 엔티티 자체를 반환하여 문제 해결.

사용 기술

	•	Spring Boot
	•	Spring Data JPA
	•	QueryDSL
	•	Thymeleaf
	•	JavaScript
	•	H2 Database (개발 및 테스트 용도)