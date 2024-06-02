# gamenome-project-server

## 소개

- 게임에 관한 리뷰를 진행 하며 게임과 관련된 다양한 평가를 제공 하는 프로젝트 입니다

## 목차

## [1. 사전 작업](#1-사전-작업)
## 2. 기능 설명
### [2-1. Review](#1-Review)
### [2-2. Comment](#2-Comment)
### [2-3. User](#3-User)
### [2-4. reaction, star](#4-reaction_star)
### [2-5. report](#5-report)
### [2-6. report](#6-follow)
### [2-7. 기타 추가 기능](#7-기타-추가-기능)
### [2-8. 버그 및 이슈 사항](#8-버그-및-이슈-사항)
## [3. 환경 변수](#3-환경-변수)

# 1-사전-작업

## 1-1. 이벤트 스토밍

<details>
<summary>이벤트 스토밍 보기</summary>

<div markdown="1">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/eventstoming.png">
</div>

</details>



## 1-2. 와이어 프레임

<details>
<summary>와이어 프레임 보기</summary>

<div markdown="1">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/wireframe1.png">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/wireframe2.png">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/wireframe3.png">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/wireframe4.png">
</div>

</details>

## 1-3. API 명세서

<details>
<summary>API 명세서 보기</summary>

<div markdown="1">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/api_spec_summary1.png">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/api_spec_summary2.png">
    <img src="https://github.com/gamenome-project/gamenome-project-server/blob/readme/src/main/resources/img/api_spec_summary3.png">
</div>

</details>

## 1-4. ERD

```mermaid
erDiagram
    review }|--|| app_user : user_id
    review ||--o| star : review_id
    review ||--o{ comment : review_id
    comment ||--o| star : comment_id
    comment ||--|{ app_user : user_id
    comment ||--|{ reaction : comment_id
    app_user ||--o{ follow : user_id
    app_user ||--o{ report : user_id
    app_user ||--o| star : user_id
    app_user ||--o| reaction : user_id



    review{
         id BIGINT PK
         game_name TEXT
         title TEXT
         description TEXT
         is_deleted BOOLEAN
         created_at TIMESTAMP
         updated_at TIMESTAMP
         deleted_at TIMESTAMP 
    }

    comment{
         id BIGINT PK
         user_id BIGINT FK
         review_id BIGINT FK
         content TEXT
         is_deleted BOOLEAN
         created_at TIMESTAMP
         updated_at TIMESTAMP
         deleted_at TIMESTAMP 
    }

     app_user{
         id BIGINT PK
         email VARCHAR(255)
         password VARCHAR(255)
         nickname VARCHAR(255)
         about_summary VARCHAR(255)
         profile_user_image TEXT
         created_at TIMESTAMP
         updated_at TIMESTAMP
         last_signin_at TIMESTAMP 
    }

    report{
         id BIGINT PK
         user_id BIGINT FK
         entity_id BIGINT 
         entity_type ENUM
         description TEXT
         created_at TIMESTAMP
    }

    star{
         user_id BIGINT FK
         review_id BIGINT FK
         comment_id BIGINT FK
         score FLOAT
    }


    follow{
         user_id BIGINT FK
         following_user_id BIGINT
    }

    reaction{
         user_id BIGINT FK
         comment_id BIGINT FK
         reaction ENUM
    }
```
[자세한 ERD 상세 정보 보기](https://www.erdcloud.com/d/2o4n8pSuC9zzytQvK)

## 1-5. gamenome-project-conventions

- Naming convention
    - URI 작성
        - 띄어쓰기는 - 를 사용 한다
        - 기본적 으로 소문자 를 사용 한다
        - 기본적 으로 복수형 (posts, comments) 을 사용 한다
        - Query parameter 는 camelCase → ex) ?userId=1 로 작성한다
        - 버저닝 관리 (/api/v1/)
            - 패키지 구조가 v1.{Controller, Service, Repository}
    - 변수명
    - 함수명
    - 생성자
        - 생성자는 명시적 으로 생성 한다  ex) Post(id = id, password = password, name = name)
    - Entity
        - `@ColumnName(name=””, nullable=(false/true))`
        - Mutable 여부는 var, val로 구분 한다
        - 날짜에 대한 어노테이션 으로 @CreationTimeStamp createdAt, @UpdateTimeStamp updatedAt 을 사용 한다
        - Soft delete 사용 한다 (Delete 쿼리는 지양하자)
