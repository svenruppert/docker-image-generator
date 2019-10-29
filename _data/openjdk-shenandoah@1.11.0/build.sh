#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/openjdk-shenandoah 1.11.0; then
    echo skip building, image already existing - svenruppert/openjdk-shenandoah:1.11.0
else
    echo start building the images
    docker build -t svenruppert/openjdk-shenandoah .

    docker tag svenruppert/openjdk-shenandoah:latest svenruppert/openjdk-shenandoah:1.11.0
    docker push svenruppert/openjdk-shenandoah:1.11.0

fi 
    docker image rm svenruppert/openjdk-shenandoah:latest
    docker image rm svenruppert/openjdk-shenandoah:1.11.0