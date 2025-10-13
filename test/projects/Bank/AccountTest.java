package projects.Bank;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AccountTest {
    private Account account1;

    @BeforeEach
    void setup(){
        account1 = new Account("123456789","jogn",1000.0,AccountType.SAVINGS);
    }
    
    

    @Test
    void constructorThrowsForInvalidID(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {new Account(null,"Jogn",100.0,AccountType.SAVINGS);}
        );
        assertEquals(
            "ID cant be null.",
            exception.getMessage()
        );
    }

    @Test
    void constructerThrowsForInvalidName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("123456789",null,100000.0,AccountType.CHECKING);}

        );
        assertEquals(
            "Name cant be null.",
            exception.getMessage()
        );
    }

    //"savings,wz240833,Anna Gomez,8111.00"
    @Test
    void accountMakeWorks(){
        Account acc2 = Account.make("savings,wz240833,Anna Gomez,8111.00");
        assertEquals(AccountType.SAVINGS,acc2.getType());
        assertEquals("wz240833",acc2.getID());
        assertEquals("Anna Gomez", acc2.getName());
        assertEquals(8111.0,acc2.getBalance());

    }
}