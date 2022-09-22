package all.quarta.binding;

class Figura{
    private String colore;
    public Figura(String colore){
        this.colore=colore;
    }
    public double getArea(){
        throw new UnsupportedOperationException();
    }
    public String getInfo(){
        return "colore: " + colore + " area: " + getArea();
    }
}
