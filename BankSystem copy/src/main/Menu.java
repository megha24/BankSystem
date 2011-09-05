package main;

import main.UserInterface.UserInterface;
import main.domain.Currency;
import main.domain.InsufficientFundsException;
import main.domain.Money;
import org.junit.Before;
import sun.awt.windows.ThemeReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;


public class Menu
{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        BankSystem bankSystem;
        String choice;
        UserInterface userInterface;

       public Menu(BankSystem bankSystem)
       {
           this.bankSystem=bankSystem;
           choice="1";
       }
       public void performOperation()
        {
            Integer toBeContinued=1;
            do{
                System.out.println("Select Any Option");
                System.out.println("Press 1. to Transfer Amount");
                System.out.println("Press 2. to Exit");
                Integer choice=userInterface.getOptionNumber();
                if(choice==1)
                {
                    transferAmount();
                }
             else
                    System.out.print("Thank u ");
                toBeContinued=0;
        //To change body of created methods use File | Settings | File Templates.
         }while(toBeContinued==1);
        }

    private void transferAmount() {
        userInterface.print("ENTER AccountId from which money is to be transferred");
        String toAccountId= userInterface.getUserInput();
        userInterface.print("ENTER AccountId to which money is to be transferred");
        String fromAccountId= userInterface.getUserInput();
        userInterface.print("ENTER the amount to be transferred");
        String value= userInterface.getUserInput();
        userInterface.print("ENTER the Currency ");
        String currency= userInterface.getUserInput();
        Money amountToTransfer=new Money(new BigDecimal(value), Currency.parse(currency));
        try
        {
      bankSystem.transfer(fromAccountId,toAccountId,amountToTransfer);
        }
        catch (InsufficientFundsException ie)
        {

        }
    }
}






