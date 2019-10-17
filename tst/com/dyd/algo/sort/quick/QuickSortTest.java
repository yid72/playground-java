package com.dyd.algo.sort.quick;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class QuickSortTest {
    private QuickSort quickSort = new QuickSort();


    @Test
    public void test1() {
        test(new int[] {3, 1, 9, 7, 10, 4, 5});
    }

    @Test
    public void test2() {
        test(new int[] {1, 8, 2, 3, 4});
    }

    public void test(int[] a) {
        System.out.println("Input: " + Arrays.toString(a));
        quickSort.sort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
    }

}
