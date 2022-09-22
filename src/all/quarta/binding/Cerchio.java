package all.quarta.binding;

class Cerchio extends Figura{
    private int raggio;
    public Cerchio(String colore,int raggio){
        super(colore);
        this.raggio=raggio;
    }
    @Override
    public double getArea(){
        return Math.pow(raggio,2) * Math.PI;
    }

    public String getInfo(){
        return super.getInfo() + " raggio: " + raggio;
    }
}