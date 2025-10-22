package projects.Bank;

public class SavingsAccount extends Account{
    public SavingsAccount(String uid, String name, double balance){
        super(uid, name, balance);
    }
    @Override
    public AccountType getType(){
        return AccountType.SAVINGS;
    }
    
}
