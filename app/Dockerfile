# Stage 1: build the project via maven
FROM maven:latest as packageSourceCode

# RUN mkdir /app  

COPY /. /app

WORKDIR /app

RUN mvn clean install

# Stage 2: spin up a tomcat container
FROM tomcat:9.0-alpine
COPY --from=packageSourceCode /app/target/assignment.war /usr/local/tomcat/webapps/
# the port which the tomcat is listening inside the container
EXPOSE 8080
# run the tomcat
CMD ["catalina.sh", "run"]