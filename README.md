# 📜 **프로젝트 요약**

- 매일매일 영화 순위 정보를 DB에 저장하는 chunk 기반의 배치 프로세스 작성

# 📅 프로젝트 기간

- 2023.07 ~ 2023.08

# 🫙 Github

- https://github.com/verystrongvery/spring_batch_practice

# 🛠 기술 스택

- Spring Boot 2, Spring Batch 4, Spring Data JPA, Docker 24, MariaDB 11

## 🏫 프로젝트에서 배운점

- Docker를 활용하여, MariaDB 환경 구축
- Spring Scheduler를 활용하여, Batch Job 호출
- Spring Batch를 활용하여, ETL 작업처리
- WebClient를 활용하여, 영화 순위 관련 정보를 조회할 수 있는 KOBIS OPEN API 호출

# 🖥 **프로젝트 구현 내용**

### 1. Maria DB 환경 구축

- MariaDB Docker Image Pull
- Docker Volume 생성
- MariaDB Image Docker Container 실행(볼륨지정, 포트지정, root 패스워드 지정)
- MariaDB Image Docker Container 접속
- MariaDB DB 생성

### 2. Batch Job 구현

- `SavePastDailyMovieJob`: 과거의 일별 영화 순위를 저장하는 3개의 Batch Step을 실행

### 3. Batch Step 구현

- `SavePastDailyTotalMovieStep`: 전체 영화 순위 저장
- `SavePastDailyKoreanMovieStep`: 한국 영화 순위 저장
- `SavePastDailyForeignMovieStep`: 외국 영화 순위 저장

### 4. ItemReader, ItemProcessor, ItemWriter 구현

1. `ItemReader`
   - `SavePastDailyTotalMovieReader`: 전체 영화 순위 조회
   - `SavePastDailyKoreanMovieReader`: 한국 영화 순위 조회
   - `SavePastDailyForeignMovieReader`: 외국 영화 순위 조회
2. `ItemProcessor`
   - `SavePastDailyTotalMovieProcessor`: 전체 영화 순위 데이터 가공
   - `SavePastDailyKoreanMovieProcessor`: 한국 영화 순위 데이터 가공
   - `SavePastDailyForeignMovieProcessor`: 외국 영화 순위 데이터 가공
3. `ItemWriter`
   - 가공된 영화 순위 데이터를 DB에 저장

# 🍊 ETL 과정

### 1. Spring Scheduler를 활용하여 Batch Job 실행

- 과거의 일별 영화 순위를 저장하는 Batch Job을 1분마다 호출

### 2. Extract

- WebClient를 활용하여 KOBIS OPEN API를 호출
- 전체영화, 한국영화, 외국영화 별로 과거의 일별 영화 순위 데이터 조회

### 3. Transform

- 조회한 영화 순위 데이터를 DB에 저장할 데이터로 가공

### 4. Load

- 가공된 영화 순위 데이터를 DB에 저장

### 5. Batch Job Listener

- Batch Job이 끝난 후, DB에 영화 순위 데이터가 저장된 날짜 변경

# ◀️ **저장되는 데이터 예시**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3c7f7014-45bc-48b9-9bf5-5688770b70f3/02ae1986-b615-48b7-9ae6-771b99096719/Untitled.png)

