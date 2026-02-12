package untitled;

public class CalculateVolume {
    public int maxArea(int[] height){
        if (height == null){
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int resultArea = 0;

        while (left < right){

            int width = right - left;

            int currentHeight = Math.min(height[right], height[left]);

            int currentArea = width * currentHeight;

            resultArea = Math.max(resultArea, currentArea);

            if (height[left]>height[right]){
                right--;
            }
            else {
                left++;
            }

        }


        return resultArea;

    }



}
