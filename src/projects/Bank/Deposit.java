package projects.Bank;
public class Deposit extends Transaction {
    // constructor 
    public Deposit(String id, double amount){
        super(id,amount);   
    }

    @Override
    public boolean validate(Account acc1){return true;}

    @Override
    public void execute(Account acc1){
        acc1.increaseBal(getAmount());
    }
    
    @Override
    public TransactionType getType(){return TransactionType.DEPOSIT;}
}
