package exam._13_collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class CollectionsExample {
    public static void main(String[] args) {
        System.out.println(sortedList(Arrays.asList(3, 1, 2)));
        System.out.println(countWords(new String[] {"java", "test", "java", "code"}));
        System.out.println(uniqueSet(Arrays.asList(10, 10, 20)));
        System.out.println(queueExample());
        System.out.println(heapExample());
        System.out.println(treeMapExample());
    }

    public static List<Integer> sortedList(List<Integer> input) {
        List<Integer> numbers = new ArrayList<>(input);
        Collections.sort(numbers);
        return numbers;
    }

    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        return countMap;
    }

    public static Set<Integer> uniqueSet(List<Integer> values) {
        Set<Integer> visited = new HashSet<>();
        visited.addAll(values);
        return visited;
    }

    public static List<Integer> queueExample() {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        result.add(queue.poll());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        result.add(deque.pollLast());
        return result;
    }

    public static List<Integer> heapExample() {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(1);
        minHeap.offer(3);
        result.add(minHeap.poll());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(1);
        maxHeap.offer(3);
        result.add(maxHeap.poll());
        return result;
    }

    public static List<Integer> treeMapExample() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(30, "thirty");
        treeMap.put(10, "ten");
        treeMap.put(20, "twenty");

        return Arrays.asList(treeMap.firstKey(), treeMap.ceilingKey(15));
    }
}
