# spring-boot-grpc-retry

### spring-boot-grpc-retry-client

Build ```mvn clean package -DskipTests=true```

Run this using ```mvn spring-boot:run --debug```

**Console output**
```
pring-boot-grpc-retry/spring-boot-grpc-retry-client/target/classes started by sandeep in /home/sandeep/private/.repository/spring-boot-grpc-retry/spring-boot-grpc-retry-client)
2024-12-06 13:11:51.023  INFO 1829373 --- [           main] g.r.SpringBootGrpcRetryClientApplication : No active profile set, falling back to 1 default profile: "default"
2024-12-06 13:11:51.540  INFO 1829373 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9001 (http)
2024-12-06 13:11:51.545  INFO 1829373 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-12-06 13:11:51.545  INFO 1829373 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.83]
2024-12-06 13:11:51.597  INFO 1829373 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-12-06 13:11:51.598  INFO 1829373 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 552 ms
2024-12-06 13:11:51.931  INFO 1829373 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint(s) beneath base path '/actuator'
2024-12-06 13:11:51.952  INFO 1829373 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9001 (http) with context path ''
2024-12-06 13:11:51.959  INFO 1829373 --- [           main] g.r.SpringBootGrpcRetryClientApplication : Started SpringBootGrpcRetryClientApplication in 1.131 seconds (JVM running for 1.317)
2024-12-06 13:11:52.216  INFO 1829373 --- [           main] g.r.SpringBootGrpcRetryClientApplication : Finally : Message received: Warning - Heavy rains expected
Exception in thread "main" io.grpc.StatusRuntimeException: UNAVAILABLE: Service temporarily unavailable...
        at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:268)
        at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:249)
        at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:167)
        at org.ranasoftcraft.com.grpc.retry.NotificationServiceGrpc$NotificationServiceBlockingStub.notify(NotificationServiceGrpc.java:160)
        at org.ranasoftcraft.com.grpc.retry.config.GrpcClientBuilder.sendNotification(GrpcClientBuilder.java:65)
        at org.ranasoftcraft.com.grpc.retry.config.GrpcClientBuilder.broadcastMessage(GrpcClientBuilder.java:58)
        at org.ranasoftcraft.com.grpc.retry.SpringBootGrpcRetryClientApplication.main(SpringBootGrpcRetryClientApplication.java:27)


```


### spring-boot-grpc-retry-server

Build ```mvn clean package -DskipTests=true```

Run this using ```mvn spring-boot:run --debug```

**Console output**
```
2024-12-06 13:11:52.205  INFO 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : message:Heavy rains expected     message type:Warning    message id:71591
2024-12-06 13:11:52.205 ERROR 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : Will work only when the random number 71 is prime
2024-12-06 13:11:52.221  INFO 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : message:Heavy rains expected     message type:Warning    message id:21487
2024-12-06 13:11:52.221 ERROR 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : Will work only when the random number 18 is prime
2024-12-06 13:11:52.221 ERROR 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : Service temporarily unavailable would go for retry if the policy permits
2024-12-06 13:11:52.603  INFO 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : message:Heavy rains expected     message type:Warning    message id:21487
2024-12-06 13:11:52.603 ERROR 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : Will work only when the random number 12 is prime
2024-12-06 13:11:52.603 ERROR 1824386 --- [ault-executor-1] o.r.c.g.r.c.NotificationServiceImpl      : Service temporarily unavailable would go for retry if the policy permits

```