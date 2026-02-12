package untitled;

public class CalculateVolume {
    public int calculateMaxArea(int[] height) {
        if (height == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        if (height.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        if (height.length < 2) {
            throw new IllegalArgumentException("Для создания контейнера необходимо минимум 2 линии");
        }

        int left = 0;
        int right = height.length - 1;
        int resultArea = 0;

        while (left < right) {

            int width = right - left;
            int currentHeight = Math.min(height[right], height[left]);
            int currentArea = width * currentHeight;
            resultArea = Math.max(resultArea, currentArea);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }

        }


        return resultArea;

    }


}
