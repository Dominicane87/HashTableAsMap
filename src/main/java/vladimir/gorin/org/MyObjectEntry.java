package vladimir.gorin.org;

import java.util.Objects;

public class MyObjectEntry {
   private Object key;
    private Object value;

    public MyObjectEntry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    public boolean equalsKey(Object key){
        return this.key.equals(key);
    }
    public boolean equalsValue(Object value){
        return  this.value.equals(value);
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setValue(Object value) {
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
