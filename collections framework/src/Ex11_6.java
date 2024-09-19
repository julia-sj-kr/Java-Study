import java.util.Arrays;

public class Ex11_6 {
    public static void main(String[] args) {

        int[] arr={0,1,2,3,4};
        int[][] arr2D={{11,12},{21,22}};

        System.out.println(Arrays.toString(arr));//결과값(1): [0, 1, 2, 3, 4]
        System.out.println(Arrays.deepToString(arr2D));// 결과값(2): [[11, 12], [21, 22]]

        int[] arr2={0,1,2,3,4};
        int[][] arr2D2={{11,12},{21,22}};

        System.out.println(Arrays.equals(arr,arr2));//결과값(3):true
        System.out.println(Arrays.equals(arr2D,arr2D2));//결과값(4):false
        System.out.println(Arrays.deepEquals(arr2D,arr2D2));//결과값(5):true
    }
}
