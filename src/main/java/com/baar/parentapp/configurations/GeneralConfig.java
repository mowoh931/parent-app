package com.baar.parentapp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeneralConfig {


    public WebClient webClient(){
        return  WebClient.create();
    }
}
