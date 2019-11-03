package vladimir.gorin.org;

import java.util.*;

public class MyHashMap<K,V> implements Map {

    private static final double CAPACITY_LIMIT = 0.75;
    private static final int DEEPNESS_LIST = 16;
    private int amountBuckets = 5;
    private MyLinkedList<MyObjectEntry>[] hashMap;

    private int count = 0;

    MyHashMap() {
        init();
    }

    private void init() {
        hashMap = new MyLinkedList[amountBuckets];
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new MyLinkedList<>();
        }
    }

    private MyLinkedList<MyObjectEntry> getList(K key) {
        int hashPos = findHashTable(key);
        return hashMap[hashPos];
    }


    private void increaseMyHashMap(MyLinkedList<MyObjectEntry>[] hashMap) {
        amountBuckets = amountBuckets * 2;
        MyLinkedList<MyObjectEntry>[] tmpHashMap = (MyLinkedList<MyObjectEntry>[]) new MyLinkedList[amountBuckets];
        System.arraycopy(hashMap, 0, tmpHashMap, 0, hashMap.length);
        this.hashMap=tmpHashMap;
    }

    private boolean add(K key, V value) {
        //Think about increasing massive and evolving
        if (size() > CAPACITY_LIMIT * amountBuckets * DEEPNESS_LIST) increaseMyHashMap(hashMap);

        if (key == null) {
            throw new NullPointerException("The key for addLast() is null.");
        }
        MyObjectEntry<K,V> myObjectEntry = new MyObjectEntry<>(key, value);

        if (!containsKeyMyHashMap(key)) {
            getList(key).add(myObjectEntry);
            count++;
            return true;
        } else {
            throw new IllegalStateException("This key is already exist");
        }

    }

    private boolean update(K key, V value) {
        try {
            getList(key).findByKey(new MyObjectEntry<K,V>(key, null)).setValue(value);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private V getMyHashMap(K key) {
        MyObjectEntry<K,V> isFinding = new MyObjectEntry<>(key, null);
        try {
            return getList(key).findByKey(isFinding).getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Object removeMyHashMap(K key) {
        MyObjectEntry<K,V> isDeleting = new MyObjectEntry<>(key, null);
        Object result = getList(key).remove(isDeleting);
        if (result != null) count--;
        return result;
    }

    public HashSet<MyObjectEntry> entrySet() {
        HashSet<MyObjectEntry> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry);
            }
        }
        return set;
    }

    private HashSet<K> keySetHashMap() {
        HashSet<K> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add((K) myObjectEntry.getKey());
            }
        }
        return set;
    }

    private Collection valuesHashMap() {
        Collection<Object> set = new LinkedList<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry.getValue());
            }
        }
        return set;
    }


    private boolean containsKeyMyHashMap(K key) {
        if (key == null) throw new NullPointerException("Input key is null");
        HashSet<K> set = keySetHashMap();
        for (K object : set) {
            if (object.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsValueMyHashMap(V value) {
        Collection<V> set = valuesHashMap();
        for (V object : set) {
            if (object.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
//        int count = 0;
//        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {
//            count += myObjectEntries.size();
//        }
        return count;
    }

    private boolean isEmptyMyHashMap() {
        return size() == 0;
    }


    private int findHashTable(Object key) {
        return key.hashCode() % hashMap.length;
    }

    @Override
    public boolean isEmpty() {
        return isEmptyMyHashMap();
    }

    @Override
    public boolean containsKey(Object key) {
        return containsKeyMyHashMap(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValueMyHashMap(value);
    }

    @Override
    public Object get(K key) {
        return getMyHashMap(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return update(key, value) ? update(key, value) : add(key, value);
    }

    @Override
    public Object remove(Object key) {
        return removeMyHashMap(key);
    }


    @Override
    public Set keySet() {
        return keySetHashMap();
    }

    @Override
    public Collection values() {
        return valuesHashMap();
    }

    @Override
    public void putAll(Map m) {
        Set<Map.Entry<Object, Object>> set = (Set<Map.Entry<Object, Object>>) m.entrySet();
        for (Map.Entry<Object, Object> myObjectEntry : set) {
            put(myObjectEntry.getKey(), myObjectEntry.getValue());
        }
    }

    @Override
    public void clear() {
        init();
    }
}
