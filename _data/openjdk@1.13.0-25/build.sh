#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/openjdk 1.13.0-25; then
    echo skip building, image already existing
else
    echo start building the images
    docker build -t svenruppert/openjdk .

    docker tag svenruppert/openjdk:latest svenruppert/openjdk:1.13.0-25
    docker push svenruppert/openjdk:1.13.0-25
fi