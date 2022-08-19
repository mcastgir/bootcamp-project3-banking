package com.nttdata.bootcamp.banking.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Product {

    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private String codeProductType;
    private boolean movementControl;
    private int movementCountMax;
    private double monthlyCommission;
    private boolean state;

}