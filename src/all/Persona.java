package all;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Persona {
    private double altezza;
    private int eta;
    private String nome;
    private Data dataDiNascita;
    private static int nInstanze;

    public Persona(){
        nInstanze++;
    }

    public Persona(double altezza, int eta, String nome, Data dataDiNascita) throws Exception{
        this.altezza = altezza;
        this.eta = eta;
        this.nome = nome;
        setDataDiNascita(dataDiNascita);
        nInstanze++;
    }
    public void setDataDiNascita(Data in) throws Exception{
        Calendar cal=new GregorianCalendar();
        if(in.getAnno()>cal.get(Calendar.YEAR) || in.getAnno()<1900)
            throw new Exception("anno non valido");
        if(in.getMese()<=0|| in.getMese()>12)
            throw new Exception("mese non disponibile");
        if(in.getGiorno()<=0 || in.getGiorno()>30)
            throw new Exception("giorno non valido");
        dataDiNascita=in.getCopy();
    }

    public double getAltezza() {
        return altezza;
    }

    public int getEta() {
        return eta;
    }

    public String getNome() {
        return nome;
    }

    public Data getDataDiNascita() {
        return dataDiNascita;
    }

    public static int getnInstanze() {
        return nInstanze;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
