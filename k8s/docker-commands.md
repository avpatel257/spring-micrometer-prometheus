### Run docker image with volume mount and env variables:

```
docker run -d -e TZ=America/New_York -p 8080:8080 -v <source>:<destination> <docker-image>
```

### ssh to a running container

```
docker exec -it <container-id> /bin/bash
```

### Override entry point

```
docker run -d --entrypoint "java -jar app.jar" <docker-image>

docker run -d --entrypoint "sleep 10" <docker-image>

```
