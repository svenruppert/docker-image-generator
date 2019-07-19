#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/maven-3.5.4-zulu 1.10.0; then
    echo skip building, image already existing - svenruppert/maven-3.5.4-zulu 1.10.0
else
    echo start building the images
    docker build -t svenruppert/maven-3.5.4-zulu .
    docker tag svenruppert/maven-3.5.4-zulu:latest svenruppert/maven-3.5.4-zulu:1.10.0
    docker push svenruppert/maven-3.5.4-zulu:1.10.0
fi