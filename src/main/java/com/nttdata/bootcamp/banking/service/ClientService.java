package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.dto.Client;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<Client> findByCode(String code);

}
