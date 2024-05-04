/*
 * @Author: Mingyan Jia
 * @Date: 04/05/2024
 * @Name: recursiveBinarySearch
 */
package com.jeremy.recursiveBInarySearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * recursiveBinarySearch implements the binary search algorithm recursively.
 * The first step is to sort the int array.(as sorted array is needed)
 * The indices are from the sorted array.(not the original one)
 * Considering possessively there are duplicates in the array, so after finding
 * one matched value, check the left and the right near the matched one.
 */
public class recursiveBinarySearch {

    public static void main(String[] args) {
        int[] collection = {72, 12, 23, 5, 2, 16, 8, 91, 38, 56,23,9,44,33,101,0};
        int key = 23;

        // Sort the array
        Arrays.sort(collection);
        // [0, 2, 5, 8, 9, 12, 16, 23, 23, 33, 38, 44, 56, 72, 91, 101]
        // Print the sorted array
        System.out.println("Array after sorting: " + Arrays.toString(collection));

        // Find all indices of matching values
        List<Integer> resultIndices = findAllMatching(collection, key);

        // Output
        if (resultIndices.get(0) == -1) {
            System.out.println("The key " + key + " was not found in the array.");
        } else {
            Collections.sort(resultIndices); // sorting the resultIndices
            System.out.println("All occurrences of the key " + key + " are at indices: " + resultIndices);
            // [7, 8]
        }
    }

    /**
     * first findAllMatching takes two parameters
     * second findAllMatching takes four parameters(overloading)
     */

    // put length-1 and new ArrayList as another two parameters
    // using the overloading feature of java
    public static List<Integer> findAllMatching(int[] collection, int key) {
        return findAllMatching(collection, key, 0, collection.length - 1, new ArrayList<>());
    }

    /**
     * findAllMatching is used to find all the matching index from the array.
     * With the recursive performing of itself, every recursion will reduce the
     * checking scale to the half (either left or right).
     * Once one matched value is found, try to check if the near values are matching
     * to the key as well.
     */
    private static List<Integer> findAllMatching(int[] collection, int key, int low, int high, List<Integer> indices) {
        // If there is no target value, add -1 into the indices
        if (low > high) {
            indices.add(-1);
            return indices;
        }

        int mid = low + (high - low) / 2; // middle index

        // if the middle value equals to key, check if the left and right values.
        if (collection[mid] == key) {
            // Add current index
            indices.add(mid);

            // Check to the left
            findAllMatchingToLeft(collection, key, low, mid - 1, indices);

            // Check to the right
            findAllMatchingToRight(collection, key, mid + 1, high, indices);
        } else if (collection[mid] > key) {
            findAllMatching(collection, key, low, mid - 1, indices); // check values before middle
        } else {
            findAllMatching(collection, key, mid + 1, high, indices); // check values after middle
        }

        return indices;
    }

    /**
     * After sorting, the array is ascending, so try to check the
     * left part before the middle value(matched).
     */
    private static void findAllMatchingToLeft(int[] collection, int key, int low, int high, List<Integer> indices) {
        // loop until find the last value that match the key
        for (int i = high; i >= low; i--) {
            if (collection[i] == key) {
                indices.add(i); // add index to the indices
            } else {
                break;
            }
        }
    }

    /**
     * After sorting, the array is ascending, so try to check the
     * right part after the middle value(matched).
     */
    private static void findAllMatchingToRight(int[] collection, int key, int low, int high, List<Integer> indices) {
        // loop until find the last value that match the key
        for (int i = low; i <= high; i++) {
            if (collection[i] == key) {
                indices.add(i); // add index to the indices
            } else {
                break;
            }
        }
    }
}
