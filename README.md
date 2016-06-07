# remote-ejb

Configure 2 Wildfly instances according to https://docs.jboss.org/author/display/WFLY8/EJB+invocations+from+a+remote+server+instance?_sscc=t .

# Client instance
Port offset: 10100

# Server instance
Port offset: 10000

Alternatively there is also standalone-client project which is directly taken from wildfly samples and slightly modified to call Greeter. This can be started via mvn clean install exec:exec

