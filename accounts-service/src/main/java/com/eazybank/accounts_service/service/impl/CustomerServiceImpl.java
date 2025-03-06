package com.eazybank.accounts_service.service.impl;

import com.eazybank.accounts_service.dto.AccountsDto;
import com.eazybank.accounts_service.dto.CardsDto;
import com.eazybank.accounts_service.dto.CustomerDetailsDto;
import com.eazybank.accounts_service.dto.LoansDto;
import com.eazybank.accounts_service.entity.Accounts;
import com.eazybank.accounts_service.entity.Customer;
import com.eazybank.accounts_service.exception.ResourceNotFoundException;
import com.eazybank.accounts_service.mapper.AccountsMapper;
import com.eazybank.accounts_service.mapper.CustomerMapper;
import com.eazybank.accounts_service.repository.AccountsRepository;
import com.eazybank.accounts_service.repository.CustomerRepository;
import com.eazybank.accounts_service.service.CustomerService;
import com.eazybank.accounts_service.service.client.CardsFeignClient;
import com.eazybank.accounts_service.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
