package all.VacanzeEstive;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Fermata implements Identificable, Clonable2<Fermata> {
    private final String name;
    private final int code;
    private Set<Studente> queue;
    private static int fermate = 0;

    public Fermata(String name) {
        this.name = name;
        this.code = fermate++;
        this.queue = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void addStudente(Studente s) {
        queue.add(s);
    }

    @Override
    public Fermata clone2() throws CloneNotSupportedException {
        Fermata out = (Fermata) super.clone();
        out.queue = new HashSet<>(queue);
        return out;
    }


    public void removeStudente(Studente s) {
        queue.remove(s);
    }

    public boolean hasStudente() {
        return !queue.isEmpty();
    }

    public int getQueueSize() {
        return queue.size();
    }

    @Override
    public boolean haveSameId(Identificable id) {
        if (id instanceof Fermata f) {
            return this.code == f.code;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Fermata{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean haveThisID(Object id) {
        if(id instanceof Integer i) {
            return this.code == i;
        }
        return false;
    }

    public boolean contains(Studente s) {
        return queue.contains(s);
    }

}

