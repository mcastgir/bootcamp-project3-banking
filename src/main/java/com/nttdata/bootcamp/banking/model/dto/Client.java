package com.nttdata.bootcamp.banking.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Client {

    @Id
    private String id;
    private String code;
    private String name;
    private String codeDocumentType;
    private String documentNumber;
    private String codeClientType;
    private String phoneNumber;
    private String email;
    private boolean state;

}
