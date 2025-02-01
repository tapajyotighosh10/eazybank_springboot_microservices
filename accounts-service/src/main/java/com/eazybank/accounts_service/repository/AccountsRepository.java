package com.eazybank.accounts_service.repository;


import com.eazybank.accounts_service.entity.Accounts;
import com.eazybank.accounts_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    
}
