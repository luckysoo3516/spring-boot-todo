# Todo App

[![Build Status](https://travis-ci.org/luckysoo3516/spring-boot-todo.svg?branch=master)](https://travis-ci.org/luckysoo3516/spring-boot-todo)

## Build Docker image

```bash
docker build -t todo .
```

## Run Docker Container

```bash
docker run -d --name todo-api \
    -p 80:8080 \
    todo

docker logs -f todo-api
```

## Kill Docker Container

```bash
docker stop todo-api
docker rm todo-api
```
