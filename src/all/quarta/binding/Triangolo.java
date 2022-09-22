package all.quarta.binding;

class Triangolo extends FiguraBaseAltezza{
    public Triangolo(String colore,int base,int altezza){
        super(colore,base,altezza);
    }
    @Override
    public double getArea(){
        return super.getArea() / 2;
    }
}
