# What is this?

This is an extremely lightweight logging server.
Send payloads using restful post requets, the server will log them in a text file.
Comes with the minimal amount of dependancies and features, optimised for speed, security and simplicity.

# Installing

This install requires Maven (mvn) and docker.

Move to the maven project source directory and build an executable jar file:
```
cd LoggingServer
mvn clean
mvn build package
```

If you wish, the jar can be run bare metal, you would just need to create a log folder next to it:
```
cd target
mkdir logs
java -jar LoggingServer.jar [optional:Path/to/config.properties]
```

Otherwise, you may create a docker image with a volume for the logs folder.
```
docker build -t name/loggingserver:latest .
```

You can run the docker image by creating a container and running it:
```
docker run -p 8000:8000 -v logsvolume:/logs name/loggingserver:latest
```
The log files will be in a docker volume with the same name as above.
On windows with Docker Engine v20.10.16 and above, they can be accessed in :
```
\\wsl$\docker-desktop-data\data\docker\volumes
```