#!/bin/sh
#


mvn install package -DskipTests
java -Xmx1G -jar target/barefoot-0.1.5-matcher-jar-with-dependencies.jar config/server.properties local/database.properties