- Git convention
    - PR
    - Commit



## 1-6. 역할 분담

### 전체
- 코드 리펙터링 및 코드 리뷰

### 노재원
- `User Domain`에 대한 전체 적인 로직 작성
- 인증 과 인가 Jwt 구현
- 불변성 / 유효성 검증 및 세부 정책 구현
- 패스워드 암호화 구현
- 소셜 로그인 구현
- 전체 적인 코드 리펙터링
### 임성우
- `Review Domain`에 대한 전체 적인 로직 작성
- `Review` 및 `Comment` 신고 하기 기능 구현
- 이메일 및 닉네임 인증 부분 구현
- AWS S3 이미지 업로드 구현
### 김도균
- `Comment Domain`에 대한 전체 적인 로직 작성
- 코맨트에 좋아요 / 싫어요 기능 구현
- PageNation 및 SoftDelete 구현
- 게임 리뷰 별점 주기 기능 구현
- 유저 팔로잉 기능 구현
- HTTPS 업그레이드 구현 <- 도메인 이슈로 보류

# 기능 설명

## 패키지 구조


## 1-Review

```mermaid
classDiagram
    
    ReviewDto <--> ReviewController
    ReviewServiceImpl <.. ReviewService
    ReviewServiceImpl <--> ReviewDto
    ReviewRepository <-- ReviewServiceImpl
    ReviewRepository ..> ReviewRepositoryImpl
    ReviewServiceImpl <-- ReviewRepositoryImpl
    ReviewJpaRepository <--> ReviewRepository
```
- 게임에 대한 평가를 CRUD 할 수 있는 기능을 포함 했습니다
- 리뷰를 조회 할 경우에 평균 평점을 계산 하여 조회 합니다
- 게임에 대한 특정 게시물 이나 모든 게시물을 다 조회할 수 있습니다 
- 또한 악성 글을 작성한 유저를 신고 하는 기능과 팔로잉 한 유저의 게시물을 필터링 하여 보여 줄 수 있는 기능을 포함 했습니다
- 게임 리뷰에 대한 정보는 Soft Delete 기능을 사용 하여 추후에 복원이 가능하게끔 설계를 하였습니다
- 게임 리뷰를 작성할 시에 게임 이름은 100자 내외, 제목은 200자 내외, 내용은 2000자 내외로 작성 해야 합니다

## 2-Comment

```mermaid
classDiagram
    
    CommentDto <--> CommentController
    CommentService <--> CommentDto
    CommentRepository <-- CommentService
    CommentRepository ..> CommentRepositoryImpl
    CommentService <-- CommentRepositoryImpl
    CommentJpaRepository <--> CommentRepository
```
- 리뷰에 대한 평가를 댓글로 CRUD 할 수 있는 기능을 추가 했습니다
- 리뷰 와는 달리 리뷰에 대한 모든 댓글을 불러 옵니다
- 코맨트를 조회할 때 좋아요와 싫어요, 유저가 준 평점을 같이 조회 합니다
- 코맨트도 리뷰와 마찬가지로 신고를 가능하게끔 하여 악성 글을 작성 하는 유저의 게시물을 필터링 하여 볼 수 있습니다
- 코맨트는 코맨트에 대한 유저들이 좋아요와 싫어요를 통해서 반응할 수 있습니다
- 코맨트는 대한 정보는 Soft Delete 기능을 사용 하여 추후에 복원이 가능하게끔 설계를 하였습니다 그러나 좋아요와 싫어요, 별점은 초기화 됩니다
- 코맨트는 200자 내외로 작성 해야 합니다



## 3-User

