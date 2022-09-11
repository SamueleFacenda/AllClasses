package all.VacanzeEstive;

import java.util.HashSet;
import java.util.Set;

public class Pullman implements Identificable, Clonable2<Pullman>{
    private final String targa, autista;
    private final int  capienza;
    private int ultimaFermata, corsa;
    private boolean isOnRoad;
    private Set<Studente> passeggeri;

    public Pullman(String targa, String autista, int corsa, int capienza) {
        this.targa = targa;
        this.autista = autista;
        this.corsa = corsa;
        this.capienza = capienza;
        this.ultimaFermata = -1;
        this.isOnRoad = false;
        passeggeri = new HashSet<>();
    }

    @Override
    public Pullman clone2() throws CloneNotSupportedException {
        Pullman out = (Pullman) super.clone();
        out.passeggeri = new HashSet<>(passeggeri);
        return out;
    }

    public Pullman(String targa, String autista, int capienza) {
        this.targa = targa;
        this.autista = autista;
        this.corsa = -1;
        this.capienza = capienza;
        this.ultimaFermata = -1;
        this.isOnRoad = false;
        passeggeri = new HashSet<>();
    }


    public void setOnRoad(boolean isOnRoad) {
        this.isOnRoad = isOnRoad;
    }

    public boolean isOnRoad() {
        return isOnRoad;
    }

    public String getTarga() {
        return targa;
    }

    public String getAutista() {
        return autista;
    }

    public int getCorsa() {
        return corsa;
    }

    public int getCapienza() {
        return capienza;
    }

    public int getUltimaFermata() {
        return ultimaFermata;
    }

    public int getPostiOccupati() {
        return passeggeri.size();
    }

    public boolean isFull() {
        return passeggeri.size() == capienza;
    }

    public void updateCorsa(int corsa) {
        this.corsa = corsa;
        ultimaFermata = -1;
    }

    /**
     * i controlli vengono effettuati all'esterno, dalla gestione
     */
    public void addStudente(Studente s) {
        passeggeri.add(s);
    }

    public void clearStudente(){
        passeggeri.clear();
    }

    public void setUltimaFermata(int ultimaFermata) {
        this.ultimaFermata = ultimaFermata;
    }

    @Override
    public boolean haveSameId(Identificable id) {
        if (id instanceof Pullman p) {
            return this.targa.equals(p.targa);
        }
        return false;
    }

    @Override
    public boolean haveThisID(Object id) {
        if(id instanceof String i) {
            return this.targa.equals(i);
        }
        return false;
    }

    public boolean contains(Studente s){
        return passeggeri.contains(s);
    }
}
