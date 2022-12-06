import java.util.LinkedList;

/*
 * @author Gwarda(942437)
 * @author Maushart(939781)
 */

// Inner class for Key-Value-Pairs with attributes key and String
class KeyValuePair {
    int key;
    String value;

    //Getter
    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    // Constructor
    public KeyValuePair(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

/*
    Class hashtable with linked-lists as an array for implementation
    with variable, chose able size as an integer
 */
public class HashTable implements IntStringMap {
    private int size;
    private int capacy;
    private LinkedList<KeyValuePair>[] hashtable;

    // Getter
    public int getSize() {
        return size;
    }

    public int getCapacy() {
        return capacy;
    }

    // Constructor hashtable with size and array of linked-list with KeyValuePairs
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        hashtable = (LinkedList<KeyValuePair>[]) new LinkedList[size];
    }

    // Hash-function to calculate the positive hash for Index (absolute -> Math.abs)
    private int hashFunction(Integer key) {
        return Math.abs(key.hashCode() % size);
    }


    /**
     * Method for factorisation the Hashtable to more capacy
     * @param old       = Linked list of KeyValuePairs -> hashtable
     * @param oldCapacy = int with the capacy of the hashtable filled over a certain amount of %
     * @return Linked list of KeyValuePairs
     * HT = Hashtable
     * Method to refactor the input HT to a new one with the size of the next given prime number from the
     * old size. The method generates the new HT as an array of Linked-list and iterates with the nested
     * for-loops through the old list to copy the KeyValuePairs with recalculation into the new HT.
     */
    private LinkedList<KeyValuePair>[] reFactorHashtable(LinkedList<KeyValuePair>[] old, int oldCapacy) {
        @SuppressWarnings("unchecked")
        LinkedList<KeyValuePair>[] newHashTable = new LinkedList[nextPrime(oldCapacy)];
        for (int i = 0; i < old.length; i++) {
            LinkedList<KeyValuePair> list = hashtable[i];
            for (int j = 0; j <= old.length; j++) {
                if (list != null) {
                    if (list.size() == j)   // to avoid out-of-bounds-exception
                        break;
                    KeyValuePair kvp = list.get(j);
                    int index = hashFunction(kvp.getKey());
                    if (newHashTable[index] == null)              // to avoid null-pointer-exception
                        newHashTable[index] = new LinkedList<>();
                    LinkedList<KeyValuePair> newList = newHashTable[index];
                    KeyValuePair add = new KeyValuePair(kvp.getKey(), kvp.getValue());
                    newList.add(add);
                }
            }
        }
        return newHashTable;
    }
    // method for calculating prime numbers
    private int nextPrime(int num) {
        num++;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                num++;
                i = 2;
            }
        }
        return num;
    }

    /**
     * Method for checking keys and removing them if found
     * @param key   = Integer
     * @param index = int
     * @return = removed Value or if not available null
     * The method is iterating through the linked list at the calculated index in the array
     * and compares every saved key with the new given key. The value of a matching key gets saved
     * in a variable called "value" and the "KeyValuePair" removed from the list. If no match is
     * the value given back is null
     */
    private String checkAndRemove(Integer key, int index) {
        String value = null;
        LinkedList<KeyValuePair> list = hashtable[index];
        for (int i = 0; i < list.size(); i++) {
            KeyValuePair keyValuePair = list.get(i);
            int keyIndex = keyValuePair.getKey();
            if (key == keyIndex) {
                value = keyValuePair.getValue();
                list.remove(i);
                break;
            }
        }
        return value;
    }
    // Interface-implementation:
    /**
     * {@link  IntStringMap#put(Integer, String)}
     * Method for adding new Key-Value-pairs to the hashtable. With included function
     * to avoid doubled keys, in that case the key is saved with the new given value
     */
    public String put(Integer key, String newValue) {
        int index = hashFunction(key);
        if ((100 / hashtable.length) * capacy >= 80) {                  // capacy-check at over 80 % -> refactor
            hashtable = reFactorHashtable(hashtable, hashtable.length); //info @ reFactorHashtable
            size = hashtable.length;
        }
        if (hashtable[index] == null) {     // to avoid null-pointer-exception
            hashtable[index] = new LinkedList<>();
            capacy++;
        }
        String value = checkAndRemove(key, index);        //checks if the key already exists info @ checkAndRemove
        LinkedList<KeyValuePair> list = hashtable[index]; // Generates and adds a new Key-Value-Pair to hashtable
        KeyValuePair add = new KeyValuePair(key, newValue);
        list.add(add);
        return value;
    }

    /**
     * {@link IntStringMap#get(Integer)}
     * Method for getting a value, to a key. It checks if the index isn't null and iterates through the
     * linked-list at the index in the hashtable. Return value is a string of the value from the matching key,
     * or null if not found.
     */
    public String get(Integer key) {
        int index = hashFunction(key);
        if (hashtable[index] != null) {
            LinkedList<KeyValuePair> list = hashtable[index];   // Gets the linked list at the Index to compare keys
            for (KeyValuePair keyValuePair : list) {
                int savedKey = keyValuePair.getKey();
                if (key == savedKey) {
                    return keyValuePair.getValue();
                }
            }
        }
        return null;
    }

    /**
     * {@link IntStringMap#remove(Integer)}
     * Method that uses {@link HashTable#checkAndRemove(Integer, int)}
     * to remove objects from the hashtable, with return as String from removed value
     * or null if not found
     */
    public String remove(Integer key) {
        int index = hashFunction(key);
        if (hashtable[index] != null) {
            System.out.println(checkAndRemove(key, index));
            return checkAndRemove(key, index);
        }
        return null;
    }
}

