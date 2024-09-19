import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

public class Ex11_7 {
    public static void main(String[] args) {

        /*
        Integer a = 5;
        Integer b = 10;

        // compareTo 메서드를 사용하여 비교
        int result = a.compareTo(b);  // result는 -1 (a가 b보다 작음)

        // 결과 출력
        System.out.println("Result of compareTo: " + result); //결과값:Result of compareTo: -1
        if (result == 0) {
            System.out.println(a + " is equal to " + b);
        } else if (result > 0) {
            System.out.println(a + " is greater than " + b);
        } else {
            System.out.println(a + " is less than " + b);
        }

         */

        String[] strArr={"cat","Dog","lion","tiger"};

        System.out.println("befor 정렬 "+Arrays.toString(strArr));

        Arrays.sort(strArr);//String의 Comparable구현에 의한 정렬
        System.out.println("after 정렬 "+Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);//대소문자 구분안함
        System.out.println("대소문자 구분x "+Arrays.toString(strArr));

        Arrays.sort(strArr,new Descending());//역순 정렬
        System.out.println("역순 정렬 "+Arrays.toString(strArr));

    }
}

//Comparator 인터페이스 구현
class Descending implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Comparable && o2 instanceof Comparable){
            Comparable c1=(Comparable) o1;
            Comparable c2=(Comparable) o2;
            return  c1.compareTo(c2)*-1;
        }
        return -1;
    }
}