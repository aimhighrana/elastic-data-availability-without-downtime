package org.ranasoftcraft.com.grpc.retry;

import org.ranasoftcraft.com.grpc.retry.config.GrpcClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.io.IOException;

@SpringBootApplication
public class SpringBootGrpcRetryClientApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootGrpcRetryClientApplication.class);

    public static void main(String[] args) {
		var app = SpringApplication.run(SpringBootGrpcRetryClientApplication.class, args);

		GrpcClientBuilder clientBuilder = app.getBean(GrpcClientBuilder.class);
        try {
            StopWatch sp = new StopWatch();
            sp.start();
            NotificationResponse notificationResponse =  clientBuilder.broadcastMessage();
            log.info("Finally : " + notificationResponse.getResponse());

            NotificationResponse notificationResponse2 =  clientBuilder.broadcastMessage();
            log.info("Finally : " + notificationResponse2.getResponse());


            NotificationResponse notificationResponse3 =  clientBuilder.broadcastMessage();
            log.info("Finally : " + notificationResponse3.getResponse());


            sp.stop();
            log.info(sp.prettyPrint());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
