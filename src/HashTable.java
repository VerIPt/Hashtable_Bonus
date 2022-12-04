import java.util.LinkedList;

    /*
     * @author Gwarda(942437)
     * @author Maushart(939781)
     */

    // Inner class for Key-Value-Pairs with attributes key and String
    class KeyValuePair
    {
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
        public KeyValuePair(int key, String value)
        {
            this.key = key;
            this.value = value;
        }
    }
    /*
        Class hashtable with linked-lists as an array for implementation
        with variable, chose able size as integer
     */
    public class HashTable implements IntStringMap {
        private final int size;
        private LinkedList<KeyValuePair>[] hashtable;

        // Getter
        public int getSize() {
            return size;
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
         * Method for checking keys and removing them if found
         * @param key = Integer
         * @param index = int
         * @return = removed Value or if not available null
         */
        private String checkAndRemove(Integer key, int index) {
            String value = null;
            LinkedList<KeyValuePair> list = hashtable[index];
            //iterating through linked-list at index
            for (int i = 0; i < list.size(); i++) {
                KeyValuePair keyValuePair = list.get(i);
                int keyIndex = keyValuePair.getKey();       // saves every key for checking
                // checks for matching keys, and removes the Key-Value-pair -> returns removed value
                if (key == keyIndex) {
                    value = keyValuePair.getValue();
                    list.remove(i);
                    break;
                }
            }
            return value;
        }

        /**
         * {@link  IntStringMap#put(Integer, String)}
         * Method for adding new Key-Value-pairs to the hashtable. With included function
         * to avoid doubled keys, in that case the key is saved with the new given value
         */
        public String put(Integer key, String newValue) {
            // determine new index with the hash function
            int index = hashFunction(key);
            int capacy = 0;
            // checking capacy of hashTable
            for (LinkedList<KeyValuePair> keyValuePairs : hashtable)
                if (keyValuePairs != null) {
                    //System.out.println(capacy);
                    capacy++;
                }

            if (capacy + 2 == hashtable.length) {
                LinkedList<KeyValuePair>[] newHashTable;
                newHashTable = reFactorHashtable(hashtable, hashtable.length);
                hashtable = newHashTable;
                System.out.println(newHashTable.length);

            }
            /*
            In case the index in the array (hashtable) is null, there will be
            a new empty linked-list created, in witch there can be new Key-Value-Objects
            generated and saved
            */
            if (hashtable[index] == null)
                hashtable[index] = new LinkedList<>();

            //checks if the key already exists info @ checkAndRemove
            String value = checkAndRemove(key, index);
            // Generates and adds a new Key-Value-Pair to hashtable
            LinkedList<KeyValuePair> list = hashtable[index];
            KeyValuePair add = new KeyValuePair(key, newValue);
            list.add(add);
            System.out.println("Hinzugefügt bei key; "+key+" at index: "+index+" "+newValue);
            System.out.println( "Alter Wert: "+ value);
            return value;
        }

        /**{@link IntStringMap#get(Integer)}
         * Method for getting a value, to a key. It checks if the index is null and goes through all linked-lists
         * at the index in the hashtable if the index isn't null. Return value is a string of the value from the
         * matching key, or null if not found
         */
        public String get(Integer key) {
            int index = hashFunction(key);
            if (hashtable[index] != null) {
                // saves the linked-list at the index in the hashtable from the given Key-Value-pair
                LinkedList<KeyValuePair> list = hashtable[index];
                for (KeyValuePair keyValuePair : list) {        // iterates through linked-list
                    int savedKey = keyValuePair.getKey();       // saves every key for checking
                    // check for matching keys and returns value as String if found
                    if (key == savedKey) {
                        System.out.println("get from key: "+key+"at index: "+index+" "+keyValuePair.getValue());
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
                return checkAndRemove(key,index);

            }
            return null;
        }
        // Bonus
        private LinkedList<KeyValuePair>[] reFactorHashtable(LinkedList<KeyValuePair>[] old, int oldCapacy){
            @SuppressWarnings("unchecked")
            LinkedList<KeyValuePair>[] newHashTable = new LinkedList[nextPrime(oldCapacy)];
            for (int i = 0; i < old.length; i ++){
                LinkedList<KeyValuePair> list = hashtable[i];
                for (int j = 0; j <= old.length; j++) {
                    if (hashtable[i] != null) {
                        if (list.size() == j)
                            break;
                        KeyValuePair kvp = list.get(j);
                        int key = kvp.getKey();
                        String value = kvp.getValue();
                        if (newHashTable[hashFunction(kvp.getKey())] == null)
                            newHashTable[hashFunction(kvp.getKey())] = new LinkedList<>();
                        LinkedList<KeyValuePair> newList = newHashTable[hashFunction(kvp.getKey())];
                        KeyValuePair add = new KeyValuePair(key, value);
                        newList.add(add);
                        System.out.println("Hinzugefügt bei key; "+key+" at index: "+hashFunction(kvp.getKey())+" "+value);
                    }
                }
            }
            return newHashTable;
        }
        private int nextPrime(int num) {
            num++;
            for (int i = 2; i < num; i++) {
                if(num%i == 0) {
                    num++;
                    i=2;
                }
            }
            return num;
        }
    }

