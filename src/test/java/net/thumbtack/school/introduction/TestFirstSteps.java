package net.thumbtack.school.introduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFirstSteps {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testSum() {
        FirstSteps firstSteps = new FirstSteps();
        assertEquals(4, firstSteps.sum(2, 2));
        assertEquals(20000, firstSteps.sum(10000, 10000));
    }

    @Test
    public void testMul() {
        FirstSteps firstSteps = new FirstSteps();
        assertEquals(4, firstSteps.mul(2, 2));
        assertEquals(100000000, firstSteps.mul(10000, 10000));
    }

    @Test
    public void testDiv() {
        FirstSteps firstSteps = new FirstSteps();
        assertEquals(1, firstSteps.div(2, 2));
        assertEquals(0, firstSteps.div(1, 2));
        assertEquals(1, firstSteps.div(10200, 10000));
    }

    @Test
    public void testMod() {
        FirstSteps firstSteps = new FirstSteps();
        assertEquals(1, firstSteps.mod(3, 2));
        assertEquals(0, firstSteps.mod(4, 2));
        assertEquals(10, firstSteps.mod(70, 20));
    }

    @Test
    public void testEquals() {
        FirstSteps firstSteps = new FirstSteps();
        assertTrue(firstSteps.isEqual(2, 2));
        assertFalse(firstSteps.isEqual(3, 2));
    }

    @Test
    public void testGreater() {
        FirstSteps firstSteps = new FirstSteps();
        assertTrue(firstSteps.isGreater(3, 2));
        assertFalse(firstSteps.isGreater(2, 2));
        assertFalse(firstSteps.isGreater(1, 2));
    }

    @Test
    public void testIsInsideRect() {
        FirstSteps firstSteps = new FirstSteps();
        assertAll(
                () -> assertTrue(firstSteps.isInsideRect(0, 0, 100, 100, 50, 50)),
                () -> assertTrue(firstSteps.isInsideRect(0, 0, 100, 100, 50, 100)),
                () -> assertTrue(firstSteps.isInsideRect(0, 0, 100, 100, 100, 100)),
                () -> assertTrue(firstSteps.isInsideRect(0, 0, 100, 100, 0, 0)),
                () -> assertFalse(firstSteps.isInsideRect(0, 0, 100, 100, 200, 200)),
                () -> assertFalse(firstSteps.isInsideRect(0, 0, 100, 100, 0, 101)),
                () -> assertFalse(firstSteps.isInsideRect(0, 0, 100, 100, 101, 0)),
                () -> assertFalse(firstSteps.isInsideRect(0, 0, 100, 100, -1, 0)),
                () -> assertFalse(firstSteps.isInsideRect(0, 0, 100, 100, 0, -1))
        );
    }

    @Test
    public void testSumArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 3};
        assertEquals(6, firstSteps.sum(array1));
        int[] array2 = {-1, -2, 3};
        assertEquals(0, firstSteps.sum(array2));
        int[] array3 = {};
        assertEquals(0, firstSteps.sum(array3));
    }

    @Test
    public void testMulArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 3};
        assertEquals(6, firstSteps.mul(array1));
        int[] array2 = {-1, -2, 3};
        assertEquals(6, firstSteps.mul(array2));
        int[] array3 = {1, 2, 0};
        assertEquals(0, firstSteps.mul(array3));
        int[] array4 = {};
        assertEquals(0, firstSteps.mul(array4));
    }

    @Test
    public void testMinArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {5, 1, 2, 3, -2};
        assertEquals(-2, firstSteps.min(array1));
        int[] array2 = {-5, -1, -22, -3, -2};
        assertEquals(-22, firstSteps.min(array2));
        int[] array3 = {};
        assertEquals(Integer.MAX_VALUE, firstSteps.min(array3));
    }

    @Test
    public void testMaxArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {5, 1, 22, 3, -2};
        assertEquals(22, firstSteps.max(array1));
        int[] array2 = {-5, -1, -22, -3, -2};
        assertEquals(-1, firstSteps.max(array2));
        int[] array3 = {};
        assertEquals(Integer.MIN_VALUE, firstSteps.max(array3));
    }

    @Test
    public void testAverageArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 3, 4};
        assertEquals(2.5, firstSteps.average(array1), DOUBLE_EPS);
        int[] array2 = {};
        assertEquals(0, firstSteps.average(array2), DOUBLE_EPS);
    }

    @Test
    public void testSortedDescendantArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {3, 2, 1};
        assertTrue(firstSteps.isSortedDescendant(array1));
        int[] array2 = {3, 2, 2};
        assertFalse(firstSteps.isSortedDescendant(array2));
        int[] array3 = {3, 2, 1, 4};
        assertFalse(firstSteps.isSortedDescendant(array3));
        int[] array4 = {1};
        assertTrue(firstSteps.isSortedDescendant(array4));
        int[] array5 = {};
        assertTrue(firstSteps.isSortedDescendant(array5));
    }

    @Test
    public void testCubeArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 3, 0};
        firstSteps.cube(array1);
        int[] arrayResult1 = {1, 8, 27, 0};
        assertArrayEquals(arrayResult1, array1);
        int[] array2 = {100, 200, 3, 10};
        firstSteps.cube(array2);
        int[] arrayResult2 = {1000000, 8000000, 27, 1000};
        assertArrayEquals(arrayResult2, array2);
    }

    @Test
    public void testFindValueArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array = {1, 2, 3, 0};
        assertTrue(firstSteps.find(array, 2));
        assertTrue(firstSteps.find(array, 0));
        assertFalse(firstSteps.find(array, 10));
    }

    @Test
    public void testReverseArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 3, 0};
        int[] arrayResult1 = {0, 3, 2, 1};
        firstSteps.reverse(array1);
        assertArrayEquals(arrayResult1, array1);
        int[] array2 = {1, 2, 6, 3, 0};
        int[] arrayResult2 = {0, 3, 6, 2, 1};
        firstSteps.reverse(array2);
        assertArrayEquals(arrayResult2, array2);
        int[] array3 = {1};
        int[] arrayResult3 = {1};
        firstSteps.reverse(array3);
        assertArrayEquals(arrayResult3, array3);
        int[] array4 = {};
        int[] arrayResult4 = {};
        firstSteps.reverse(array4);
        assertArrayEquals(arrayResult4, array4);
    }

    @Test
    public void testIsPalindromeArray() {
        FirstSteps firstSteps = new FirstSteps();
        int[] array1 = {1, 2, 2, 1};
        assertTrue(firstSteps.isPalindrome(array1));
        int[] array2 = {1, 2, 6, 2, 1};
        assertTrue(firstSteps.isPalindrome(array2));
        int[] array3 = {1, 2, 6, 3, 1};
        assertFalse(firstSteps.isPalindrome(array3));
        int[] array4 = {1};
        assertTrue(firstSteps.isPalindrome(array4));
        int[] array5 = {};
        assertTrue(firstSteps.isPalindrome(array5));
    }

    @Test
    public void testSumMatrix() {
        FirstSteps firstSteps = new FirstSteps();
        int[][] matrix = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
        assertEquals(39, firstSteps.sum(matrix));
    }

    @Test
    public void testMaxMatrix() {
        FirstSteps firstSteps = new FirstSteps();
        int[][] matrix1 = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
        assertEquals(8, firstSteps.max(matrix1));
        int[][] matrix2 = {{100, 200, 300}, {3, 4, 5}, {6, 7, 8}};
        assertEquals(300, firstSteps.max(matrix2));
        int[][] matrix3 = {{-1, -2, -3}, {-3, -4, -5}, {-6, -7, -8}};
        assertEquals(-1, firstSteps.max(matrix3));
        int[][] matrix4 = {{}};
        assertEquals(Integer.MIN_VALUE, firstSteps.max(matrix4));
    }

    @Test
    public void testDiagonalMaxMatrix() {
        FirstSteps firstSteps = new FirstSteps();
        int[][] matrix1 = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
        assertEquals(8, firstSteps.diagonalMax(matrix1));
        int[][] matrix2 = {{100, 2, 3}, {3, 4, 5}, {6, 7, 800}};
        assertEquals(800, firstSteps.diagonalMax(matrix2));
        int[][] matrix3 = {{}};
        assertEquals(Integer.MIN_VALUE, firstSteps.max(matrix3));
        int[][] matrix4 = {{-1, -2, 3}, {3, -4, 5}, {6, -7, -8}};
        assertEquals(-1, firstSteps.diagonalMax(matrix4));
    }

    @Test
    public void testSortedDescendantMatrixRows() {
        FirstSteps firstSteps = new FirstSteps();
        int[][] matrix1 = {{3, 2, 1}, {5, 4, 3}, {8, 7, 6}};
        assertTrue(firstSteps.isSortedDescendant(matrix1));
        int[][] matrix2 = {{3, 2, 2}, {5, 4, 3}, {8, 7, 6}};
        assertFalse(firstSteps.isSortedDescendant(matrix2));
        int[][] matrix3 = {{3, 2, 1}};
        assertTrue(firstSteps.isSortedDescendant(matrix3));
        int[][] matrix4 = {{}};
        assertTrue(firstSteps.isSortedDescendant(matrix4));
        int[][] matrix5 = {{5, 4, 3, 2, 1}, {12, 5, 4, 3}, {34, 12, 10, 9, 8, 7, 6}};
        assertTrue(firstSteps.isSortedDescendant(matrix5));
    }
}
