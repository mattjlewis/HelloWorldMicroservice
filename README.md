# HelloWorldMicroservice - An Example Microservice Based Application

## Preparation
Two mechanisms, both require VirtualBox to be installed.

### 1. vagrant

Install [Vagrant](http://vagrantup.com/).
Change directory to HelloWorldMicroservice
'''
vagrant up
vagrant ssh
cd HelloWorldMicroservice/playbook
ansible-playbook site.yaml
'''

### 2. docker-machine

If on Windows or Mac install [docker-toolbox](https://www.docker.com/products/docker-toolbox) (I only installed Docker Client and Machine).
'''
docker-machine create
'''

## Docker Stuff

Build the machines:
'''
docker build -t hwms .
docker build -t hwms_rest docker-images/hwms_rest
docker build -t hwms_web docker-images/hwms_web
'''

Create the user-defined bridge network (so we get embedded DNS):
'''
docker network create --driver bridge hwms_nw
'''

Start the machines:
'''
docker run --detach --network=hwms_nw --publish 8080:8080 --hostname hwmsrest1 --name hwmsrest1 hwms_rest
docker run --detach --network=hwms_nw --publish 4567:4567 --hostname hwmsweb1 --name hwmsweb1 hwms_web
'''

Test:
'''
curl http://localhost:8080/service/users
'''

Access the [Browser UI](http://localhost:4567/).
