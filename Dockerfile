FROM isuper/java-oracle:jdk_latest

# Install wget (for Maven install)
RUN apt-get update && apt-get install -y wget

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

ENV MAVEN_VERSION=3.5.0

# Install maven
RUN wget http://mirror.vorboss.net/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz && tar zxf apache-maven-${MAVEN_VERSION}-bin.tar.gz

# Compile and package all projects
RUN MVN_HOME=/app/apache-maven-${MAVEN_VERSION} /app/apache-maven-${MAVEN_VERSION}/bin/mvn package -DskipTests
