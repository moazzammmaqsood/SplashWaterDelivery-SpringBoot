package com.splash.utils;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.backblaze.b2.client.contentSources.B2Headers.USER_AGENT;

@Component
public class UtilBeans {


    @Bean
    B2StorageClient getB2StorageClient(){
        B2StorageClient client = B2StorageClientFactory
                .createDefaultFactory()
                .create(Constants.APP_KEY_ID, Constants.APP_KEY, USER_AGENT);
    return client;

    }

}
