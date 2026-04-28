package exam._17_binary_search;

import java.util.Arrays;

public class BinarySearchExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 5, 8};
        System.out.println(binarySearch(arr, 5));
        System.out.println(lowerBound(arr, 2));
        System.out.println(upperBound(arr, 2));
        System.out.println(parametricSearchExample(11));
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int parametricSearchExample(int need) {
        int[] lengths = {7, 4, 5};
        Arrays.sort(lengths);

        int left = 1;
        int right = lengths[lengths.length - 1];
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCutAtLeast(lengths, mid, need)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    public static boolean canCutAtLeast(int[] lengths, int unit, int need) {
        int count = 0;
        for (int length : lengths) {
            count += length / unit;
        }
        return count >= need;
    }
}
