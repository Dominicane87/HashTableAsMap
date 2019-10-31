package vladimir.gorin.org;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
    private MyHashMap myMap;

    @Test
    public void add() {
        myMap.add("1", "2");
        assertTrue(myMap.isExistKey("1"));
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void isExistKey() {
    }

    @Test
    public void isExistValue() {
    }

    @Test
    public void size() {
    }
}