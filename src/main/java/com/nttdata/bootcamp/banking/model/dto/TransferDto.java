package com.nttdata.bootcamp.banking.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransferDto {

    private String codeAccountOrigin;
    private String codeAccountDestination;
    private double amountTransfer;
    private String codeTypeTransfer;

}
