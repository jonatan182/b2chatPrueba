#Auto Jonatan Velandia
#Imagen Alpine, La mas liviana
FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/B2ChatOrdenCompra-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} B2ChatOrdenCompra.jar
ENTRYPOINT ["java","-jar","/B2ChatOrdenCompra.jar"]