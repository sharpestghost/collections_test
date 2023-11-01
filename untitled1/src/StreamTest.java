import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
       // test1();
        test5();
    }

    private static void test1() {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(4, "Alice"));
        list.add(new Cat(5, "Oscar", CatColor.GRAY));
        var stream = list.stream().filter(c -> c.color == CatColor.GRAY);
        System.out.println(stream);
    }

    private static void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squaredNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squaredNumbers);
    }

    private static void streamOrderCode() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean result = numbers.stream()
                .filter(n -> n < 10)
                .map(n -> n * n)
                .anyMatch(n -> n > 0);
        System.out.println(result);
    }

    private static void collectionOrderTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean result = false;
        List<Integer> numbersUpdatedAfterFilter = new ArrayList<>();
        for (int el: numbers) {
            if (el < 10) numbersUpdatedAfterFilter.add(el);
        }
        List<Integer> numbersUpdatedAfterMap = new ArrayList<>();
        for (int el: numbersUpdatedAfterFilter) {
            numbersUpdatedAfterMap.add(el * el);
        }
        for (int el: numbersUpdatedAfterMap) {
            if (el > 0) {
                result = true;
                break;
            }
        }
        System.out.println(result);
    }

    private static void test5() {
        List<Integer> numbers = Stream.generate(() -> 10)
                .limit(1000000).toList();
        Predicate<Integer> predicate = i -> i == 10;
        boolean result = numbers.stream()
                .filter(predicate)
                .anyMatch(predicate);
        System.out.println(result);
    }
}

class Cat {
    final int age;
    final String name;
    final CatColor color;

    public Cat(int age, String name) {
        this.age = age;
        this.name = name;
        this.color = CatColor.UNKNOWN;
    }

    public Cat(int age, String name, CatColor color) {
        this.age = age;
        this.name = name;
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public CatColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (age != cat.age) return false;
        if (!name.equals(cat.name)) return false;
        return color == cat.color;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + name.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}

enum CatColor {
    GRAY, BLACK, YELLOW, WHITE, UNKNOWN
}
