#!/bin/sh

git clone https://github.com/ghusta/docker-postgres-world-db worlddb

cd worlddb

docker build -t world/db .

docker run --publish 9090:8081 --detach world/db