```mermaid
classDiagram

    UserDto <--> UserController
    UserServiceImpl <.. UserService
    UserServiceImpl <--> UserDto
    UserRepository <-- UserServiceImpl
    UserRepository ..> UserRepositoryImpl
    UserServiceImpl <-- UserRepositoryImpl
    UserJpaRepository <--> UserRepository
```
- 유저에 대한 정보를 저장 혹은 인증을 하는 공간 입니다
- 유저의 회원 가입과 회원 탈퇴를 진행 하며 유저의 로그인 로그아웃을 진행 합니다
- 회원 가입 시에 유저의 이메일과 닉네임이 중복 되지 않도록 처리 합니다
- 또한 이메일을 통한 유저의 인증 절차를 진행 하며 인증 코드를 통해서 이메일 인증을 완료 합니다
- 이메일은 이메일 검증을 확인 해야 하며 비밀 번호는 최소 8자 ~ 최대 50자 까지 입력이 가능 합니다
- Aws S3 기능을 통해서 유저의 프로필 이미지를 업로드 할 수 있게끔 구현 했습니다

## 4-reaction_star

```mermaid
classDiagram
    ReactionDto <--> commentController
    ReactionService <--> ReactionDto
    ReactionRepository <-- ReactionService
    ReactionRepository ..> ReactionRepositoryImpl
    ReactionService <-- ReactionRepositoryImpl
    ReactionJpaRepository <--> ReactionRepository

    StarScoreDto <--> commentController
    StarScoreService <--> StarScoreDto
    StarScoreRepository <--> StarScoreService
    StarScoreJpaRepository <--> StarScoreRepository
```
- 유저가 코맨트에 대한 좋아요, 싫어요에 대한 정보를 저장 및 전달 합니다
- 1명의 유저는 하나에 댓글 에만 좋아요와 싫어요 둘 중 하나만 달 수 있습니다
- 평점은 총 5점 만점 이며 0.5점씩 부여가 가능 합니다
- 하나의 댓글 당 하나의 평점 만이 작성이 가능 합니다 
- commentController 가 댓글을 작성할 경우에 좋아요와 싫어요, 평점을 선택 및 입력 수 있습니다
- commentController 가 댓글을 변경할 경우에 좋아요와 싫어요, 평점을 변경할 수 있습니다
- commentController 가 댓글을 삭제할 경우에 좋아요와 싫어요, 평점이 자동 으로 삭제 됩니다

## 5-report
```mermaid
classDiagram
    ReportDto <--> commentController
    ReportDto <--> ReviewController
    ReportService <--> ReportDto
    ReportRepository <-- ReportService
    ReportRepository ..> ReportRepositoryImpl
    ReportService <-- ReportRepositoryImpl
    ReportJpaRepository <--> ReportRepository
```
- 리뷰와 코맨트에 악성 글이 작성될 경우에 신고를 하는 기능 입니다
- 리뷰와 코맨트 모두 5회 초과 신고 접수 이후 에는 조회 에서 제외 됩니다
- 신고 내용은 200자 내외로 작성 해야 합니다

## 6-follow
```mermaid
classDiagram
    FollowDto <--> FollowController
    FollowServiceImpl <.. FollowService
    FollowServiceImpl <--> FollowDto
    FollowRepository <-- FollowServiceImpl
    FollowRepository ..> FollowRepositoryImpl
    FollowServiceImpl <-- FollowRepositoryImpl
    FollowJpaRepository <--> FollowRepository
```
- 유저는 특정 유저를 팔로우 할 수 있습니다
- 유저는 팔로우한 유저의 목록을 볼 수 있으며 팔로우한 유저의 게시글만 볼 수 있습니다

## 7-기타-추가-기능

### HTTP -> HTTPS 업데이트

