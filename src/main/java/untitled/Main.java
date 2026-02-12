package untitled;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculateVolume calculateVolume = new CalculateVolume();

        int[] height = {1, 2};
        int result = calculateVolume.maxArea(height);

        System.out.println("Массив: " + Arrays.toString(height));
        System.out.println("Результат: " + result);




    }
}