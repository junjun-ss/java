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
        listExample();
        mapCountingExample();
        setExample();
        queueDequeExample();
        priorityQueueExample();
        treeMapExample();
    }

    private static void listExample() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 1, 2));
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    private static void mapCountingExample() {
        String[] words = {"java", "test", "java", "code"};
        Map<String, Integer> countMap = new HashMap<>();

        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        System.out.println(countMap);
    }

    private static void setExample() {
        Set<Integer> visited = new HashSet<>();
        visited.add(10);
        visited.add(10);
        System.out.println(visited.contains(10));
    }

    private static void queueDequeExample() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println(deque.pollLast());
    }

    private static void priorityQueueExample() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(1);
        minHeap.offer(3);
        System.out.println(minHeap.poll());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(1);
        maxHeap.offer(3);
        System.out.println(maxHeap.poll());
    }

    private static void treeMapExample() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(30, "thirty");
        treeMap.put(10, "ten");
        treeMap.put(20, "twenty");

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.ceilingKey(15));
    }
}
