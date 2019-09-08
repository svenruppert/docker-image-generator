# Docker Image Generator for JDK and Maven usage
This project includes a generator to build all needed scripts to create 
JDK and JDK/Maven Docker images. 

## Supported Versions and vendors
### Vendors
* OpenJDK
* Oracle
* Oracle - GraalVM
* Amazon
* IBM
* Adopt-OpenJ9
* Zulu
* Liberica

### JDK Versions (not from all vendors)
* JDK 6
* JDK 7
* JDK 8
* JDK 9
* JDK 10
* JDK 11
* JDK 12
* JDK 13
* JDK 14

### Maven Versions
* 3.6.1
* 3.6.0
* 3.5.4
* 3.5.3
* 3.5.2
* 3.5.1
* 3.5.0
* 3.3.9
* 3.3.3
* 3.2.5


## Why basic Docker images again? 
Different persons or organizations maintain the available "original" Docker images.
This situation leads to small differences, and this was leading to different behaviour during the usage of these images at runtime. Instead of hunting bugs, I decided to create a clean and straightforward solution. All Docker images are using the open source solution called 
[jabber](https://github.com/shyiko/jabba) to install the JDK.
A set of Docker images that are built precisely, in the same way, will help you to test your app against a matrix of JDK versions 
without handling the tiny details that are stealing your time.

## Why do we need so many Docker images?
With the new release cycle that will bring us a new JDK every few months, we must decide how to deal with this in a project or product.
The generated Docker images are helping you to test your application against different JDK versions and vendors. All images are created and pushed to the Docker HUB already. 
If you need a custom image, you can either use the provided images as a base or create your custom ones.

## How to use
In the following examples, I assume that maven organizes the project.
With gradle, it should be the same, but maybe different paths.

If you need a JDK only
```bash
docker run \
       --rm \
       --name run \
       -v "$(pwd)":/usr/src/mymaven \
       -w /usr/src/mymaven \
       svenruppert/adopt:1.12.0-1 \
       java -jar target/myapp.jar
```

If you need maven and a JDK
```bash
docker run \
       --rm \
       --name compile \
       -v "$(pwd)":/usr/src/mymaven \
       -w /usr/src/mymaven \
       svenruppert/maven-3.6.1-adopt:1.12.0-1 \
       mvn clean install -Dmaven.test.skip=true
```

## How to generate
If you want to generate custom Dockerfiles by yourself, 
it would help if you edited the class ```Generator```.
Inside this class, you will find the templates as well. 
This implementation is not the super duper clean application, 
however, it works.
Feel free to help me and sent a PR. ;-)

The generated files, including the scripts 
for creating and pushing the images, 
are available under the directory *_data*.

