
/*
 * @author Gwarda(942437),
 * @author Maushart(939781)
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MyTest {

    // Test to check the proper generation of the Object "KeyValuePair"
    @Test
    void keyValuePair() {
        Integer key = 12345;
        String value = "test";
        KeyValuePair test = new KeyValuePair(key, value);
        Assertions.assertEquals(key, test.getKey());
        Assertions.assertEquals(value, test.getValue());
    }

    //Test to check the capacy of the Array
    @Test
    void hashtableInitialisation() {
        HashTable test = new HashTable(31);
        Assertions.assertEquals(31, test.getSize());
    }

    //Test for the hashtable with put, get, and remove
    @Test
    void hashtablePutGetRemove() {
        HashTable test = new HashTable(11);
        // check for put and get
        Assertions.assertNull(test.put(98873, "Ingo Igel"));
        Assertions.assertEquals("Ingo Igel", test.get(98873));
        // check for linked-list to exclude problems on the same index
        // |98873 % 11| = 5
        Assertions.assertNull(test.put(5, "Fischers Fritz"));
        Assertions.assertEquals("Fischers Fritz", test.get(5));
        // check other value again on same index
        Assertions.assertEquals("Ingo Igel", test.put(98873, "Hugo Hafer"));
        Assertions.assertEquals("Hugo Hafer", test.get(98873));
        // check for not existing key
        Assertions.assertNull(test.get(12345));
        // check remove and value on same index after removal
        Assertions.assertEquals("Hugo Hafer", test.remove(98873));
        Assertions.assertEquals("Fischers Fritz", test.get(5));
        Assertions.assertNull(test.get(98873));
    }
    // Test for factorization of the hashtable, with check for size, capacy, index and other keys before
    // and after the actual refactoring

    @Test
     void hashtableRefactor() {
        int key = 98873;
        String value = "Ingo Igel";
        int capacy = 0;
        HashTable test = new HashTable(11);
        // tests before
        test.put(key, value);
        Assertions.assertEquals(1,test.getCapacy());
        Assertions.assertEquals(value, test.get(key));
        Assertions.assertEquals(key % test.getSize(), test.getIndex(key)); // key % 11 = 5
        // force hashtable to refactor
        for (int i = 0; i <= 11; i++) {
            test.put(i, Integer.toString(i));
            capacy++;
        }
        // tests after
        Assertions.assertEquals(13, test.getSize());
        Assertions.assertEquals(capacy, test.getCapacy());
        Assertions.assertEquals(value, test.get(key));
        Assertions.assertEquals(key % test.getSize(), test.getIndex(key)); // key % 13 = 8

    }
}
