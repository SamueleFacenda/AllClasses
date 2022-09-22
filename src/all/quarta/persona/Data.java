package all.quarta.persona;


import java.util.regex.Pattern;

public class Data {
    private int giorno, mese, anno;

    public Data(int giorno, int mese, int anno)throws Exception {
        setGiorno(giorno);
        setMese(mese);
        setAnno(anno);
    }
    public Data(java.util.Date in)throws Exception{
        setGiorno(in.getDay());
        setMese(in.getMonth()+1);
        setAnno(in.getYear());
    }
    public Data(String in)throws Exception{
        if(!validaData(in))
            throw new Exception("formato non valido");
        String[] num=in.split("/");
        setGiorno(Integer.parseInt(num[0]));
        setMese(Integer.parseInt(num[1]));
        setAnno(Integer.parseInt(num[2]));
    }
    public static boolean validaData(String in){
        return Pattern.compile("[0-9]/[0-9]/[0-9]").matcher(in).matches();
    }
    public void setGiorno(int giorno) throws Exception {
        if(giorno>0 && giorno <= 31)
            this.giorno=giorno;
        else
            throw new Exception("giorno non valido");
    }

    public int getGiorno() {
        return giorno;
    }

    public void setMese(int mese) throws Exception{
        if(mese > 0 && mese <= 12)
            this.mese=mese;
        else
            throw new Exception("mese non valido");
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
    public Data getCopy() throws Exception {
        return new Data(giorno,mese,anno);
    }
    public boolean isDopo(Data check){
        boolean out=false;
        if(check.anno<anno)
            out=true;
        else if(check.anno==anno){
            if(check.mese<mese)
                out=true;
            else if(check.mese==mese){
                if(check.giorno<giorno)
                    out=true;
            }
        }
        return out;
    }
    public int getDiffAnni(Data in){
        if(in.mese>mese)
            return in.anno-anno;
        else if(in.mese==mese){
            if(in.giorno>giorno)
                return in.anno-anno;
            else if(in.giorno!=giorno)
                return in.anno-anno-1;
            else
                return in.anno-anno;
        }else
            return in.anno-anno-1;
    }
    public static int getDiffInGiorni(Data data1,Data data2){
        int anni=data1.anno- data2.anno,mesi=data1.mese- data2.mese;
        int out=anni*365-mesi*31;
        out-=data1.giorno- data2.giorno;
        return out;
    }
    public static int differenzaInSettimane(Data data1,Data data2){
        return getDiffInGiorni(data1,data2)/7;
    }
    public static int getDiffInMesi(Data data1,Data data2){
        return getDiffInGiorni(data1,data2)/31;
    }
    public static String diffInGiorniMesiAnni(Data data1, Data data2){
        int diff=getDiffInGiorni(data1,data2);
        String out=Integer.toString((int)(diff/365));
        diff%=365;
        out=Integer.toString((int)(diff/31))+"/"+out;
        diff%=31;
        return ""+diff+"/"+out;
    }


}