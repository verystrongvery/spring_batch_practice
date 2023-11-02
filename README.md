# 프로젝트 요약
1. Spring Batch를 활용하여 chunked 기반의 배치 프로세스 작성
2. Docker 환경에서 MariaDB 환경 구축
3. WebClient를 활용하여 KOBIS OPEN API 호출
4. Spring Scheduler 사용법

# 기술스택
- Spring Boot 2
- Spring Batch 4
- Spring Data JPA
- Docker 24
- MariaDB 11

# 프로젝트 구현내용
### 1. Maria DB 환경 구축
   - MariaDB Docker Image Pull
   - Docker Volume 생성
   - MariaDB Image Docker Container 실행(볼륨지정, 포트지정, root 패스워드 지정)
   - MariaDB Image Docker Container 접속
   - MariaDB DB 생성
### 2. Batch Job 구현
   - SavePastDailyMovieJob: 과거의 일별 영화 순위를 저장하는 3개의 Batch Step을 실행
### 3. Batch Step 구현
   - SavePastDailyTotalMovieStep: 전체 영화 순위 저장
   - SavePastDailyKoreanMovieStep: 한국 영화 순위 저장
   - SavePastDailyForeignMovieStep: 외국 영화 순위 저장
### 4. ItemReader, ItemProcessor, ItemWriter 구현
1) ItemReader
   - SavePastDailyTotalMovieReader: 전체 영화 순위 조회
   - SavePastDailyKoreanMovieReader: 한국 영화 순위 조회
   - SavePastDailyForeignMovieReader: 외국 영화 순위 조회
2) ItemProcessor
   - SavePastDailyTotalMovieProcessor: 전체 영화 순위 데이터 가공
   - SavePastDailyKoreanMovieProcessor: 한국 영화 순위 데이터 가공
   - SavePastDailyForeignMovieProcessor: 외국 영화 순위 데이터 가공
3) ItemWriter
   - 가공된 영화 순위 데이터를 DB에 저장

# ETL 과정
### 1. Spring Scheduler를 활용하여 Batch Job 실행
   - 과거의 일별 영화 순위를 저장하는 Batch Job을 1분마다 호출
### 1. Extract
   - WebClient를 활용하여 KOBIS OPEN API를 호출
   - 전체영화, 한국영화, 외국영화 별로 과거의 일별 영화 순위 데이터 조회
### 2. Transform
   - 조회한 영화 순위 데이터를 DB에 저장할 데이터로 가공
### 3. Load
   - 가공된 영화 순위 데이터를 DB에 저장
### 4. Batch Job Listener
   - Batch Job이 끝난 후, DB에 영화 순위 데이터가 저장된 날짜 변경


# 저장되는 데이터 예시
![](https://velog.velcdn.com/images/topmedia/post/9b3a0b09-5178-437e-b926-2f7ab1acb940/image.png)
