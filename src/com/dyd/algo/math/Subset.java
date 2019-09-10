package com.dyd.algo.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subset {
    public List<Set<Character>> subsets(String s) {
        return subsets(s, 0);
    }

    private List<Set<Character>> subsets(String s, int start) {
        List<Set<Character>> newSets = new ArrayList<>();

        if (start == s.length() - 1) {
            Set set1 = new HashSet();
            set1.add(s.charAt(start));
            newSets.add(set1);

            Set set2 = new HashSet();
            newSets.add(set2);
            return newSets;
        }

        List<Set<Character>> subSubsets = subsets(s, start+1);
        newSets.addAll(subSubsets);
        for (Set set : subSubsets) {
            Set set1 = new HashSet();
            set1.addAll(set);
            set1.add(s.charAt(start));
            newSets.add(set1);
        }
        return newSets;
    }

    public static void main(String[] args) {
        Subset subset = new Subset();
        System.out.println(subset.subsets("a"));
        System.out.println(subset.subsets("ab"));
        System.out.println(subset.subsets("abc"));
        System.out.println(subset.subsets("abcd"));
    }
}
