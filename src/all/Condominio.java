package all;

public class Condominio {
    private String indirizzo,nome;
    private Appartamento[] arr;

    public Condominio(String indirizzo, String nome, Appartamento[] arr) {
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.arr = new Appartamento[arr.length];
        for(int i=0;i<arr.length;i++)
            this.arr[i]=arr[i].getCopy();
    }
    public Condominio getCopy(){
        return new Condominio(indirizzo,nome,arr);
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public Appartamento[] getArr() {
        return arr;
    }
    public int getNCondomini(){
        return arr.length;
    }
    public Appartamento getMaxMilli(){
        int max=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i].getMillesimi()<arr[max].getMillesimi())
                max=i;
        }
        return arr[max].getCopy();
    }
    @Override
    public String toString(){
        String out="indirizzo: "+indirizzo+"\nnome: "+nome+"\n";
        for(Appartamento ap:arr)
            out+="\n"+ap;
        return out;
    }
}
