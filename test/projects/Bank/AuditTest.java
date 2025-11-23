package projects.Bank;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AuditTest {
    String fileName;
    Audit audit;
    @BeforeEach
    void setup(){
        fileName = "test/projects/bank/auditTest.log";
        try{
            audit = new Audit(fileName);
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    @Test
    void testNoSuchAccount(){

        Transaction a = new Deposit("ididid5",1000.00);
        audit.recordNSA(a);
        audit.close();
        
        try{
            Scanner scan = new Scanner(new File(fileName));

            assertTrue(scan.hasNextLine());

            String logLine = scan.nextLine();

            assertTrue(logLine.contains(
                String.format(
                    "ERROR account %s could not be found",
                    a.getAccountID()
                )
            ));

            assertFalse(scan.hasNextLine());
            scan.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    @Test
    void testNonSufficiantfunds(){
        Transaction withdraw = new Withdrawal("ididid", 1000.00);
        Account account = new SavingsAccount("ididid", "kisandra", 0);
        audit.recordNSF(withdraw,account);
        audit.close();
        Scanner scan;
        try{
            scan = new Scanner(new File(fileName));

            assertTrue(scan.hasNextLine());

            String logLine = scan.nextLine();

            assertTrue(
                logLine.contains(
                    String.format("ERROR unsuccessful withdrawal of $%.2f from account %s, balance $%.2f",
                        withdraw.getAmount(),
                        account.getID(),
                        account.getBalance()

                    )
                )
            );
            assertFalse(scan.hasNextLine());
            scan.close();
        }catch(FileNotFoundException e){e.printStackTrace();}
    }

    @Test
    void testSuccessfulRecord(){
        Transaction deposit = new Deposit("ididid",1000.00);
        Account account = new CheckingAccount("id","name",1000.00);
        audit.recordExecute(deposit, account);
        audit.close();

        Scanner scan;
        try{
            scan = new Scanner(new File(fileName));
            assertTrue(scan.hasNextLine());
            String logLine = scan.nextLine();
            assertTrue(logLine.contains(
                String.format(
                    "INFO successful %s with balance $%.2f",
                    deposit.toString(),
                    account.getBalance()
                )
            ));
            assertFalse(scan.hasNextLine());
            scan.close();
        }catch(FileNotFoundException e){e.printStackTrace();}

    }
}
