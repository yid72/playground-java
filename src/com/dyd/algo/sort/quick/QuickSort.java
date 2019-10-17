package com.dyd.algo.sort.quick;

public class QuickSort {
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = partition(a, lo, hi);
        sort(a, lo, pivot - 1);
        sort(a, pivot + 1, hi);
    }

    private int partition(int[] a, int lo, int hi) {
        int pivotVal = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (++ i < j && a[i] < pivotVal);

            while (-- j >= i && a[j] >= pivotVal);

            if (i >= j) {
                break;
            }
            
            swap(a, i, j);
        }
        swap(a, lo, i - 1);
        return i - 1;
    }

    private void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}
