package all.comparable;

import java.util.Comparator;

public class ComparePiatti implements Comparator<Piatto> {
    public int compare(Piatto uni, Piatto due){
        return uni.getPortata().getN()-due.getPortata().getN();
    }
}