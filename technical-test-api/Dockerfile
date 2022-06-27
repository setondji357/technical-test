FROM openjdk:11-jdk-slim

# time zone
ENV TZ=Africa/Lome
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# update sources
RUN apt-get update

# install some packages
RUN apt-get install -y curl

# run under a user
RUN groupadd tenanciagroup
RUN useradd -G tenanciagroup tenanciauser
USER tenanciauser:tenanciagroup

# run app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

#ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=file:/application.yml"]
ENTRYPOINT ["java","-jar","/app.jar"]
