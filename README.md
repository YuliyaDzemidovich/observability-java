## Observability with Java

### Stack:  
Java 21  
Spring Boot 3.5  
Maven  

### Observability components:  
Loki  
Tempo  
Prometheus  
Grafana  

### Local start

1. Build project:
``` 
mvn clean install
```

2. Download [latest](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar) OpenTelemetry agent for Java
3. Add VM options in your run configuration for OpenTelemetry agent:
```
-javaagent:${absolute_path_here}/opentelemetry-javaagent.jar
-Dotel.service.name=observability-app
-Dotel.exporter.otlp.endpoint=http://localhost:4318
-Dotel.exporter.otlp.protocol=http/protobuf
-Dotel.traces.exporter=otlp
-Dotel.metrics.exporter=none
-Dotel.logs.exporter=none
-Dotel.instrumentation.logback-mdc.enable=true
```
4. Start observability stack in docker-compose:
```
docker-compose up -d
```
5. Start Java application on localhost:8080 with VM options from #3
