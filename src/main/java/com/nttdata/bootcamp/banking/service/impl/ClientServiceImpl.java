package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dto.Client;
import com.nttdata.bootcamp.banking.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ClientServiceImpl implements ClientService {

    private static final String API_CLIENT = "/api/clients";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    @Qualifier("WcClient")
    private WebClient wcClient;

    @Override
    public Mono<Client> findByCode(String code) {
        return wcClient.get()
                .uri (API_CLIENT.concat("/findByCode/").concat(code))
                .retrieve()
                .bodyToMono(Client.class)
                .timeout(Duration.ofMillis(10_000))
                .doFirst(() -> LOGGER.info("Begin FindByCode Client"))
                .doOnNext(c -> LOGGER.info(c.toString()))
                .doAfterTerminate(() -> LOGGER.info("Finish FindByCode Client"));
    }

}
