package all.quarta.pescaOggettiPoli;


import all.quarta.pescaOggettiPoli.oggetto.Forbice;
import all.quarta.pescaOggettiPoli.oggetto.Oggetto;

public class PartitaPoli {
    private Tabellone tab;
    private Oggetto[] pescate;
    private int[] punti;
    private String message;
    private int objectLast,nPlayer;
    public PartitaPoli(int nPlayer,int nRighe,int nColonne)throws Exception{
        if(nPlayer<1)
            throw new Exception("nGiocatori non valido");
        if(nColonne*nRighe<nRighe*4)
            throw new Exception("numero di righe e colonne non valido");
        tab=new Tabellone(nColonne,nRighe);
        punti=new int[nPlayer];
        nPlayer=0;
        objectLast=nRighe*4;
        pescate=new Oggetto[objectLast];
    }
    public int getnPlayer(){
        return nPlayer;
    }
    public int getObjectLast(){
        return objectLast;
    }
    public int getPunti(int nPlayer){
        return punti[nPlayer];
    }
    public boolean isEnded(){
        return objectLast==0;
    }
    public String getMessage(){
        return message;
    }
    public boolean pesca(int x,int y){
        boolean out=false;
        if(isEnded())
            message="la partita è finita";
        else{
            if(tab.isDrawed(x,y))
                message="cella già pescata";
            else{
                if(tab.isNull(x,y))
                    message="cella vuota";
                else{
                    Oggetto og=tab.getCella(x,y);
                    tab.setLetta(x,y);
                    punti[nPlayer]+=og.getValore();
                    if(og instanceof Forbice){
                        for(int i=0;i<punti.length;i++)
                            if(i!=nPlayer)punti[i]-=((Forbice) og).getMalus();
                    }
                    message="hai pescato una "+og.toString();
                    objectLast--;
                    pescate[pescate.length-objectLast-1]=og;
                }
                nPlayer=(nPlayer+1)%punti.length;
                out=true;
            }
        }
        return out;
    }
    public int getWinner()throws Exception{
        if(isEnded()){
            int max=0;
            for(int i=0;i<punti.length;i++)
                if(punti[i]>punti[max]) max=i;
            return max;
        }else
            throw new Exception("partita non finita");
    }

    @Override
    public String toString() {
        String out="Partita di pesca oggetti\nPunteggi:\n";
        for (int i=0;i<punti.length;i++) out+=""+i+": "+punti[i]+"\n";
        if(isEnded()){
            try {
                out += "Partita finita\nVincitore: " + getWinner()+"\nPescate: \n";
                for (Oggetto og : pescate) out+= og.toString()+"\n";
            }catch(Exception e){}
        }else{
            out+="Giocatore corrente: "+nPlayer+"\nOggetti mancanti: "+objectLast;
        }
        return out;
    }
}
