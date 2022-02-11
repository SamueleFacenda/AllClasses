package all.persona;

import java.util.Arrays;
import java.util.Comparator;

public class Studente1 extends Persona7{
    public static final String SCUOLA="I.T.T. Buonarroti";
    private Integer classe;
    private Boolean ripetente;
    private Double[] voti;

    public Studente1(Double altezza, Integer eta, String nome, Data dataDiNascita, String email, String password, Integer classe, Boolean ripetente) throws Exception {
        super(altezza, eta, nome, dataDiNascita, email, password);
        setClasse(classe);
        setRipetente(ripetente);
        voti=new Double[50];
    }
    public String getSCUOLA(){
        return SCUOLA;
    }
    public Integer getClasse(){
        return classe;
    }

    public Boolean getRipetente() {
        return ripetente;
    }

    public Double[] getVoti() {
        Double[] out=new Double[voti.length];
        for(int i=0;i<voti.length;i++)
            out[i]=voti[i];
        return out;
    }

    public void setClasse(Integer classe)throws Exception {
        if(classe == null || classe<=0 || classe > 5)
            throw new Exception("classe non valida");
        this.classe = classe;
    }

    public void setRipetente(Boolean ripetente) throws Exception{
        if(ripetente == null) throw new Exception("ripetente nullo");
        this.ripetente = ripetente;
    }

    public void setVoti(Double[] voti) {
        Double[] out=new Double[voti.length];
        for(int i=0;i<voti.length;i++)
            out[i]=voti[i];
        this.voti=out;
    }
    public Boolean aggiungiVoto(Double voto){
        int i=0;
        while(i<voti.length && voti[i]!=null) i++;
        if(i==voti.length)
            return false;
        else{
            voti[i]=voto;
            return true;
        }
    }
    public Boolean rimuoviVoto(Integer pos){
        try{
            voti[pos]=null;
            return true;
        }catch(NullPointerException e){
            return false;
        }
    }
    public String info(){
        return toString();
    }

    @Override
    public String toString() {
        return "Studente1{" +
                "classe=" + classe +
                ", ripetente=" + ripetente +
                ", voti=" + Arrays.toString(voti) +
                '}'+super.toString();
    }
    public Boolean promuovi(){
        if(classe == 5)
            return false;
        else{
            classe++;
            return true;
        }
    }
    public Boolean promuovi(Integer numeroCLassi){
        if(classe + numeroCLassi > 5)
            return false;
        else{
            classe += numeroCLassi;
            return true;
        }
    }
    public Double votoMinore(){
        return Arrays.stream(voti).min(Comparator.naturalOrder()).orElse(0.0);
    }
    public Double votoMaggiore(){
        return Arrays.stream(voti).max(Comparator.naturalOrder()).orElse(0.0);
    }
    public Double mediaVoti(){
        return Arrays.stream(voti).reduce(0.0, Double::sum)/voti.length;
    }
}
