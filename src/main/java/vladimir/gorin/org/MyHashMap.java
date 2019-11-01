package vladimir.gorin.org;

import java.util.HashSet;

public class MyHashMap {
    private MyLinkedList<MyObjectEntry>[] hashMap;
    static final double CAPACITY_LIMIT = 0.75;
    static final int DEEPNESS_LIST =16;
    public int AMOUNT_BUCKETS =5;

    int count = 0;

    MyHashMap() {
        hashMap = new MyLinkedList[AMOUNT_BUCKETS];
        for (int i=0;i<hashMap.length;i++){
            hashMap[i]=new MyLinkedList<>();
        }
    }

    private MyLinkedList<MyObjectEntry> getList(Object key) {
        int hashPos = findHashTable(key);
        MyLinkedList<MyObjectEntry> myObjectEntries = hashMap[hashPos];
        return myObjectEntries;
    }


    public <myHashMap> void increaseMyHashMap(MyLinkedList<MyObjectEntry>[] hashMap ){
        AMOUNT_BUCKETS=AMOUNT_BUCKETS*2;
        MyLinkedList<MyObjectEntry>[] tmpHashMap = new MyLinkedList[AMOUNT_BUCKETS];
        for (int i=0;i<tmpHashMap.length;i++){
            tmpHashMap[i]=new MyLinkedList<>();
        }
        for (int i=0;i<hashMap.length;i++){
            tmpHashMap[i]=hashMap[i];
        }
        hashMap=tmpHashMap;
    }

    public void add(Object key, Object value) {
        //Think about increasing massive and evolving
        if (size()>CAPACITY_LIMIT*AMOUNT_BUCKETS*DEEPNESS_LIST) increaseMyHashMap(hashMap);

        if (key==null) { throw new NullPointerException("The key for addLast() is null."); }
        MyObjectEntry myObjectEntry = new MyObjectEntry(key, value);

        if (!isExistKey(key)) {
            getList(key).add(myObjectEntry);
            count++;
        } else {throw new IllegalMonitorStateException("This key is already exist");}

    }

    public void update(Object key, Object value) {
        MyObjectEntry isInHash = getMyObject(key);
        isInHash.setValue(value);
    }

    public MyObjectEntry getMyObject(Object key){
        MyObjectEntry isFinding = new MyObjectEntry(key, null);
        MyObjectEntry isInHash = getList(key).findByKey(isFinding);
        return isInHash;
    }

    public void delete(Object key) {
        MyObjectEntry isDeleting = new MyObjectEntry(key, null);
       Boolean result = getList(key).remove(isDeleting);
       if (result) count--;
    }


    public boolean isExistKey(Object key) {
        if (key==null) throw new IllegalMonitorStateException("Input key is null");
        HashSet<MyObjectEntry> set = entrySet();
        for (MyObjectEntry myObjectEntry : set) {
            if (myObjectEntry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistValue(Object value) {
        HashSet<MyObjectEntry> set = entrySet();
        for (MyObjectEntry myObjectEntry : set) {
            if (myObjectEntry.getValue().equals(value)) {
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

    private HashSet<MyObjectEntry> entrySet() {
        HashSet<MyObjectEntry> set = new HashSet<>();
        for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {

            for (MyObjectEntry myObjectEntry : myObjectEntries) {
                set.add(myObjectEntry);
            }
        }
        return set;
    }

    private int findHashTable(Object key) {
        return key.hashCode() % hashMap.length;
    }

}
