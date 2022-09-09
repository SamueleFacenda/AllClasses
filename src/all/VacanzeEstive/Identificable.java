package all.VacanzeEstive;

public interface Identificable {
    /**
     * controlla se un altro oggetto identificabile ha lo stesso id di questo
     * @param id oggetto identificabile da controllare
     * @return true se hanno lo stesso id
     */
    public boolean haveSameId(Identificable id);

    /**
     * controlla se l'id inserito è lo stesso di questo oggetto
     * @param id id da controllare
     * @return true se l'id è lo stesso
     */
    public boolean haveThisID(Object id);
}
