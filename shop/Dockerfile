# Docker 이미지 파일 생성 및 빌드 방법
# 참고 URL - https://alphalok.tistory.com/entry/%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4IntelliJ-%EC%8A%A4%ED%94%84%EB%A7%81Spring-boot-%EB%8F%84%EC%BB%A4Docker-%EC%97%B0%EB%8F%99-%EC%B5%9C%EC%A2%85-%EA%B3%B5%EC%8B%9D%EB%AC%B8%EC%84%9C%EA%B8%B0%EC%A4%80-%EC%95%84%EA%B3%BC%EB%85%B8%ED%8A%B8
# 프로젝트 폴더에 Dockerfile 만들고 이런거 작성하는게 끝입니다.
# - Java 21버전으로 설치했는데 여러분이 쓰던 버전으로 설치하면 되겠습니다.
# - 아마 .jar 파일은 /build/libs 폴더에 생성되어있을 것입니다.
#
# 근데 용량을 더 줄이고 싶으면 이런 편법을 써도 되는데
# 실은 생성된 .jar 파일만 있으면 서버를 돌릴 수 있기 때문에 다른 소스코드나 그런건 전혀 필요없습니다.
# 그래서 .jar 파일 하나만 담은 이미지를 생성해서 그것만 실행하라고 Dockerfile을 작성하면
# 이미지 용량을 훨씬 작게 만들 수 있습니다.
#
# 1. 터미널에 ./gradlew build를 입력해서 .jar파일을 만들기
# 2. 새로운 이미지를 만들어서 그 .jar 파일을 새로운 이미지로 옮기기
# 3. 명령어로 .jar 파일을 실행하기
# 이렇게 작성하면 되는 것임

# shop 폴더 -> jar 파일(shop-0.0.1-SNAPSHOT.jar) 파일 경로
# "D:\minjae\JavaSpringBootStudy\shop\build\libs" 폴더 -> shop-0.0.1-SNAPSHOT.jar

# Dockerfile 작성 예시 1
# FROM amazoncorretto:21.0.4
# WORKDIR /app
# COPY . .
# RUN ./gradlew build
# CMD ["java", "-jar", ".jar파일경로"]

# Dockerfile 작성 예시 2
# 실은 Dockerfile에 FROM을 2번 이상 작성할 수 있는데
# FROM을 만날 때 마다 위에 있는 작업내역들이 삭제되고 새로운 마음으로 깨끗하게 시작됩니다.
# 근데 깨끗하게 시작할 때 위의 작업내역에서 만든 파일들을 몰래 훔쳐올 수 있습니다.
# 이게 비결임
#
# 첫째 FROM에선 /app 폴더에서 .jar 파일(shop-0.0.1-SNAPSHOT.jar)만 만들어줍니다.
# 두번째 FROM에선 이전 FROM에서 나온 .jar 파일(shop-0.0.1-SNAPSHOT.jar)을 /app/server.jar 경로로 훔쳐오라고 했습니다.
# --from=build 이러면 build라고 이름지은 곳에 있던 파일을 카피하라는 뜻입니다.
# (AS 명령어 쓰면 FROM마다 이름을 마음대로 붙일 수 있습니다.)
# 그 다음에 마지막에 .jar 파일(shop-0.0.1-SNAPSHOT.jar)을 실행하는겁니다.
#
# 그럼 이제 최종 이미지에는 .jar 파일, 리눅스OS, 자바21 JDK 이 정도만 들어있어서 좀 가벼워졌겠군요.
# FROM 여러번 쓰는 짓거리를 multi-stage build 라고 하는데
# 그래서 빌드과정이 필요한 프로젝트들은 이런 식으로 작성해서 용량을 줄이고 보안도 약간 챙길 수 있습니다.

FROM amazoncorretto:21.0.4 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# Runtime stage
FROM amazoncorretto:21.0.4 AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/server.jar
CMD ["java", "-jar", "/app/server.jar"]