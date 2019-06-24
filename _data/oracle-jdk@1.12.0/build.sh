#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/oracle-jdk 1.12.0; then
    echo skip building, image already existing - svenruppert/oracle-jdk:1.12.0
else
    echo start building the images
    docker build -t svenruppert/oracle-jdk .

    docker tag svenruppert/oracle-jdk:latest svenruppert/oracle-jdk:1.12.0
    docker push svenruppert/oracle-jdk:1.12.0
fi