#!/bin/bash
################################################
# delete
################################################
echo ""; echo "-=-=-=-=-= Delete -=-=-=-=-="; echo "";
kubectl delete -f K8s/db/pv.yaml
kubectl delete -f K8s/db/pvc.yaml

kubectl delete -f K8s/db/configmap.yaml
kubectl delete -f K8s/db/secret.yaml
kubectl delete -f K8s/db/deployment.yaml
kubectl delete -f K8s/db/service.yaml
################################################
kubectl delete -f K8s/app/deployment.yaml
kubectl delete -f K8s/app/service.yaml
################################################
kubectl delete -f K8s/ingress.yaml

minikube stop
