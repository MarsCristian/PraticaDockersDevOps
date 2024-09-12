# Ambiente dockers e minikube
docker context use default

# Exibe mensagem para configurar o Docker do Minikube
Write-Host "Configurando Docker para usar o Docker daemon do Minikube..."
& minikube -p minikube docker-env --shell powershell | Invoke-Expression

# Exibe mensagem para construção da imagem
Write-Host "Construindo a imagem da aplicação..."
docker build -t app -f Docker/app/Dockerfile .

# Exibe mensagem para taguear a imagem
Write-Host "Tagueando a imagem para uso no Minikube..."
docker tag app app:latest

# INICIANDO CLUSTER
Write-Host "INICIANDO MINIKUBE"
minikube start

minikube addons enable ingress
minikube addons enable dashboard
minikube addons enable metrics-server

# Aplicar os arquivos de configuração do Kubernetes
cd ./K8s/db/
kubectl apply -f secret.yaml; kubectl create -f pv.yaml; kubectl create -f pvc.yaml;
kubectl apply -f deployment.yaml; kubectl apply -f service.yaml
cd ..
cd ./app/
kubectl apply -f deployment.yaml; kubectl apply -f service.yaml

cd ..
kubectl apply -f ingress.yaml
