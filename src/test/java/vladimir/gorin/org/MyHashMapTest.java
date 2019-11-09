package vladimir.gorin.org;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap myMap = new MyHashMap();
    private HashMap<Object, Object> map = new HashMap<>();


    @Test
    public void increaseMap() {
        for (int i=0;i<100;i++) {
            myMap.put(i,1);
            map.put(i,1);
        }
        assertEquals(map.entrySet(),myMap.entrySet());
        assertEquals(map.size(),myMap.size());
    }
    @Test
    public void containsKey() {
        assertFalse(myMap.containsKey("1"));
        assertFalse(map.containsKey("1"));
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put(null, "1");
        map.put(null, "1");
        assertEquals(map.containsKey("1"),myMap.containsKey("1"));
        assertEquals(map.containsKey(null),myMap.containsKey(null));
        assertTrue(myMap.containsKey(null));

    }

    @Test
    public void containsValue() {
        assertFalse(myMap.containsValue("1"));
        assertFalse(map.containsValue("1"));
        myMap.put("1", "1");
        map.put("1", "1");
        assertTrue(myMap.containsValue("1"));
        assertTrue(map.containsValue("1"));
        assertEquals(map.containsValue("1"),myMap.containsValue("1"));
        myMap.put("1", null);
        map.put("1", null);
        assertTrue(myMap.containsValue(null));
        assertTrue(map.containsValue(null));
        assertEquals(map.containsValue(null),myMap.containsValue(null));
    }

    @Test
    public void get() {
        myMap.put("1", "1");
        map.put("1", "1");
        assertEquals(map.get("1"), myMap.get("1"));
        myMap.put(null, "1");
        map.put(null, "1");
        assertEquals(map.get(null), myMap.get(null));
        map.put(null, "2");
        assertNotEquals(map.get(null), myMap.get(null));
    }

    @Test
    public void put() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put("1", "2");
        map.put("1", "2");
        assertEquals("2",myMap.get("1"));
        assertEquals(map.get("1"), myMap.get("1"));
        map.put("1", 3);
        assertNotEquals(map.get("1"), myMap.get("1"));
        myMap.put(null,"2");
        assertNotEquals(map.get(null), myMap.get(null));
        map.put(null,"2");
        assertEquals(map.get(null), myMap.get(null));
    }

    @Test
    public void remove() {
        myMap.put("1", "1");
        map.put("1", "1");

        myMap.remove("1");
        map.remove("1");
        assertEquals(0,myMap.size());
        assertEquals(map.entrySet(),myMap.entrySet());


        try {
            map.remove("2");
        }catch (IllegalStateException e){
            assertTrue(true);
        }
        try {
            myMap.remove("2");
        }catch (IllegalStateException e){
            assertTrue(true);
        }

    }

    @Test
    public void keySet() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put("2", "1");
        map.put("2", "1");
        assertEquals(myMap.keySet(),map.keySet());
    }

    @Test
    public void values() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put("2", "1");
        map.put("2", "1");
        assertEquals(map.size(),myMap.size());
        Set<Object> set=map.keySet();
        for (Object o : set) {
            assertEquals(map.get(o),myMap.get(o));
            }
        }



    @Test
    public void putAll() {
        Map<Object, Object> tmp = new HashMap<>();
        tmp.put("1","1");
        tmp.put("2","1");
        map.putAll(tmp);
        myMap.putAll(tmp);
        assertEquals(map.entrySet(),myMap.entrySet());
    }

    @Test
    public void clear() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put("2", "1");
        map.put("2", "1");
        myMap.remove("2");
        map.remove("2");
        myMap.remove(null);
        map.remove(null);

        assertEquals(map.size(),myMap.size());
    }
}