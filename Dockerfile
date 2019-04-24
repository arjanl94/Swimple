FROM payara/server-full
USER payara

RUN curl http://central.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar -o /opt/payara/appserver/glassfish/domains/production/lib/postgresql-42.2.5.jar
COPY domain.xml /opt/payara/appserver/glassfish/domains/production/config

COPY /target/swimple.war $DEPLOY_DIR
