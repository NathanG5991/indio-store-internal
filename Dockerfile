#
#    Copyright 2010-2023 the original author or authors.
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#       https://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#

# Utilise un serveur Tomcat officiel prêt à l'emploi avec Java 17
FROM tomcat:9.0-jre17

# Supprime les applications par défaut de Tomcat pour faire place nette
RUN rm -rf /usr/local/tomcat/webapps/*

# Copie le fichier .war généré par Jenkins directement dans le dossier de Tomcat
# (On le renomme ROOT.war pour qu'il réponde sur l'URL / au lieu de /jpetstore)
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose le port 8080
EXPOSE 8080

# Démarre Tomcat
CMD ["catalina.sh", "run"]
