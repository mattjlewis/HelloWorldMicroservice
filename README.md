# HelloWorldMicroservice - An Example Microservice Based Application

## Preparation
There are two choices for setting up a docker host on Windows 7 and 8; both require [VirtualBox](https://www.virtualbox.org/wiki/Downloads) to be installed. Docker Machine is by far the simplest solution.

### 1. Create your own Linux virtual server

Easiest way is via [Vagrant](https://www.vagrantup.com/downloads.html). Change directory to HelloWorldMicroservice then start and configure the OEL 7 virtual machine:
```
vagrant up
vagrant ssh
cd HelloWorldMicroservice/playbook
ansible-playbook site.yaml
```

### 2. Docker Machine

Docker Machine creates a light-weight virtual machine based on [boot2docker](http://boot2docker.io/) for hosting containers and installs the docker client. If on Windows or Mac install [docker-toolbox](https://www.docker.com/products/docker-toolbox) (I only selected Docker Client and Machine).
```
docker-machine create --virtualbox-memory "4096"
```

## Docker Stuff

Build the machines:
```
docker build -t hwms .
docker build -t hwms_rest docker-images/hwms_rest
docker build -t hwms_web docker-images/hwms_web
```

Create the user-defined bridge network (so we get embedded DNS):
```
docker network create --driver bridge hwms_nw
```

Start the machines:
```
docker run --detach --network=hwms_nw --publish 8010:8010 --hostname hwmsrest1 --name hwmsrest1 hwms_rest
docker run --detach --network=hwms_nw --publish 8020:8020 --hostname hwmsweb1 --name hwmsweb1 hwms_web
docker run --detach --network=hwms_nw --publish 8080:80 -v /home/docker/nginx.conf.d:/etc/nginx/conf.d:ro --hostname hwmslb1 --name hwmslb1 nginx
```

Test:
```
curl http://localhost:8010/service/users
curl http://localhost:8080/service/users
```

Access the Browser UI [direct](http://localhost:8020/), [via NGINX](http://localhost:8080/).
