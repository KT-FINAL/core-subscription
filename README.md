# Core Subscription System

## 프로젝트 소개
이 프로젝트는 구독 기반 서비스를 위한 핵심 시스템으로, 구독 관리와 결제 처리를 담당하는 마이크로서비스 아키텍처 기반의 애플리케이션입니다.

## 시스템 구성
- **Subscription Server**: 구독 관리 서비스
  - 구독 생성, 수정, 취소
  - 구독 상태 관리
  - 구독자 정보 관리
  
- **Payment Server**: 결제 처리 서비스
  - 결제 처리 및 검증
  - 결제 내역 관리
  - 카프카를 통한 비동기 메시지 처리

## 기술 스택
- **Backend**
  - Java
  - Spring Boot
  - Gradle
  - Kafka (메시지 큐)
  
- **Infrastructure**
  - Docker
  - Docker Compose
  - 환경 변수 기반 설정 (.env)

## 프로젝트 구조
```
core-subscription/
├── subscription-server/     # 구독 관리 서비스
│   ├── src/                # 소스 코드
│   ├── build.gradle        # Gradle 빌드 설정
│   ├── Dockerfile          # Docker 이미지 설정
│   └── docker-compose.yaml # Docker Compose 설정
│
└── payment-server/         # 결제 처리 서비스
    ├── src/                # 소스 코드
    ├── build.gradle        # Gradle 빌드 설정
    ├── Dockerfile          # Docker 이미지 설정
    ├── docker-compose.yaml # Docker Compose 설정
    └── kafka/              # 카프카 관련 설정
```

## 설치 및 실행 방법

### 사전 요구사항
- Java 11 이상
- Docker
- Docker Compose
- Gradle

### 실행 방법
1. 저장소 클론
```bash
git clone [repository-url]
cd core-subscription
```

2. 환경 변수 설정
```bash
# subscription-server/.env 파일 수정
# payment-server/.env 파일 수정
```

3. 서비스 실행
```bash
# subscription-server 실행
cd subscription-server
./gradlew bootRun

# payment-server 실행
cd payment-server
./gradlew bootRun
```

### Docker를 통한 실행
```bash
# subscription-server
cd subscription-server
docker-compose up -d

# payment-server
cd payment-server
docker-compose up -d
```

## API 문서
각 서비스별 API 문서는 다음 경로에서 확인할 수 있습니다:
- Subscription Server: `http://localhost:8080/swagger-ui.html`
- Payment Server: `http://localhost:8081/swagger-ui.html`

## 개발 가이드
1. 코드 스타일 가이드 준수
2. 테스트 코드 작성 필수
3. PR 전 코드 리뷰 진행

## 라이선스
이 프로젝트는 [라이선스 이름] 라이선스를 따릅니다.

## 연락처
프로젝트 관련 문의사항은 [이메일 주소]로 연락주세요. 
