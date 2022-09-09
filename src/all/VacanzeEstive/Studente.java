package all.VacanzeEstive;

import java.util.Objects;

/**
 * @author Samuele Facenda
 * class Studente implements Identificable
 * with name and code, haveSameId method that check for same class and same code,
 * static attributes, getter, toString and equals
 * Tutti i metodi sono classici di java o spiegati nelle interfacce
**/
class Studente implements Identificable, Clonable2<Studente> {
    private final String name;
    private final int code;
    private static int studenti = 0;


    public Studente(String name) {
        this.name = name;
        this.code = studenti++;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }


    @Override
    public boolean haveSameId(Identificable id) {
        if (id instanceof Studente s) {
            return this.code == s.code;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Studente studente = (Studente) o;

        if (code != studente.code) return false;
        return Objects.equals(name, studente.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }

    @Override
    public Studente clone2() throws CloneNotSupportedException{
        return (Studente) super.clone();
    }

    @Override
    public boolean haveThisID(Object id) {
        if(id instanceof Integer i) {
            return this.code == i;
        }
        return false;
    }
}

