package com.eazybank.accounts_service.mapper;

import com.eazybank.accounts_service.dto.AccountsDto;
import com.eazybank.accounts_service.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto acoountsDto) {
        acoountsDto.setAccountNumber(accounts.getAccountNumber());
        acoountsDto.setAccountType(accounts.getAccountType());
        acoountsDto.setBranchAddress(accounts.getBranchAddress());
        return acoountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts acoounts) {
        acoounts.setAccountNumber(accountsDto.getAccountNumber());
        acoounts.setAccountType(accountsDto.getAccountType());
        acoounts.setBranchAddress(accountsDto.getBranchAddress());
        return acoounts;
    }
}



