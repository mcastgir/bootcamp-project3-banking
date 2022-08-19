package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dto.Product;
import com.nttdata.bootcamp.banking.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String API_PRODUCT = "/api/products";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    @Qualifier("WcProduct")
    private WebClient wcProduct;

    @Override
    public Mono<Product> findByCode(String code) {
        return wcProduct.get()
                .uri (API_PRODUCT.concat("/findByCode/").concat(code))
                .retrieve()
                .bodyToMono(Product.class)
                .timeout(Duration.ofMillis(10_000))
                .doFirst(() -> LOGGER.info("Begin FindByCode Product"))
                .doOnNext(p -> LOGGER.info(p.toString()))
                .doAfterTerminate(() -> LOGGER.info("Finish FindByCode Product"));
    }

}
