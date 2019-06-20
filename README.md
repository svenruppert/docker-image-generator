# Docker Image Generator for JDK and Maven usage
Generator for generating a bunch of developer docker images.
This will help you to test your app against a matrix of JDK versions.

## Why
With the new release cycle that will bring us a new JDK every few months,
we must decide how to deal with this in a project or product.
This project will help you to test your application against different JDK
versions. Based on Jabba, a set of Docker images are generated and pushed to the Docker HUB. 
If you need a custom image, you can either use the provided images as a base or 
create your custom ones.

## How to use
In the following examples I assume that the project is a 
maven project. 
With gradle it should be the same, but maybe different path's.

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
If you want to generate the Dockerfiles by yourself, 
you should edit the class ```Generator```.
Inside this class you will find the templates as well. 
This is not the super duper clean implementation, 
but it works.
Fell free to help me and sent a PR. ;-)

The generated files, including the scripts 
for generating and pushing the images, 
are available under the directory *_data*.

All images are available 
in the official docker registry.

## Current active JDK versions

1.12.0
1.12.0-1
adopt@1.12.33-0
adopt@1.12.0-1
adopt@1.11.28-0
adopt@1.11.0-3
adopt@1.11.0-2
adopt@1.11.0-1
adopt@1.10.0-2
adopt@1.9.181-0
adopt@1.9.0-4
adopt@1.8.212-04
adopt@1.8.212-03
adopt@1.8.202-08
adopt@1.8.192-12
adopt@1.8.181-13
adopt@1.8.172-11
adopt-openj9@1.12.33-0
adopt-openj9@1.12.0-1
adopt-openj9@1.11.28-0
adopt-openj9@1.11.0-3
adopt-openj9@1.11.0-2
adopt-openj9@1.11.0-1
adopt-openj9@1.10.0-2
adopt-openj9@1.9.0-4
adopt-openj9@1.8.212-04
adopt-openj9@1.8.212-03
adopt-openj9@1.8.202-08
adopt-openj9@1.8.192-12
adopt-openj9@1.8.181-13
adopt-openj9@1.8.162-12
amazon-corretto@1.11.0-3.7.1
amazon-corretto@1.8.212-04.2
graalvm@19.0.0
graalvm@1.0.0-16
graalvm@1.0.0-15
graalvm@1.0.0-14
graalvm@1.0.0-13
graalvm@1.0.0-12
graalvm@1.0.0-11
graalvm@1.0.0-10
graalvm@1.0.0-9
graalvm@1.0.0-8
graalvm@1.0.0-7
graalvm@1.0.0-6
graalvm@1.0.0-5
graalvm@1.0.0-4
graalvm@1.0.0-3
graalvm@1.0.0-2
graalvm@1.0.0-1
ibm@1.8.0
ibm@1.8.0-5.36
ibm@1.8.0-5.35
ibm@1.8.0-5.31
ibm@1.8.0-5.30
ibm@1.8.0-5.7
ibm@1.8.0-5.6
ibm@1.7.1
ibm@1.7.1-4.45
ibm@1.7.1-4.40
ibm@1.7.0
ibm@1.7.0-10.45
ibm@1.7.0-10.40
liberica@1.12.0
liberica@1.12.0-1
liberica@1.12.0-0
liberica@1.11.0
liberica@1.11.0-3
liberica@1.11.0-2
liberica@1.11.0-1
liberica@1.11.0-0
liberica@1.10.0
liberica@1.10.0-2
liberica@1.8.212
liberica@1.8.202
liberica@1.8.192
openjdk@1.13.0
openjdk@1.13.0-24
openjdk@1.12.0
openjdk@1.12.0-1
openjdk@1.11.0
openjdk@1.11.0-2
openjdk@1.11.0-1
openjdk@1.10.0
openjdk@1.10.0-2
openjdk@1.10.0-1
openjdk@1.9.0
openjdk@1.9.0-4
openjdk@1.9.0-1
openjdk-ri@1.12.0
openjdk-ri@1.12.0-0
openjdk-ri@1.11.0
openjdk-ri@1.11.0-0
openjdk-ri@1.10.0
openjdk-ri@1.10.0-0
openjdk-ri@1.9.0
openjdk-ri@1.9.0-0
openjdk-ri@1.8.40
openjdk-ri@1.7.75
openjdk-shenandoah@1.11.0
openjdk-shenandoah@1.8.0
zulu@1.12.0
zulu@1.12.0-1
zulu@1.11.0
zulu@1.11.0-3
zulu@1.11.0-2
zulu@1.11.0-1
zulu@1.10.0
zulu@1.10.0-2
zulu@1.10.0-1
zulu@1.9.0
zulu@1.9.0-7
zulu@1.9.0-4
zulu@1.9.0-1
zulu@1.9.0-0
zulu@1.8.212
zulu@1.8.202
zulu@1.8.201
zulu@1.8.192
zulu@1.8.181
zulu@1.8.172
zulu@1.8.163
zulu@1.8.162
zulu@1.8.152
zulu@1.8.144
zulu@1.8.131
zulu@1.8.121
zulu@1.8.112
zulu@1.8.102
zulu@1.8.92
zulu@1.8.91
zulu@1.8.72
zulu@1.8.71
zulu@1.7.222
zulu@1.7.211
zulu@1.7.201
zulu@1.7.191
zulu@1.7.181
zulu@1.7.171
zulu@1.7.161
zulu@1.7.154
zulu@1.7.141
zulu@1.7.131
zulu@1.7.121
zulu@1.7.111
zulu@1.7.101
zulu@1.7.95
zulu@1.6.119
zulu@1.6.113
zulu@1.6.107
zulu@1.6.103
zulu@1.6.99
zulu@1.6.97
zulu@1.6.93
zulu@1.6.89
zulu@1.6.87
zulu@1.6.83
zulu@1.6.79
zulu@1.6.77


## ToDo 
To optimize the performance and to reduce the amount of downloads
a few steps are needed.

* Create an image with installed jabba that will be used during the process
* download the maven binary to a local server that is used during the process
* build, tag, push and delete an image in one step
