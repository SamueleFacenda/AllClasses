package all.quarta.testListe;

public class VideoLezione {
    private Integer codice,min,sec;
    private String arg;

    public VideoLezione(int codice, int min, int sec, String arg) {
        this.codice = codice;
        this.min = min;
        this.sec = sec;
        this.arg = arg;
    }
    public VideoLezione(Integer min, Integer sec, String arg,Integer anno,Integer codeArg) {
        this.codice = Integer.parseInt(anno.toString()+codeArg.toString());
        this.min = min;
        this.sec = sec;
        this.arg = arg;
    }
    public int getCode(){
        return codice;
    }
}
