FROM gradle:jdk17 as builder

WORKDIR /project

COPY . /project

RUN gradle clean build -x test

FROM azul/zulu-openjdk-alpine:17-jre-headless as layer

WORKDIR /application

COPY --from=builder /project/build/libs/*.jar application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM azul/zulu-openjdk-alpine:17-jre-headless

WORKDIR /application

COPY --from=layer /application/dependencies/ ./
COPY --from=layer /application/spring-boot-loader/ ./
COPY --from=layer /application/snapshot-dependencies/ ./
COPY --from=layer /application/application/ ./

RUN addgroup --system pingservergroup
RUN adduser --system --shell /bin/sh -G pingservergroup pingserveruser
RUN chown -R pingserveruser:pingservergroup /application

USER pingserveruser

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