- apache를 기반 으로 한 라이브러리로 HTTPS 업데이트를 구현 했습니다
- ServletWebServerFactory를 상속 받아서 모든 HTTP로 연결 되는 API들을 전부 HTTPS로 연결을 변경 해주는 로직을 구성 했습니다
- 현재 인증서 발급 문제로 HTTPS는 main과 dev 브렌치에 Pull Request 를 하지 않은 상태 입니다
- 자세한 내용은 [#HTTP를 HTTPS로 업그레이드 하기](https://github.com/gamenome-project/gamenome-project-server/issues/64) 참고


### KAKAO OAuth 인증

### JWT 토큰 인증

### 이메일 인증
- 이메일 인증 시 네이버 smtp 사용하여 진행하였습니다.
- redis 사용하여 이메일 인증 시 사용자 에게 전송한 코드를 이메일을 키로 하여 저장 합니다.
- 전송 코드 체크 api 에서 사용자가 받은 인증코드와 저장 되어 있는 코드를 비교 합니다.
- 자세한 내용은 [이메일 인증 및 닉네임 중복 확인 기능](https://github.com/gamenome-project/gamenome-project-server/pull/80) 참고

## 8-버그-및-이슈-사항

## 1. `FATAL: Max client connections reached` 애러 발생
- 해당 오류는 최대 클라이언트 연결에 도달 했다는 오류로 이것은 뭔가 서로 DB에 동시에 접근 할 경우 발생 하는 오류로 확인이 됩니다
- 확인 결과 supabase 무료 버전은 최대 요청이 15로 설정이 되어 있는데 기준 보다 더 많은 요청이 들어 가면서 발생한 현상 으로 확인 됩니다
### AS-IS
- application.yml 
```
   spring:
   datasource:
   url: jdbc:URL/postgres?user=USER&password=PASSWORD
```
### TO-BE
```
spring:
   datasource:
   url: jdbc:URL/postgres?user=USER&password=PASSWORD&pgbouncer=true&connection_limit=1000
```

- `pgbouncer=true&connection_limit=1000`해당 값을 붙여서 최대 클라이언트 값을 증가 시켜 주면 정상적 으로 작동 되는 걸 확인할 수 있습니다 
- 이후 supabase 에 접속해서 요청 개수를 늘려 주면서 애러 해결을 하였습니다

## 2. ApiV1MappingConfig 설정 시 Mapping 애러 발생

## 3. JWT Token userId 캐스팅 실패

## 4. StarScore 복합키 데이터 베이스 생성시에 null one-to-one property 발생 현상

### 1. Comment -> `EntityNotFoundException unable to find with id 0` 애러 발생

#### AS-IS
   - 코맨트가 Id가 0으로 Entity를 찾는 문제가 발생 하였습니다

#### TO-BE
   - 해당 부분은 복합키를 설정 하는 과정에서 0을 조회하는 현상 이라고 원인을 파악 했습니다, `@NotFound` Annotation 을 사용 하여 해당 현상을 해결 하였습니다

### 2. user -> duplicated primary key 애러 발생

#### AS-IS
   -  user 가 primary key 로 설정이 되어 중복 값이 발생 하여 발생된 애러였습니다
   -  기존에 작업 실수로 `user`가 foreign Key가 들어가 있어야 하는데 primary key 로 들어가 있는 부분 확인 하였습니다

#### TO-BE
   - 기존에 작업 실수로 `user`가 primary key로 들어가 있는 부분 확인 하고 제외 처리 후에 foreign Key 로 재설정 완료 했습니다

### 3. comment -> attempted to assign id from null one-to-one property 애러 발생

#### AS-IS
   - 1:1 맵핑 관계에서 코맨트의 ID가 `null`을 할당하려고 시도
   - `comment`가 create 하는 과정에서 동시에 `star_score`도 create 가 되는 로직 에서 `comment`가 create 이후에 `star_score`도 create 이후에 `comment` 데이터 에도 `null` 값이 할당 되는 문제 발생
#### TO-BE
   - `cascodetype.MERGE` 를 사용하여 부모 Entity가 생성을 시도 할 때 자식 Entity 도 병합 하여 생성 시도 할 수 있게 끔 조치 하였습니다


# 3-환경-변수

개발 언어 : Kotlin 1.9.23, JDK 21

IDE : IntelliJ IDEA 2024.2

Build tool : Gradle.kts

FrameWork : Spring Boot 3.2.5

Library : Springdoc 2.5.0

DataBase : PostgresQL 14.1 with Supabase