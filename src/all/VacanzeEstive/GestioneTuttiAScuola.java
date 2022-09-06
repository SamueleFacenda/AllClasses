package all.VacanzeEstive;

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

    public String timbroStudente(int codiceStudente, String targa){
        Studente s = studenti.get(codiceStudente);
        Pullman p = pullman.get(targa);
        Fermata f = fermate.get(p.getUltimaFermata());
        if(p.isFull()){
            //la corsa non è bastata, attivo quella supplementare
            //se non ce n'è già un bus sulla stessa corsa che non è ancora arrivato
            Corsa c = corse.get(p.getCorsa());
            if(!checkForNextBus(c,f))
                return corsaSupplementare(c);
            else
                return "Corsa supplementare già attiva";
        }
        else {
            p.addStudente(s);
            f.removeStudente(s);
        }
        return null;
    }

    public void timbroStudente(int codiceStudente, int codiceFermata) {
        Studente s = studenti.get(codiceStudente);
        Fermata f = fermate.get(codiceFermata);
        f.addStudente(s);
    }

    public void startPullman(String targa, int corsa){
        Pullman p = pullman.get(targa);
        p.updateCorsa(corsa);
        p.setOnRoad(true);
        p.clearStudente();
    }

    public void avanzaPullman(String targa){
        Pullman p = pullman.get(targa);
        if(p.isOnRoad())
            avanzaFermataPullman(p);
        else{
            p.setOnRoad(true);
            p.clearStudente();
        }
    }

    private void avanzaFermataPullman(Pullman p){
        Corsa c = corse.get(p.getCorsa());
        int f = c.getNextFermata(p.getUltimaFermata());
        p.setUltimaFermata(f);
        if(p.getUltimaFermata() == -1){
            p.setOnRoad(false);
            p.clearStudente();
        }
    }

    private boolean checkForNextBus(Corsa c, Fermata f){
        return pullman.stream()
                .filter(Pullman::isOnRoad)
                .filter(p -> p.getCorsa() == c.getCode())
                .filter(p -> !p.isFull())
                .anyMatch(p -> c.isFermataBefore(p.getUltimaFermata(), f.getCode()));
    }

    private String corsaSupplementare(Corsa c){
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
