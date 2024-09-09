#!/bin/bash

# Aplicar os arquivos de configuração do Kubernetes
kubectl apply -f mysql-deployment.yaml
kubectl apply -f app-deployment.yaml

# Habilitar o Ingress Controller no Minikube (caso ainda não esteja habilitado)
minikube addons enable ingress

# Aplicar o Ingress
kubectl apply -f ingress.yaml

# Adicionar k8s.local ao arquivo /etc/hosts para acessar a aplicação localmente
$ip = minikube ip
$hostsPath = "$env:SystemRoot\System32\drivers\etc\hosts"
Add-Content -Path $hostsPath -Value "$ip k8s.local"
