package projects.Bank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Bank {
    private Account[] bankAccounts;
    private int numberOfAccounts;
    private final int incrementAcount = 100;

    /**
     * constructer for Bank Class
     */
    public Bank(){
        
            bankAccounts = new Account[incrementAcount];
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
            if(find(acc1.getID()) == -1){
                try{
                    
                    bankAccounts[numberOfAccounts] = acc1;
                }catch (ArrayIndexOutOfBoundsException e){
                    Account[] tempAccount = new Account[bankAccounts.length + incrementAcount];
                    for (int i = 0; i < bankAccounts.length; i++){
                        tempAccount[i] = bankAccounts[i];
                    }
                    tempAccount[numberOfAccounts] = acc1;
                    bankAccounts = tempAccount;
                }
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
            if((bankAccounts[i] !=null)&&(UID.equals(bankAccounts[i].getID()))  ){
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

    /**
     * loops through a csv file to fill bankAccounts with Accounts
     * @param fileName - String - location of the csv file to read from
     * @return true if and only if the function worked
     */
    public boolean loadCSV(String fileName){
        if (fileName == null){
            throw new IllegalArgumentException("fileName cant be null");
        }

        File accountsFile = new File(fileName);
        boolean succeed = true;
        Scanner scanner;
        try{
            scanner = new Scanner(accountsFile);
            while(scanner.hasNextLine()){
                String curLine = scanner.nextLine();
                add(Account.make(curLine));
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            succeed = false;
        }
        return succeed;
    }

    /**
     * converts the bankAccounts array into a csv file
     * @param fileName - String - location to write to
     * @return true if and only if the function worked
     */
    public boolean writeCSV(String fileName){
        if (fileName == null){
            throw new IllegalArgumentException("fileName cant be null");
        }
        
        try{
            File file = new File(fileName);
            FileWriter writer;
            writer = new FileWriter(file);
            for (int i = 0; i < numberOfAccounts; i++){
                String csvLine = bankAccounts[i].toCSV();
                writer.write(csvLine + System.lineSeparator());
            }
            writer.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Takes a csv file of transactions and runs them 
     * @param transactionFileName - String - name of file that is storing the transactions
     * @return - int - number of transactions 
     */ 
    public int processTransactions(String transactionFileName, String auditFileName) {
        if(transactionFileName == null){
            throw new IllegalArgumentException("fileName cant be null");
        }
        int numTransactions = 0;

        try{
            Audit audit = new Audit(auditFileName);
            Scanner scan;
            try{
                scan = new Scanner(new File(transactionFileName));
                while(scan.hasNextLine()) {
                    Transaction t = Transaction.make(scan.nextLine());
                    int index = find(t.getAccountID());
                    if(index >= 0){
                        Account target = bankAccounts[index];
                        if(t.validate(target)){
                            t.execute(target);
                            audit.recordExecute(t, target);
                        }else{
                            /**
                             * ok, but must be changed if (hypothetically) 
                             * another type of validation error is introduced 
                             * to the simulation
                             */ 
                            audit.recordNSF(t, target);
                        }

                    }else{
                        audit.recordNSA(t);    
                    }
                    numTransactions++;

                }
                audit.close();
                scan.close();
            }catch(FileNotFoundException e){
            e.printStackTrace();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(numTransactions);
        return numTransactions;
    }

}
