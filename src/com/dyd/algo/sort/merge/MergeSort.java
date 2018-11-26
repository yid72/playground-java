package com.dyd.algo.sort.merge;

import java.util.Arrays;

public class MergeSort {
    public void sort(int[] a) {
        System.out.print("Input array: ");
        printArray(a);

        System.out.println("Sorting...");
        sort(a, new int[a.length], 0, a.length - 1);
    }

    private void sort(int[] a, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);

        printArray(a);
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, (hi - lo) + 1);

        int i = lo;
        int j = mid + 1;
        int k = lo;

        while (i <= mid && j <= hi) {
            if (aux[i] <= aux[j]) {
                a[k++] = aux[i++];
            } else {
                a[k++] = aux[j++];
            }
        }

        while (i <= mid) {
            a[k++] = aux[i++];
        }

        while (j <= hi) {
            a[k++] = aux[j++];
        }
    }

    private void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
