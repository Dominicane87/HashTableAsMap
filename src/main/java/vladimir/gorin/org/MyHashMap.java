package vladimir.gorin.org;

import java.util.*;

public class MyHashMap<K,V> implements Map {

    private static final double CAPACITY_LIMIT = 0.75;
    private static final int DEEPNESS_LIST = 16;
    private int amountBuckets = 5;
    private MyLinkedList<MyObjectEntry<K,V>>[] hashMap;

    private int count = 0;

    MyHashMap() {
        init();
    }
@SuppressWarnings("unchecked")
    private void init() {
        hashMap = new MyLinkedList[amountBuckets];
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new MyLinkedList<>();
        }
    }

    private MyLinkedList<MyObjectEntry<K,V>> getList(Object key) {
        int hashPos = findHashTable(key);
        return hashMap[hashPos];
    }

@SuppressWarnings("unchecked")
    private void increaseMyHashMap(MyLinkedList<MyObjectEntry<K,V>>[] hashMap) {
        amountBuckets = amountBuckets * 2;
        MyLinkedList<MyObjectEntry<K,V>>[] tmpHashMap = (MyLinkedList<MyObjectEntry<K,V>>[]) new MyLinkedList[amountBuckets];
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

    public Set<Entry<K,V>> entrySet() {
        HashMap<K,V> map=new HashMap<>();
        Set<Entry<K,V>> set = map.entrySet();
        Entry<K,V> entry;
        for (MyLinkedList<MyObjectEntry<K,V>> myObjectEntries : hashMap) {

            for (MyObjectEntry<K,V> myObjectEntry : myObjectEntries) {
                entry=new MyEntry<>(myObjectEntry.getKey(),myObjectEntry.getValue());
                set.add(entry);
            }
        }
        return set;
    }

    private HashSet<K> keySetHashMap() {
        HashSet<K> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry<K,V>> myObjectEntries : hashMap) {

            for (MyObjectEntry<K,V> myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry.getKey());
            }
        }
        return set;
    }

    private Collection<V> valuesHashMap() {
        HashMap<Integer,V> map=new HashMap<>();
        int i=0;
        for (MyLinkedList<MyObjectEntry<K,V>> myObjectEntries : hashMap) {

            for (MyObjectEntry<K,V> myObjectEntry : myObjectEntries) {
                map.put(i,myObjectEntry.getValue());
                i++;
            }
        }
        return map.values();
    }


    private boolean containsKeyMyHashMap(K key) {
        if (key == null) throw new NullPointerException("Input key is null");

        return getMyHashMap(key) != null;
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

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsKey(Object key) {
        return containsKeyMyHashMap((K) key);
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean containsValue(Object value) {
        return containsValueMyHashMap((V) value);
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object get(Object key) {
        return getMyHashMap((K)key);
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return update((K) key, (V) value) ? update((K) key, (V) value) : add((K) key, (V) value);
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object remove(Object key) {
        return removeMyHashMap((K) key);
    }


    @Override
    public Set keySet() {
        return keySetHashMap();
    }

    @Override
    public Collection values() {
        return valuesHashMap();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(Map m) {
        Set<Map.Entry<K, V>> set = (Set<Map.Entry<K, V>>) m.entrySet();
        for (Map.Entry<K, V> myObjectEntry : set) {
            put(myObjectEntry.getKey(), myObjectEntry.getValue());
        }
    }

    @Override
    public void clear() {
        init();
    }
}
