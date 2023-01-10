#!/usr/bin/env sh
mvn clean install
# shellcheck disable=SC2164
cd backend
mvn spring-boot:run
