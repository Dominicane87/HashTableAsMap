package vladimir.gorin.org;

import java.util.*;

public class MyHashMap<K, V> implements Map {

    private static final double CAPACITY_LIMIT = 0.75;
    private static final int DEEPNESS_LIST = 16;
    private int amountBuckets = 5;
    private MyObjectEntry<K,V>[] hashMap;
    private int count = 0;


    MyHashMap() {
       initMyHashMap();
    }

    @SuppressWarnings("unchecked")
    private void initMyHashMap(){
        hashMap=(MyObjectEntry<K,V>[])new MyObjectEntry[amountBuckets];
    }



    private void increaseMyHashMap() {
        count=0;
        Set<Entry<K, V>> set = entrySet();
        amountBuckets = amountBuckets * 2;
        initMyHashMap();
        for (Entry<K, V> entry : set) {
            this.add(entry.getKey(), entry.getValue());
        }
    }
    private int findHashTable(Object key) {
        return key==null?1:key.hashCode()%amountBuckets;
    }
    private MyObjectEntry<K,V> getFirstElement(Object key){
        return hashMap[findHashTable(key)];
    }


    private V add(K key, V value) {
        if (size() > CAPACITY_LIMIT * amountBuckets * DEEPNESS_LIST) increaseMyHashMap();

           MyObjectEntry<K,V> first =getFirstElement(key);
           if (first==null){
               hashMap[findHashTable(key)]=new MyObjectEntry<>(key,value);
               count++;
               return null;
           }
           MyObjectEntry<K,V> prev=new MyObjectEntry<>();
           while (first!=null){
               prev=first;
               if (first.getKey().equals(key)) {
                   V old =first.getValue();
                   first.setValue(value);
                   return old;
               }
               first=first.getNext();
           }
           prev.setNext(new MyObjectEntry<>(key,value));
            count++;
            return null;
        }

    private V getMyHashMap(K key) {
        MyObjectEntry<K,V> first =getFirstElement(key);
        while (first!=null) {

            if ((first.getKey()==key)||(first.getKey().equals(key))){
                return first.getValue();
            }
            first=first.getNext();
        }
        return null;
    }

    private V removeMyHashMap(K key) {
        MyObjectEntry<K, V> first = getFirstElement(key);
        MyObjectEntry<K, V> prev = first;
        MyObjectEntry<K, V> curr = first;
        while (curr!=null) {
            //if first element
            if (curr.getKey().equals(key)&(curr==first)){
                hashMap[findHashTable(key)] =null;
                count--;
                return first.getValue();
            }

            if (curr.getKey().equals(key)){
                V old=curr.getValue();
                prev.setNext(curr.getNext());
                count--;
                return old;
            }
            prev=curr;
            curr=curr.getNext();
        }
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();

        for (MyObjectEntry<K, V> myObjectEntries : hashMap) {
            MyObjectEntry<K, V> curr=myObjectEntries;
            while (curr!=null){
                set.add(new MyEntry<>(curr.getKey(), curr.getValue()));
                curr=curr.getNext();
            }
        }
        return set;
    }

    private HashSet<K> keySetHashMap() {
        HashSet<K> set = new HashSet<>();
        for (Entry<K, V> kvEntry : entrySet()) {
            set.add(kvEntry.getKey());
        }
        return set;
    }

    private Collection<V> valuesHashMap() {
        List<V> list = new LinkedList<>();
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        Entry<K, V> entry;

        while (iterator.hasNext()) {
            entry = iterator.next();
            list.add(entry.getValue());
        }
        return list;
    }


    private boolean containsKeyMyHashMap(K key) {
        return keySetHashMap().contains(key);
    }

    private boolean containsValueMyHashMap(V value) {
        return valuesHashMap().contains(value);
    }

    public int size() {
        return count;
    }

    private boolean isEmptyMyHashMap() {
        return size() == 0;
    }




    @Override
    public boolean isEmpty() {
        return isEmptyMyHashMap();
    }


    @Override
    public boolean containsKey(Object key) {
        return containsKeyMyHashMap((K) key);
    }


    @Override
    public boolean containsValue(Object value) {
        return containsValueMyHashMap((V) value);
    }



    @Override
    public Object get(Object key) {
        return getMyHashMap((K) key);
    }


    @Override
    public Object put(Object key, Object value) {
        return add((K)key,(V)value);
    }


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


    @Override
    public void putAll(Map m) {
        Set<Map.Entry<K, V>> set = (Set<Map.Entry<K, V>>) m.entrySet();
        for (Map.Entry<K, V> myObjectEntry : set) {
            put(myObjectEntry.getKey(), myObjectEntry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(hashMap, null);
        count = 0;
    }
}
