package all.threads;

public class SuperRuotata extends Auto{
    private int ruote;

    public SuperRuotata(String nome, double peso, double velocita, Semaforo traguardo, int ruote) {
        super(nome, peso, velocita, traguardo);
        this.ruote=ruote;
    }
    @Override
    protected void dosso(){
        if(ruote>2)
            velocita*=1.05;
    }
    @Override
    public String toString(){
        return "SuperRuotata-->"+super.toString();
    }
}
