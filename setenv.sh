#!/bin/bash
while read line; do
  heroku config:set "$line" -a twitchtrendz
done < .env
