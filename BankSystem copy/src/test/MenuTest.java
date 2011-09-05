package test;

import main.BankSystem;
import main.Menu;
import main.Repositories.AccountRepository;
import main.UserInterface.UserInterface;
import main.domain.Account;
import main.domain.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuTest {
    BankSystem bankSystem;
    AccountRepository accountRepository;
    Account toAccount,fromAccount;
    Money moneyToBeTransferred;
    Menu menu;
    UserInterface userInterface;
    Integer choice=1;
    String toAccount,fromAccount;

    @Before
    public void setUp()
    {
        accountRepository=mock(AccountRepository.class);
        userInterface=mock(UserInterface.class);
        bankSystem=mock(BankSystem.class);
        menu=new Menu(bankSystem);
        toAccount="123";
        fromAccount="256";
        //Integer choice=1;
    }

    @Test
    public void performOperationsOnUserChoice() throws IOException {

        when(userInterface.getOptionNumber()).thenReturn(choice);
        menu.performOperation();


    }






}
