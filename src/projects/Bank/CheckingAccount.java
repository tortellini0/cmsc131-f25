package projects.Bank;

public class CheckingAccount extends Account{

    public CheckingAccount(String uid, String name, double balance){
        super(uid, name, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }
}
