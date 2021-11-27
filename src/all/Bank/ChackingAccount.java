package all.Bank;

public class ChackingAccount extends BankAccount {
    private int transactionCount;
    public ChackingAccount(String nome){
        super(nome);
        transactionCount=0;
    }
    @Override
    public void deposit(double amount){
        setBalance(getBalance()+amount);
        transactionCount++;
    }
    public int getTransactionCount(){
        return transactionCount;
    }
}
