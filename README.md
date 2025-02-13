# 🏙 Seoul Tourism

## 1. 소개와 기능
### 1.1 소개
서울을 방문하는 여행자들을 위한 맞춤형 관광 가이드 웹 애플리케이션입니다.  \
사용자는 원하는 관광지를 찜하고, 여행 일정을 캘린더로 관리할 수 있으며,  \
굿즈 상품 구매 및 결제 기능을 이용할 수 있습니다.  \
또한, 지도 API 및 공공 데이터 OpenAPI를 활용하여 여행 관련 정보를 제공하고,  \
사용자가 선호하는 인기 관광지를 분석하여 추천하는 기능도 포함하고 있습니다.

### 1.2 기능
- 📍 여행지 찜하기: DB와 지도 API를 활용하여 사용자가 원하는 관광지를 저장 및 관리  
- 📆 여행 캘린더: 일정 및 세부 내용을 기록하고 관리하는 캘린더 기능 제공  
- 🛒 굿즈 상품 & 결제: 여행 관련 기념품 및 상품을 구매하고 결제 진행  
- 📊 관광 통계 분석: 공공 데이터 OpenAPI를 활용하여 서울 관광 통계를 제공  
- ⭐ 인기 관광지 추천: 사용자 선호도를 반영한 TOP 5 관광지 추천 기능  
- 🔑 회원가입 & 로그인: JWT 기반 사용자 인증 및 보안 강화 (Spring Security)

### 1.3 팀 구성
#### 팀원 소개 
- [B.E]김도현 
- [B.E]우비주 
- [B.E]안성현 
- [B.E]김보경 


## 2. 개발 환경 및 배포 URL 
### 2.1 개발 환경 

#### 🛠 기술 스택
- Backend: Java 17, Spring Boot 3.x, Spring Security, JPA  
- Database: MariaDB 
- CI/CD: Docker, AWS EC2 
- Authentication: JWT (Spring Security 필터 방식)  
- API: 지도 API, 공공 데이터 OpenAPI 활용  
- Payment: PortOne


### 2.2 배포 URL

- http://ec2-3-36-66-32.ap-northeast-2.compute.amazonaws.com:8080/
- 테스트용 계정
    ```
    id : bbb@abcdef.com
    pw : super1234!
    ```


### 2.3 \[F.E] URL 구조 ※ 예시로 작성된 URL입니다.

- main

|App|URL|Views Function|HTML File Name|Note|
|---|---|---|---|---|
|main|'/'|home|main/home.html|홈화면|
|main|'/about/'|about|main/about.html|소개화면|

- accounts

|App|URL|Views Function|HTML File Name|Note|
|---|---|---|---|---|
|accounts|'register/'|register|accounts/register.html|회원가입|
|accounts|'login/'|login|accounts/login.html|로그인|
|accounts|'logout/'|logout|accounts/logout.html|로그아웃|
|accounts|'profile/'|profile|accounts/profile.html|비밀번호변경기능 /  <br>프로필 수정/ 닉네임추가|

- boardapp

|App|URL|Views Function|HTML File Name|Note|
|---|---|---|---|---|
|board|'board/'|board|boardapp/post_list.html|게시판 목록|
|board|'board/int:pk/'|post_detail|boardapp/post_detail.html|게시글 상세보기|
|board|'board/write/'|post_write|boardapp/post_write.html|게시글 작성|
|board|'board/edit/int:pk/'|post_edit|boardapp/post_edit.html|게시글 수정|
|board|'board/delete/int:pk/'|post_delete|boardapp/post_delete.html|게시글 삭제|
|board|'board/int:pk/comment/'|comment_create|boardapp/comment_form.html|댓글 작성|
|board|'board/int:pk/comment/  <br>int:comment_pk/edit/'|comment_edit|boardapp/comment_form.html|댓글 수정|
|board|'board/int:pk/comment/  <br>int:comment_pk/delete/'|comment_delete|boardapp/comment_  <br>confirm_delete.html|댓글 삭제|

- blog

|App|URL|Views Function|HTML File Name|Note|
|---|---|---|---|---|
|blog|'blog/'|blog|blog/blog.html|갤러리형 게시판 메인 화면|
|blog|'blog/int:pk/'|post|blog/post.html|상세 포스트 화면|
|blog|'blog/write/'|write|blog/write.html|카테고리 지정, 사진업로드,  <br>게시글 조회수 반영|
|blog|'blog/edit/int:pk/'|edit|blog/edit.html|게시물목록보기|
|blog|'blog/delete/int:pk/'|delete|blog/delete.html|삭제 화면|
|blog|'blog/search/'|search|blog/search.html|주제와 카테고리에 따라 검색,  <br>시간순에 따라 정렬|
|blog|'post/int:post_pk/comment/'|comment_new|blog/comment_form.html|댓글 입력 폼|
|blog|'post/int:post_pk/comment/  <br>int:parent_pk/'|reply_new|blog/comment_form.html|대댓글 폼|
|blog|'post/int:pk/like/'|like_post|blog/post.html|좋아요를 누르면 blog/post로 Redirect됨|
|blog|'comment/int:pk/update/'|comment_update|blog/comment_form.html|댓글 업데이터 경로|
|blog|'comment/int:pk/delete/'|comment_delete|blog/comment_  <br>confirm_delete.html|댓글 삭제 폼|

### 2.4 \[B.E] URL 구조 --> 변경하여 작성

