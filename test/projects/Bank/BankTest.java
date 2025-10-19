package projects.Bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BankTest {
    public Bank bank1;

    @BeforeEach
    void setUp(){
        bank1 = new Bank();
        bank1.add(new Account("id1", "name1", 1.0, AccountType.SAVINGS));
        bank1.add(new Account("id2", "name2", 2.0, AccountType.SAVINGS));
        bank1.add(new Account("id3", "name3", 3.0, AccountType.SAVINGS));
        bank1.add(new Account("id4", "name4", 4.0, AccountType.SAVINGS));
    }


    @Test
    void addThrowsForInvalidAcc1() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {bank1.add(null);}
        );
        assertEquals(
            "Account acc1 cant be null",
            exception.getMessage()
        );
    }


    @Test
    void findThrowsForInvalidUID() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {bank1.find(null);}
        );
        assertEquals(
            "String UID cant be null",
            exception.getMessage()
        );
    }


    @Test
    void findReturnsCorrectValuePositive(){
        int i = bank1.find("id4");
        assertEquals(i, 3);
    }


    @Test
    void findReturnsCorrectValueNegative(){
        int i = bank1.find("id6");
        assertEquals(i, -1);
    }


    @Test
    void addReturnsTrueForAddedAccount(){
        boolean t = bank1.add(new Account("id5", "name5", 5.0, AccountType.CHECKING));
        assertEquals(t , true);
    }
    @Test
    void addReturnsFalseForFailedAdd(){
        bank1.add(new Account("id5", "name5", 5.0, AccountType.CHECKING));
        boolean t = bank1.add(new Account("id3", "name6", 6.0, AccountType.CHECKING));
        assertEquals(t , false);
    }

    @Test
    void verifyLoadAccountsAndWriteCSV(){
        Bank bank2 = new Bank();
        String read = "data/accounts.csv";
        String write = "data/20251013.csv";
        bank2.loadCSV(read); // TODO test returned value
        bank2.writeCSV(write); // TODO test returned value
        try{
            File fileR = new File(read);
            File fileW = new File(write);
            Scanner scannerR = new Scanner(fileR);
            Scanner scannerW = new Scanner(fileW);
            while ((scannerR.hasNextLine()) || (scannerW.hasNextLine())){
                assertEquals(scannerR.nextLine(), scannerW.nextLine());
            }
            scannerR.close();
            scannerW.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // Phase 2 failure mode coverage
    // TODO include tests for loadAccounts and writeAccounts returning false
    @Test
    void loadCSVThrowsForInvalidFileName() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {bank1.loadCSV(null);}
        );
        assertEquals(
            "fileName cant be null",
            exception.getMessage()
        );
    }
    @Test
    void writeCSVThrowsForInvalidFileName() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {bank1.writeCSV(null);}
        );
        assertEquals(
            "fileName cant be null",
            exception.getMessage()
        );
    }
    
    
}
