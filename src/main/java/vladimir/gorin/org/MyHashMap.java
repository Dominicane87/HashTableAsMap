package vladimir.gorin.org;

import java.util.*;

public class MyHashMap implements Map {

    private static final double CAPACITY_LIMIT = 0.75;
    private static final int DEEPNESS_LIST = 16;
    private int AMOUNT_BUCKETS = 5;
    private MyLinkedList<MyObjectEntry>[] hashMap = new MyLinkedList[AMOUNT_BUCKETS];

    private int count = 0;

    MyHashMap() {
        init();
    }

    private void init() {
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new MyLinkedList<>();
        }
    }

    private MyLinkedList<MyObjectEntry> getList(Object key) {
        int hashPos = findHashTable(key);
        return hashMap[hashPos];
    }


    private <myHashMap> void increaseMyHashMap(MyLinkedList<MyObjectEntry>[] hashMap) {
        AMOUNT_BUCKETS = AMOUNT_BUCKETS * 2;
        MyLinkedList<MyObjectEntry>[] tmpHashMap = new MyLinkedList[AMOUNT_BUCKETS];
        for (int i = 0; i < tmpHashMap.length; i++) {
            tmpHashMap[i] = new MyLinkedList<>();
        }
        System.arraycopy(hashMap, 0, tmpHashMap, 0, hashMap.length);
    }

    boolean add(Object key, Object value) {
        //Think about increasing massive and evolving
        if (size() > CAPACITY_LIMIT * AMOUNT_BUCKETS * DEEPNESS_LIST) increaseMyHashMap(hashMap);

        if (key == null) {
            throw new NullPointerException("The key for addLast() is null.");
        }
        MyObjectEntry myObjectEntry = new MyObjectEntry(key, value);

        if (!containsKeyMyHashMap(key)) {
            getList(key).add(myObjectEntry);
            count++;
            return true;
        } else {
            throw new IllegalStateException("This key is already exist");
        }

    }

    private boolean update(Object key, Object value) {
        try {
            getList(key).findByKey(new MyObjectEntry(key, null)).setValue(value);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private Object getMyHashMap(Object key) {
        MyObjectEntry isFinding = new MyObjectEntry(key, null);
        try {
            return getList(key).findByKey(isFinding).getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Object removeMyHashMap(Object key) {
        MyObjectEntry isDeleting = new MyObjectEntry(key, null);
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

    private HashSet<Object> keySetHashMap() {
        HashSet<Object> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry.getKey());
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


    private boolean containsKeyMyHashMap(Object key) {
        if (key == null) throw new NullPointerException("Input key is null");
        HashSet<Object> set = keySetHashMap();
        for (Object object : set) {
            if (object.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsValueMyHashMap(Object value) {
        Collection<Object> set = valuesHashMap();
        for (Object object : set) {
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
    public Object get(Object key) {
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
        Set<Map.Entry<Object, Object>> set = m.entrySet();
        for (Map.Entry<Object, Object> myObjectEntry : set) {
            put(myObjectEntry.getKey(), myObjectEntry.getValue());
        }
    }

    @Override
    public void clear() {
        init();
    }
}
