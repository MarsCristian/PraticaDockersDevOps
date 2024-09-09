#!/bin/bash

# Construir a imagem da aplicação
docker build -t app-image -f Docker/app/Dockerfile .

# Configurar o Docker para usar o Docker daemon do Minikube
& minikube -p minikube docker-env --shell powershell | Invoke-Expression

# Construir a imagem da aplicação
docker build -t app-image -f Docker/app/Dockerfile .

# Tag da imagem para uso no Minikube
docker tag app-image app:latest
