# springboot-hospital

## 접속

> http://43.201.40.149:8080

### 데모 로그인

> ID : tester3
>
> PW : qwerty123

## 배포

```bash
sudo sh release.sh {spring.datasource.url} {spring.datasource.username} {spring.datasource.password} {util.jwt.secret}
```

## Endpoint

### View

#### 로그인 & 로그아웃

+ 로그인 `GET /users/login`
+ 로그아웃 `GET /users/logout`

#### 게시판

+ 전체 조회 `GET /boards`
+ 게시물 조회 `GET /boards/{id}`
+ 게시물 등록 `GET /boards/new`
+ 게시물 수정 `GET /boards/edit/{id}`

#### 병원 조회

+ 전체 조회 `GET /hospitals`
+ 지역 검색 `GET /hospitals?city={address}`
    + `GET /hospitals?city=서울특별시`

### API

#### User

+ 회원가입 `POST /api/v1/users/signup`
  ```json
  {
    "userName": "String",
    "password": "String",
    "emailAddress": "String"
  }
  ```
+ 로그인 `POST /api/v1/users/signin`
  ```json
  {
    "userName": "String",
    "password": "String"
  }
  ```
+ 로그아웃 `POST /api/v1/users/signout`

#### Board

+ 게시글 등록 `POST /api/v1/boards` **[Header] Authorization : token**
  ```json
  {
     "author": "String", 
     "title": "String",
     "content": "String" 
  }
  ```
+ 게시글 전체 조회 `GET /api/v1/boards?page={number}&size={number}`
+ 게시글 조회 `GET /api/v1/boards/{id}` **[Header] Authorization : token**
+ 게시글 수정 `PATCH /api/v1/boards/{id}` **[Header] Authorization : token**
  ```json
  {
    "author": "String", 
    "title": "String",
    "content": "String" 
  }
  ```
+ 게시글 삭제 `DELETE /api/v1/boards/{id}` **[Header] Authorization : token**

#### Reply

+ 댓글 등록 `POST /api/v1/replies` **[Header] Authorization : token**
  ```json
  {
    "author": "String",
    "content": "String",
    "boardId": "String"
  }
  ```
+ 게시물 댓글 전체 조회 `GET /api/v1/replies?board={boardId}` **[Header] Authorization : token**
    + `GET /api/v1/replies?board=1`