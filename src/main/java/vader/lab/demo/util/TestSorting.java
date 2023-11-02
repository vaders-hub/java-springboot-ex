package vader.lab.demo.util;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.json.JSONArray;
import org.json.JSONObject;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@Data
class Developer {
    String name;
    BigDecimal salary;
    Integer age;


    public Developer(String name, BigDecimal hh, Integer age) {
        this.name = name;
        this.salary = hh;
        this.age = age;
    }

}

@Data
@AllArgsConstructor
class Employee {

    String name;
    Integer age;
    BigDecimal salary;
}

class SimplePrinter {
    public static void print(String str) {
        System.out.println(str);
    }
}

class IntegerUtils {

    public static String join(Integer a, Integer b) {
        return String.valueOf(a + b);
    }

}

public class TestSorting {
    public TestSorting(String[] args) {

        List<Developer> listDevs = getDevelopers();

        List<Employee> list = Arrays.asList(
                new Employee("mkyong", 38, BigDecimal.valueOf(3800)),
                new Employee("zilap", 5, BigDecimal.valueOf(100)),
                new Employee("ali", 25, BigDecimal.valueOf(2500)),
                new Employee("unknown", 99, BigDecimal.valueOf(9999)));

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        System.out.println("After Sort");

        //lambda here!
        listDevs.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
        // listDevs.sort((Developer o1, Developer o2)->o1.getName().compareTo(o2.getName()));
        // listDevs.sort((o1, o2)->o1.getName().compareTo(o2.getName()));

        // Comparator<Developer> salaryComparator = (o1, o2) -> o1.getSalary().compareTo(o2.getSalary());
        // listDevs.sort(salaryComparator.reversed());

        // String result1 = playTwoArgument(1, 2, (a, b) -> IntegerUtils.join(a, b));  // 3

        String result2 = playTwoArgument(1, 2, IntegerUtils::join);

        // take two Integers and return a List<Integer>
        BiFunction<Integer, Integer, List<Integer>> func3 = (x1, x2) -> Collections.singletonList(x1 + x2);
        List<Developer> lines = Collections.singletonList(new Developer("mkyong", new BigDecimal("70000"), 33));

        List<Integer> result3 = func3.apply(2, 3);

        System.out.println(result3);


        listDevs.forEach((developer) -> System.out.println(developer));

        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        List<String> collect = Stream.of(array)     // Stream<String[]>
                .flatMap(Stream::of)                // Stream<String>
                .filter(x -> !"a".equals(x))        // filter out the a
                .collect(Collectors.toList());      // return a List

        collect.forEach(System.out::println);

        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> streamOfArrayPart =
                Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]


        JSONObject authObj = new JSONObject();

    }

    private static <R> R playTwoArgument(Integer i1, Integer i2,
                                         BiFunction<Integer, Integer, R> func) {
        return func.apply(i1, i2);
    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();
        List<String> list = Arrays.asList("node", "java", "python", "ruby");

        list.forEach(System.out::println);

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String x) {
                SimplePrinter.print(x);
            }
        });

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;

    }
}
