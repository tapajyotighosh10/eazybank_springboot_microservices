package com.eazybank.accounts_service.service;

import com.eazybank.accounts_service.dto.CustomerDto;

public interface AccountsService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
