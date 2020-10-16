
![logo](front-end/src/assets/mainlogo.png)

<center><h2>A608 - 스터디의 발견</h2></center>

<center>
    <img src="https://img.shields.io/badge/platform-web-green">
    <img src="https://img.shields.io/badge/front-Vue-brightgreen">
    <img src="https://img.shields.io/badge/framework-spring boot-blue">
    <img src="https://img.shields.io/badge/database-MySQL-9cf">
    <img src="https://img.shields.io/badge/server-AWS-yellow">
    <img src="https://img.shields.io/badge/language-Java-yellowgreen">
    <img src="https://img.shields.io/badge/swagger-valid-brightgreen">
    <img src="https://img.shields.io/badge/container-Docker-blue">
</center>



<br>

<center>
    취업 준비생을 위한 취업 스터디 운영 서비스와 자소서 컨설팅 서비스<br>
    취업 스터디 플랫폼 TiktaCAR
</center>


## :point_right: 프로젝트 기술 특이점

* SpringBoot + Vue, Vuetify 
* JWT 토큰을 활용한 로그인 기능
* JPA를 통한 ORM 활용
* Jenkins 기반 자동 빌드/배포
* Docker 컨테이너를 활용한 Mysql DB
* Google FireBase storage를 사용하여 이미지 저장
* 카카오 지도 API를 통한 지도 표시 API


## :package: 프로젝트 구성

### frontend

|        Package         |    Version    |                     Comment                     |
| :--------------------: | :-----------: | :---------------------------------------------: |
|       vue-router       |     3.2.0     |     요청 URL에 따라 브라우저에서 돔을 변경      |
|          vuex          |     3.5.1     |           상태 관리 패턴 + 라이브러리           |
|         axios          |    0.19.2     | REST API 서버로부터 데이터를 받아오기 위해 사용 |
|        bootstrap-vue   |     2.15.0    |             전반적인 디자인에 사용              |
| chart.js / vue-chartjs | 2.9.3 / 3.5.0 |     데이터를 시각화하여 보여주기 위해 사용      |
|    vue-infinite-loading|     2.0.2     |         무한스크롤을 구현하기 위해 사용         |


### backend

| Package | Version | Comment |
| :--: | :--: | :--- |
| OpenJDK - zulu |  8.33   | 프로젝트의 Java vendor로써 사용 |
|   SpringBoot   |  2.3.3  | Java의 서버 개발 환경을 구성하기 위해 사용 |
| JWT | 0.9.1 | 클라이언트와 서버의 stateless 환경 구성을 위해 사용 |
|    Docker     |  19.03.12  | 서버 환경 구축을 위해 컨테이너로 사용 |
| Spring Data JPA | 2.3.3 | Java와 DB의 ORM을 위해 사용 |
|    MySQL     |  8.0.21  | 서버와 DB의 연동을 위해 사용 |
|     Lombok     | 1.18.10 | Annotation을 이용한 코드 생성으로 가독성 & 유지 보수를 위해 사용 |
|    Swagger     |  2.9.2  | API 스펙 문서의 자동화를 위해 사용 |
|     Nginx     | 1.14.0  | 서버 배포를 용이하게 하기 위해 사용 |
| pm2 | 4.4.0 | 서버의 무중단 배포를 위해 사용 |
| git | 2.17.1 | 코드 관리 목적을 위해 사용 |
|firebase storage| |이미지 저장소 목적으로 사용|
| Jenkins | 2.249.1 | git 버전 관리를 위해 사용 |



## :two_men_holding_hands: 개발자

* **성근제** - *팀장, 프론트엔드리더*
* **이영민** - *프론트엔드*
* **이창로** - *프론트엔드*
* **부정수** - *백엔드리더*
* **박준수** - *백엔드* 
* **김상열** - *백엔드* 


### :star: 브랜치 전략

```bash
master -> release -> development -> front-end/back-end -> frontend-feature/backend-feature
```