|App|Method|URL|Views Class|Note|Authentication|
|---|---|---|---|---|---|
|blog|GET|'/blog/posts/'|PostViewSet|게시글 목록||
|blog|POST|'/blog/posts/'|PostViewSet|게시글 생성 / ChatGPT API 요청|✅|
|blog|GET|'/blog/posts/{post_id}/'|PostViewSet|게시글 상세보기 / 게시글 조회수 증가||
|blog|PATCH|'/blog/posts/{post_id}/'|PostViewSet|게시글 수정||
|blog|DELETE|'/blog/posts/{post_id}/'|PostViewSet|게시글 삭제||
|blog|POST|'/blog/posts/{post_id}/like/'|PostViewSet|게시글 좋아요 증가||
|blog|GET|'/blog/posts/{post_id}/comments/'|CommentViewSet|게시물의 댓글 목록||
|blog|POST|'/blog/posts/{post_id}/comments/'|CommentViewSet|게시물의 댓글 생성||
|blog|GET|'/blog/posts/{post_id}/comments/{comment_id}/'|CommentViewSet|게시물의 특정 댓글 보기||
|blog|PATCH|'/blog/posts/{post_id}/comments/{comment_id}/'|CommentViewSet|게시물의 특정 댓글 수정||
|blog|DELETE|'/blog/posts/{post_id}/comments/{comment_id}/'|CommentViewSet|게시물의 특정 댓글 삭제||



## 3. 요구사항 명세와 기능 명세 --> 변경하여 작성

- [https://www.mindmeister.com/](https://www.mindmeister.com/) 등을 사용하여 모델링 및 요구사항 명세를 시각화하면 좋습니다.
- 이미지는 셈플 이미지입니다.

[![](https://github.com/weniv/project_sample_repo/raw/main/map.png)](https://github.com/weniv/project_sample_repo/blob/main/map.png)

- 머메이드를 이용해 시각화 할 수 있습니다.

## 4. 개발 일정 --> 변경하여 작성

## 데일리 스크럼 시간 : 매일 오후 2시

### 4.1 개발 일정(WBS) --> 변경하여 작성


[![](https://github.com/weniv/project_sample_repo/raw/main/wbs_xlsx.png)](https://github.com/weniv/project_sample_repo/blob/main/wbs_xlsx.png)

- 좀 더 가벼운 프로젝트는 아래 일정표를 사용하세요.
- 아래 일정표는 [habitmaker.co.kr](https://habitmaker.co.kr/) 에서 작성되었습니다.
- 관련된 스택 표시는 [dev.habitmaker.co.kr](https://dev.habitmaker.co.kr/) 에서 작성되었습니다.


## 5. 와이어프레임 / UI / BM --> 변경하여 작성
### 5.1 와이어프레임
- 아래 페이지별 상세 설명, 더 큰 이미지로 하나하나씩 설명 필요

[![](https://github.com/weniv/project_sample_repo/raw/main/ui.png)](https://github.com/weniv/project_sample_repo/blob/main/ui.png)

- 와이어 프레임은 디자인을 할 수 있다면 '피그마'를, 디자인을 할 수 없다면 '카카오 오븐'으로 쉽게 만들 수 있습니다.

### 5.2 화면 설계 ※ 화면은 gif,jpg 파일로 업로드해주세요.

- 표는 가로/세로 상관이 없습니다.

|   |   |
|---|---|
|메인|로그인|
|[![](https://github.com/weniv/project_sample_repo/raw/main/ui1.png)](https://github.com/weniv/project_sample_repo/blob/main/ui1.png)|[![](https://github.com/weniv/project_sample_repo/raw/main/ui2.png)](https://github.com/weniv/project_sample_repo/blob/main/ui2.png)|
|회원가입|정보수정|
|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|
|검색|번역|
|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|
|선택삭제|글쓰기|
|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|
|글 상세보기|댓글|
|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|[![](https://github.com/weniv/project_sample_repo/raw/main/ui3.png)](https://github.com/weniv/project_sample_repo/blob/main/ui3.png)|

## 6. 데이터베이스 모델링(ERD) 및 변수명 --> 변경하여 작성

- 프론트와 백엔드 간에 정보를 주고 받는 과정에서 혼선이 일어나지 않도록 변수명을 설정이 필요합니다.
- 프론트에서 submit과정을 진행할 때 어떤 변수명으로 보내는지 알아야 데이터 맞추기가 수월합니다.

- 아래 ERD는 [ERDCloud](https://www.erdcloud.com/)를 사용했습니다.

[![](https://github.com/weniv/project_sample_repo/raw/main/erd.png)](https://github.com/weniv/project_sample_repo/blob/main/erd.png)

- [https://dbdiagram.io/home도](https://dbdiagram.io/home%EB%8F%84) 많이 사용합니다.

## 7. Architecture --> 변경하여 작성

- 아래 Architecture 설계도는 PPT를 사용했습니다.

[![image](https://github.com/weniv/project_sample_repo/raw/main/architecture.png)](https://github.com/weniv/project_sample_repo/blob/main/architecture.png)

- PPT로 간단하게 작성하였으나, 아키텍쳐가 커지거나, 상세한 내용이 필요할 경우 [AWS architecture Tool](https://online.visual-paradigm.com/ko/diagrams/features/aws-architecture-diagram-tool/)을 사용하기도 합니다.

## 8. 메인 기능 --> 변경하여 작성
- 머메이드나 이미지 파일을 사용하면 좋을 것 같습니다.
- 요구사항 및 기능 명세에서 제외한 기능을 작성하면 좋을 것 같습니다.
- 예를 들어, 이미지 저장을 위해 Amazon S3를 사용했다. OAuth 기능 구현을 위해 카카오로그인 API를 사용했다. 등등
- 요구사항 및 기능 명세에서 간단하게 설명만 하였다면 구현한 API를 자세하게 설명해도 괜찮습니다.
