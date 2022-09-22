package all.quarta.condominii;

public class GestoreCondomini {
    private Condominio[] arr;
    public GestoreCondomini(Condominio[] arr){
        this.arr=new Condominio[arr.length];
        for(int i=0;i<arr.length;i++)
            this.arr[i]=arr[i].getCopy();
    }
    public Condominio maxNumAppartamenti(){
        int max=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i].getNCondomini()<arr[max].getNCondomini())
                max=i;
        }
        return arr[max].getCopy();
    }
    public String[] getMaxMilliForApartment(){
        String[] out=new String[arr.length];
        for(int j=0;j<arr.length;j++)
            out[j]="\nappartamento: "+arr[j].getNome()+"\nnome del proprietario con il maggior numero di millesimi: "+arr[j].getMaxMilli().getProprietario();
        return out;
    }
    public void addAppartamento(String nome, Appartamento in){
        int i=0;
        while(i<arr.length&&!arr[i].getNome().equals(nome))
            i++;
        if(i!=arr.length)
            ;//arr[i].add(in);
    }
}
