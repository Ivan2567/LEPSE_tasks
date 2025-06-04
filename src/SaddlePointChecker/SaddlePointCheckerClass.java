package SaddlePointChecker;


import java.util.Scanner;

public class SaddlePointCheckerClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        
        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы:");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Элемент [%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println("\nВведенная матрица:");
        printMatrix(matrix);
        
        findSaddlePoints(matrix);
        
        scanner.close();
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }
    
    public static void findSaddlePoints(int[][] matrix) {
        boolean foundAny = false;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int current = matrix[i][j];
                boolean isMinInRow = true;
                boolean isMaxInCol = true;
                
                // Проверяем, минимальный ли элемент в строке
                for (int col = 0; col < matrix[i].length; col++) {
                    if (matrix[i][col] < current) {
                        isMinInRow = false;
                        break;
                    }
                }
                
                // Проверяем, максимальный ли элемент в столбце
                for (int row = 0; row < matrix.length; row++) {
                    if (matrix[row][j] > current) {
                        isMaxInCol = false;
                        break;
                    }
                }
                
                if (isMinInRow && isMaxInCol) {
                    System.out.printf("Седловая точка: %d (строка %d, столбец %d)\n", current, i, j);
                    foundAny = true;
                }
            }
        }
        
        if (!foundAny) {
            System.out.println("Седловых точек нет.");
        }
    }
}
