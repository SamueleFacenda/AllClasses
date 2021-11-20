package all.GestioneUni;

public class GestoreKeyValuateClasses<T extends KeyValueClass> {
    private T[] lista;
    private int dimL;
    public GestoreKeyValuateClasses(T[] in){
        dimL=in.length;
        lista=new T[dimL];

    }
}
