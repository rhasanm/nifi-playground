docker-run:
	docker run --name nifi \
  -p 8443:8443 \
  -d \
  -e SINGLE_USER_CREDENTIALS_USERNAME=admin \
  -e SINGLE_USER_CREDENTIALS_PASSWORD=ctsBtRBKHRAx69EqUghvvgEvjnaLjFEB \
  apache/nifi:latest

# Makefile for SSHD container setup and SSH tunneling
.PHONY: start-sshd-container create-ssh-tunnel stop-sshd-container

include .env

start-sshd-container:
	docker-compose up sshd-container -d

create-ssh-tunnel:
	docker exec -it $$(docker-compose ps -q sshd-container) \
		ssh -N -4 -L $(LOCAL_PORT):$(REMOTE_LOCAL_IP):$(REMOTE_PORT) -i ${SSH_PRIVATE_KEY} ${SSH_USER}@${REMOTE_IP} -p ${REMOTE_SSH_PORT}

stop-sshd-container:
	docker-compose down

