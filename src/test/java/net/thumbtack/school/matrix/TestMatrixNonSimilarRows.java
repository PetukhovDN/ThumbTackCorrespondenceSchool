package net.thumbtack.school.matrix;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMatrixNonSimilarRows {

    @Test
    public void testMatrixSimilarRows() {
        int[][] matrix1 = {{}, {1, 2, 2, 4, 4}, {4, 2, 1, 4}, {3, 2, 4, 1, 5, 8}, {2, 3, 1, 4, 1, 5, 8}, {1, 8, 1, 1, 8}};
        Set<int[]> nonSimilarRows1 = new MatrixNonSimilarRows(matrix1).getNonSimilarRows();
        assertEquals(4, nonSimilarRows1.size());
        assertTrue(nonSimilarRows1.contains(matrix1[0]));
        assertTrue(nonSimilarRows1.contains(matrix1[1]));
        assertTrue(nonSimilarRows1.contains(matrix1[3]));
        assertTrue(nonSimilarRows1.contains(matrix1[5]));
        int[][] matrix2 = {{1, 2, 2, 4, 4}, {4, 5, 1, 4}, {2, 4, 1}, {2, 4, 1, 4, 1, 2}, {1, 8, 1, 1, 8}};
        Set<int[]> nonSimilarRows2 = new MatrixNonSimilarRows(matrix2).getNonSimilarRows();
        assertEquals(3, nonSimilarRows2.size());
        assertTrue(nonSimilarRows2.contains(matrix2[0]));
        assertTrue(nonSimilarRows2.contains(matrix2[1]));
        assertTrue(nonSimilarRows2.contains(matrix2[4]));
    }
}