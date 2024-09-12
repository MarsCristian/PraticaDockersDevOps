# Prática de DevOps
## Atividade com Dockers e Kubernetes
Cristian César Martins 799714

Descrição da Aplicação

É uma aplicação Web usando Java, spring, mysql, maven, etc. Se consiste em um site de locação de bicicletas. Neste trabalho realizei um fork da aplicação original ( créditos em  README.md) para aplicar conteinerização com dockers nela.
	A minha versão com dockers terá dois contêineres: App, responsável por pegar as imagens do maven e do openjdk  e o Bd responsável pelo MySql. o Bd não teve necessidade de dockerfile, mas o App sim. Para sanar o problema dos contêineres rodarem ao mesmo tempo e o App não conseguir acessar o Bd, não bastou o “depends-on”, pois ele não garante que o Bd esteja completamente funcional quando inicia o App, então foi feito uma condition que foi o “service_healty” e também um teste simples no Bd, para aí sim rodar o App. Sobre as portas, o docker compose gerencia uma rede interna do dockers, a qual as portas dos contêineres podem acessar entre elas, não necessitando a exposição da porta 3306 do host, o que aumenta a segurança da aplicação.

## Arquivos Docker

- Dockerfile do App em: LocacaoBike/Docker/app/Dockerfile
- Docker-compose em: LocacaoBike/docker-compose.yml
- Créditos da aplicação sem dockers em: README.md (logo abaixo)
- Outras alterações em:  LocacaoBike/src/main/resources/application.properties

## Manual de Instalação

```git clone https://github.com/MarsCristian/PraticaDockersDevOps```

```cd ./PraticaDockersKubernetesDevOps/LocacaoBike```

```minikube status```

```chmod +x deploy.sh```
```chmod +x delete.sh```

```./deploy.sh```

Certifique-se de estar com o Minikube instalado e configurado, para inicializar use o comando “./deploy” e para finalizar “delete.sh”
Acesse a Aplicação em: http://locacaobike.k8s.local


### Certifique que você tem Dockers, Docker-compose e git instalados.
- Clone o repositório:

```git clone https://github.com/MarsCristian/PraticaDockersDevOps```
```cd PraticaDockersDevOps```

- Construa os Contêineres:

```docker-compose build```

- Inicie os Contêineres:

```docker-compose up```

- Acesse a Aplicação:
http://localhost:8080


# Créditos a aplicação original sem o dockers:
# DesWeb1_3
Repositorio criado para a disciplina de Desenvolvimento de Software Web 1 da Universidade Federal de São Carlos.

Neste repositorio, será realizado um trabalho contínuo, sendo dividido em 3 etapas (3 branchs). Posteriormente, será realizado o merge destes brenchs, pois cada etapa depende da etapa anterior. Sendo assim, no final, teremos o trabalho completo no ramo main.

Etapa 1: Java, Servlets, JSP, MVC e JDBC.

Etapa 2: Springer, Ajaxa e JPA.

Etapa 3: REST API

Realizado por :

- Carlos Henrique Renno Matos Filho - https://github.com/Carlos-HRM
- Gabriel Lourenço de Paula Graton - https://github.com/Notargg
- Pietro Minghini Moralles - https://github.com/Pietro-MM
- Rafael Naoki Arakaki Uyeta - https://github.com/Arata2703
- Vitor Matheus da Silva - https://github.com/Portix-S
