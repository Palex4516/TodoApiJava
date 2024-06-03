#!/usr/bin/env bash

curl --request GET -sL \
     --url 'https://raw.githubusercontent.com/Palex4516/TodoApi/main/openapi.json'\
     --output 'todoapi_openapi.json'