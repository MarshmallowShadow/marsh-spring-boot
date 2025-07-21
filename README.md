# Spring Boot Backend Project

## Base Specs

- Language: Java 17
- Framework: Spring Boot 3.4.4
- DB: MySQL 8.0 / JPA


## Local 실행

### 환경 변수 (Local 세팅)

```
AES_KEY=qwcMvzsveyLcwfw2fNqUoAIdOHclcnwe;DB_PASSWORD=1234;DB_URL=jdbc:mysql://localhost:3306/lifelog?serverTimezone\=Asia/Seoul;DB_USERNAME=root;JWT_SECRET=9a7f2d3b4e6c89f1a2b5d7e8c1f4a0b3c7d9e1f5g8h0k2l4m6n8p0r2t4v6x8z;PORT=9090
```

### URL

```
localhost:9090
```

### API 명세서

```
localhost:9090/swagger-ui/index.html
```


## 빌드 방법

```
./gradlew bootJar
```


## 프로젝트 폴더 구성

```

src/
├── main/
│   ├── java/
│   │   └── com.huray.lifelog/
│   │       ├── config/
│   │       ├── controller/
│   │       │   └── dto/
│   │       │       └── base/
│   │       │       └── req/
│   │       │       └── res/
│   │       ├── repository/
│   │       ├── exception/
│   │       │   ├── dto/
│   │       │   ├── error/
│   │       │   ├── handler/
│   │       │   └── upper/
│   │       ├── service/
│   │       ├── type/
│   │       ├── util/
│   │       └── MainApplication.java
│   └── resources/
│       └── application.yml
└── test/
build.gradle
settings.gradle

```
