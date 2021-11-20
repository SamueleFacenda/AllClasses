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

}
