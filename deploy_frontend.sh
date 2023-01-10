#!/usr/bin/env sh
mvn clean install
# shellcheck disable=SC2164
cd frontend
mvn spring-boot:run
