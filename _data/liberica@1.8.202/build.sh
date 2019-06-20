#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/liberica 1.8.202; then
    echo skip building, image already existing
else
    echo start building the images
    docker build -t svenruppert/liberica .

    docker tag svenruppert/liberica:latest svenruppert/liberica:1.8.202
    docker push svenruppert/liberica:1.8.202
fi