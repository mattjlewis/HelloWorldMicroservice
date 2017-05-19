box = "http://yum.oracle.com/boxes/oraclelinux/ol73/ol73.box"
name = "HelloWorldMicroservice"
ram = 4096
cpus = 2
local_software_folder = "D:/Devel/Shared/software"

Vagrant.configure("2") do |config|
	config.vm.define name do |nodeconfig|
		nodeconfig.vm.box = box
		nodeconfig.vm.hostname = name
		#nodeconfig.vm.network :private_network, ip: ip
		nodeconfig.vm.synced_folder ".", "/vagrant", disabled: true, type: "virtualbox"
		nodeconfig.vm.synced_folder ".", "/home/vagrant/HelloWorldMicroservice", :create => true, :owner => "vagrant", :group => "vagrant", :mount_options => ["dmode=775", "fmode=775"]
		nodeconfig.vm.synced_folder local_software_folder, "/opt/software", :create => true, :owner => "vagrant", :group => "vagrant", :mount_options => ["dmode=775", "fmode=775"]

		nodeconfig.vm.provider :virtualbox do |vb|
			vb.name = name
			vb.memory = ram
			vb.cpus = cpus
			vb.customize ["modifyvm", :id, "--vram", "9"]
		end
	end
	config.vm.provision "shell", path: "./vagrant_boot.sh", privileged: false, args: [name]
end
