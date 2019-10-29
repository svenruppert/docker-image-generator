#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/maven-3.5.4-openjdk 1.14.0-16; then
    echo skip building, image already existing - svenruppert/maven-3.5.4-openjdk 1.14.0-16
else
    echo start building the images
    docker build -t svenruppert/maven-3.5.4-openjdk .
    docker tag svenruppert/maven-3.5.4-openjdk:latest svenruppert/maven-3.5.4-openjdk:1.14.0-16
    docker push svenruppert/maven-3.5.4-openjdk:1.14.0-16
fi