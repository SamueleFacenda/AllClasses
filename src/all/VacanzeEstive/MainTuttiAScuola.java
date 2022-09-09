package all.VacanzeEstive;

import java.util.Arrays;

public class MainTuttiAScuola {
    //TODO aggiungere commenti
    public static void main(String[] args) {
        Studente[] studenti = {
                new Studente("Mario Rossi"),
                new Studente("Luigi Bianchi"),
                new Studente("Giovanni Verdi"),
                new Studente("Giuseppe Neri"),
                new Studente("Antonio Gialli"),
                new Studente("Francesco Arancioni"),
                new Studente("Giacomo Viola"),
                new Studente("Giorgio Rosso"),
                new Studente("Gianluca Verdi"),
                new Studente("Gianmarco Gialli"),
                new Studente("Gianluigi Arancioni"),
                new Studente("Gianfranco Viola"),
                new Studente("Gianluca Rosso")
        };
        Fermata[] fermate = {
                new Fermata("Borgo Valsugana"),
                new Fermata("Ponte alto"),
                new Fermata("Trento centro"),
                new Fermata("Trento sud"),
                new Fermata("Trento nord"),
                new Fermata("Trento ovest"),
                new Fermata("Trento est"),
                new Fermata("Bolghera"),
                new Fermata("scuola"),
        };
        Corsa[] corse = {
                new Corsa(
                        fermate[0].getName(),
                        fermate[fermate.length - 1].getName(),
                        Arrays.stream(Arrays.
                                        copyOfRange(fermate, 1, fermate.length - 1))
                                .mapToInt(Fermata::getCode)
                                .toArray()
                )
        };
        Pullman[] pullman = {
                new Pullman("AA000AA", "Mario Rossi", 4),
                new Pullman("BB000BB", "Luigi Bianchi", 4),
                new Pullman("CC000CC", "Giovanni Verdi", 2),
                new Pullman("DD000DD", "Giuseppe Neri", 2),
        };

        GestioneTuttiAScuola gestione = new GestioneTuttiAScuola(fermate, corse, studenti, pullman);

        gestione.startPullman(pullman[0].getTarga(), corse[0].getCode());

        for(int i=0; i<Math.min(fermate.length-2, studenti.length/2); i++){
            gestione.timbroStudente(studenti[i*2].getCode(), fermate[i+1].getCode());
            gestione.timbroStudente(studenti[i*2+1].getCode(), fermate[i+1].getCode());
        }
        while(Arrays.stream(pullman).anyMatch(Pullman::isOnRoad)){
            Arrays.stream(pullman)
                    .filter(Pullman::isOnRoad)
                    .forEach(p -> {
                        //faccio avanzare il pullman
                        gestione.avanzaPullman(p.getTarga());
                        System.out.println("pullman " + p.getTarga() + " arrivato  a " + p.getUltimaFermata());

                        //la fermata del pullman
                        Fermata f = Arrays.stream(fermate).filter(a -> a.getCode() == p.getUltimaFermata()).findFirst().orElse(null);
                        //per ogni pullman in corsa
                        Arrays.stream(studenti)
                                //prendo gli studenti che sono nella fermata(se la corsa è finita la fermata sarà null)
                                .filter(s -> f != null && f.contains(s))
                                .forEach(s -> {
                                            String tmp = gestione.timbroStudente(s.getCode(), p.getTarga());
                                            if(tmp != null){
                                                System.out.println("attivata corsa supplementare, targa: " + tmp);
                                            }else{
                                                System.out.println("studente " + s.getName() + " salito su pullman " + p.getTarga());
                                            }
                                        });
                    });
        }

    }
}
