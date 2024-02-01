FROM gradle:jdk21 as builder

WORKDIR /project

COPY . /project

RUN gradle clean build -x test

FROM azul/zulu-openjdk-alpine:21-jre-headless as layer

WORKDIR /application

COPY --from=builder /project/build/libs/*.jar application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM azul/zulu-openjdk-alpine:21-jre-headless

WORKDIR /application

COPY --from=layer /application/dependencies/ ./
COPY --from=layer /application/spring-boot-loader/ ./
COPY --from=layer /application/snapshot-dependencies/ ./
COPY --from=layer /application/application/ ./

RUN apk add --no-cache tini

RUN addgroup --system psgrp
RUN adduser --system --shell /bin/sh -G psgrp psusr
RUN chown -R psusr:psgrp /application

USER psusr

ENTRYPOINT ["/sbin/tini", "--", "java", "org.springframework.boot.loader.JarLauncher"]
