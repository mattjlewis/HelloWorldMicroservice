FROM hwms

EXPOSE 8020

USER hwms

# Direct, with log file in the container
#CMD java -DHWMS_REST_HOSTNAME=hwmsrest1 -jar /app/HelloWorldMicroserviceWeb/target/HelloWorldMicroserviceWeb-jar-with-dependencies.jar > hwms_web.out 2>&1
# Load balanced, use "docker logs" to access logs
CMD ["java", "-DHWMS_REST_HOSTNAME=hwmslb", "-DHWMS_REST_PORT=80", "-jar", "/app/HelloWorldMicroserviceWeb/target/HelloWorldMicroserviceWeb-jar-with-dependencies.jar"]
