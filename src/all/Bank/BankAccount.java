package all.Bank;

public class BankAccount {
    private double balance;
    private String nome;
    public BankAccount(String nome){
        this.nome=nome;
        balance=0;
    }

    public double getBalance() {
        return balance;
    }

    public String getNome() {
        return nome;
    }
    protected void setBalance(double in){
        balance=in;
    }
    public void deposit(double amount){
        balance+=amount;
    }
}
