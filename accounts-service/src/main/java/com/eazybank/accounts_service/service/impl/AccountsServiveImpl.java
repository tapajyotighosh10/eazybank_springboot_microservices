package com.eazybank.accounts_service.service.impl;

import com.eazybank.accounts_service.constants.AccountsConstants;
import com.eazybank.accounts_service.dto.CustomerDto;
import com.eazybank.accounts_service.entity.Accounts;
import com.eazybank.accounts_service.entity.Customer;
import com.eazybank.accounts_service.exception.CustomerAlreadyExistsException;
import com.eazybank.accounts_service.mapper.CustomerMapper;
import com.eazybank.accounts_service.repository.AccountsRepository;
import com.eazybank.accounts_service.repository.CustomerRepository;
import com.eazybank.accounts_service.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiveImpl implements AccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists with the given mobile number: "+customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomNumber = (long) Math.floor(Math.random() * 900000) + 100000;

        newAccounts.setAccountNumber(randomNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setCreatedBy("Anonymous");
        return newAccounts;
    }
}
