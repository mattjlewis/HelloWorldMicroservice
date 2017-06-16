FROM mysql
LABEL maintainer "Matthew Lewis"

ENV MYSQL_DATABASE=hwms \
	MYSQL_ROOT_PASSWORD=helloworld

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306
