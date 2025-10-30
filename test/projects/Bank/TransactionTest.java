package projects.Bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account checkAccount;
    private Transaction defaultDeposit, defaultWithdrawal;

    @BeforeEach
    void setUp() {
        checkAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
    }

    @Test
    void constructorThrowsForInvalidID(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {new Withdrawal(null,100.0);}
        );
        assertEquals(
            "itemID cant be null",
            exception.getMessage()
        );
    }

    @Test
    void constructorThrowsForInvaliAmount(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {new Withdrawal("id",-100.00);}
        );
        assertEquals(
            "value of the transaction cant be negative",
            exception.getMessage()
        );
    }

    @Test
    void makeThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class, 
            () -> {Transaction.make(null);}
        );
        assertEquals(
            "csvLine cant be null",
            exception.getMessage()
        );
    }

    @Test
    void transactionMakeWorks(){
        Transaction deposit2 = Transaction.make("deposit,wz240833,8111.00");
        assertEquals(TransactionType.DEPOSIT,deposit2.getType());
        assertEquals("wz240833",deposit2.getAccountID());
        assertEquals(8111.0,deposit2.getAmount());
    }
    

    @Test
    void testValidateDeposit() {
        boolean succeed = defaultDeposit.validate(checkAccount);
        assertEquals(true,succeed); 
    
    }

    @Test
    void testValidateWithdrawal() {
        CheckingAccount account1 = new CheckingAccount("uid", "jordy", 1.0);
        boolean trueValidate = defaultWithdrawal.validate(checkAccount);
        boolean falseValidate = defaultWithdrawal.validate(account1);

        assertEquals(true, trueValidate);
        assertEquals(false, falseValidate);
    
    }

    @Test
    void testExecuteDeposit() {
        defaultDeposit.execute(checkAccount);
        assertEquals(12.51,checkAccount.getBalance());
    }

    @Test
    void testExecuteWithdrawal() {
        defaultWithdrawal.execute(checkAccount);
        assertEquals(8.25, checkAccount.getBalance());
    }

}
