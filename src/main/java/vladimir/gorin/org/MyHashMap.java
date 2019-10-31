package vladimir.gorin.org;

public class MyHashMap {
    MyLinkedList<MyObjectEntry>[] hashMap = new MyLinkedList[5];


public int findHashTable(Object key){
    return key.hashCode() % hashMap.length;
}

public void add(Object key, Object value){
    //Think about increasing massive and evolving

    int hashPos=findHashTable(key);
    MyLinkedList<MyObjectEntry> list = hashMap[hashPos];
    MyObjectEntry myObjectEntry=new MyObjectEntry(key,value);
    list.add(myObjectEntry);
}

public void update(Object key, Object value){
    MyObjectEntry isFinding = new MyObjectEntry(key,value);
    int hashPos=findHashTable(key);

    MyLinkedList<MyObjectEntry> list = hashMap[hashPos];

    MyObjectEntry isInHash=list.findByKey(isFinding);
    isInHash.setValue(value);
}

public boolean isExistKey(Object key){
    try {
        hashMap[findHashTable(key)].findByKey(new MyObjectEntry(key,null));
    } catch (IllegalMonitorStateException e){
        return false;
    }
    return true;
}

//public  boolean isExistValue(Object value){
//    for (MyObjectEntry object:hashMap){
//        if (object.getValue().equals(value)) {
//            return  true;
//        }
//    }
//    return false;
//}

public int size(){
    int count=0;
    for (MyLinkedList<MyObjectEntry> myObjectEntries : hashMap) {
        count+=myObjectEntries.size();
    }
         return  count;
}
}
