FROM mysql:5.7.22

ADD my.cnf /etc/mysql/my.cnf
ADD mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
ADD setup.sh /opt/setup.sh

ADD sql/1.sql /docker-entrypoint-initdb.d/1.sql

EXPOSE 3322
CMD ["/bin/bash", "/opt/setup.sh"]
