
public class Main {
    public static void main(String[] args) {
        HashTable ht = new HashTable(11);
        addingTests(ht);

        for (int i = 0; i <= 11; i++) {
            System.out.println("Add to key " + i + ": " + ht.put(i, Integer.toString(i)));
        }
        System.out.println("Capacy : " + ht.getCapacy());

        System.out.println(ht.getSize());
        addingTests(ht);

        for (int i = 0; i <= 15; i++) {
            System.out.println("Add to key " + i + ": " + ht.put(i, Integer.toString(i)));
        }
        System.out.println(ht.getSize());
        System.out.println("Capacy : " + ht.getCapacy());
        System.out.println();
    }





    private static void addingTests(HashTable ht) {
        System.out.println("Add to key 12334: " + ht.put(12334, "lala luna"));
        System.out.println("Add to key 98873: " + ht.put(98873, "Hugo Hafer "));
        System.out.println("Add to key 98873: " + ht.put(98873, "Fina Fein ") + ht.get(98873));
        System.out.println("Key 98873: " + ht.get(98873));
        System.out.println("key 98873 removed: " + ht.remove(98873));
        System.out.println("Key 98873: " + ht.get(98873));
        System.out.println("Add to key 5: " + ht.put(5, "Fischers Fritz "));
        System.out.println("Key 12334: " + ht.get(12334));
        System.out.println("key 5: " + ht.get(5));
        System.out.println("Add to key 12334: " + ht.put(12334, "lala luna"));
        ht.get(98873);
        ht.put(98873, "Ingo Igel ");
        System.out.println(ht.getSize());
        System.out.println("Capacy : " + ht.getCapacy());
    }
}