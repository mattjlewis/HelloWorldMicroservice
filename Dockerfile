FROM isuper/java-oracle:jdk_latest

COPY pom.xml /app/pom.xml

# Install wget (for Maven install)
RUN apt-get update && apt-get install -y wget

# Install some basic network tools to assist with debugging only
RUN apt-get update && apt-get install -y net-tools iproute iputils-ping dnsutils

RUN groupadd -r hwms && useradd -r -m -g hwms hwms

WORKDIR /app

# Prepare by downloading dependencies
COPY pom.xml /app/pom.xml
COPY HelloWorldMicroserviceCommon/pom.xml /app/HelloWorldMicroserviceCommon/pom.xml
COPY HelloWorldMicroserviceCommon/src /app/HelloWorldMicroserviceCommon/src
COPY HelloWorldMicroserviceService/pom.xml /app/HelloWorldMicroserviceService/pom.xml
COPY HelloWorldMicroserviceService/src /app/HelloWorldMicroserviceService/src
COPY HelloWorldMicroserviceWeb/pom.xml /app/HelloWorldMicroserviceWeb/pom.xml
COPY HelloWorldMicroserviceWeb/src /app/HelloWorldMicroserviceWeb/src

RUN chown -R hwms. /app

USER hwms

# Install maven
RUN wget http://mirror.vorboss.net/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz && tar zxf apache-maven-3.5.0-bin.tar.gz

# Compile and package all projects
RUN MVN_HOME=/app/apache-maven-3.5.0 /app/apache-maven-3.5.0/bin/mvn package
