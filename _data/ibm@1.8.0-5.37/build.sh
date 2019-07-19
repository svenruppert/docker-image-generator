#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/ibm 1.8.0-5.37; then
    echo skip building, image already existing - svenruppert/ibm:1.8.0-5.37
else
    echo start building the images
    docker build -t svenruppert/ibm .

    docker tag svenruppert/ibm:latest svenruppert/ibm:1.8.0-5.37
    docker push svenruppert/ibm:1.8.0-5.37
fi