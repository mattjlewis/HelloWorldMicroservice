# HelloWorldMicroservice - An Example Microservice Based Application

## Step 1: Prepare the Host Machine

As docker-engine is only supported on Windows 10 and I am running Windows 7 I need a Linux Virtual Machine to act as my docker engine.
I could create my own Linux Virtual Machine and install docker-engine on that, however, docker-machine makes this much easier.
Make sure you have [VirtualBox](https://www.virtualbox.org/wiki/Downloads) installed.

Docker Machine creates a light-weight Virtual Machine based on [boot2docker](http://boot2docker.io/) for hosting containers.
If on Windows or Mac install [docker-toolbox](https://www.docker.com/products/docker-toolbox) (I selected Docker Client and Docker Machine).

Create a new docker machine:
```
docker-machine create --virtualbox-memory "4096"
```

## Docker Stuff

Download the projects:
```
git clone https://github.com/mattjlewis/HelloWorldMicroservice
cd HelloWorldMicroservice
```

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
```

Start the database:
```
docker run --detach --network=hwms_nw --hostname hwmsdb --name hwmsdb --publish 3306:3306 --volume /home/docker/datadir:/var/lib/mysql hwms_db
```

Start the NGINX load balancer:
```
docker run --detach --network=hwms_nw --hostname hwmslb1 --name hwmslb1 --publish 8080:80 --volume /home/docker/HelloWorldMicroservice/nginx.conf.d:/etc/nginx/conf.d:ro nginx
```

Start the REST services tier:
```
docker run --detach --network=hwms_nw --hostname hwmsrest1 --name hwmsrest1 hwms_rest
docker run --detach --network=hwms_nw --hostname hwmsrest2 --name hwmsrest2 hwms_rest
```

Start the web tier:
```
docker run --detach --network=hwms_nw --hostname hwmsweb1 --name hwmsweb1 hwms_web
docker run --detach --network=hwms_nw --hostname hwmsweb2 --name hwmsweb2 hwms_web
```

Test:
```
curl http://localhost:8080/service/users
```

Access the [Browser UI](http://localhost:8080/) via NGINX.
