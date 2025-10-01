package projects.Bank;
public class Account{
    private final String accountID;
    private String accountName;
    private double accountBalance;
    private final AccountType accountType;
    /**
     * Constructer to initialize an Account
     * @param id - String - ID for each account made
     * @param name - String - Name associated with wach account
     * @param balance - double - balance of account
     * @param type - AccountType CHECKING or SAVINGS - is the account a savings account or checkings account
     * @throws IllegalArgumentException If id or name is null
     */
    public Account(String id, String name, double balance, AccountType type){
        if(id == null){
            throw new IllegalArgumentException(
                "ID cant be null."
            );
        }else if(name == null){
            throw new IllegalArgumentException(
                "Name cant be null."
            );
        }else {
            accountID = id;
            accountName = name;
            accountBalance = balance;
            accountType = type;
        }
    }
    /**
     * Accesor method to get ID
     * @return - String - ID of the account
     */
    public String getID(){
        return accountID;
    }
    /**
     * Accesor method to get account Name
     * @return - string - Name of the account
     */
    public String getName(){
        return accountName;
    }
    /**
     * Accesor method to get balance
     * @return - double - balance of the account
     */
    public double getBalance(){
        return accountBalance;
    }
}
