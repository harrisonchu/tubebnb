#!/bin/bash

JAR_FILE=$(ls ./target/tubebnb-*.jar)

JAVA_CLASSPATH="$JAR_FILE:src/test/resources"

java -server -classpath $JAVA_CLASSPATH -Dtm-devops.defaults.file=src/test/resources/local-environment.properties com.tubemogul.platform.tubebnb.TubeBnbServer server  ./src/test/resources/test-tubebnb.local.yaml

