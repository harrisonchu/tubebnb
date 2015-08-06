#! bin/bash

JAR_FILE=$(ls ./target/tubebnb-*.jar)

JAVA_CLASSPATH="$JAR_FILE:src/test/resources"

java -server -classpath $JAVA_CLASSPATH com.tubemogul.platform.tubebnb.TubeBnbServer server  ./src/main/config/tubebnb.local.yaml

