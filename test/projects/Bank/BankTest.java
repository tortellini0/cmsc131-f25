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
        bank1.add(new SavingsAccount("id1", "name1", 1.0));
        bank1.add(new SavingsAccount("id2", "name2", 2.0));
        bank1.add(new SavingsAccount("id3", "name3", 3.0));
        bank1.add(new SavingsAccount("id4", "name4", 4.0));
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
        boolean t = bank1.add(new CheckingAccount("id5", "name5", 5.0));
        assertEquals(t , true);
    }
    @Test
    void addReturnsFalseForFailedAdd(){
        bank1.add(new CheckingAccount("id5", "name5", 5.0));
        boolean t = bank1.add(new CheckingAccount("id3", "name6", 6.0));
        assertEquals(t , false);
    }

    @Test
    void verifyLoadAccountsAndWriteCSV(){
        Bank bank2 = new Bank();
        String read = "/workspaces/cmsc131-f25/data/accounts.csv";
        String write = "/workspaces/cmsc131-f25/data/20251013.csv";
        bank2.loadCSV(read);
        bank2.writeCSV(write);
        try{
            File fileR = new File(read);
            File fileW = new File(write);
            Scanner scannerR = new Scanner(fileR);
            Scanner scannerW = new Scanner(fileW);
            while ((scannerR.hasNextLine()) || (scannerW.hasNextLine())){
                assertEquals(scannerR.nextLine(), scannerW.nextLine());
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

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
