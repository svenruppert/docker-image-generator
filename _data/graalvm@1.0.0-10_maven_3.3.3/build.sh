#!/bin/bash
function docker_tag_exists() {
    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r "[.results | .[] | .name == \"$2\"] | any")
    test $EXISTS = true
}

if docker_tag_exists svenruppert/maven-3.3.3-graalvm 1.0.0-10; then
    echo skip building, image already existing - svenruppert/maven-3.3.3-graalvm 1.0.0-10
else
    echo start building the images
    docker build -t svenruppert/maven-3.3.3-graalvm .
    docker tag svenruppert/maven-3.3.3-graalvm:latest svenruppert/maven-3.3.3-graalvm:1.0.0-10
    docker push svenruppert/maven-3.3.3-graalvm:1.0.0-10
fi