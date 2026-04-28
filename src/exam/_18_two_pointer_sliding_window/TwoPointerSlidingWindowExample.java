package exam._18_two_pointer_sliding_window;

import java.util.HashMap;
import java.util.Map;

public class TwoPointerSlidingWindowExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 5};
        System.out.println(countPositiveSubarraySum(arr, 5));
        System.out.println(minLengthAtLeast(arr, 7));
        System.out.println(longestSubstringWithoutDuplicate("abcaabcd"));
        System.out.println(countSubarraySumWithNegatives(new int[] {1, -1, 2, 3, -2}, 3));
    }

    // 모든 원소가 0 이상일 때만 쓸 수 있는 투포인터 방식입니다.
    public static int countPositiveSubarraySum(int[] arr, int target) {
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

    // 음수가 섞일 수 있으면 prefix sum + HashMap 방식을 사용합니다.
    public static int countSubarraySumWithNegatives(int[] arr, int target) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int value : arr) {
            sum += value;
            count += prefixCount.getOrDefault(sum - target, 0);
            prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static int minLengthAtLeast(int[] arr, int target) {
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

    public static int longestSubstringWithoutDuplicate(String text) {
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
