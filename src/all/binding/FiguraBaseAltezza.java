package all.binding;

class FiguraBaseAltezza extends Figura{
    private int base, altezza;
    public FiguraBaseAltezza(String colore,int base,int altezza){
        super(colore);
        this.base=base;
        this.altezza=altezza;
    }
    @Override
    public double getArea(){
        return base * altezza;
    }
    @Override
    public String getInfo(){
        return super.getInfo() + " base: "+base+"\naltezza: "+altezza+"\narea"+getArea();
    }
}

