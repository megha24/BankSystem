package test;

import main.BankSystem;
import main.ForeignExchangeManager.ForeignExchangeService;
import main.ForeignExchangeManager.*;

import main.Repositories.AccountRepository;
import main.domain.Account;
import main.domain.Currency;
import main.domain.InsufficientFundsException;
import main.domain.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.awt.geom.AreaOp;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

//import static org.mockito.stubbing.*;

public class BankSystemTest {

    AccountRepository accountRepository;
    BankSystem bankSystem;
    Account toAccount,fromAccount;
    Money toMoney,fromMoney,money;
   Currency currencyInd,currencyCan;


    ForeignExchangeServiceImpl foreignExchangeServiceImpl;


    @Before
   public void setUp()
    {
       // currencyfrom=new Currency("INR");
        //currencyto=new Currency("INR");
        toMoney=new Money(new BigDecimal(600),Currency.INR);
        fromMoney=new Money(new BigDecimal(600),Currency.INR);

        accountRepository = mock(AccountRepository.class);
        foreignExchangeServiceImpl=mock(ForeignExchangeServiceImpl.class);
        bankSystem=new BankSystem(accountRepository,foreignExchangeServiceImpl);
        toAccount=new Account("256", Currency.INR , toMoney);
        fromAccount=new Account("123",Currency.INR, fromMoney);
    }

    @Test
    public void testshouldTransfer() throws InsufficientFundsException {

       // money=new Money(new BigDecimal(100),Currency.INR);

       // when(accountRepository.load("256")).thenReturn(toAccount);
       // when(accountRepository.load("123")).thenReturn(fromAccount);
        //Assert.assertEquals(money,toAccount.getBalance());
        //bankSystem.transfer("123","256",money);
        //verify(accountRepository).load("123");
        //verify(accountRepository).load("256");
//        verify(accountRepository).update(toAccount);
  //     verify(accountRepository).update(fromAccount);
       // Assert.assertEquals(new Money(new BigDecimal(600),Currency.INR),toAccount.getBalance());
        //Assert.assertEquals(new Money(new BigDecimal(600),Currency.CAD),fromAccount.getBalance());


    }
    @Test
    public void testShouldHandleCurrentExchange() throws InsufficientFundsException {
         toMoney=new Money(new BigDecimal(600),Currency.INR);
        fromMoney=new Money(new BigDecimal(600),Currency.CAD);
        // bankSystem=new BankSystem(accountRepository,foreignExchangeServiceImpl);
        toAccount=new Account("256", Currency.INR , toMoney);
        fromAccount=new Account("123",Currency.CAD, fromMoney);

         when(accountRepository.load("256")).thenReturn(toAccount);
        when(accountRepository.load("123")).thenReturn(fromAccount);
        when(foreignExchangeServiceImpl.conversionRate(Currency.USD,Currency.CAD)).thenReturn(new BigDecimal(.20));
        when(foreignExchangeServiceImpl.conversionRate(Currency.USD,Currency.INR)).thenReturn(new BigDecimal(.60));

        money=new Money(new BigDecimal(100),Currency.USD);
       bankSystem.transfer("123","256",money);
verify(accountRepository).load("123");
        verify(accountRepository).load("256");
        verify(accountRepository).update(toAccount);
        verify(accountRepository).update(fromAccount);
       // Assert.assertEquals(new Money(new BigDecimal(660),Currency.INR),toAccount.getBalance());



       // Assert.assertEquals("579.99 CAD",fromAccountBalance.toString());
       Assert.assertEquals(new Money(new BigDecimal(123/24),Currency.CAD),fromAccount.getBalance());


    }

}
