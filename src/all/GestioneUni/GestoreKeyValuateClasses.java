package all.GestioneUni;


import java.lang.reflect.Array;

public class GestoreKeyValuateClasses<T extends KeyValueClass> {
    private KeyValueClass[] lista;
    private int dimL;
    public GestoreKeyValuateClasses(T[] in){
        dimL=in.length;
        lista=new KeyValueClass[dimL];
        for (int i = 0; i < dimL; i++)
            lista[i]=in[i];
    }
    public GestoreKeyValuateClasses(){
        dimL=0;
        lista=new KeyValueClass[1];
    }
    public T get(int i)throws Exception{
        try{
            return (T)lista[i];
        }catch (IndexOutOfBoundsException e){
            throw new Exception("valore non accettabile");
        }
    }
    public void add(T in) throws Exception{
        if(indexOf(in)==-1){
            if(dimL==lista.length)
                resize();
            lista[dimL]=in;
            dimL++;
        }else
            throw new Exception("oggetto giá presente");
    }
    private void resize(){
        KeyValueClass[] listaDue=new KeyValueClass[(int)Math.ceil(1.10*dimL)];
        for (int i = 0; i < dimL; i++)
            listaDue[i]=lista[i];
        lista=listaDue;
    }
    public int indexOf(T in){
        int i=findKeyValue(in.getKeyValue());
        return i==dimL?-1:i;
    }
    public T get(String keyValue){
        int i=findKeyValue(keyValue);
        return i==dimL?null:(T)lista[i];
    }
    private int findKeyValue(String keyValue){
        int i=0;
        while(i<dimL && !keyValue.equals(lista[i++].getKeyValue()));
        return i;
    }
    public T getLast(){
        if(dimL==0)
            throw new IndexOutOfBoundsException();
        else
            return (T)lista[0];
    }
    public int getNumberOfElements(){
        return dimL;
    }
}
