#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/liberica 1.13.0; then
    echo skip building, image already existing - svenruppert/liberica:1.13.0
else
    echo start building the images
    docker build -t svenruppert/liberica .

    docker tag svenruppert/liberica:latest svenruppert/liberica:1.13.0
    docker push svenruppert/liberica:1.13.0

fi 
    docker image rm svenruppert/liberica:latest
    docker image rm svenruppert/liberica:1.13.0