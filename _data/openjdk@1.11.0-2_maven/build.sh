#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/maven-3.6.1-openjdk 1.11.0-2; then
    echo skip building, image already existing
else
    echo start building the images
    docker build -t svenruppert/maven-3.6.1-openjdk .
    docker tag svenruppert/maven-3.6.1-openjdk:latest svenruppert/maven-3.6.1-openjdk:1.11.0-2
    docker push svenruppert/maven-3.6.1-openjdk:1.11.0-2
fi