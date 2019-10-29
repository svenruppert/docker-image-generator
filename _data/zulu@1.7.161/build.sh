#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/zulu 1.7.161; then
    echo skip building, image already existing - svenruppert/zulu:1.7.161
else
    echo start building the images
    docker build -t svenruppert/zulu .

    docker tag svenruppert/zulu:latest svenruppert/zulu:1.7.161
    docker push svenruppert/zulu:1.7.161

fi 
    docker image rm svenruppert/zulu:latest
    docker image rm svenruppert/zulu:1.7.161