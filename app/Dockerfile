# Stage 1: download the code from github
FROM alpine/git as sourceCode

WORKDIR /app

RUN git clone https://github.com/luffy92/assignment


# Stage 2: build the project via maven
FROM maven:latest as packageSourceCode

WORKDIR /app

COPY --from=sourceCode /app/assignment/app/ /app/

RUN mvn clean install

# Stage 3: spin up a tomcat container
FROM tomcat:9.0-alpine

RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY --from=packageSourceCode /app/target/assignment.war /usr/local/tomcat/webapps/ROOT.war

# the port which the tomcat is listening inside the container
EXPOSE 8080

# run the tomcat
CMD ["catalina.sh", "run"]