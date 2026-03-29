#!/bin/bash
docker exec -it jpetstore cp -r /usr/src/myapp/src/main/webapp/. /usr/src/myapp/target/cargo/configurations/tomcat9x/webapps/jpetstore/
echo "✅ Site mis à jour ! Fais F5 sur ton navigateur."
