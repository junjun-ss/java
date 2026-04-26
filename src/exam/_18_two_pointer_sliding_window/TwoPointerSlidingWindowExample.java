package exam._18_two_pointer_sliding_window;

import java.util.HashMap;
import java.util.Map;

public class TwoPointerSlidingWindowExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 5};
        System.out.println(countSubarraySum(arr, 5));
        System.out.println(minLengthAtLeast(arr, 7));
        System.out.println(longestSubstringWithoutDuplicate("abcaabcd"));
    }

    private static int countSubarraySum(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            if (sum == target) {
                count++;
            }
        }

        return count;
    }

    private static int minLengthAtLeast(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum >= target) {
                answer = Math.min(answer, right - left + 1);
                sum -= arr[left++];
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private static int longestSubstringWithoutDuplicate(String text) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int answer = 0;

        for (int right = 0; right < text.length(); right++) {
            char ch = text.charAt(right);
            if (lastIndex.containsKey(ch) && lastIndex.get(ch) >= left) {
                left = lastIndex.get(ch) + 1;
            }

            lastIndex.put(ch, right);
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}
