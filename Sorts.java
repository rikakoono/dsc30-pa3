/*
 * NAME: Rikako Ono
 * PID:  A17583780
 */

import java.util.Collections;
import java.util.ArrayList;

/**
 * The Sorts class provides implementations of various sorting algorithms,
 * including Bucket Sort and Counting Sort. It includes helper methods to
 * determine the number of buckets and assign elements to appropriate buckets.
 */
public class Sorts {
    /**
     * This helper finds the appropriate number of buckets you should allocate
     * based on the range of the values in the input list.
     *
     * @param list the input list to bucket sort
     * @return number of buckets
     */
    private static int assignNumBuckets(ArrayList<Integer> list) {
        Integer max = Collections.max(list);
        Integer min = Collections.min(list);
        return (int) Math.sqrt(max - min) + 1;
    }

    /**
     * This helper finds the appropriate bucket index that a data should be
     * placed in.
     *
     * @param data       a particular data from the input list if you are using
     *                   loop iteration
     * @param numBuckets number of buckets
     * @param listMin    the smallest element of the input list
     * @return the index of the bucket for which the particular data should
     * be placed in
     */
    private static int assignBucketIndex(Integer data, int numBuckets, Integer listMin) {
        return (data - listMin) / numBuckets;
    }

    /**
     * This method performs bucket sort on the input arraylist.
     *
     * @param list The arraylist we want to sort
     * @return A new ArrayList containing the sorted elements
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        int numBuckets = assignNumBuckets(list);
        for (int i = 0; i < numBuckets; i++) {
            ArrayList<Integer> freshBucket = new ArrayList<>();
            buckets.add(i, freshBucket);
        }
        Integer listMin = Collections.min(list);
        for (Integer data : list) {
            int bucketIndex = assignBucketIndex(data, numBuckets, listMin);
            // Adds data to correct bucket
            buckets.get(bucketIndex).add(data);
        }

        ArrayList<Integer> sortedList = new ArrayList<>();
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket.size() > 0)
                insertionSort(bucket, 0, bucket.size() - 1);
            // Adds all elements in bucket to sortedList
            sortedList.addAll(bucket);
        }
        return sortedList;
    }

    /**
     * This method performs count sort on the input arraylist.
     *
     * @param list The arraylist we want to sort
     * @return A new ArrayList containing the sorted elements
     */
    public static ArrayList<Integer> countSort(ArrayList<Integer> list) {
        ArrayList<Integer> output = new ArrayList<Integer>();

        // Find the largest element of the array
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max)
                max = list.get(i);
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i <= max; ++i) { // Corrected loop condition
            count[i] = 0;
        }

        // Initialize output with all zeros.
        for (int i = 0; i < list.size(); ++i) {
            output.add(0);
        }

        // Store the count of each element
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            // Increment count array at correct index
            count[value]++;
        }

        // Store the cumulative count of each array
        for (int i = 1; i <= max; i++) {
            // Update count array to be a running total
            count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        // To make the sort stable, iterate from the end of the list
        for (int i = list.size() - 1; i >= 0; i--) {
            int value = list.get(i);
            count[value]--;
            output.set(count[value], value);
        }

        return output;
    }

    /**
     * This method performs insertion sort on a sublist of the input arraylist.
     *
     * @param list The sublist to sort
     * @param left The starting index of the sublist
     * @param right The ending index of the sublist
     */
    private static void insertionSort(ArrayList<Integer> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= left && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    ////////////////////////
    ///// EXTRA CREDIT /////
    ////////////////////////

    public static boolean inspectInsertion(int[] arr, int n) {
        // TODO
        return false;
    }

    public static boolean inspectSelection(int[] arr, int n) {
        // TODO
        return false;
    }

    public static boolean inspectMerge(int[] arr, int n) {
        // TODO
        return false;
    }
}
