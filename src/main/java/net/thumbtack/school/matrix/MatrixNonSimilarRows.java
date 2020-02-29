package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Map<Set<Integer>, int[]> map = new HashMap<>();
        for (int[] ints : matrix) {
            Set<Integer> integerSet = new TreeSet<>();
            for (int anInt : ints) {
                integerSet.add(anInt);
            }
            map.putIfAbsent(integerSet, ints);
        }
        return new HashSet<>(map.values());
    }
}
