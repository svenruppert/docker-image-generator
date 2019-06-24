FROM svenruppert/ibm:1.8.0-5.7
MAINTAINER sven.ruppert@gmail.com

ARG MAVEN_VERSION=3.2.5
ARG USER_HOME_DIR="/root"
ARG BASE_URL=http://mirror.synyx.de/apache/maven/maven-3/3.2.5/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -o /tmp/apache-maven.tar.gz http://mirror.synyx.de/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

CMD ["mvn"]