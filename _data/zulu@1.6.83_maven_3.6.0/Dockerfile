FROM svenruppert/zulu:1.6.83
MAINTAINER sven.ruppert@gmail.com

ARG MAVEN_VERSION=3.6.0
ARG USER_HOME_DIR="/root"
ARG BASE_URL=http://mirror.dkd.de/apache/maven/binaries/

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -o /tmp/apache-maven.tar.gz http://mirror.dkd.de/apache/maven/binaries/apache-maven-3.6.0-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

CMD ["mvn"]