package org.ranasoftcraft.com.grpc.retry.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.ranasoftcraft.com.grpc.retry.NotificationRequest;
import org.ranasoftcraft.com.grpc.retry.NotificationResponse;
import org.ranasoftcraft.com.grpc.retry.NotificationServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class GrpcClientBuilder {

    private static final Logger logger = LoggerFactory.getLogger(GrpcClientBuilder.class);

    @Value("classpath:retry-service-config.json")
    private Resource retryPolicy;

    @Value("${grpc.server.port}")
    private int serverPort;

    protected  Map<String, ?> getServiceConfig() throws IOException {
        return new Gson().fromJson(new JsonReader(new InputStreamReader(retryPolicy.getInputStream(), StandardCharsets.UTF_8)), Map.class);
    }


    public NotificationResponse broadcastMessage() throws IOException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
                .usePlaintext()
                .disableServiceConfigLookUp()
                .defaultServiceConfig(getServiceConfig())
                .enableRetry()
                .build();
        return sendNotification(channel);
    }

    public NotificationResponse sendNotification(ManagedChannel channel) {
        NotificationServiceGrpc.NotificationServiceBlockingStub notificationServiceStub = NotificationServiceGrpc
                .newBlockingStub(channel);

        NotificationResponse response = notificationServiceStub.notify(NotificationRequest.newBuilder()
                .setType("Warning")
                .setMessage("Heavy rains expected")
                .setMessageID(generateMessageID())
                .build());
        channel.shutdown();
        return response;
    }

    private static int generateMessageID() {
        return new Random().nextInt(90000) + 10000;
    }
}
