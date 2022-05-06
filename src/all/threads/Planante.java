package all.threads;

public class Planante extends Auto{
    private double alettone;

    public Planante(String nome, double peso, double velocita, Semaforo traguardo, double alettone) {
        super(nome, peso, velocita, traguardo);
        this.alettone = alettone;
    }
    @Override
    protected void pedana(){
        if(alettone>90)
            velocita*=1.02;
    }
    @Override
    public String toString(){
        return "Planante-->"+super.toString();
    }

}
