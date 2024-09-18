import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Ex11_5 {
    public static void main(String[] args) {
        ArrayList list=new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

//        Iterator it=list.iterator();
//
//        while (it.hasNext()){
//            Object obj=it.next();
//            System.out.println(obj);
//        }

        for (int i=0;i<list.size();i++){
            Object obj=list.get(i);
            System.out.println(obj);
        }
    }
}

//List클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해서 읽어 온 결과 역시 저장순서와 동일하지만
//Set클래스들은 각 요소간의 순서가 유지 되지 않기때문에 Iteraotr를 이용해서 저장된 요소들을 읽어 와도 처음에 저장된 순서와 같지 않다.
