package vladimir.gorin.org;

import java.util.Objects;

public class MyObjectEntry<K, V> {
    private K key;
    private V value;

    MyObjectEntry(K key, V value) {
        this.key = key;
        this.value = value;
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

    public void setKey(K key) {
        this.key = key;
    }

    void setValue(V value) {
        this.value = value;
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
}