All images are available 
in the official docker registry.
Have a look at [https://cloud.docker.com/u/svenruppert/](https://cloud.docker.com/u/svenruppert/)

## Pure JDK Docker Images (08.09.2019)
* svenruppert/adopt-openj9:1.10.0-2
* svenruppert/adopt-openj9:1.11.0-1
* svenruppert/adopt-openj9:1.11.0-2
* svenruppert/adopt-openj9:1.11.0-3
* svenruppert/adopt-openj9:1.11.0-4
* svenruppert/adopt-openj9:1.11.28-0
* svenruppert/adopt-openj9:1.12.0-1
* svenruppert/adopt-openj9:1.12.0-2
* svenruppert/adopt-openj9:1.12.33-0
* svenruppert/adopt-openj9:1.8.162-12
* svenruppert/adopt-openj9:1.8.181-13
* svenruppert/adopt-openj9:1.8.192-12
* svenruppert/adopt-openj9:1.8.202-08
* svenruppert/adopt-openj9:1.8.212-03
* svenruppert/adopt-openj9:1.8.212-04
* svenruppert/adopt-openj9:1.8.222-10
* svenruppert/adopt-openj9:1.9.0-4
* svenruppert/adopt:1.10.0-2
* svenruppert/adopt:1.11.0-1
* svenruppert/adopt:1.11.0-2
* svenruppert/adopt:1.11.0-3
* svenruppert/adopt:1.11.0-4
* svenruppert/adopt:1.11.28-0
* svenruppert/adopt:1.12.0-1
* svenruppert/adopt:1.12.0-2
* svenruppert/adopt:1.12.33-0
* svenruppert/adopt:1.8.172-11
* svenruppert/adopt:1.8.181-13
* svenruppert/adopt:1.8.192-12
* svenruppert/adopt:1.8.202-08
* svenruppert/adopt:1.8.212-03
* svenruppert/adopt:1.8.212-04
* svenruppert/adopt:1.8.222-10
* svenruppert/adopt:1.9.0-4
* svenruppert/adopt:1.9.181-0
* svenruppert/amazon-corretto:1.11.0-4.11.1
* svenruppert/amazon-corretto:1.8.222-10.1
* svenruppert/graalvm:1.0.0-1
* svenruppert/graalvm:1.0.0-10
* svenruppert/graalvm:1.0.0-11
* svenruppert/graalvm:1.0.0-12
* svenruppert/graalvm:1.0.0-13
* svenruppert/graalvm:1.0.0-14
* svenruppert/graalvm:1.0.0-15
* svenruppert/graalvm:1.0.0-16
* svenruppert/graalvm:1.0.0-2
* svenruppert/graalvm:1.0.0-3
* svenruppert/graalvm:1.0.0-4
* svenruppert/graalvm:1.0.0-5
* svenruppert/graalvm:1.0.0-6
* svenruppert/graalvm:1.0.0-7
* svenruppert/graalvm:1.0.0-8
* svenruppert/graalvm:1.0.0-9
* svenruppert/graalvm:19.0.0
* svenruppert/graalvm:19.0.2
* svenruppert/graalvm:19.1.0
* svenruppert/graalvm:19.1.1
* svenruppert/graalvm:19.2.0
* svenruppert/graalvm:19.2.0-1
* svenruppert/ibm:1.7.0
* svenruppert/ibm:1.7.0-10.40
* svenruppert/ibm:1.7.0-10.45
* svenruppert/ibm:1.7.0-10.50
* svenruppert/ibm:1.7.1
* svenruppert/ibm:1.7.1-4.40
* svenruppert/ibm:1.7.1-4.45
* svenruppert/ibm:1.7.1-4.50
* svenruppert/ibm:1.8.0
* svenruppert/ibm:1.8.0-5.30
* svenruppert/ibm:1.8.0-5.31
* svenruppert/ibm:1.8.0-5.35
* svenruppert/ibm:1.8.0-5.36
* svenruppert/ibm:1.8.0-5.37
* svenruppert/ibm:1.8.0-5.40
* svenruppert/ibm:1.8.0-5.6
* svenruppert/ibm:1.8.0-5.7
* svenruppert/liberica:1.10.0
* svenruppert/liberica:1.10.0-2
* svenruppert/liberica:1.11.0
* svenruppert/liberica:1.11.0-0
* svenruppert/liberica:1.11.0-1
* svenruppert/liberica:1.11.0-2
* svenruppert/liberica:1.11.0-3
* svenruppert/liberica:1.11.0-4
* svenruppert/liberica:1.12.0
* svenruppert/liberica:1.12.0-0
* svenruppert/liberica:1.12.0-1
* svenruppert/liberica:1.12.0-2
* svenruppert/liberica:1.8.192
* svenruppert/liberica:1.8.202
* svenruppert/liberica:1.8.212
* svenruppert/liberica:1.8.222
* svenruppert/openjdk-ri:1.10.0
* svenruppert/openjdk-ri:1.10.0-0
* svenruppert/openjdk-ri:1.11.0
* svenruppert/openjdk-ri:1.11.0-0
* svenruppert/openjdk-ri:1.12.0
* svenruppert/openjdk-ri:1.12.0-0
* svenruppert/openjdk-ri:1.7.75
* svenruppert/openjdk-ri:1.8.40
* svenruppert/openjdk-ri:1.9.0
* svenruppert/openjdk-ri:1.9.0-0
* svenruppert/openjdk-shenandoah:1.11.0
* svenruppert/openjdk-shenandoah:1.8.0
* svenruppert/openjdk:1.10.0
* svenruppert/openjdk:1.10.0-1
* svenruppert/openjdk:1.10.0-2
* svenruppert/openjdk:1.11.0
* svenruppert/openjdk:1.11.0-1
* svenruppert/openjdk:1.11.0-2
* svenruppert/openjdk:1.12.0
* svenruppert/openjdk:1.12.0-2
* svenruppert/openjdk:1.13.0
* svenruppert/openjdk:1.14.0
* svenruppert/openjdk:1.9.0
* svenruppert/openjdk:1.9.0-1
* svenruppert/openjdk:1.9.0-4
* svenruppert/oracle-jdk:1.12.0
* svenruppert/oracle-jdk:1.12.0-2
* svenruppert/zulu:1.10.0
* svenruppert/zulu:1.10.0-1
* svenruppert/zulu:1.10.0-2
* svenruppert/zulu:1.11.0
* svenruppert/zulu:1.11.0-1
* svenruppert/zulu:1.11.0-2
* svenruppert/zulu:1.11.0-3
* svenruppert/zulu:1.12.0
* svenruppert/zulu:1.12.0-1
* svenruppert/zulu:1.6.103
* svenruppert/zulu:1.6.107
* svenruppert/zulu:1.6.113
* svenruppert/zulu:1.6.119
* svenruppert/zulu:1.6.77
* svenruppert/zulu:1.6.79
* svenruppert/zulu:1.6.83
* svenruppert/zulu:1.6.87
* svenruppert/zulu:1.6.89
* svenruppert/zulu:1.6.93
* svenruppert/zulu:1.6.97
* svenruppert/zulu:1.6.99
* svenruppert/zulu:1.7.101
* svenruppert/zulu:1.7.111
* svenruppert/zulu:1.7.121
* svenruppert/zulu:1.7.131
* svenruppert/zulu:1.7.141
* svenruppert/zulu:1.7.154
* svenruppert/zulu:1.7.161
* svenruppert/zulu:1.7.171
* svenruppert/zulu:1.7.181
* svenruppert/zulu:1.7.191
* svenruppert/zulu:1.7.201
* svenruppert/zulu:1.7.211
* svenruppert/zulu:1.7.222
* svenruppert/zulu:1.7.95
* svenruppert/zulu:1.8.102
* svenruppert/zulu:1.8.112
* svenruppert/zulu:1.8.121
* svenruppert/zulu:1.8.131
* svenruppert/zulu:1.8.144
* svenruppert/zulu:1.8.152
* svenruppert/zulu:1.8.162
* svenruppert/zulu:1.8.163
* svenruppert/zulu:1.8.172
* svenruppert/zulu:1.8.181
* svenruppert/zulu:1.8.192
* svenruppert/zulu:1.8.201
* svenruppert/zulu:1.8.202
* svenruppert/zulu:1.8.212
* svenruppert/zulu:1.8.71
* svenruppert/zulu:1.8.72
* svenruppert/zulu:1.8.91
* svenruppert/zulu:1.8.92
* svenruppert/zulu:1.9.0
* svenruppert/zulu:1.9.0-0
* svenruppert/zulu:1.9.0-1
* svenruppert/zulu:1.9.0-4
* svenruppert/zulu:1.9.0-7

## Maven JDK Docker Images (08.09.2019)

* svenruppert/maven-3.6.1-ibm 1.8.0-5.40
* svenruppert/maven-3.6.2-ibm 1.8.0-5.40
* svenruppert/maven-3.0.5-ibm 1.8.0-5.6
* svenruppert/maven-3.1.1-ibm 1.8.0-5.6
* svenruppert/maven-3.2.5-ibm 1.8.0-5.6
* svenruppert/maven-3.3.9-ibm 1.8.0-5.6
* svenruppert/maven-3.5.4-ibm 1.8.0-5.6
* svenruppert/maven-3.6.0-ibm 1.8.0-5.6
* svenruppert/maven-3.6.1-ibm 1.8.0-5.6
* svenruppert/maven-3.6.2-ibm 1.8.0-5.6
* svenruppert/maven-3.0.5-ibm 1.8.0-5.7
* svenruppert/maven-3.1.1-ibm 1.8.0-5.7
* svenruppert/maven-3.2.5-ibm 1.8.0-5.7
* svenruppert/maven-3.3.9-ibm 1.8.0-5.7
* svenruppert/maven-3.5.4-ibm 1.8.0-5.7
* svenruppert/maven-3.6.0-ibm 1.8.0-5.7
* svenruppert/maven-3.6.1-ibm 1.8.0-5.7
* svenruppert/maven-3.6.2-ibm 1.8.0-5.7
* svenruppert/maven-3.0.5-liberica 1.10.0-2
* svenruppert/maven-3.1.1-liberica 1.10.0-2
* svenruppert/maven-3.2.5-liberica 1.10.0-2
* svenruppert/maven-3.3.9-liberica 1.10.0-2
* svenruppert/maven-3.5.4-liberica 1.10.0-2
* svenruppert/maven-3.6.0-liberica 1.10.0-2
* svenruppert/maven-3.6.1-liberica 1.10.0-2
* svenruppert/maven-3.6.2-liberica 1.10.0-2
* svenruppert/maven-3.0.5-liberica 1.10.0
* svenruppert/maven-3.1.1-liberica 1.10.0
* svenruppert/maven-3.2.5-liberica 1.10.0
* svenruppert/maven-3.3.9-liberica 1.10.0
* svenruppert/maven-3.5.4-liberica 1.10.0
* svenruppert/maven-3.6.0-liberica 1.10.0
* svenruppert/maven-3.6.1-liberica 1.10.0
* svenruppert/maven-3.6.2-liberica 1.10.0
* svenruppert/maven-3.0.5-liberica 1.11.0-0
* svenruppert/maven-3.1.1-liberica 1.11.0-0
* svenruppert/maven-3.2.5-liberica 1.11.0-0
* svenruppert/maven-3.3.9-liberica 1.11.0-0
* svenruppert/maven-3.5.4-liberica 1.11.0-0
* svenruppert/maven-3.6.0-liberica 1.11.0-0
* svenruppert/maven-3.6.1-liberica 1.11.0-0
* svenruppert/maven-3.6.2-liberica 1.11.0-0
* svenruppert/maven-3.0.5-liberica 1.11.0-1
* svenruppert/maven-3.1.1-liberica 1.11.0-1
* svenruppert/maven-3.2.5-liberica 1.11.0-1
* svenruppert/maven-3.3.9-liberica 1.11.0-1
* svenruppert/maven-3.5.4-liberica 1.11.0-1
* svenruppert/maven-3.6.0-liberica 1.11.0-1
* svenruppert/maven-3.6.1-liberica 1.11.0-1
* svenruppert/maven-3.6.2-liberica 1.11.0-1
* svenruppert/maven-3.0.5-liberica 1.11.0-2
* svenruppert/maven-3.1.1-liberica 1.11.0-2
* svenruppert/maven-3.2.5-liberica 1.11.0-2
* svenruppert/maven-3.3.9-liberica 1.11.0-2
* svenruppert/maven-3.5.4-liberica 1.11.0-2
* svenruppert/maven-3.6.0-liberica 1.11.0-2
* svenruppert/maven-3.6.1-liberica 1.11.0-2
* svenruppert/maven-3.6.2-liberica 1.11.0-2
* svenruppert/maven-3.0.5-liberica 1.11.0-3
* svenruppert/maven-3.1.1-liberica 1.11.0-3
* svenruppert/maven-3.2.5-liberica 1.11.0-3
* svenruppert/maven-3.3.9-liberica 1.11.0-3
* svenruppert/maven-3.5.4-liberica 1.11.0-3
* svenruppert/maven-3.6.0-liberica 1.11.0-3
* svenruppert/maven-3.6.1-liberica 1.11.0-3
* svenruppert/maven-3.6.2-liberica 1.11.0-3
* svenruppert/maven-3.0.5-liberica 1.11.0
* svenruppert/maven-3.1.1-liberica 1.11.0
* svenruppert/maven-3.2.5-liberica 1.11.0
* svenruppert/maven-3.3.9-liberica 1.11.0
* svenruppert/maven-3.5.4-liberica 1.11.0
* svenruppert/maven-3.6.0-liberica 1.11.0
* svenruppert/maven-3.6.1-liberica 1.11.0
* svenruppert/maven-3.6.2-liberica 1.11.0
* svenruppert/maven-3.0.5-liberica 1.11.0-4
* svenruppert/maven-3.1.1-liberica 1.11.0-4
* svenruppert/maven-3.2.5-liberica 1.11.0-4
* svenruppert/maven-3.3.9-liberica 1.11.0-4
* svenruppert/maven-3.5.4-liberica 1.11.0-4
* svenruppert/maven-3.6.0-liberica 1.11.0-4
* svenruppert/maven-3.6.1-liberica 1.11.0-4
* svenruppert/maven-3.6.2-liberica 1.11.0-4
* svenruppert/maven-3.0.5-liberica 1.12.0-0
* svenruppert/maven-3.1.1-liberica 1.12.0-0
* svenruppert/maven-3.2.5-liberica 1.12.0-0
* svenruppert/maven-3.3.9-liberica 1.12.0-0
* svenruppert/maven-3.5.4-liberica 1.12.0-0
* svenruppert/maven-3.6.0-liberica 1.12.0-0
* svenruppert/maven-3.6.1-liberica 1.12.0-0
* svenruppert/maven-3.6.2-liberica 1.12.0-0
* svenruppert/maven-3.0.5-liberica 1.12.0-1
* svenruppert/maven-3.1.1-liberica 1.12.0-1
* svenruppert/maven-3.2.5-liberica 1.12.0-1
* svenruppert/maven-3.3.9-liberica 1.12.0-1
* svenruppert/maven-3.5.4-liberica 1.12.0-1
* svenruppert/maven-3.6.0-liberica 1.12.0-1
* svenruppert/maven-3.6.1-liberica 1.12.0-1
* svenruppert/maven-3.6.2-liberica 1.12.0-1
* svenruppert/maven-3.0.5-liberica 1.12.0-2
* svenruppert/maven-3.1.1-liberica 1.12.0-2
* svenruppert/maven-3.2.5-liberica 1.12.0-2
* svenruppert/maven-3.3.9-liberica 1.12.0-2
* svenruppert/maven-3.5.4-liberica 1.12.0-2
* svenruppert/maven-3.6.0-liberica 1.12.0-2
* svenruppert/maven-3.6.1-liberica 1.12.0-2
* svenruppert/maven-3.6.2-liberica 1.12.0-2
* svenruppert/maven-3.0.5-liberica 1.12.0
* svenruppert/maven-3.1.1-liberica 1.12.0
* svenruppert/maven-3.2.5-liberica 1.12.0
* svenruppert/maven-3.3.9-liberica 1.12.0
* svenruppert/maven-3.5.4-liberica 1.12.0
* svenruppert/maven-3.6.0-liberica 1.12.0
* svenruppert/maven-3.6.1-liberica 1.12.0
* svenruppert/maven-3.6.2-liberica 1.12.0
* svenruppert/maven-3.0.5-liberica 1.8.192
* svenruppert/maven-3.1.1-liberica 1.8.192
* svenruppert/maven-3.2.5-liberica 1.8.192
* svenruppert/maven-3.3.9-liberica 1.8.192
* svenruppert/maven-3.5.4-liberica 1.8.192
* svenruppert/maven-3.6.0-liberica 1.8.192
* svenruppert/maven-3.6.1-liberica 1.8.192
* svenruppert/maven-3.6.2-liberica 1.8.192
* svenruppert/maven-3.0.5-liberica 1.8.202
* svenruppert/maven-3.1.1-liberica 1.8.202
* svenruppert/maven-3.2.5-liberica 1.8.202
* svenruppert/maven-3.3.9-liberica 1.8.202
* svenruppert/maven-3.5.4-liberica 1.8.202
* svenruppert/maven-3.6.0-liberica 1.8.202
* svenruppert/maven-3.6.1-liberica 1.8.202
* svenruppert/maven-3.6.2-liberica 1.8.202
* svenruppert/maven-3.0.5-liberica 1.8.212
* svenruppert/maven-3.1.1-liberica 1.8.212
* svenruppert/maven-3.2.5-liberica 1.8.212
* svenruppert/maven-3.3.9-liberica 1.8.212
* svenruppert/maven-3.5.4-liberica 1.8.212
* svenruppert/maven-3.6.0-liberica 1.8.212
* svenruppert/maven-3.6.1-liberica 1.8.212
* svenruppert/maven-3.6.2-liberica 1.8.212
* svenruppert/maven-3.0.5-liberica 1.8.222
* svenruppert/maven-3.1.1-liberica 1.8.222
* svenruppert/maven-3.2.5-liberica 1.8.222
* svenruppert/maven-3.3.9-liberica 1.8.222
* svenruppert/maven-3.5.4-liberica 1.8.222
* svenruppert/maven-3.6.0-liberica 1.8.222
* svenruppert/maven-3.6.1-liberica 1.8.222
* svenruppert/maven-3.6.2-liberica 1.8.222
* svenruppert/maven-3.0.5-openjdk-ri 1.10.0-0
* svenruppert/maven-3.1.1-openjdk-ri 1.10.0-0
* svenruppert/maven-3.2.5-openjdk-ri 1.10.0-0
* svenruppert/maven-3.3.9-openjdk-ri 1.10.0-0
* svenruppert/maven-3.5.4-openjdk-ri 1.10.0-0
* svenruppert/maven-3.6.0-openjdk-ri 1.10.0-0
* svenruppert/maven-3.6.1-openjdk-ri 1.10.0-0
* svenruppert/maven-3.6.2-openjdk-ri 1.10.0-0
* svenruppert/maven-3.0.5-openjdk-ri 1.10.0
* svenruppert/maven-3.1.1-openjdk-ri 1.10.0
* svenruppert/maven-3.2.5-openjdk-ri 1.10.0
* svenruppert/maven-3.3.9-openjdk-ri 1.10.0
* svenruppert/maven-3.5.4-openjdk-ri 1.10.0
* svenruppert/maven-3.6.0-openjdk-ri 1.10.0
* svenruppert/maven-3.6.1-openjdk-ri 1.10.0
* svenruppert/maven-3.6.2-openjdk-ri 1.10.0
* svenruppert/maven-3.0.5-openjdk-ri 1.11.0-0
* svenruppert/maven-3.1.1-openjdk-ri 1.11.0-0
* svenruppert/maven-3.2.5-openjdk-ri 1.11.0-0
* svenruppert/maven-3.3.9-openjdk-ri 1.11.0-0
* svenruppert/maven-3.5.4-openjdk-ri 1.11.0-0
* svenruppert/maven-3.6.0-openjdk-ri 1.11.0-0
* svenruppert/maven-3.6.1-openjdk-ri 1.11.0-0
* svenruppert/maven-3.6.2-openjdk-ri 1.11.0-0
* svenruppert/maven-3.0.5-openjdk-ri 1.11.0
* svenruppert/maven-3.1.1-openjdk-ri 1.11.0
* svenruppert/maven-3.2.5-openjdk-ri 1.11.0
* svenruppert/maven-3.3.9-openjdk-ri 1.11.0
* svenruppert/maven-3.5.4-openjdk-ri 1.11.0
* svenruppert/maven-3.6.0-openjdk-ri 1.11.0
* svenruppert/maven-3.6.1-openjdk-ri 1.11.0
* svenruppert/maven-3.6.2-openjdk-ri 1.11.0
* svenruppert/maven-3.0.5-openjdk-ri 1.12.0-0
* svenruppert/maven-3.1.1-openjdk-ri 1.12.0-0
* svenruppert/maven-3.2.5-openjdk-ri 1.12.0-0
* svenruppert/maven-3.3.9-openjdk-ri 1.12.0-0
* svenruppert/maven-3.5.4-openjdk-ri 1.12.0-0
* svenruppert/maven-3.6.0-openjdk-ri 1.12.0-0
* svenruppert/maven-3.6.1-openjdk-ri 1.12.0-0
* svenruppert/maven-3.6.2-openjdk-ri 1.12.0-0
* svenruppert/maven-3.0.5-openjdk-ri 1.12.0
* svenruppert/maven-3.1.1-openjdk-ri 1.12.0
* svenruppert/maven-3.2.5-openjdk-ri 1.12.0
* svenruppert/maven-3.3.9-openjdk-ri 1.12.0
* svenruppert/maven-3.5.4-openjdk-ri 1.12.0
* svenruppert/maven-3.6.0-openjdk-ri 1.12.0
* svenruppert/maven-3.6.1-openjdk-ri 1.12.0
* svenruppert/maven-3.6.2-openjdk-ri 1.12.0
* svenruppert/maven-3.0.5-openjdk-ri 1.7.75
* svenruppert/maven-3.1.1-openjdk-ri 1.7.75
* svenruppert/maven-3.2.5-openjdk-ri 1.7.75
* svenruppert/maven-3.3.9-openjdk-ri 1.7.75
* svenruppert/maven-3.5.4-openjdk-ri 1.7.75
* svenruppert/maven-3.6.0-openjdk-ri 1.7.75
* svenruppert/maven-3.6.1-openjdk-ri 1.7.75
* svenruppert/maven-3.6.2-openjdk-ri 1.7.75
* svenruppert/maven-3.0.5-openjdk-ri 1.8.40
* svenruppert/maven-3.1.1-openjdk-ri 1.8.40
* svenruppert/maven-3.2.5-openjdk-ri 1.8.40
* svenruppert/maven-3.3.9-openjdk-ri 1.8.40
* svenruppert/maven-3.5.4-openjdk-ri 1.8.40
* svenruppert/maven-3.6.0-openjdk-ri 1.8.40
* svenruppert/maven-3.6.1-openjdk-ri 1.8.40
* svenruppert/maven-3.6.2-openjdk-ri 1.8.40
* svenruppert/maven-3.0.5-openjdk-ri 1.9.0-0
* svenruppert/maven-3.1.1-openjdk-ri 1.9.0-0
* svenruppert/maven-3.2.5-openjdk-ri 1.9.0-0
* svenruppert/maven-3.3.9-openjdk-ri 1.9.0-0
* svenruppert/maven-3.5.4-openjdk-ri 1.9.0-0
* svenruppert/maven-3.6.0-openjdk-ri 1.9.0-0
* svenruppert/maven-3.6.1-openjdk-ri 1.9.0-0
* svenruppert/maven-3.6.2-openjdk-ri 1.9.0-0
* svenruppert/maven-3.0.5-openjdk-ri 1.9.0
* svenruppert/maven-3.1.1-openjdk-ri 1.9.0
* svenruppert/maven-3.2.5-openjdk-ri 1.9.0
* svenruppert/maven-3.3.9-openjdk-ri 1.9.0
* svenruppert/maven-3.5.4-openjdk-ri 1.9.0
* svenruppert/maven-3.6.0-openjdk-ri 1.9.0
* svenruppert/maven-3.6.1-openjdk-ri 1.9.0
* svenruppert/maven-3.6.2-openjdk-ri 1.9.0
* svenruppert/maven-3.0.5-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.1.1-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.2.5-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.3.9-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.5.4-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.6.0-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.6.1-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.6.2-openjdk-shenandoah 1.11.0
* svenruppert/maven-3.0.5-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.1.1-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.2.5-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.3.9-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.5.4-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.6.0-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.6.1-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.6.2-openjdk-shenandoah 1.8.0
* svenruppert/maven-3.0.5-openjdk 1.10.0-1
* svenruppert/maven-3.1.1-openjdk 1.10.0-1
* svenruppert/maven-3.2.5-openjdk 1.10.0-1
* svenruppert/maven-3.3.9-openjdk 1.10.0-1
* svenruppert/maven-3.5.4-openjdk 1.10.0-1
* svenruppert/maven-3.6.0-openjdk 1.10.0-1
* svenruppert/maven-3.6.1-openjdk 1.10.0-1
* svenruppert/maven-3.6.2-openjdk 1.10.0-1
* svenruppert/maven-3.0.5-openjdk 1.10.0-2
* svenruppert/maven-3.1.1-openjdk 1.10.0-2
* svenruppert/maven-3.2.5-openjdk 1.10.0-2
* svenruppert/maven-3.3.9-openjdk 1.10.0-2
* svenruppert/maven-3.5.4-openjdk 1.10.0-2
* svenruppert/maven-3.6.0-openjdk 1.10.0-2
* svenruppert/maven-3.6.1-openjdk 1.10.0-2
* svenruppert/maven-3.6.2-openjdk 1.10.0-2
* svenruppert/maven-3.0.5-openjdk 1.10.0
* svenruppert/maven-3.1.1-openjdk 1.10.0
* svenruppert/maven-3.2.5-openjdk 1.10.0
* svenruppert/maven-3.3.9-openjdk 1.10.0
* svenruppert/maven-3.5.4-openjdk 1.10.0
* svenruppert/maven-3.6.0-openjdk 1.10.0
* svenruppert/maven-3.6.1-openjdk 1.10.0
* svenruppert/maven-3.6.2-openjdk 1.10.0
* svenruppert/maven-3.0.5-openjdk 1.11.0-1
* svenruppert/maven-3.1.1-openjdk 1.11.0-1
* svenruppert/maven-3.2.5-openjdk 1.11.0-1
* svenruppert/maven-3.3.9-openjdk 1.11.0-1
* svenruppert/maven-3.5.4-openjdk 1.11.0-1
* svenruppert/maven-3.6.0-openjdk 1.11.0-1
* svenruppert/maven-3.6.1-openjdk 1.11.0-1
* svenruppert/maven-3.6.2-openjdk 1.11.0-1
* svenruppert/maven-3.0.5-openjdk 1.11.0-2
* svenruppert/maven-3.1.1-openjdk 1.11.0-2
* svenruppert/maven-3.2.5-openjdk 1.11.0-2
* svenruppert/maven-3.3.9-openjdk 1.11.0-2
* svenruppert/maven-3.5.4-openjdk 1.11.0-2
* svenruppert/maven-3.6.0-openjdk 1.11.0-2
* svenruppert/maven-3.6.1-openjdk 1.11.0-2
* svenruppert/maven-3.6.2-openjdk 1.11.0-2
* svenruppert/maven-3.0.5-openjdk 1.11.0
* svenruppert/maven-3.1.1-openjdk 1.11.0
* svenruppert/maven-3.2.5-openjdk 1.11.0
* svenruppert/maven-3.3.9-openjdk 1.11.0
* svenruppert/maven-3.5.4-openjdk 1.11.0
* svenruppert/maven-3.6.0-openjdk 1.11.0
* svenruppert/maven-3.6.1-openjdk 1.11.0
* svenruppert/maven-3.6.2-openjdk 1.11.0
* svenruppert/maven-3.0.5-openjdk 1.12.0-2
* svenruppert/maven-3.1.1-openjdk 1.12.0-2
* svenruppert/maven-3.2.5-openjdk 1.12.0-2
* svenruppert/maven-3.3.9-openjdk 1.12.0-2
* svenruppert/maven-3.5.4-openjdk 1.12.0-2
* svenruppert/maven-3.6.0-openjdk 1.12.0-2
* svenruppert/maven-3.6.1-openjdk 1.12.0-2
* svenruppert/maven-3.6.2-openjdk 1.12.0-2
* svenruppert/maven-3.0.5-openjdk 1.12.0
* svenruppert/maven-3.1.1-openjdk 1.12.0
* svenruppert/maven-3.2.5-openjdk 1.12.0
* svenruppert/maven-3.3.9-openjdk 1.12.0
* svenruppert/maven-3.5.4-openjdk 1.12.0
* svenruppert/maven-3.6.0-openjdk 1.12.0
* svenruppert/maven-3.6.1-openjdk 1.12.0
* svenruppert/maven-3.6.2-openjdk 1.12.0
* svenruppert/maven-3.0.5-openjdk 1.13.0
* svenruppert/maven-3.1.1-openjdk 1.13.0
* svenruppert/maven-3.2.5-openjdk 1.13.0
* svenruppert/maven-3.3.9-openjdk 1.13.0
* svenruppert/maven-3.5.4-openjdk 1.13.0
* svenruppert/maven-3.6.0-openjdk 1.13.0
* svenruppert/maven-3.6.1-openjdk 1.13.0
* svenruppert/maven-3.6.2-openjdk 1.13.0
* svenruppert/maven-3.0.5-openjdk 1.14.0
* svenruppert/maven-3.1.1-openjdk 1.14.0
* svenruppert/maven-3.2.5-openjdk 1.14.0
* svenruppert/maven-3.3.9-openjdk 1.14.0
* svenruppert/maven-3.5.4-openjdk 1.14.0
* svenruppert/maven-3.6.0-openjdk 1.14.0
* svenruppert/maven-3.6.1-openjdk 1.14.0
* svenruppert/maven-3.6.2-openjdk 1.14.0
* svenruppert/maven-3.0.5-openjdk 1.9.0-1
* svenruppert/maven-3.1.1-openjdk 1.9.0-1
* svenruppert/maven-3.2.5-openjdk 1.9.0-1
* svenruppert/maven-3.3.9-openjdk 1.9.0-1
* svenruppert/maven-3.5.4-openjdk 1.9.0-1
* svenruppert/maven-3.6.0-openjdk 1.9.0-1
* svenruppert/maven-3.6.1-openjdk 1.9.0-1
* svenruppert/maven-3.6.2-openjdk 1.9.0-1
* svenruppert/maven-3.0.5-openjdk 1.9.0
* svenruppert/maven-3.1.1-openjdk 1.9.0
* svenruppert/maven-3.2.5-openjdk 1.9.0
* svenruppert/maven-3.3.9-openjdk 1.9.0
* svenruppert/maven-3.5.4-openjdk 1.9.0
* svenruppert/maven-3.6.0-openjdk 1.9.0
* svenruppert/maven-3.6.1-openjdk 1.9.0
* svenruppert/maven-3.6.2-openjdk 1.9.0
* svenruppert/maven-3.0.5-openjdk 1.9.0-4
* svenruppert/maven-3.1.1-openjdk 1.9.0-4
* svenruppert/maven-3.2.5-openjdk 1.9.0-4
* svenruppert/maven-3.3.9-openjdk 1.9.0-4
* svenruppert/maven-3.5.4-openjdk 1.9.0-4
* svenruppert/maven-3.6.0-openjdk 1.9.0-4
* svenruppert/maven-3.6.1-openjdk 1.9.0-4
* svenruppert/maven-3.6.2-openjdk 1.9.0-4
* svenruppert/maven-3.0.5-oracle-jdk 1.12.0-2
* svenruppert/maven-3.1.1-oracle-jdk 1.12.0-2
* svenruppert/maven-3.2.5-oracle-jdk 1.12.0-2
* svenruppert/maven-3.3.9-oracle-jdk 1.12.0-2
* svenruppert/maven-3.5.4-oracle-jdk 1.12.0-2
* svenruppert/maven-3.6.0-oracle-jdk 1.12.0-2
* svenruppert/maven-3.6.1-oracle-jdk 1.12.0-2
* svenruppert/maven-3.6.2-oracle-jdk 1.12.0-2
* svenruppert/maven-3.0.5-oracle-jdk 1.12.0
* svenruppert/maven-3.1.1-oracle-jdk 1.12.0
* svenruppert/maven-3.2.5-oracle-jdk 1.12.0
* svenruppert/maven-3.3.9-oracle-jdk 1.12.0
* svenruppert/maven-3.5.4-oracle-jdk 1.12.0
* svenruppert/maven-3.6.0-oracle-jdk 1.12.0
* svenruppert/maven-3.6.1-oracle-jdk 1.12.0
* svenruppert/maven-3.6.2-oracle-jdk 1.12.0
* svenruppert/maven-3.0.5-zulu 1.10.0-1
* svenruppert/maven-3.1.1-zulu 1.10.0-1
* svenruppert/maven-3.2.5-zulu 1.10.0-1
* svenruppert/maven-3.3.9-zulu 1.10.0-1
* svenruppert/maven-3.5.4-zulu 1.10.0-1
* svenruppert/maven-3.6.0-zulu 1.10.0-1
* svenruppert/maven-3.6.1-zulu 1.10.0-1
* svenruppert/maven-3.6.2-zulu 1.10.0-1
* svenruppert/maven-3.0.5-zulu 1.10.0-2
* svenruppert/maven-3.1.1-zulu 1.10.0-2
* svenruppert/maven-3.2.5-zulu 1.10.0-2
* svenruppert/maven-3.3.9-zulu 1.10.0-2
* svenruppert/maven-3.5.4-zulu 1.10.0-2
* svenruppert/maven-3.6.0-zulu 1.10.0-2
* svenruppert/maven-3.6.1-zulu 1.10.0-2
* svenruppert/maven-3.6.2-zulu 1.10.0-2
* svenruppert/maven-3.0.5-zulu 1.10.0
* svenruppert/maven-3.1.1-zulu 1.10.0
* svenruppert/maven-3.2.5-zulu 1.10.0
* svenruppert/maven-3.3.9-zulu 1.10.0
* svenruppert/maven-3.5.4-zulu 1.10.0
* svenruppert/maven-3.6.0-zulu 1.10.0
* svenruppert/maven-3.6.1-zulu 1.10.0
* svenruppert/maven-3.6.2-zulu 1.10.0
* svenruppert/maven-3.0.5-zulu 1.11.0-1
* svenruppert/maven-3.1.1-zulu 1.11.0-1
* svenruppert/maven-3.2.5-zulu 1.11.0-1
* svenruppert/maven-3.3.9-zulu 1.11.0-1
* svenruppert/maven-3.5.4-zulu 1.11.0-1
* svenruppert/maven-3.6.0-zulu 1.11.0-1
* svenruppert/maven-3.6.1-zulu 1.11.0-1
* svenruppert/maven-3.6.2-zulu 1.11.0-1
* svenruppert/maven-3.0.5-zulu 1.11.0-2
* svenruppert/maven-3.1.1-zulu 1.11.0-2
* svenruppert/maven-3.2.5-zulu 1.11.0-2
* svenruppert/maven-3.3.9-zulu 1.11.0-2
* svenruppert/maven-3.5.4-zulu 1.11.0-2
* svenruppert/maven-3.6.0-zulu 1.11.0-2
* svenruppert/maven-3.6.1-zulu 1.11.0-2
* svenruppert/maven-3.6.2-zulu 1.11.0-2
* svenruppert/maven-3.0.5-zulu 1.11.0-3
* svenruppert/maven-3.1.1-zulu 1.11.0-3
* svenruppert/maven-3.2.5-zulu 1.11.0-3
* svenruppert/maven-3.3.9-zulu 1.11.0-3
* svenruppert/maven-3.5.4-zulu 1.11.0-3
* svenruppert/maven-3.6.0-zulu 1.11.0-3
* svenruppert/maven-3.6.1-zulu 1.11.0-3
* svenruppert/maven-3.6.2-zulu 1.11.0-3
* svenruppert/maven-3.0.5-zulu 1.11.0
* svenruppert/maven-3.1.1-zulu 1.11.0
* svenruppert/maven-3.2.5-zulu 1.11.0
* svenruppert/maven-3.3.9-zulu 1.11.0
* svenruppert/maven-3.5.4-zulu 1.11.0
* svenruppert/maven-3.6.0-zulu 1.11.0
* svenruppert/maven-3.6.1-zulu 1.11.0
* svenruppert/maven-3.6.2-zulu 1.11.0
* svenruppert/maven-3.0.5-zulu 1.12.0-1
* svenruppert/maven-3.1.1-zulu 1.12.0-1
* svenruppert/maven-3.2.5-zulu 1.12.0-1
* svenruppert/maven-3.3.9-zulu 1.12.0-1
* svenruppert/maven-3.5.4-zulu 1.12.0-1
* svenruppert/maven-3.6.0-zulu 1.12.0-1
* svenruppert/maven-3.6.1-zulu 1.12.0-1
* svenruppert/maven-3.6.2-zulu 1.12.0-1
* svenruppert/maven-3.0.5-zulu 1.12.0
* svenruppert/maven-3.1.1-zulu 1.12.0
* svenruppert/maven-3.2.5-zulu 1.12.0
* svenruppert/maven-3.3.9-zulu 1.12.0
* svenruppert/maven-3.5.4-zulu 1.12.0
* svenruppert/maven-3.6.0-zulu 1.12.0
* svenruppert/maven-3.6.1-zulu 1.12.0
* svenruppert/maven-3.6.2-zulu 1.12.0
* svenruppert/maven-3.0.5-zulu 1.6.103
* svenruppert/maven-3.1.1-zulu 1.6.103
* svenruppert/maven-3.2.5-zulu 1.6.103
* svenruppert/maven-3.3.9-zulu 1.6.103
* svenruppert/maven-3.5.4-zulu 1.6.103
* svenruppert/maven-3.6.0-zulu 1.6.103
* svenruppert/maven-3.6.1-zulu 1.6.103
* svenruppert/maven-3.6.2-zulu 1.6.103
* svenruppert/maven-3.0.5-zulu 1.6.107
* svenruppert/maven-3.1.1-zulu 1.6.107
* svenruppert/maven-3.2.5-zulu 1.6.107
* svenruppert/maven-3.3.9-zulu 1.6.107
* svenruppert/maven-3.5.4-zulu 1.6.107
* svenruppert/maven-3.6.0-zulu 1.6.107
* svenruppert/maven-3.6.1-zulu 1.6.107
* svenruppert/maven-3.6.2-zulu 1.6.107
* svenruppert/maven-3.0.5-zulu 1.6.113
* svenruppert/maven-3.1.1-zulu 1.6.113
* svenruppert/maven-3.2.5-zulu 1.6.113
* svenruppert/maven-3.3.9-zulu 1.6.113
* svenruppert/maven-3.5.4-zulu 1.6.113
* svenruppert/maven-3.6.0-zulu 1.6.113
* svenruppert/maven-3.6.1-zulu 1.6.113
* svenruppert/maven-3.6.2-zulu 1.6.113
* svenruppert/maven-3.0.5-zulu 1.6.119
* svenruppert/maven-3.1.1-zulu 1.6.119
* svenruppert/maven-3.2.5-zulu 1.6.119
* svenruppert/maven-3.3.9-zulu 1.6.119
* svenruppert/maven-3.5.4-zulu 1.6.119
* svenruppert/maven-3.6.0-zulu 1.6.119
* svenruppert/maven-3.6.1-zulu 1.6.119
* svenruppert/maven-3.6.2-zulu 1.6.119
* svenruppert/maven-3.0.5-zulu 1.6.77
* svenruppert/maven-3.1.1-zulu 1.6.77
* svenruppert/maven-3.2.5-zulu 1.6.77
* svenruppert/maven-3.3.9-zulu 1.6.77
* svenruppert/maven-3.5.4-zulu 1.6.77
* svenruppert/maven-3.6.0-zulu 1.6.77
* svenruppert/maven-3.6.1-zulu 1.6.77
* svenruppert/maven-3.6.2-zulu 1.6.77
* svenruppert/maven-3.0.5-zulu 1.6.79
* svenruppert/maven-3.1.1-zulu 1.6.79
* svenruppert/maven-3.2.5-zulu 1.6.79
* svenruppert/maven-3.3.9-zulu 1.6.79
* svenruppert/maven-3.5.4-zulu 1.6.79
* svenruppert/maven-3.6.0-zulu 1.6.79
* svenruppert/maven-3.6.1-zulu 1.6.79
* svenruppert/maven-3.6.2-zulu 1.6.79
* svenruppert/maven-3.0.5-zulu 1.6.83
* svenruppert/maven-3.1.1-zulu 1.6.83
* svenruppert/maven-3.2.5-zulu 1.6.83
* svenruppert/maven-3.3.9-zulu 1.6.83
* svenruppert/maven-3.5.4-zulu 1.6.83
* svenruppert/maven-3.6.0-zulu 1.6.83
* svenruppert/maven-3.6.1-zulu 1.6.83
* svenruppert/maven-3.6.2-zulu 1.6.83
* svenruppert/maven-3.0.5-zulu 1.6.87
* svenruppert/maven-3.1.1-zulu 1.6.87
* svenruppert/maven-3.2.5-zulu 1.6.87
* svenruppert/maven-3.3.9-zulu 1.6.87
* svenruppert/maven-3.5.4-zulu 1.6.87
* svenruppert/maven-3.6.0-zulu 1.6.87
* svenruppert/maven-3.6.1-zulu 1.6.87
* svenruppert/maven-3.6.2-zulu 1.6.87
* svenruppert/maven-3.0.5-zulu 1.6.89
* svenruppert/maven-3.1.1-zulu 1.6.89
* svenruppert/maven-3.2.5-zulu 1.6.89
* svenruppert/maven-3.3.9-zulu 1.6.89
* svenruppert/maven-3.5.4-zulu 1.6.89
* svenruppert/maven-3.6.0-zulu 1.6.89
* svenruppert/maven-3.6.1-zulu 1.6.89
* svenruppert/maven-3.6.2-zulu 1.6.89
* svenruppert/maven-3.0.5-zulu 1.6.93
* svenruppert/maven-3.1.1-zulu 1.6.93
* svenruppert/maven-3.2.5-zulu 1.6.93
* svenruppert/maven-3.3.9-zulu 1.6.93
* svenruppert/maven-3.5.4-zulu 1.6.93
* svenruppert/maven-3.6.0-zulu 1.6.93
* svenruppert/maven-3.6.1-zulu 1.6.93
* svenruppert/maven-3.6.2-zulu 1.6.93
* svenruppert/maven-3.0.5-zulu 1.6.97
* svenruppert/maven-3.1.1-zulu 1.6.97
* svenruppert/maven-3.2.5-zulu 1.6.97
* svenruppert/maven-3.3.9-zulu 1.6.97
* svenruppert/maven-3.5.4-zulu 1.6.97
* svenruppert/maven-3.6.0-zulu 1.6.97
* svenruppert/maven-3.6.1-zulu 1.6.97
* svenruppert/maven-3.6.2-zulu 1.6.97
* svenruppert/maven-3.0.5-zulu 1.6.99
* svenruppert/maven-3.1.1-zulu 1.6.99
* svenruppert/maven-3.2.5-zulu 1.6.99
* svenruppert/maven-3.3.9-zulu 1.6.99
* svenruppert/maven-3.5.4-zulu 1.6.99
* svenruppert/maven-3.6.0-zulu 1.6.99
* svenruppert/maven-3.6.1-zulu 1.6.99
* svenruppert/maven-3.6.2-zulu 1.6.99
* svenruppert/maven-3.0.5-zulu 1.7.101
* svenruppert/maven-3.1.1-zulu 1.7.101
* svenruppert/maven-3.2.5-zulu 1.7.101
* svenruppert/maven-3.3.9-zulu 1.7.101
* svenruppert/maven-3.5.4-zulu 1.7.101
* svenruppert/maven-3.6.0-zulu 1.7.101
* svenruppert/maven-3.6.1-zulu 1.7.101
* svenruppert/maven-3.6.2-zulu 1.7.101
* svenruppert/maven-3.0.5-zulu 1.7.111
* svenruppert/maven-3.1.1-zulu 1.7.111
* svenruppert/maven-3.2.5-zulu 1.7.111
* svenruppert/maven-3.3.9-zulu 1.7.111
* svenruppert/maven-3.5.4-zulu 1.7.111
* svenruppert/maven-3.6.0-zulu 1.7.111
* svenruppert/maven-3.6.1-zulu 1.7.111
* svenruppert/maven-3.6.2-zulu 1.7.111
* svenruppert/maven-3.0.5-zulu 1.7.121
* svenruppert/maven-3.1.1-zulu 1.7.121
* svenruppert/maven-3.2.5-zulu 1.7.121
* svenruppert/maven-3.3.9-zulu 1.7.121
* svenruppert/maven-3.5.4-zulu 1.7.121
* svenruppert/maven-3.6.0-zulu 1.7.121
* svenruppert/maven-3.6.1-zulu 1.7.121
* svenruppert/maven-3.6.2-zulu 1.7.121
* svenruppert/maven-3.0.5-zulu 1.7.131
* svenruppert/maven-3.1.1-zulu 1.7.131
* svenruppert/maven-3.2.5-zulu 1.7.131
* svenruppert/maven-3.3.9-zulu 1.7.131
* svenruppert/maven-3.5.4-zulu 1.7.131
* svenruppert/maven-3.6.0-zulu 1.7.131
* svenruppert/maven-3.6.1-zulu 1.7.131
* svenruppert/maven-3.6.2-zulu 1.7.131
* svenruppert/maven-3.0.5-zulu 1.7.141
* svenruppert/maven-3.1.1-zulu 1.7.141
* svenruppert/maven-3.2.5-zulu 1.7.141
* svenruppert/maven-3.3.9-zulu 1.7.141
* svenruppert/maven-3.5.4-zulu 1.7.141
* svenruppert/maven-3.6.0-zulu 1.7.141
* svenruppert/maven-3.6.1-zulu 1.7.141
* svenruppert/maven-3.6.2-zulu 1.7.141
* svenruppert/maven-3.0.5-zulu 1.7.154
* svenruppert/maven-3.1.1-zulu 1.7.154
* svenruppert/maven-3.2.5-zulu 1.7.154
* svenruppert/maven-3.3.9-zulu 1.7.154
* svenruppert/maven-3.5.4-zulu 1.7.154
* svenruppert/maven-3.6.0-zulu 1.7.154
* svenruppert/maven-3.6.1-zulu 1.7.154
* svenruppert/maven-3.6.2-zulu 1.7.154
* svenruppert/maven-3.0.5-zulu 1.7.161
* svenruppert/maven-3.1.1-zulu 1.7.161
* svenruppert/maven-3.2.5-zulu 1.7.161
* svenruppert/maven-3.3.9-zulu 1.7.161
* svenruppert/maven-3.5.4-zulu 1.7.161
* svenruppert/maven-3.6.0-zulu 1.7.161
* svenruppert/maven-3.6.1-zulu 1.7.161
* svenruppert/maven-3.6.2-zulu 1.7.161
* svenruppert/maven-3.0.5-zulu 1.7.171
* svenruppert/maven-3.1.1-zulu 1.7.171
* svenruppert/maven-3.2.5-zulu 1.7.171
* svenruppert/maven-3.3.9-zulu 1.7.171
* svenruppert/maven-3.5.4-zulu 1.7.171
* svenruppert/maven-3.6.0-zulu 1.7.171
* svenruppert/maven-3.6.1-zulu 1.7.171
* svenruppert/maven-3.6.2-zulu 1.7.171
* svenruppert/maven-3.0.5-zulu 1.7.181
* svenruppert/maven-3.1.1-zulu 1.7.181
* svenruppert/maven-3.2.5-zulu 1.7.181
* svenruppert/maven-3.3.9-zulu 1.7.181
* svenruppert/maven-3.5.4-zulu 1.7.181
* svenruppert/maven-3.6.0-zulu 1.7.181
* svenruppert/maven-3.6.1-zulu 1.7.181
* svenruppert/maven-3.6.2-zulu 1.7.181
* svenruppert/maven-3.0.5-zulu 1.7.191
* svenruppert/maven-3.1.1-zulu 1.7.191
* svenruppert/maven-3.2.5-zulu 1.7.191
* svenruppert/maven-3.3.9-zulu 1.7.191
* svenruppert/maven-3.5.4-zulu 1.7.191
* svenruppert/maven-3.6.0-zulu 1.7.191
* svenruppert/maven-3.6.1-zulu 1.7.191
* svenruppert/maven-3.6.2-zulu 1.7.191
* svenruppert/maven-3.0.5-zulu 1.7.201
* svenruppert/maven-3.1.1-zulu 1.7.201
* svenruppert/maven-3.2.5-zulu 1.7.201
* svenruppert/maven-3.3.9-zulu 1.7.201
* svenruppert/maven-3.5.4-zulu 1.7.201
* svenruppert/maven-3.6.0-zulu 1.7.201
* svenruppert/maven-3.6.1-zulu 1.7.201
* svenruppert/maven-3.6.2-zulu 1.7.201
* svenruppert/maven-3.0.5-zulu 1.7.211
* svenruppert/maven-3.1.1-zulu 1.7.211
* svenruppert/maven-3.2.5-zulu 1.7.211
* svenruppert/maven-3.3.9-zulu 1.7.211
* svenruppert/maven-3.5.4-zulu 1.7.211
* svenruppert/maven-3.6.0-zulu 1.7.211
* svenruppert/maven-3.6.1-zulu 1.7.211
* svenruppert/maven-3.6.2-zulu 1.7.211
* svenruppert/maven-3.0.5-zulu 1.7.222
* svenruppert/maven-3.1.1-zulu 1.7.222
* svenruppert/maven-3.2.5-zulu 1.7.222
* svenruppert/maven-3.3.9-zulu 1.7.222
* svenruppert/maven-3.5.4-zulu 1.7.222
* svenruppert/maven-3.6.0-zulu 1.7.222
* svenruppert/maven-3.6.1-zulu 1.7.222
* svenruppert/maven-3.6.2-zulu 1.7.222
* svenruppert/maven-3.0.5-zulu 1.7.95
* svenruppert/maven-3.1.1-zulu 1.7.95
* svenruppert/maven-3.2.5-zulu 1.7.95
* svenruppert/maven-3.3.9-zulu 1.7.95
* svenruppert/maven-3.5.4-zulu 1.7.95
* svenruppert/maven-3.6.0-zulu 1.7.95
* svenruppert/maven-3.6.1-zulu 1.7.95
* svenruppert/maven-3.6.2-zulu 1.7.95
* svenruppert/maven-3.0.5-zulu 1.8.102
* svenruppert/maven-3.1.1-zulu 1.8.102
* svenruppert/maven-3.2.5-zulu 1.8.102
* svenruppert/maven-3.3.9-zulu 1.8.102
* svenruppert/maven-3.5.4-zulu 1.8.102
* svenruppert/maven-3.6.0-zulu 1.8.102
* svenruppert/maven-3.6.1-zulu 1.8.102
* svenruppert/maven-3.6.2-zulu 1.8.102
* svenruppert/maven-3.0.5-zulu 1.8.112
* svenruppert/maven-3.1.1-zulu 1.8.112
* svenruppert/maven-3.2.5-zulu 1.8.112
* svenruppert/maven-3.3.9-zulu 1.8.112
* svenruppert/maven-3.5.4-zulu 1.8.112
* svenruppert/maven-3.6.0-zulu 1.8.112
* svenruppert/maven-3.6.1-zulu 1.8.112
* svenruppert/maven-3.6.2-zulu 1.8.112
* svenruppert/maven-3.0.5-zulu 1.8.121
* svenruppert/maven-3.1.1-zulu 1.8.121
* svenruppert/maven-3.2.5-zulu 1.8.121
* svenruppert/maven-3.3.9-zulu 1.8.121
* svenruppert/maven-3.5.4-zulu 1.8.121
* svenruppert/maven-3.6.0-zulu 1.8.121
* svenruppert/maven-3.6.1-zulu 1.8.121
* svenruppert/maven-3.6.2-zulu 1.8.121
* svenruppert/maven-3.0.5-zulu 1.8.131
* svenruppert/maven-3.1.1-zulu 1.8.131
* svenruppert/maven-3.2.5-zulu 1.8.131
* svenruppert/maven-3.3.9-zulu 1.8.131
* svenruppert/maven-3.5.4-zulu 1.8.131
* svenruppert/maven-3.6.0-zulu 1.8.131
* svenruppert/maven-3.6.1-zulu 1.8.131
* svenruppert/maven-3.6.2-zulu 1.8.131
* svenruppert/maven-3.0.5-zulu 1.8.144
* svenruppert/maven-3.1.1-zulu 1.8.144
* svenruppert/maven-3.2.5-zulu 1.8.144
* svenruppert/maven-3.3.9-zulu 1.8.144
* svenruppert/maven-3.5.4-zulu 1.8.144
* svenruppert/maven-3.6.0-zulu 1.8.144
* svenruppert/maven-3.6.1-zulu 1.8.144
* svenruppert/maven-3.6.2-zulu 1.8.144
* svenruppert/maven-3.0.5-zulu 1.8.152
* svenruppert/maven-3.1.1-zulu 1.8.152
* svenruppert/maven-3.2.5-zulu 1.8.152
* svenruppert/maven-3.3.9-zulu 1.8.152
* svenruppert/maven-3.5.4-zulu 1.8.152
* svenruppert/maven-3.6.0-zulu 1.8.152
* svenruppert/maven-3.6.1-zulu 1.8.152
* svenruppert/maven-3.6.2-zulu 1.8.152
* svenruppert/maven-3.0.5-zulu 1.8.162
* svenruppert/maven-3.1.1-zulu 1.8.162
* svenruppert/maven-3.2.5-zulu 1.8.162
* svenruppert/maven-3.3.9-zulu 1.8.162
* svenruppert/maven-3.5.4-zulu 1.8.162
* svenruppert/maven-3.6.0-zulu 1.8.162
* svenruppert/maven-3.6.1-zulu 1.8.162
* svenruppert/maven-3.6.2-zulu 1.8.162
* svenruppert/maven-3.0.5-zulu 1.8.163
* svenruppert/maven-3.1.1-zulu 1.8.163
* svenruppert/maven-3.2.5-zulu 1.8.163
* svenruppert/maven-3.3.9-zulu 1.8.163
* svenruppert/maven-3.5.4-zulu 1.8.163
* svenruppert/maven-3.6.0-zulu 1.8.163
* svenruppert/maven-3.6.1-zulu 1.8.163
* svenruppert/maven-3.6.2-zulu 1.8.163
* svenruppert/maven-3.0.5-zulu 1.8.172
* svenruppert/maven-3.1.1-zulu 1.8.172
* svenruppert/maven-3.2.5-zulu 1.8.172
* svenruppert/maven-3.3.9-zulu 1.8.172
* svenruppert/maven-3.5.4-zulu 1.8.172
* svenruppert/maven-3.6.0-zulu 1.8.172
* svenruppert/maven-3.6.1-zulu 1.8.172
* svenruppert/maven-3.6.2-zulu 1.8.172
* svenruppert/maven-3.0.5-zulu 1.8.181
* svenruppert/maven-3.1.1-zulu 1.8.181
* svenruppert/maven-3.2.5-zulu 1.8.181
* svenruppert/maven-3.3.9-zulu 1.8.181
* svenruppert/maven-3.5.4-zulu 1.8.181
* svenruppert/maven-3.6.0-zulu 1.8.181
* svenruppert/maven-3.6.1-zulu 1.8.181
* svenruppert/maven-3.6.2-zulu 1.8.181
* svenruppert/maven-3.0.5-zulu 1.8.192
* svenruppert/maven-3.1.1-zulu 1.8.192
* svenruppert/maven-3.2.5-zulu 1.8.192
* svenruppert/maven-3.3.9-zulu 1.8.192
* svenruppert/maven-3.5.4-zulu 1.8.192
* svenruppert/maven-3.6.0-zulu 1.8.192
* svenruppert/maven-3.6.1-zulu 1.8.192
* svenruppert/maven-3.6.2-zulu 1.8.192
* svenruppert/maven-3.0.5-zulu 1.8.201
* svenruppert/maven-3.1.1-zulu 1.8.201
* svenruppert/maven-3.2.5-zulu 1.8.201
* svenruppert/maven-3.3.9-zulu 1.8.201
* svenruppert/maven-3.5.4-zulu 1.8.201
* svenruppert/maven-3.6.0-zulu 1.8.201
* svenruppert/maven-3.6.1-zulu 1.8.201
* svenruppert/maven-3.6.2-zulu 1.8.201
* svenruppert/maven-3.0.5-zulu 1.8.202
* svenruppert/maven-3.1.1-zulu 1.8.202
* svenruppert/maven-3.2.5-zulu 1.8.202
* svenruppert/maven-3.3.9-zulu 1.8.202
* svenruppert/maven-3.5.4-zulu 1.8.202
* svenruppert/maven-3.6.0-zulu 1.8.202
* svenruppert/maven-3.6.1-zulu 1.8.202
* svenruppert/maven-3.6.2-zulu 1.8.202
* svenruppert/maven-3.0.5-zulu 1.8.212
* svenruppert/maven-3.1.1-zulu 1.8.212
* svenruppert/maven-3.2.5-zulu 1.8.212
* svenruppert/maven-3.3.9-zulu 1.8.212
* svenruppert/maven-3.5.4-zulu 1.8.212
* svenruppert/maven-3.6.0-zulu 1.8.212
* svenruppert/maven-3.6.1-zulu 1.8.212
* svenruppert/maven-3.6.2-zulu 1.8.212
* svenruppert/maven-3.0.5-zulu 1.8.71
* svenruppert/maven-3.1.1-zulu 1.8.71
* svenruppert/maven-3.2.5-zulu 1.8.71
* svenruppert/maven-3.3.9-zulu 1.8.71
* svenruppert/maven-3.5.4-zulu 1.8.71
* svenruppert/maven-3.6.0-zulu 1.8.71
* svenruppert/maven-3.6.1-zulu 1.8.71
* svenruppert/maven-3.6.2-zulu 1.8.71
* svenruppert/maven-3.0.5-zulu 1.8.72
* svenruppert/maven-3.1.1-zulu 1.8.72
* svenruppert/maven-3.2.5-zulu 1.8.72
* svenruppert/maven-3.3.9-zulu 1.8.72
* svenruppert/maven-3.5.4-zulu 1.8.72
* svenruppert/maven-3.6.0-zulu 1.8.72
* svenruppert/maven-3.6.1-zulu 1.8.72
* svenruppert/maven-3.6.2-zulu 1.8.72
* svenruppert/maven-3.0.5-zulu 1.8.91
* svenruppert/maven-3.1.1-zulu 1.8.91
* svenruppert/maven-3.2.5-zulu 1.8.91
* svenruppert/maven-3.3.9-zulu 1.8.91
* svenruppert/maven-3.5.4-zulu 1.8.91
* svenruppert/maven-3.6.0-zulu 1.8.91
* svenruppert/maven-3.6.1-zulu 1.8.91
* svenruppert/maven-3.6.2-zulu 1.8.91
* svenruppert/maven-3.0.5-zulu 1.8.92
* svenruppert/maven-3.1.1-zulu 1.8.92
* svenruppert/maven-3.2.5-zulu 1.8.92
* svenruppert/maven-3.3.9-zulu 1.8.92
* svenruppert/maven-3.5.4-zulu 1.8.92
* svenruppert/maven-3.6.0-zulu 1.8.92
* svenruppert/maven-3.6.1-zulu 1.8.92
* svenruppert/maven-3.6.2-zulu 1.8.92
* svenruppert/maven-3.0.5-zulu 1.9.0-0
* svenruppert/maven-3.1.1-zulu 1.9.0-0
* svenruppert/maven-3.2.5-zulu 1.9.0-0
* svenruppert/maven-3.3.9-zulu 1.9.0-0
* svenruppert/maven-3.5.4-zulu 1.9.0-0
* svenruppert/maven-3.6.0-zulu 1.9.0-0
* svenruppert/maven-3.6.1-zulu 1.9.0-0
* svenruppert/maven-3.6.2-zulu 1.9.0-0
* svenruppert/maven-3.0.5-zulu 1.9.0-1
* svenruppert/maven-3.1.1-zulu 1.9.0-1
* svenruppert/maven-3.2.5-zulu 1.9.0-1
* svenruppert/maven-3.3.9-zulu 1.9.0-1
* svenruppert/maven-3.5.4-zulu 1.9.0-1
* svenruppert/maven-3.6.0-zulu 1.9.0-1
* svenruppert/maven-3.6.1-zulu 1.9.0-1
* svenruppert/maven-3.6.2-zulu 1.9.0-1
* svenruppert/maven-3.0.5-zulu 1.9.0
* svenruppert/maven-3.1.1-zulu 1.9.0
* svenruppert/maven-3.2.5-zulu 1.9.0
* svenruppert/maven-3.3.9-zulu 1.9.0
* svenruppert/maven-3.5.4-zulu 1.9.0
* svenruppert/maven-3.6.0-zulu 1.9.0
* svenruppert/maven-3.6.1-zulu 1.9.0
* svenruppert/maven-3.6.2-zulu 1.9.0
* svenruppert/maven-3.0.5-zulu 1.9.0-4
* svenruppert/maven-3.1.1-zulu 1.9.0-4
* svenruppert/maven-3.2.5-zulu 1.9.0-4
* svenruppert/maven-3.3.9-zulu 1.9.0-4
* svenruppert/maven-3.5.4-zulu 1.9.0-4
* svenruppert/maven-3.6.0-zulu 1.9.0-4
* svenruppert/maven-3.6.1-zulu 1.9.0-4
* svenruppert/maven-3.6.2-zulu 1.9.0-4
* svenruppert/maven-3.0.5-zulu 1.9.0-7
* svenruppert/maven-3.1.1-zulu 1.9.0-7
* svenruppert/maven-3.2.5-zulu 1.9.0-7
* svenruppert/maven-3.3.9-zulu 1.9.0-7
* svenruppert/maven-3.5.4-zulu 1.9.0-7
* svenruppert/maven-3.6.0-zulu 1.9.0-7
* svenruppert/maven-3.6.1-zulu 1.9.0-7
* svenruppert/maven-3.6.2-zulu 1.9.0-7






## ToDo 
To optimize the performance and to reduce the amount of downloads
a few steps are needed.

* Create an image with installed jabba that will be used during the process
* download the maven binary to a local server that is used during the process
* build, tag, push and delete an image in one step

