FROM hwms

EXPOSE 8020

USER hwms

CMD java -DHWMS_REST_HOSTNAME=hwmsrest1 -jar /app/HelloWorldMicroserviceWeb/target/HelloWorldMicroserviceWeb-jar-with-dependencies.jar > hwms_web.out 2>&1
