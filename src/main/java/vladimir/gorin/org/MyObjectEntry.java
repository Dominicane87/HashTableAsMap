package vladimir.gorin.org;

import java.util.Objects;

public class MyObjectEntry<K, V> {
    private K key;
    private V value;
    private MyObjectEntry<K,V> next;


    MyObjectEntry() {

    }

    MyObjectEntry<K,V> getNext() {
        return next;
    }

    void setNext(MyObjectEntry<K,V> next) {
        this.next = next;
    }

    MyObjectEntry(K key, V value) {
        this.key = key;
        this.value = value;
        next=null;
    }

    public boolean equalsKey(K key) {
        return this.key.equals(key);
    }

    public boolean equalsValue(V value) {
        return this.value.equals(value);
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

}
