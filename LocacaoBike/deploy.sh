#!/bin/bash

################################################
# Inicializando o Minikube
################################################
echo ""; echo "-=-=-=-=-= Inicializando Minikube -=-=-=-=-="; echo "";
minikube start

################################################
# Habilitando o Ingress
################################################
echo ""; echo "-=-=-=-=-= Ingress e DashBoard -=-=-=-=-="; echo "";
minikube image load k8s.gcr.io/ingress-nginx/controller:v1.9.4

minikube addons enable ingress

################################################
# Inicializando o DashBoard
################################################
minikube addons enable dashboard
minikube addons enable metrics-server
minikube dashboard &

################################################
# Build APP
################################################
echo ""; echo "-=-=-=-=-= Build APP -=-=-=-=-="; echo "";
docker context use default
eval $(minikube docker-env)
docker build -t app-image -f Docker/app/Dockerfile .

################################################
# Load Images
################################################
echo ""; echo "-=-=-=-=-= Load Images -=-=-=-=-="; echo "";
minikube image load app-image
minikube image load mysql:8.0

################################################
# Setup
################################################
echo ""; echo "-=-=-=-=-= Setup -=-=-=-=-="; echo "";
kubectl create -f K8s/db/pv.yaml
kubectl create -f K8s/db/pvc.yaml

kubectl apply -f K8s/db/configmap.yaml
kubectl apply -f K8s/db/secret.yaml
kubectl apply -f K8s/db/deployment.yaml
kubectl apply -f K8s/db/service.yaml
################################################
kubectl apply -f K8s/app/deployment.yaml
kubectl apply -f K8s/app/service.yaml
################################################
kubectl apply -f K8s/ingress.yaml
