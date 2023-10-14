# 프로젝트 프로세스
### 1. Spring Boot 프로젝트 생성
### 2. Maria DB 환경 구축
   - MariaDB Docker Image Pull
   - Docker Volume 생성
   - MariaDB Image Docker Container 실행(볼륨지정, 포트지정, root 패스워드 지정)
   - MariaDB Image Docker Container 접속
   - MariaDB DB 생성
### 3. 과거의 일별 영화 순위를 저장하는 Batch Job 작성
1) 과거의 일별 영화 순위를 저장하는 Batch Step(전체영화, 한국영화, 외국영화) 3개 작성
   - SavePastDailyTotalMovieStep: 전체 영화 순위 저장
   - SavePastDailyKoreanMovieStep: 한국 영화 순위 저장
   - SavePastDailyForeignMovieStep: 외국 영화 순위 저장
2) ItemReader
   - WebClient를 활용하여 KOBIS OPEN API 호출하여 영화 순위 데이터 조회
   - SavePastDailyTotalMovieReader: 전체 영화 순위 조회
   - SavePastDailyKoreanMovieReader: 한국 영화 순위 조회
   - SavePastDailyForeignMovieReader: 외국 영화 순위 조회
3) ItemProcessor
   - 조회한 영화 순위 데이터를 DB에 저장할 데이터로 가공
   - SavePastDailyTotalMovieProcessor: 전체 영화 순위 데이터 가공
   - SavePastDailyKoreanMovieProcessor: 한국 영화 순위 데이터 가공
   - SavePastDailyForeignMovieProcessor: 외국 영화 순위 데이터 가공
4) ItemWriter
   - 가공된 영화 순위 데이터를 DB에 저장
5) Batch Job Listener
   - Batch Job이 끝난 후, DB에 영화 순위 데이터가 저장된 날짜 변경
### 4.Spring Scheduler를 활용하여 Batch Job 실행
   - 과거의 일별 영화 순위를 저장하는 Batch Job을 1분마다 호출

# 저장되는 데이터 예시
![스크린샷 2023-10-14 오후 3.38.02.png](..%2FDesktop%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-10-14%20%EC%98%A4%ED%9B%84%203.38.02.png)