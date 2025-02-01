package com.eazybank.accounts_service.service;

import com.eazybank.accounts_service.dto.CustomerDto;

public interface AccountsService {
    void createAccount(CustomerDto customerDto);
}
