package all.VacanzeEstive;

import java.util.ArrayList;
import java.util.Arrays;

public class Corsa implements Identificable, Clonable2<Corsa> {
    private final String partenza, arrivo;
    private final int code;
    private int[] fermateIntermedie;
    private static int corse = 0;

    @Override
    public Corsa clone2() throws CloneNotSupportedException {
        Corsa out = (Corsa) super.clone();
        out.fermateIntermedie = Arrays.copyOf(fermateIntermedie, fermateIntermedie.length);
        return out;
    }

    public Corsa(String partenza, String arrivo, int[] fermateIntermedie) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.code = corse++;
        this.fermateIntermedie = new int[fermateIntermedie.length];
        System.arraycopy(fermateIntermedie, 0, this.fermateIntermedie, 0, fermateIntermedie.length);
    }

    public String getPartenza() {
        return partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public int getCode() {
        return code;
    }

    /**
     * return the fermataIntermedia after the inserted one, if it's the last one return -1
     * @param fermataIntermedia the fermataIntermedia to check
     * @return the fermataIntermedia after the inserted one, if it's the last one return -1
     */
    public int getNextFermata(int fermataIntermedia) {
        if(fermataIntermedia == -1)
            return fermateIntermedie[0];
        int i=0;
        while(i<fermateIntermedie.length-1 && fermateIntermedie[i]!=fermataIntermedia)
            i++;
        return i != fermateIntermedie.length-1 ? fermateIntermedie[i+1] : -1;
    }

    @Override
    public boolean haveSameId(Identificable id) {
        if (id instanceof Corsa c) {
            return this.code == c.code;
        }
        return false;
    }

    @Override
    public boolean haveThisID(Object id) {
        if(id instanceof Integer i) {
            return this.code == i;
        }
        return false;
    }

    /**
     * controlla se la prima fermata é prima della seconda,
     * così, in qualunque altro caso(le fermate non esistono...) ritorna false
     * @param f1 la prima fermata
     * @param f2 la seconda fermata
     * @return true se la prima fermata é prima della seconda, false altrimenti
     */
    public boolean isFermataBefore(int f1, int f2) {
        int i = 0;
        if(-1 == f2)
            return false;
        if(-1 == f1)
            return true;
        for(int f : fermateIntermedie) {
            if(f == f2)
                return false;
            if(f == f1)
                return true;
        }
        return false;
    }

}
