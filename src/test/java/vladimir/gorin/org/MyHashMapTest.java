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
        }
    }
    @Test
    public void containsKey() {
        assertFalse(myMap.containsKey("1"));
        assertFalse(map.containsKey("1"));
        myMap.put("1", "1");
        map.put("1", "1");
        assertTrue(myMap.containsKey("1"));
        assertTrue(map.containsKey("1"));
    }

    @Test
    public void containsValue() {
        assertFalse(myMap.containsValue("1"));
        assertFalse(map.containsValue("1"));
        myMap.put("1", "1");
        map.put("1", "1");
        assertTrue(myMap.containsValue("1"));
        assertTrue(map.containsValue("1"));
    }

    @Test
    public void get() {
        myMap.put("1", "1");
        map.put("1", "1");
        assertEquals(map.get("1"), myMap.get("1"));
    }

    @Test
    public void put() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.put("1", "2");
        map.put("1", "2");
        assertEquals(map.get("1"), myMap.get("1"));
        map.put("1", 3);
        assertNotEquals(map.get("1"), myMap.get("1"));
    }

    @Test
    public void remove() {
        myMap.put("1", "1");
        map.put("1", "1");
        myMap.remove("1");
        map.remove("1");
        assertEquals(myMap.containsKey("1"), map.containsKey(1));


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
        assertEquals(map.size(),myMap.size());
    }
}