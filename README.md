# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.cainz.pac-batch-ec2' is invalid and this project uses 'com.cainz.pacbatchec2' instead.

# Getting Started
How to create sample Batch Service:

* [Official Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.6/maven-plugin/reference/html/#build-image)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.7.6/reference/htmlsingle/#howto.batch)

### Guides
The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Using logback.xml](https://blog.csdn.net/qq_25851237/article/details/121263371)

### Use maven to get Jar file
```shell
mvn clean install -DskipTests
mvn clean install -Dmaven.test.skip=true
ls .\target\pac-batch-ec2-0.1.jar 
```

### Excute CMD

#### Without parameter
```shell
java -jar .\target\pac-batch-ec2-0.1.jar 
```

#### With parameter, Add *-Dxxxx=xxxx* , Use System.getProperty("") in the main() to get the parameter
```shell
java -Dtype=pac-batch-get-s3-all -Dxxxx=xxxx -jar .\target\pac-batch-ec2-0.1.jar 
```


