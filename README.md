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

Prometheus metrics are available at: [http://localhost:8080/prom](http://localhost:8080/prom)

To run the latest image in K8S cluster:

```
kubectl apply -f kube.yaml
```