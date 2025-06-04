package BoxBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoxBuilderClass {
    public static void main(String[] args) throws FileNotFoundException {
        // Чтение данных из файла
        Scanner scanner = new Scanner(new File("src/input_data/Edges3.txt"));
        List<int[]> sheets = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            // Сохраняем размеры в упорядоченном виде (обльшая сторона сначала)
            sheets.add(new int[]{Math.max(a, b), Math.min(a, b)});
        }
        scanner.close();

        // Проверка возможности собрать коробку
        if (canFormBox(sheets)) {
            System.out.println("Возможно");
        } else {
            System.out.println("Невозможно");
        }
    }

    private static boolean canFormBox(List<int[]> sheets) {
        if (sheets.size() != 6) {
            return false;
        }

        int[] counts = new int[6]; //Количество внутри уникальной группы
        int[][] dimensions = new int[6][2]; //Размеры листа фанеры для уникальной группы

        // Находим уникальные размеры
        int uniqueCount = 0;
        for (int[] sheet : sheets) {
            boolean found = false;
            for (int i = 0; i < uniqueCount; i++) {
                if (dimensions[i][0] == sheet[0] && dimensions[i][1] == sheet[1]) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }
            if (!found && uniqueCount < 6) {
                dimensions[uniqueCount][0] = sheet[0];
                dimensions[uniqueCount][1] = sheet[1];
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }
        // Обрабатываем ситуацию с более чем 3 уникальными гранями
        if (uniqueCount > 3) {
            return false;
        }
        // Обрабатываем ситуацию с кубом
        if (uniqueCount == 1) {
        	if (dimensions[0][0] != dimensions[0][1])
        		return false;
        	else
        		return true;
        }
        // Обрабатываем ситуацию с 2 уникальными гранями
        if (uniqueCount == 2) {
        	if (!(counts[0] == 2 && counts[1] == 4)&&!(counts[0] == 4 && counts[1] == 2))// проверка распределения эл-тов по группам
        		return false;
        	else {
        		int small_group = indexElem(counts,2);
        		int big_group = indexElem(counts,4);
        		if (dimensions[small_group][0] != dimensions[small_group][1])
            		return false;
        		if ((dimensions[small_group][0] != dimensions[big_group][0])&&(dimensions[small_group][0] != dimensions[big_group][1]))
            		return false;
        		return true;
        	}
        		
        }
        // Обрабатываем ситуацию с 3 уникальными гранями
        if (uniqueCount == 3) {
        	if (!(counts[0] == 2 && counts[1] == 2 && counts[2] == 2))
        		return false;

        	else
        	{
        		// Выбираем грань (необязятельно)
                int a = dimensions[0][0];
                int b = dimensions[0][1];
                // Проверяем, что уникальные грани могут смежными 
                if (
                	(dimensions[1][0] == a && dimensions[1][1] == dimensions[2][0] && dimensions[2][1] == b) ||
                    (dimensions[1][0] == a && dimensions[1][1] == dimensions[2][1] && dimensions[2][0] == b) ||
                    (dimensions[1][1] == a && dimensions[1][0] == dimensions[2][0] && dimensions[2][1] == b) ||
                    (dimensions[1][1] == a && dimensions[1][0] == dimensions[2][1] && dimensions[2][0] == b) ||
                	(dimensions[2][0] == a && dimensions[1][1] == dimensions[2][0] && dimensions[1][1] == b) ||
                    (dimensions[2][0] == a && dimensions[1][1] == dimensions[2][1] && dimensions[1][0] == b) ||
                    (dimensions[2][1] == a && dimensions[1][0] == dimensions[2][0] && dimensions[1][1] == b) ||
                    (dimensions[2][1] == a && dimensions[1][0] == dimensions[2][1] && dimensions[1][0] == b)
                    ) 
                {
                    return true;
                }
        	}	
        }
        return false;
    }
    private static int indexElem(int[] mas, int elem) {
    	for (int i = 0; i < mas.length; i++) {
    	    if (mas[i] == elem) 
    	    	return i;

    }
		return -1;
    }
}
