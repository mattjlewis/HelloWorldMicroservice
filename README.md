# HelloWorldMicroservice - An Example Microservice Based Application

## Prepare the Host Machine

As docker-engine is only supported on Windows 10 and I am running Windows 7 I need a Linux Virtual Machine to act as my docker engine.
I could create my own Linux Virtual Machine and install docker-engine on that, however, docker-machine makes this much easier.
Make sure you have [VirtualBox](https://www.virtualbox.org/wiki/Downloads) installed.

Docker Machine creates a light-weight Virtual Machine based on [boot2docker](http://boot2docker.io/) for hosting containers.
If on Windows or Mac install [docker-toolbox](https://www.docker.com/products/docker-toolbox) (I selected Docker Client and Docker Machine).

Create a new docker host machine and log in to it:
```
docker-machine create --virtualbox-memory "4096"
docker-machine ssh
```

Download the projects:
```
git clone https://github.com/mattjlewis/HelloWorldMicroservice
cd HelloWorldMicroservice
```

## Docker Stuff

Create the user-defined bridge network (so we get embedded DNS):
```
docker network create --driver bridge hwms_nw
```

Build the machines:
```
docker build -t hwms_db docker-images/hwms_db
docker build -t hwms .
docker build -t hwms_rest docker-images/hwms_rest
docker build -t hwms_web docker-images/hwms_web
docker build -t hwms_sbweb docker-images/hwms_sbweb
```

Start the database:
```
docker run --detach --network=hwms_nw --hostname hwmsdb --name hwmsdb --publish 3306:3306 --volume /home/docker/datadir:/var/lib/mysql hwms_db
```

Start the NGINX load balancer:
```
docker run --detach --network=hwms_nw --hostname hwmslb --name hwmslb --publish 8080:80 --volume /home/docker/HelloWorldMicroservice/nginx.conf.d:/etc/nginx/conf.d:ro nginx
```

Start the REST services tier:
```
docker run --detach --network=hwms_nw --hostname hwmsrest1 --name hwmsrest1 hwms_rest
docker run --detach --network=hwms_nw --hostname hwmsrest2 --name hwmsrest2 hwms_rest
```

Start the Spark Framework web tier:
```
docker run --detach --network=hwms_nw --hostname hwmsweb1 --name hwmsweb1 hwms_web
docker run --detach --network=hwms_nw --hostname hwmsweb2 --name hwmsweb2 hwms_web
```

Start the Spring Boot web tier:
```
docker run --detach --network=hwms_nw --hostname hwmssbweb1 --name hwmssbweb1 hwms_sbweb
docker run --detach --network=hwms_nw --hostname hwmssbweb2 --name hwmssbweb2 hwms_sbweb
```

Test:
```
curl http://localhost:8080/service/users
```

Access the [Browser UI](http://localhost:8080/) via NGINX.

## TODO

1. Deploy to docker swarm and use ```docker service scale```
1. Use docker-compose
1. Use ```store/oracle/serverjre:8``` rather than the unsupported ```isuper/java-oracle```
