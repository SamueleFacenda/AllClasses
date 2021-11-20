package all;

public class Data {
    private int giorno, mese, anno;

    public Data(int giorno, int mese, int anno) {
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getMese() {
        return mese;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getAnno() {
        return anno;
    }
    public String stampa(){
        return "giorno: "+giorno+" mese: "+mese+"anno: "+anno;
    }
    private String mese(int mes){
        String out;
        switch(mes){
            case 1:
                out="gennaio";
                break;
            case 2:
                out="febbraio";
                break;
            case 3:
                out="marzo";
                break;
            case 4:
                out="aprile";
                break;
            case 5:
                out="maggio";
                break;
            case 6:
                out="giugno";
                break;
            case 7:
                out="luglio";
                break;
            case 8:
                out="agosto";
                break;
            case 9:
                out="settembre";
                break;
            case 10:
                out="ottobre";
                break;
            case 11:
                out="novembre";
                break;
            default:
                out="dicembre";
        }
        return out;
    }
    public String sData(){
        return giorno+" "+mese(mese)+" "+anno;
    }
    public String giornoPrima(){
        int gio=giorno-1, mes=mese, ann=anno;
        if(gio==0){
            ann=(mese==1)?anno-1:anno;
            mes=mese-1;
            gio=giorniMese(ann, mes);
        }
        return gio+" "+mese(mes)+" "+ann;
    }
    public String calcGiornoSett(){
        int m=(mese==1||mese==2)?mese+12:mese;
        int k=anno%100;
        int j=anno/100;
        int h=((((giorno+(((13*(m+1))/5)+k))+(k/4))+(j/4))-(2*j))%7;
        String out;
        switch(h){
            case 1:
                out="domenica";
                break;
            case 2:
                out="lunedì";
                break;
            case 3:
                out="martedì";
                break;
            case 4:
                out="mercoledì";
                break;
            case 5:
                out="giovedì";
                break;
            case 6:
                out="venerdì";
                break;
            default:
                out="sabato";
        }
        return out;
    }
    private boolean bisestile(int anno){
        boolean out=false;
        if(anno%400==0||(anno%4==0&&anno%100!=0))
            out=true;
        return out;
    }
    private int giorniMese(int ann, int mes){
        int out;
        if(mes==2)
            out=(bisestile(ann))?29:28;
        else{
            if(mes==4||mes==6||mes==9||mes==11)
                out=30;
            else
                out=31;
        }
        return out;
    }
    public String giornoDopo(){
        int gio=giorno+1, mes=mese, ann=anno;
        if(gio>giorniMese(ann,mes)){
            if(mes==12){
                ann++;
                mes=1;
            }else
                mes++;
            gio=1;
        }
        return gio+" "+mese(mes)+" "+ann;
    }
    public String sommaGiorni(int day){
        int gio=giorno+day;
        int mes=mese;
        int ann=anno;
        while(giorniMese(ann,mes)<gio){
            gio=gio-giorniMese(ann,mes);
            mes=(mes==12)?1:mes+1;
            ann=(mes==1)?ann+1:ann;
        }
        return gio+" "+mese(mes)+" "+ann;
    }
    public Data getCopy(){
        return new Data(giorno,mese,anno);
    }
}