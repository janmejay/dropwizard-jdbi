package com.example.remote;

import com.example.core.CartUpdateMessage;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import java.io.IOException;

public class NotificationService {
    private final CloseableHttpClient httpClient;

    private final Logger logger = LoggerFactory.getLogger("notifications");

    public NotificationService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Void sendCartUpdateMessage(CartUpdateMessage cartUpdateMessage) {
        logger.info("Sending notification: " + cartUpdateMessage);
        try {
            HttpPost request = new HttpPost("/notify");
            request.setEntity(new StringEntity(cartUpdateMessage.toString()));
            CloseableHttpResponse notificationResponse = httpClient.execute(new HttpHost("localhost", 9090), request);
            int status = notificationResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                logger.warn(String.format("Notification failed with status-code: %s.", status));
            }
        } catch (IOException e) {
            logger.warn("Notification failed.", e);
        }
        return null;
    }
}
