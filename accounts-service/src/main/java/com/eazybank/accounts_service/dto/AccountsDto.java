package com.eazybank.accounts_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AccountsDto {

    private Long accountNumber;


    private String accountType;


    private String branchAddress;
}
