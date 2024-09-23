#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/spring_security_login-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app_spring_security_login.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar", "app_spring_security_login.jar"]

# el fron basicamente toma el openjdk de java (defino la imagen base) y le agrega un slim para que sea mas pequeño
# el JAR_FILE sera un argumento en la le digo "donde" se va a gaurdar el proyecto y con que nombre tendra
 # este ejecutable. en target/ nombreEjecutable.jar
# copio ese jar_file y lo llamo app_spring_security_login.jar
# expose , es para exponer el puerto8080 que se usara en el dockerfile para los get, post, put, delete, etc

# un archivo docker ifle contiene instrucciones para construir una imagen de docker
# el dockerfile tiene 3 etapas

# Etapa de construcción
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /app
COPY . .
RUN mvn clean package assembly:single -DskipTests

# Etapa final
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/loggeospring-0.0.1-SNAPSHOT-jar-with-dependencies.jar loggeospring-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-classpath","loggeospring-0.0.1-SNAPSHOT.jar","com.example.loggeospring.Main"]
