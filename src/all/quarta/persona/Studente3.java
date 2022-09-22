package all.quarta.persona;

import java.util.Objects;

public class Studente3 extends Studente1 implements Cloneable {

    public Studente3(Double altezza, Integer eta, String nome, Data dataDiNascita, String email, String password, Integer classe, Boolean ripetente) throws Exception {
        super(altezza, eta, nome, dataDiNascita, email, password, classe, ripetente);
    }



    @Override
    protected Object clone() throws CloneNotSupportedException {
         try {
             return new Studente3(getAltezza(), getEta(), getNome(), getDataDiNascita(), getEmail(), getPassword(), getClasse(), getRipetente());
         }catch(Exception e) {
             throw new CloneNotSupportedException();
         }
    }

    @Override
    protected void finalize()throws Throwable{
        System.out.println("object garbaged");
        nInstanze--;
        super.finalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Double.compare(persona.getAltezza(), getAltezza()) == 0 &&
                getEta() == persona.getEta() &&
                Objects.equals(getNome(), persona.getNome()) &&
                Objects.equals(getDataDiNascita(), persona.getDataDiNascita());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAltezza(), getEta(), getNome(), getDataDiNascita());
    }

}
