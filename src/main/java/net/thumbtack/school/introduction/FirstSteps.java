package net.thumbtack.school.introduction;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x <= xRight && x >= xLeft && y <= yBottom && y >= yTop;
    }

    public int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public int mul(int[] array) {
        if (array.length == 0) {
            return 0;
        } else {
            int mul = 1;
            for (int value : array) {
                mul *= value;
            }
            return mul;
        }
    }

    public int min(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
            if (value < min) min = value;
        }
        return min;
    }

    public int max(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
            if (value > max) max = value;
        }
        return max;
    }

    public double average(int[] array) {
        double sum = 0.0;
        if (array.length == 0) {
            return 0;
        } else {
            for (int value : array) {
                sum += value;
            }
        }
        return sum / (array.length);
    }

    public boolean isSortedDescendant(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
            if (array[i] <= array[i + 1]) return false;
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        for (int value2 : array) {
            // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
            if (value2 == value) return true;
        }
        return false;
    }

    public void reverse(int[] array) {
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
    }

    public boolean isPalindrome(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum2 = 0;
        for (int[] ints : matrix) {
            sum2 += sum(ints);
        }
        return sum2;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] ints : matrix) {
            // REVU Метод max делает обход массива. Вы вызываете его два раза. Я предлагаю вызывать его один раз и сохранить/переиспользовать результат.
            if (max(ints) > max) {
                max = max(ints);
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
            if (matrix[i][i] > max) max = matrix[i][i];
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < ints.length; j++) {
                // REVU Используйте собки {} во всех if и for, даже если в скобках будет только одно действие
                if (!isSortedDescendant(ints)) return false;
            }
        }
        return true;
    }
}
