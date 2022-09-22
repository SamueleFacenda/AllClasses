package all.quarta.Bank;

import java.util.LinkedList;

public class SavingAccount extends BankAccount{
    private LinkedList<Double> transaction;
    public SavingAccount(String nome){
        super(nome);
        transaction=new LinkedList<>();
        transaction.add(0.0);
    }
    @Override
    public void deposit(double amount){
        setBalance(getBalance()+amount);
        transaction.add(getBalance());
    }
}
