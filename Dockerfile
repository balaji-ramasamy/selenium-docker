FROM bellsoft/liberica-openjdk-alpine:21
# install curl and jq
RUN apk add curl jq
#workspace
WORKDIR /home/selenium-docker
#add the required files
ADD target/docker-resources ./
ADD runner.sh runner.sh

#start the runner sh
ENTRYPOINT sh runner.sh