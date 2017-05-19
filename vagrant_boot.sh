#!/bin/bash

function change_vagrant_password() {
	echo "vagrant" | sudo passwd --stdin vagrant
}

function configure_sshd() {
	sudo sed -i 's|[#]*PasswordAuthentication no|PasswordAuthentication yes|g' /etc/ssh/sshd_config
	sudo systemctl restart sshd.service
}

function install_ansible() {
	epel=epel-release-latest-7
	epel_rpm=${epel}.noarch.rpm
	rpm --quiet -q epel-release-7
	if [ $? -ne 0 ]; then
		wget https://dl.fedoraproject.org/pub/epel/${epel_rpm} && sudo rpm --quiet -ivh ${epel_rpm}
	fi

	yum --quiet list installed ansible > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		sudo yum -y --quiet install ansible libselinux-python policycoreutils-python python-netaddr
	fi
}

function prepare_ssh_key() {
	if [ ! -f ~/.ssh/id_rsa ]; then
		ssh-keygen -N "" -f ~/.ssh/id_rsa
	fi
}

function distribute_ssh_key() {
	for host in $1; do
		sshpass -p vagrant ssh -q -oStrictHostKeyChecking=no ${host} "uname -a" && sshpass -p vagrant ssh-copy-id ${host}
	done
	return 0
}

if [ $# -lt 1 ]; then
	echo "Usage: $0 <ansible-controller>"
	exit 1
fi

ansible_controller=$1
configure_sshd
if [ $(hostname) == "${ansible_controller}" ]; then
	echo "Running bootstrap script on Ansible controller node"
	install_ansible
	prepare_ssh_key
	distribute_ssh_key ${ansible_controller}
fi
