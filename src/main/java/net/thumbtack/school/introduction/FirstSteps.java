package net.thumbtack.school.introduction;

public class FirstSteps {
    /*
    REVU Условия задания не требуют документирования методов.
    Названия методов хорошо описывают их назначение,
    поэтому дополнительное документирование здесь не требуется.

    Если вы всё же хотите оставить документирующие комментарии,
    то обратите внимание на следующие моменты:
    1. В Java есть отдельный синтаксис комментариев для документации.
    Такие комментарии начинаются символами /**  и заканчиваются
    как обычный многострочный комментарий.
    2. В Java принято писать документацию к методу перед методом,
    а не после него.
     */
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
    /*
     Прямоугольник с горизонтальными и вертикальными сторонами, задан двумя точками - левой верхней (xLeft, yTop)
     и правой нижней (xRight, yBottom). На плоскости OXY ось X направлена вправо, ось Y - вниз.
     Дана еще одна точка с координатами (x, y). Гарантируется, что xLeft < xRight и yTop < yBottom.
     Метод должен возвращать true, если точка лежит внутри прямоугольника , иначе false.
     Если точка лежит на границе прямоугольника, то считается, что она лежит внутри него.
     */

    public int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public int mul(int[] array) {
        // REVU Всегда используйте скобки {} в оформлении if/else даже если в
        // скобках будет только один оператор.
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
        /*
        REVU Не нужно сортировать массив.
        1. Сортировка это вычислительно более сложная операция, чем линейный поиск.
        2. Подход с сортировкой изменяет массив. Это может быть неприятным
        сюрпризом для другого программиста.
        Используйте подход без сортировки.
        */

        int min = Integer.MAX_VALUE;
        // REVU Используйте вариант цисла foreach вместо цисла со счётчиком
        for (int value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    public int max(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    public double average(int[] array) {
        double sum = 0.0;
        if (array.length == 0) return 0;

        else {

            for (int value : array) {
                sum += value;
            }
        }
        return sum / (array.length);
    }

    public boolean isSortedDescendant(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) return false;
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // REVU Используйте простое умножение вместо Math.pow().
            // Простое умножение здесь не требует преобразования типов и целочисленные операции
            // как правило работают быстрее.
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        for (int value2 : array) {
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
        //if (array.length == 0) return true; // REVU Подумайте о том, можно ли обойтись без этой проверки?
        for (int i = 0; i < array.length / 2; i++) { // REVU Действительно ли необходимо проверять до i < array.length ?
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum2 = 0;
        for (int[] ints : matrix) { // REVU Используйте цикл foreach.
            // REVU У вас уже реализован метод для суммы элементов массива. Используйте его.
            sum2 += sum(ints);
        }
        return sum2;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] ints : matrix) { // REVU Используйте цикл foreach.
            // REVU Переиспользуйте методы, уже реализованные вами.
            if (max(ints) > max) {
                max = max(ints);
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] > max) max = matrix[i][i];
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] ints : matrix) { // REVU Используйте цикл foreach.
            for (int j = 0; j < ints.length; j++) {
                if (!isSortedDescendant(ints)) return false;
            }
        }
        return true;
    }
}
