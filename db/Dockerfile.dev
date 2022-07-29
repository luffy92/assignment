FROM mysql
# Add a database
ENV MYSQL_DATABASE assignment

# set root password
ENV MYSQL_ROOT_PASSWORD supersecret 

# Add the content of the sql/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
COPY ./sql/ /docker-entrypoint-initdb.d/

EXPOSE 3306