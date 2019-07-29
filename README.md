# spring-cloud-microservice

> Spring Boot Microservices and Spring Cloud learning project

Architecture for this project will consists of
  - API Gateway (Spring Cloud Netflix - Zuul, Ribbon)
    Serve as API gateway, Load Balancer and perform Authentication on the incoming request to the microservices
  - Discovery Service (Spring Cloud Netflix - Eureka)
   Holds the information about all client-service applications. Every Micro service will register into the Eureka discovery server and Eureka discovery server knows all the client applications running on each port and IP address.
  - Config Server
    Central place to manage external properties for applications across all environments. 
  - Users Microservice
  - Account Management Microservice
  - Album Microservice

#### Reference
- Spring Cloud Config - https://spring.io/projects/spring-cloud-config
- Spring Cloud Netflix - https://spring.io/projects/spring-cloud-netflix
- Java Cryptography Extension (JCE) - https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
Generate keypair for Asymmetric Encryption
keytool -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=Indra Panjaito,OU=API Development,O=indraphan.com,L=Singapore,S=SG,C=SG" -keypass 1q2w3e4r -keystore apiEncryptionKey.jks -storepass 1q2w3e4r
keytool -importkeystore -srckeystore apiEncryptionKey.jks -destkeystore apiEncryptionKey.jks -deststoretype pkcs12
- Spring Cloud Sleuth - https://spring.io/projects/spring-cloud-sleuth
- Zipkin - https://zipkin.io/
- Postman - https://www.getpostman.com/downloads/
- MySQL Server - https://dev.mysql.com/downloads/mysql