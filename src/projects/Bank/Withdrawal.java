package projects.Bank;

public class Withdrawal extends Transaction {
    // constructor
     public Withdrawal(String id, Double amount){
        super(id, amount);
     }

     @Override
     public boolean validate(Account acc1){
        if(getAmount() > acc1.getBalance()){
            return false;
        }else{
            return true;
        }
     }

     @Override
     public void execute(Account acc1){
        
        acc1.decreaseBal(getAmount());
     }

     @Override
     public TransactionType getType(){return TransactionType.WITHDRAWAL;}

     public String toString(){
      return "withdrawal from " + getAccountID();
     }
}
