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
    /**
     * Accesor method to get accountType
     * @return - AccountType - type of account
     */
    public AccountType getType(){ 
        return accountType;
    }
    // ""
    //sample string "savings,wz240833,Anna Gomez,8111.00"
    /**
     * make an Account with a line from csv
     * @param csvLine - String - line from a csv file
     * @return account made useing csv line
     */
    public static Account make(String csvLine){
        if(csvLine == null){
            throw new IllegalArgumentException("csvLine cant be null");
        }
        String[] parts = csvLine.split(",");

        AccountType type = AccountType.valueOf(parts[0].toUpperCase());
        String UID = parts[1];
        String name = parts[2];
        double balance = Double.valueOf(parts[3]);
        return new Account(UID, name, balance, type);

    }
    /**
     * turns an Account into a String for a csv file
     * @return - String - line made of the parts of an Account (type, UID, name, balance)
     */
    public String toCSV(){
        String type = getType().name().toLowerCase();
        String name = getName();
        String UID = getID();
        String balance = String.format("%.2f", getBalance());//Double.toString(getBalance());
        String[] csvParts = {type, UID, name, balance};
        String csvLine = String.join(",", csvParts);
        return csvLine;
    }
}
