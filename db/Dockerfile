# Stage 1: download the code from github
FROM alpine/git as sourceCode

WORKDIR /app

RUN git clone https://github.com/luffy92/assignment


# Stage 2: Set up a mysql image
FROM mysql

# Add a database
ENV MYSQL_DATABASE assignment

# Set root password
ENV MYSQL_ROOT_PASSWORD supersecret 

# Add the content of the sql/ directory to image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
COPY --from=sourceCode /app/assignment/db/sql/ /docker-entrypoint-initdb.d/

EXPOSE 3306