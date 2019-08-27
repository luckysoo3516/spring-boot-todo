FROM openjdk:11

WORKDIR /home/myapp

COPY . /home/myapp

RUN ./gradlew assemble

CMD ./gradlew bootRun
