package com.nttdata.bootcamp.banking.model.dto;

import com.nttdata.bootcamp.banking.model.document.Movement;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class AccountDto {

    private String accountNumber;
    private String accountInterbankNumber;
    private String codeClient;
    private String codeProduct;
    private double creditLine;
    private double availableAmount;
    private Flux<Movement> movements;

}
