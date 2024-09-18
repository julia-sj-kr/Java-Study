import java.awt.print.Printable;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class Ex11_1 {
    public static void main(String[] args)  {

/*
        //01 기본형 타입을 다룰 수 있는 스트림

        //IntStream은 Java 8에서 추가된 인터페이스입니다. 기본형 int를 사용하여 스트림을 생성하고 조작하는 데 사용
        ArrayList<IntStream> list1 = new ArrayList<>(1);

        IntStream stream = IntStream.range(1, 6);
        list1.add(stream);

        IntStream stream2 = IntStream.range(5, 11);
        list1.add(stream2); // 두 번째 IntStream 추가

        System.out.println(list1);//출력값: [java.util.stream.IntPipeline$Head@b4c966a]

        //IntStream의 요소를 배열로 변환한 후 출력하는 코드
        //toArray() 사용: IntStream의 값을 int 배열로 변환하여 출력합니다.
        //Arrays.toString() 사용: 배열을 문자열 형태로 출력하여 가독성을 높입니다.

        // 스트림의 값을 배열로 변환 후 출력
        int[] values1=list1.get(0).toArray(); //첫번째 IntStream을 int 배열로 변환
        System.out.println("IntStream values: "+Arrays.toString(values1));//출력값: IntStream values: [1, 2, 3, 4, 5]

        int[] values2=list1.get(1).toArray(); //두번째 IntStream을 int 배열로 변환
        System.out.println("IntStream values: "+Arrays.toString(values2));//출력값: IntStream values: [5, 6, 7, 8, 9, 10]
*/

        /*
        //02 문자열(String)과 같은 참조 타입을 처리할 때는 Stream<String>을 사용, 일반 스트림(Stream<T>)을 이용해 문자열을 처리

        //문자열 스트림 생성
        Stream<String> stringStream=Stream.of("apple","banana","cherry");

        //해당 코드는 콘솔 출력 (형태 없음)으로 출력만 이루어지고, 데이터 가공이나 변환을 위한 저장소가 없으므로 후속 처리가 불가능합니다.
        //stringStream.forEach(System.out::println);

        //=>형태: 배열 (String[])
        //배열로 변환할 문자열을 저장할 리스트 생성
        List<String> stringList=new ArrayList<>();
        //스트림을 순회하여 각 요소를 리스트에 추가
        stringStream.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                stringList.add(s);
            }
        });
        //리스트를 배열로 변환
        String[] values=new String[stringList.size()];
        for (int i=0; i<stringList.size();i++){
            values[i]=stringList.get(i);
        }
        //배열 출력
        System.out.println(Arrays.toString(values));
*/
        //03 교재에 있는 예제
        ArrayList list1=new ArrayList(10);
        list1.add(5);//Integer 객체를 생성하는 방법(1)
        list1.add(6);//Java의 오토 박싱(autoboxing) 기능 덕분에 기본형(int) 값을 Integer 객체로 자동으로 변환
        list1.add(Integer.valueOf(4));//Integer 객체를 생성하는 방법(2)
        list1.add(Integer.valueOf(3));//이 메서드는 내부적으로 캐싱을 사용하여 동일한 값에 대해 동일한 객체를 반환합니다.
        list1.add(new Integer(0));//Integer 객체를 생성하는 방법(3)
        list1.add(new Integer(1));//항상 새로운 객체를 생성하므로 같은 값을 갖는 Integer 객체가 여러 개 생성될 수 있습니다.

        ArrayList list2=new ArrayList(list1.subList(1,4));
        print(list1,list2);

        Collections.sort(list1);//list1과 list2를 오름차순으로 정렬합니다.
        Collections.sort(list2);
        print(list1,list2);

        System.out.println("list1.containsAll(list2):"+list1.containsAll(list2));

        list2.add("B");
        list2.add("C");
        list2.add(3,"A");
        print(list1,list2);

        list2.set(3,"AA");
        print(list1,list2);

        //list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제한다.
        System.out.println("list1.retainAll(list2):"+list1.retainAll(list2));//출력값: list1.retainAll(list2):true <-retainAll에 의해 list1에 변화가 있었으므로 true를 반환
        print(list1, list2);

        //list2에서 list1에 포함된 객체들을 삭제한다.
        for (int i=list2.size()-1;i>=0;i--){
            if (list1.contains(list2.get(i))){//boolean type을 반환하는 메서드
                list2.remove(i);} //인덱스가 i인 곳에 저장된 요소를 삭제
        }
        print(list1, list2);
            }

    static void print(ArrayList list1, ArrayList list2){
        System.out.println("list1:"+list1);
        System.out.println("list2:"+list2);
        System.out.println();
    }
}
