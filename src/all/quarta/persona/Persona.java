package all.quarta.persona;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Persona {
    private double altezza;
    private int eta;
    private String nome;
    private Data dataDiNascita;
    protected static int nInstanze;

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

    public int getEta(){
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

    public void setNome(String nome)throws Exception {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Double.compare(persona.altezza, altezza) == 0 &&
                eta == persona.eta &&
                Objects.equals(nome, persona.nome) &&
                Objects.equals(dataDiNascita, persona.dataDiNascita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(altezza, eta, nome, dataDiNascita);
    }
}
