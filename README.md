# Ping Server

A very simple ping server developed using Spring Boot.

## Pre-requisites

- Java 17+
- Gradle
- Your favorite IDE

## Build

from the root folder of the project, execute

```console
gradle clean build
```

or

```console
./gradlew clean build
```

## Run

```console
gradle bootRun
```

or

```console
./gradlew bootRun
```

## Containerization

The project also contains a [Dockerfile](Dockerfile) which can be used to build an image

```console
docker build --rm --tag pingserver:1.0.3 ./
```

and run it

```console
docker run --rm --name ping-server --publish 8095:8095 pingserver:1.0.3
```

## Kubernetes

There's a Kubernetes [manifest](pingserver.yaml) as well

```console
kubectl apply -f ./pingserver.yaml
```

## Usage

A call to `localhost:8095/pingserver/v1/ping` from your favorite REST client should result in `PONG!`