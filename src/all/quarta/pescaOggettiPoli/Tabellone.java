package all.quarta.pescaOggettiPoli;

import all.quarta.pescaOggettiPoli.oggetto.*;

public class Tabellone {
    private Oggetto[][] tab;
    public Tabellone(int x,int y){
        tab=new Oggetto[y][x];
        carica(false);
    }
    public void carica(boolean svuota){
        if(svuota){
            for (Oggetto[] riga :
                    tab) {
                for(int i=0;i<tab[0].length;i++)
                    riga[i]=null;
            }
        }
        for(int i=0;i<tab.length;i++){
            addInVoidPlace(new Forbice());
            addInVoidPlace(new Matita());
            addInVoidPlace(new Gomma(i==0));
            addInVoidPlace(new Penna(i==0));
        }
    }
    private void addInVoidPlace(Oggetto in){
        int x,y;
        //continua finchè non trova una cella vuota
        do{
            x=(int)(Math.random()*tab[0].length);
            y=(int)(Math.random()* tab.length);
        }while(tab[y][x]!=null);
        tab[y][x]=in;
    }
    public Oggetto getCella(int x,int y){
        return tab[y][x];
    }
    public void setLetta(int x,int y){
        tab[y][x]=new Oggetto();
    }
    public boolean isNull(int x,int y){
        return tab[y][x]==null;
    }
    public boolean isDrawed(int x,int y){
        return tab[y][x]!=null && tab[y][x].getValore()==0;
    }
}
