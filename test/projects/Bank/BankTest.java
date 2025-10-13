package projects.Bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    
}
