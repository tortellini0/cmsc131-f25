package projects.Bank;
abstract class Account{
    private final String accountID;
    private String accountName;
    private double accountBalance;
    abstract AccountType getType();
    /**
     * Constructer to initialize an Account
     * @param id - String - ID for each account made
     * @param name - String - Name associated with wach account
     * @param balance - double - balance of account
     * @throws IllegalArgumentException If id or name is null
     */
    public Account(String id, String name, double balance){
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
    // ""
    //sample string "savings,wz240833,Anna Gomez,8111.00"
    /**
     * make an Account with a line from csv
     * @param csvLine - String - "AccountType,accountID,name,balance" - line from a csv file
     * @return account made useing csv line
     */
    public static Account make(String csvLine){
        if(csvLine == null){
            // TODO lacks test coverage
            throw new IllegalArgumentException("csvLine cant be null");
        }
        String[] parts = csvLine.split(",");

        AccountType type = AccountType.valueOf(parts[0].toUpperCase());
        String UID = parts[1];
        String name = parts[2];
        double balance = Double.valueOf(parts[3]);
        if (type == AccountType.SAVINGS){
            return new SavingsAccount(UID, name, balance);
        }else {
            return new CheckingAccount(UID, name, balance);
        }


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
