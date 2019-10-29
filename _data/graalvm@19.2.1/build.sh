#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/graalvm 19.2.1; then
    echo skip building, image already existing - svenruppert/graalvm:19.2.1
else
    echo start building the images
    docker build -t svenruppert/graalvm .

    docker tag svenruppert/graalvm:latest svenruppert/graalvm:19.2.1
    docker push svenruppert/graalvm:19.2.1

fi 
    docker image rm svenruppert/graalvm:latest
    docker image rm svenruppert/graalvm:19.2.1