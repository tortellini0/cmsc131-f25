package projects.Bank;

public class Bank {
    private Account[] bankAccounts;
    private int numberOfAccounts;

    /**
     * constructer for Bank Class
     * @param size - int - size > 0
     */
    public Bank(int size){
        if(size > 0){
            bankAccounts = new Account[size];
        }else{
            bankAccounts = new Account[0];
        }
        numberOfAccounts = 0;
    }
    /**
     * Adds and Account to the bankAccounts array
     * @param acc1 - Account - account that is being added to the bankAccounts array
     * @return - boolean - was the account added or not
     * @throws IllegalArgumentException if acc1 is null
     */
    public boolean add(Account acc1){
        if (acc1 == null){
            throw new IllegalArgumentException(
                "Account acc1 cant be null"
            );
        }else{
            if(bankAccounts.length > numberOfAccounts){
                bankAccounts[numberOfAccounts] = acc1;
                numberOfAccounts++;
                return true;
            }else{
                return false;
            }
        }
        
    }
    /**
     * finds the index of an account in bankAccounts by comparing the UIDS of accounts
     * @param UID - String - ID we are looking for in bankAccounts
     * @return - int - index where the account ID is the same as UID
     * @throws IllegalArgumentException if UID is null
     */
    public int find(String UID){
        if(UID == null){
            throw new IllegalArgumentException(
                "String UID cant be null"
            );
        }
        for (int i = 0; i < bankAccounts.length; i++){
            if(UID.equals(bankAccounts[i].getID())){
                return i;
            }
        }
        return -1;
    }
    /**
     * gets the amount of accounts in bankAccounts
     * @return - int - number of accounts
     */
    public int getCount(){
        return numberOfAccounts;
    }


}
