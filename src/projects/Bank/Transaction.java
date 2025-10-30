package projects.Bank;

abstract class Transaction {
    // class variables
    private final String accountID;
    private double amount;

    // getters 
    public String getAccountID(){return accountID;}
    public double getAmount(){return amount;}
    abstract TransactionType getType();

    /**
     * changes the balance of the account by the value in the transaction
     * @param account - Account - Account that has the balance changed
     */
    abstract void execute(Account account);

    /**
     * validates whether the transaction can execute on an account
     * @param acc1 - Account - account that is being checked
     * @return - boolean - True if the account can be executed on
     */
    abstract boolean validate(Account acc1);

    /**
     * Constructer for the transaction class and its subclasses
     * @param itemID - String - Account id that the transaction is aiming at
     * @param num - Double - value that the Transaction will change the Account by
     */
    protected Transaction(String itemID, double num){
        if(itemID == null){
            throw new IllegalArgumentException("itemID cant be null");
        }
        if(num < 0){
            throw new IllegalArgumentException("value of the transaction cant be negative");
        }
        accountID = itemID;
        amount = num;
    }


    /**
     * Makes a Transaction based off of a csv line
     * @param csvLine - String - csv line in the format "type, accountID, amount"
     * @return - Transaction - Transaction object with the values from the csv line
     */
    public static Transaction make(String csvLine){
        if (csvLine == null){
            throw new IllegalArgumentException("csvLine cant be null");
        }
        String[] parts = csvLine.split(",");
        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        String ID = parts[1];
        double amountMoney = Double.valueOf(parts[2]);
        if (type == TransactionType.WITHDRAWAL){
            return new Withdrawal(ID, amountMoney);
        }else{  
            return new Deposit(ID, amountMoney);

        }
    }
    



}
