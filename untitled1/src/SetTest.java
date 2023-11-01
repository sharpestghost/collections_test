import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SetTest {
    public static void main(String[] args) {
        MutableClass value = new MutableClass();
        value.setValue(1);
        Set<MutableClass> set = new HashSet();
        set.add(value);
        System.out.println("set contains key" + set.contains(value));
        value.setValue(2);
        System.out.println(set.size());
    }


}

class MutableClass {
    private int value;
    public void setValue(int value) {
        this.value = value;
    }
    public int hashCode() {
        return value;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutableClass mc = (MutableClass) o;
        return mc.value == this.value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }


}