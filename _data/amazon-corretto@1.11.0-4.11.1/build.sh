#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/amazon-corretto 1.11.0-4.11.1; then
    echo skip building, image already existing - svenruppert/amazon-corretto:1.11.0-4.11.1
else
    echo start building the images
    docker build -t svenruppert/amazon-corretto .

    docker tag svenruppert/amazon-corretto:latest svenruppert/amazon-corretto:1.11.0-4.11.1
    docker push svenruppert/amazon-corretto:1.11.0-4.11.1
fi