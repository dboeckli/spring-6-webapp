# Spring 6 Web Application

## Overview
This project is a Spring Boot 4.0.0 web application. It serves as a template or starting point for building web applications using the Spring Framework.

## Prerequisites
- Java 21
- Maven 3.x

## Available Endpoints via browser
The application provides two main endpoints:
- `http://localhost:8080/authors` - Displays information about authors
- `http://localhost:8080/books` - Displays information about books

- `http://localhost:30080/authors` - Displays information about authors
- `http://localhost:30080/books` - Displays information about books

- `http://localhost:8080/h2-console` - h2 console

## Deployment with Helm

Be aware that we are using a different namespace here (not default).

To run maven filtering for destination target/helm
```bash
mvn clean install -DskipTests 
```

Go to the directory where the tgz file has been created after 'mvn install'
```powershell
cd target/helm/repo
```

unpack
```powershell
$file = Get-ChildItem -Filter spring-6-webapp-v*.tgz | Select-Object -First 1
tar -xvf $file.Name
```

install
```powershell
$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace spring-6-webapp --create-namespace --wait --timeout 8m --debug --render-subchart-notes
```

show logs
```powershell
kubectl get pods -l app.kubernetes.io/name=$APPLICATION_NAME -n spring-6-webapp
```
replace $POD with pods from the command above
```powershell
kubectl logs $POD -n spring-6-webapp --all-containers
```

test
```powershell
helm test $APPLICATION_NAME --namespace spring-6-webapp --logs
```

uninstall
```powershell
helm uninstall $APPLICATION_NAME --namespace spring-6-webapp
```

delete all
```powershell
kubectl delete all --all -n spring-6-webapp
```

delete all
```powershell
kubectl delete all --all -n kbe-brewery-order-micro-service
```

create busybox sidecar
```powershell
kubectl run busybox-test --rm -it --image=busybox:1.36 --namespace=kbe-brewery-order-micro-service --command -- sh
```

You can use the actuator rest call to verify via port 30090


