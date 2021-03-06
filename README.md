spring-micrometer-prometheus
---

### How to run on your local

```
mvn clean package

java -jar target/spring-micrometer-prometheus.jar
```

#### Send some traffic to the `test` api:

```
brew install hey

hey http://localhost:8080/api/test

```

Prometheus metrics are available at: [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

To run the latest image in K8S cluster:

### Start minikube

```
minikube start --kubernetes-version v1.14.9 --memory=8000 --cpus=4 --vm-driver=hyperkit --disk-size=30g --extra-config=apiserver.enable-admission-plugins="LimitRanger,NamespaceExists,NamespaceLifecycle,ResourceQuota,ServiceAccount,DefaultStorageClass,MutatingAdmissionWebhook"

```

```
kubectl apply -f k8s/kube.yaml
```

Access services running in the `minikube` cluster:

```yaml
#Get the NODEPORT
export PORT=$(kubectl get svc spring-micrometer-prometheus --output=jsonpath="{.spec.ports[0].nodePort}")
 
export MINIKUBE_IP=$(minikube ip)

curl -v http://$MINIKUBE_IP:$PORT/api/test
```

### Install Prom operator on k8s cluster (Prometheus-Operator)

```
# Helm Initialization (skip to "Installing Prometheus Operator" if you already have helm + tiller setup) 
kubectl create serviceaccount tiller --namespace kube-system

kubectl create clusterrolebinding tiller-role-binding --clusterrole cluster-admin --serviceaccount=kube-system:tiller

helm init --service-account tiller

# Installing Prometheus Operator

kubectl create ns monitoring

helm install stable/prometheus-operator --version=8.10.0 --name=monitoring --namespace=monitoring --values=k8s/prom-operator-minikube-values.yaml

```

Make sure the deployment completed successfully by checking status of all components

```
kubectl get all -n monitoring
``` 


#### Access prometheus
Here you can query on the metrics, see all the predefined alerts and Prometheus status and targets.
```
# URL: http://localhost:9090
kubectl port-forward -n monitoring prometheus-monitoring-prometheus-oper-prometheus-0 9090
```

#### Access AlertManager
In the Alertmanager UI you can view alerts received from Prometheus, sort alerts by labels and silence alerts for a given time.

```
# URL: http://localhost:9093/#/alerts
kubectl port-forward -n monitoring alertmanager-monitoring-prometheus-oper-alertmanager-0 9093
```

#### Access Grafana
Here you can look at the dashboards. Grafana has a datasource ready to query on Prometheus.

```
# URL: http://localhost:3000
# Default credentials
# User: admin
# Pass: prom-operator
kubectl port-forward $(kubectl get pods --selector=app.kubernetes.io/name=grafana -n monitoring --output=jsonpath="{.items..metadata.name}") -n monitoring 3000
```


## Docker Commands (might come handy for debugging)

#### Run docker image with volume mount and env variables:

```
docker run -d -e TZ=America/New_York -p 8080:8080 -v <source>:<destination> <docker-image>
```

#### ssh to a running container

```
docker exec -it <container-id> /bin/bash
```

#### Override entry point

```
docker run -d --entrypoint "java -jar app.jar" <docker-image>

docker run -d --entrypoint "sleep 10" <docker-image>

```
