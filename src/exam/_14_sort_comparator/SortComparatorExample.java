package exam._14_sort_comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortComparatorExample {
    public static void main(String[] args) {
        intArraySort();
        objectSort();
        twoDimensionalArraySort();
    }

    private static void intArraySort() {
        int[] numbers = {3, 1, 2};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void objectSort() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("kim", 20));
        people.add(new Person("lee", 20));
        people.add(new Person("park", 18));

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                if (a.age != b.age) {
                    return Integer.compare(a.age, b.age);
                }
                return a.name.compareTo(b.name);
            }
        });

        System.out.println(people);
    }

    private static void twoDimensionalArraySort() {
        int[][] points = {
            {2, 3},
            {1, 5},
            {1, 2}
        };

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        });

        for (int[] point : points) {
            System.out.println(Arrays.toString(point));
        }
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + ":" + age;
        }
    }
}
