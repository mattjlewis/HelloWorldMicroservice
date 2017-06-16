FROM hwms
LABEL maintainer "Matthew Lewis"

EXPOSE 8030

USER hwms

# Direct, with log file in the container
#CMD java -DHWMS_REST_HOSTNAME=hwmsrest1 -jar /app/HelloWorldMicroserviceWeb/target/HelloWorldMicroserviceWeb-jar-with-dependencies.jar > hwms_web.out 2>&1
# Load balanced, use "docker logs" to access logs
CMD ["java", "-DSERVER_PORT=8030", "-DHWMS_REST_HOSTNAME=hwmslb", "-DHWMS_REST_PORT=80", "-jar", "/app/HelloWorldMicroserviceSpringBootWeb/target/hwms-sbweb-0.0.1-SNAPSHOT.jar"]
