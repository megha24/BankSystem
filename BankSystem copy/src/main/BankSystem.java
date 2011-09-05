package main;

import main.ForeignExchangeManager.ForeignExchangeService;
import main.ForeignExchangeManager.ForeignExchangeServiceImpl;
import main.Repositories.AccountRepository;
import main.domain.Account;
import main.domain.Currency;
import main.domain.InsufficientFundsException;
import main.domain.Money;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class BankSystem {
    AccountRepository accountRepository;
    ForeignExchangeServiceImpl foreignExchangeService;
    BigDecimal conversionRate ;
    public BankSystem(AccountRepository accountRepository,ForeignExchangeServiceImpl foreignExchangeServiceImpl)
    {
        this.accountRepository=accountRepository;
        this.foreignExchangeService =foreignExchangeServiceImpl;
    }

    public BankSystem(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }
    public void transfer(String fromAccountId, String toAccountId, Money money) throws InsufficientFundsException {
        Account fromAccount=accountRepository.load(fromAccountId);
        Account toAccount=accountRepository.load(toAccountId);
        conversionRate=foreignExchangeService.conversionRate(Currency.USD,Currency.CAD);
        Money moneyreturn=money.convert(conversionRate,Currency.CAD);
        fromAccount.withdraw(moneyreturn);
        conversionRate=foreignExchangeService.conversionRate(Currency.USD,Currency.INR);
        moneyreturn=money.convert(conversionRate,Currency.INR);
        toAccount.deposit(moneyreturn);
        //System.out.print("hello");
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);
    }




}
