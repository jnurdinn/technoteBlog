# technote-blog
Blogging web app built using Spring Boot. <br>
This is my attempt for studying web app development using various types of Spring functionalities. <br>
I started this app based heavily on [Wazoo Web Byte's Spring blogging application](https://www.youtube.com/watch?v=7iWlCl35p9o), and then extended the functionalities until I can consider enough to host a capable blogging app.

In general, this app is built from the following:
1. Architecture : Spring Model View Controller (MVC)
2. Database : Spring JPA & H2 embedded database
3. Frontend + Design : Thymeleaf + SB Admin 2 Bootstrap
4. Security : Spring Security

See the web live on : [https://tech.katsu.icu/](https://tech.katsu.icu/)

<img src="https://tech.katsu.icu/img/technote-logo.png" height="50px">

## How to install
1. Clone project to your target environment.
```
git clone https://github.com/jnurdinn/technoteBlog/
```
2. Change `technoteBlog/src/main/resources/application.properties` by uncomment the following to fit your environment.
```
...
# server config
security.basic.enabled=false
# server.port=443
# security.require-ssl=true
# server.ssl.key-store=<your-password>
# server.ssl.key-store-password=<your-password>
# server.ssl.keyStoreType=PKCS12
# server.ssl.keyAlias=tomcat
...
```
3. Run application by either run the development env:
```
# mvn spring-boot:run
```
Or build jar package & then run from there:
```
$ mvn clean package spring-boot:repackage
$ sudo java -jar /target/techNotes-0.0.1-SNAPSHOT-exec.jar
```

## Source & Libraries :
1. [Wazoo Web Byte's Spring blogging application](https://www.youtube.com/watch?v=7iWlCl35p9o)
2. [Simple MDE](https://simplemde.com/)
3. [SB Admin 2 Bootstrap theme](https://startbootstrap.com/theme/sb-admin-2)
4. [Spring Boot Search Example](https://codebun.com/spring-boot-search-example-using-thymeleaf-and-spring-data-jpa/)
5. [Spring Boot Image Upload](https://www.bezkoder.com/spring-boot-image-upload-thymeleaf/)
6. [Running a Spring Boot App with Maven vs an Executable War/Jar](https://www.baeldung.com/spring-boot-run-maven-vs-executable-jar)
