package all.quarta.threads;

public abstract class Auto extends Thread{
    private String nome;
    protected double peso,velocita;

    private Semaforo traguardo;

    public Auto(String nome, double peso, double velocita, Semaforo traguardo) {
        super();
        this.nome = nome;
        this.peso = peso;
        this.velocita = velocita;
        this.traguardo=traguardo;
    }

    private boolean getOstacolo(){
        return (int)(Math.random()*5) < (peso>50 ? 2: 1);
    }

    private void ostacolo(){
        if(getOstacolo()){
            if(Math.random() > 0.5) {
                System.out.println(nome+" ha preso una pedana");
                pedana();
            }else{
                System.out.println(nome+" ha preso un dosso");
                dosso();
            }
        }
    }

    protected void pedana(){
        velocita*=0.95;
    }

    protected void dosso(){
        velocita*=0.95;
    }

    @Override
    public void run(){
        int dist = 0;
        while(dist < 1000){
            try{
                sleep(1000+(int)(Math.random()*100-50));
            }catch(Exception e){}
            ostacolo();
            dist+=velocita;
            System.out.println(nome+" è a distanza "+dist+" con velocità"+velocita);
        }

        traguardo.P();
        MainMacchine.podio.add(this);
        traguardo.V();
    }
    @Override
    public String toString(){
        return "nome: "+nome+", peso: "+peso+", velocità: "+velocita;
    }

}
