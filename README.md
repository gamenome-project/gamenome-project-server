# gamenome-project-server

## 소개

- 게임에 관한 리뷰를 진행 하며 게임과 관련된 다양한 평가를 제공 하는 프로젝트 입니다

## 목차

## 1. 사전 작업
#### 1-1. 이벤트 스토밍
#### 1-2. 와이어 프레임
#### 1-3. API 명세서
#### 1-4. ERD
#### 1-5. gamenome-project-conventions
#### 1-6. 역할 분담




## 1-1. 이벤트 스토밍
## 1-2. 와이어 프레임

//사진 1 ~ 4


## 1-3. API 명세서

//사진1 ~ 3


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
## 1-6. 역할 분담