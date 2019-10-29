FROM svenruppert/graalvm:19.2.1
MAINTAINER sven.ruppert@gmail.com

ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
ARG BASE_URL=http://mirror.synyx.de/apache/maven/maven-3/3.3.9/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -o /tmp/apache-maven.tar.gz http://mirror.synyx.de/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

CMD ["mvn"]