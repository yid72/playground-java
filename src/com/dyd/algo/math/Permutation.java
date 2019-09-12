package com.dyd.algo.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
    public Set<String> permutation(String s) {
        List<StringBuilder> internalResults = permutation(s, 0);

        Set<String> results = new HashSet<>();
        for (StringBuilder sb : internalResults) {
            results.add(sb.toString());
        }
        return results;
    }

    private List<StringBuilder> permutation(String s, int start) {
        List<StringBuilder> perms = new ArrayList<>();
        char ch = s.charAt(start);

        if (start == s.length() - 1) {
            perms.add(new StringBuilder().append(ch));
            return perms;
        }

        List<StringBuilder> subPerms = permutation(s, start+1);

        for (StringBuilder subPerm : subPerms) {
            int len = subPerm.length();
            for (int i = 0; i <= len; i++) {
                perms.add(new StringBuilder(subPerm.toString()).insert(i, ch));
            }
        }

        return perms;
    }

    public static void main(String[] args) {
        test("a");
        test("ab");
        test("abc");
        test("abcd");
        test("aaa");
        test("aab");
    }

    private static void test(String input) {
        Permutation permutation = new Permutation();
        System.out.println("Input: " + input);
        Set<String> perms = permutation.permutation(input);
        System.out.println("Permutations (size=" + perms.size() + "): " + perms);
    }
}
