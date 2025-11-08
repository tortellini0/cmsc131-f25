package projects.Bank;

import java.io.FileWriter;
import java.io.IOException;

import lib.Utils;


public class Audit {
    private FileWriter writer;
   
    public Audit(String fileName) throws IOException{
        writer = new FileWriter(fileName);
    }

    /**
     * writes a string into the log file
     * @param s - String - line that will be written into the log file
     */
    private void write(String s){
        try{
            writer.write(s);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * logs a No Such Account error when there is no account in the bank
     * @param t - Transaction - the transaction that will have no account to modify
     */
    public void recordNSA(Transaction t){
        String message ="<timestamp> <level> account <id> could not be found";
        message = String.format(
            "%s ERROR account %s could not be found",
            Utils.timestamp(),
            t.getAccountID()
        );
        write(message);
    }
    /**
     * logs a non suffuciant funds error 
     * @param t - Transaction - withdrawal that has a greater amount than the account balance
     * @param a - Account - account that doesnt have enough funds
     */
    public void recordNSF(Transaction t, Account a){
        String message = "<timestamp> <level> unsuccessful withdrawal of $<withdrawal> from account <account id>, balance <bal>";
        message = String.format(
            "%s ERROR unsuccessful withdrawal of $%.2f from account %s, balance $%.2f",
            Utils.timestamp(),
            t.getAmount(),
            a.getID(),
            a.getBalance()

        );
        write(message);
    }

    /**
     * records a succesful transaction
     * @param t - Transaction - transaction involved in the successful transaction
     * @param a - Account - account involved in the successful transaction
     */
    public void recordExecute(Transaction t, Account a){
        String message = String.format(
            "%s INFO successful %s with balance $%.2f",
            Utils.timestamp(),
            t.toString(),
            a.getBalance()
        );
        write(message);
    }

    /**
     * closes the auditer
     */
    public void close(){
        try{
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }  
    }
}
