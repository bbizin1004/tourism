# 1. 기본 이미지 설정 (JDK 17 사용)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. JAR 파일 복사
COPY ./build/libs/tourism-0.0.1-SNAPSHOT.jar app.jar

# 4. 환경 변수 설정
ENV SPRING_PROFILES_ACTIVE=dev

# 5. 컨테이너 실행 시 JAR 파일 실행
CMD ["java", "-jar", "app.jar"]