package com.dyd.algo.sort.merge;

import org.junit.jupiter.api.Test;

public class MergeSortTest {
    private MergeSort mergeSort = new MergeSort();

    @Test
    public void testSort() {
        int[] a = {3, 1, 2, 5, 8, 4};
        mergeSort.sort(a);
    }
}
