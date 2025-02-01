package com.eazybank.loans_service.service;


import com.eazybank.loans_service.dto.LoansDto;

public interface LoansService {

    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);

}
