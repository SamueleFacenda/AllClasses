package all.bag;

public class MainIter {
    public static void main(String[] args) {
        Bag<String> borsa = new all.bag.Bag<>();
        System.out.println(borsa.isEmpty());
        borsa.add("primo");
        System.out.println(borsa.toString());
        borsa.add("secondo");
        System.out.println(borsa.toString());
        borsa.add("secondo");
        System.out.println(borsa.toString());
        System.out.println(borsa.contains("secondo"));
        System.out.println(borsa.getFrequency("secondo"));
        borsa.clear();
        System.out.println(borsa.toString());

    }
}
