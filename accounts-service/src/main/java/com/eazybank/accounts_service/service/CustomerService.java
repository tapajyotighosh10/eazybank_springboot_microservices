package com.eazybank.accounts_service.service;

import com.eazybank.accounts_service.dto.CustomerDetailsDto;

public interface CustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
