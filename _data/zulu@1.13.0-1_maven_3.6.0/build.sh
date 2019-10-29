#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/maven-3.6.0-zulu 1.13.0-1; then
    echo skip building, image already existing - svenruppert/maven-3.6.0-zulu 1.13.0-1
else
    echo start building the images
    docker build -t svenruppert/maven-3.6.0-zulu .
    docker tag svenruppert/maven-3.6.0-zulu:latest svenruppert/maven-3.6.0-zulu:1.13.0-1
    docker push svenruppert/maven-3.6.0-zulu:1.13.0-1
fi 
    docker image rm svenruppert/maven-3.6.0-zulu:latest
    docker image rm svenruppert/maven-3.6.0-zulu:1.13.0-1