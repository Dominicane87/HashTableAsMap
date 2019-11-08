package vladimir.gorin.org;

import java.util.Objects;

public class MyObjectEntry<K, V> {
    private K key;
    private V value;
    private MyObjectEntry next;
    private boolean isNull;

    MyObjectEntry() {
        isNull=true;
    }

    MyObjectEntry getNext() {
        return next;
    }

    void setNext(MyObjectEntry next) {
        this.next = next;
    }

    MyObjectEntry(K key, V value) {
        this.key = key;
        this.value = value;
        next=null;
        isNull=false;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyObjectEntry)) return false;
        MyObjectEntry that = (MyObjectEntry) o;
        return getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
