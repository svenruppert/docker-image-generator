FROM buildpack-deps:buster-curl
LABEL maintainer="Sven Ruppert sven.ruppert@gmail.com" description="ibm@1.7.1-4.50"

ARG USER_HOME_DIR="/root"

RUN curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | \
    JABBA_COMMAND="install ibm@1.7.1-4.50 -o /jdk" bash

ENV JAVA_HOME /jdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN java -version

CMD ["java"]