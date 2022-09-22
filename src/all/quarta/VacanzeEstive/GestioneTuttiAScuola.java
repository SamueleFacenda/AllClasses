package all.quarta.VacanzeEstive;

public class GestioneTuttiAScuola {
    private final IdentificableManager<Fermata> fermate;
    private final IdentificableManager<Corsa> corse;
    private final IdentificableManager<Studente> studenti;
    private final IdentificableManager<Pullman> pullman;
    public GestioneTuttiAScuola(Fermata[] fermata, Corsa[] corsa, Studente[] studente, Pullman[] pullman) {
        fermate = new IdentificableManager<>(fermata);
        corse = new IdentificableManager<>(corsa);
        studenti = new IdentificableManager<>(studente);
        this.pullman = new IdentificableManager<>(pullman);
    }

    /**
     * Timbro studente, con overloading per sapere se il timbro è per una fermata
     * o per un bus, ritorna una stringa se non è potuto salire, con le informazioni sulla
     * corsa supplementare
     * @param codiceStudente codice dello studente
     * @param targa targa del bus sul quale sta salendo
     * @return eventuali informazioni su una corsa aggiuntiva
     */
    public String timbroStudente(int codiceStudente, String targa){
        Studente s = studenti.get(codiceStudente);
        Pullman p = pullman.get(targa);

        if(!p.contains(s)){
            Fermata f = fermate.get(p.getUltimaFermata());
            if (p.isFull()) {
                //la corsa non è bastata, attivo quella supplementare
                //se non ce n'è già un bus sulla stessa corsa che non è ancora arrivato
                Corsa c = corse.get(p.getCorsa());
                if (!checkForNextBus(c, f))
                    return corsaSupplementare(c);
                else
                    return "Corsa supplementare già attiva";
            } else {
                if (f.contains(s))
                    throw new RuntimeException("Studente non uscito dalla fermata");
                p.addStudente(s);
            }
        }
        return null;
    }

    /**
     * Timbro di uno studente a una fermata, controlla se sta entrando o uscendo
     * @param codiceStudente studente che timbra
     * @param codiceFermata codice della fermata del timbro
     */
    public void timbroStudente(int codiceStudente, int codiceFermata) {
        Studente s = studenti.get(codiceStudente);
        Fermata f = fermate.get(codiceFermata);
        if(f.contains(s))
            f.removeStudente(s);
        else
            f.addStudente(s);
    }

    /**
     * inizia la corsa di un pullman
     * @param targa targa del pulmann da far partire
     * @param corsa corsa su cui far partire il pullman
     */
    public void startPullman(String targa, int corsa){
        Pullman p = pullman.get(targa);
        p.updateCorsa(corsa);
        p.setOnRoad(true);
        p.clearStudente();
    }

    /**
     * fa avanzare il pullman alla fermata dopo, se non era partito allora
     * lo fa partire
     * @param targa targa del pullman da far avanzare
     */
    public void avanzaPullman(String targa){
        Pullman p = pullman.get(targa);
        if(p.isOnRoad())
            avanzaFermataPullman(p);
        else{
            p.setOnRoad(true);
            p.clearStudente();
        }
    }

    /**
     * fa avanzare il pullman alla fermata dopo
     * @param p pullman da far avanzare
     */
    private void avanzaFermataPullman(Pullman p){
        Corsa c = corse.get(p.getCorsa());
        int f = c.getNextFermata(p.getUltimaFermata());
        p.setUltimaFermata(f);
        //controlla se ha finito la corsa
        if(p.getUltimaFermata() == -1){
            p.setOnRoad(false);
            p.clearStudente();
        }
    }

    /**
     * controlla se c'è un bus sulla stessa corsa che non è ancora arrivato
     * alla fermata
     * @param c corsa da controllare
     * @param f fermata da controllare
     * @return true se c'è un bus sulla stessa corsa che non è ancora arrivato
     */
    private boolean checkForNextBus(Corsa c, Fermata f){
        //filtro la lista di bus per quelli in corsa, sulla stessa corsa,
        // che non sono arrivati e hanno spazio
        return pullman.stream()
                .filter(Pullman::isOnRoad)
                .filter(p -> p.getCorsa() == c.getCode())
                .filter(p -> !p.isFull())
                .anyMatch(p -> c.isFermataBefore(p.getUltimaFermata(), f.getCode()));
    }

    /**
     * attiva una corsa supplementare
     * @param c corsa da attivare
     * @return stringa con le informazioni sulla corsa supplementare
     */
    private String corsaSupplementare(Corsa c){
        //cerco un pullman libero
        Pullman p = pullman.stream()
                .filter(a -> !a.isOnRoad())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Non ci sono pullman disponibili"));
        p.updateCorsa(c.getCode());
        p.clearStudente();
        p.setOnRoad(true);
        return p.getTarga();
    }

}
