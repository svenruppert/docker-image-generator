#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/adopt 1.11.0-0; then
    echo skip building, image already existing - svenruppert/adopt:1.11.0-0
else
    echo start building the images
    docker build -t svenruppert/adopt .

    docker tag svenruppert/adopt:latest svenruppert/adopt:1.11.0-0
    docker push svenruppert/adopt:1.11.0-0

fi 
    docker image rm svenruppert/adopt:latest
    docker image rm svenruppert/adopt:1.11.0-0