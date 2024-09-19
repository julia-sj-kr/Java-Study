 # Chapter 11 컬렉션 프레임웍  
컬렉션 프레임웍이란, 데이터 군을 저장하는 클래스들을 표준화한 설계
컬렉션은 다수의 데이터, 즉 데이터 그룹을  
프레임웍은 표준화된 프로그래밍 방식을 의미  

**라이브러리와 프레임웍**  
라이브러리는 공통으로 사용될만한 유용한 기능을 모듈화하여 제공하는데 비해,  
프레임웍은 단순히 기능뿐만 아니라 프로그래밍 방식을 정형화하여 프로그램의 개발 생산성을 높이고 유지보수를 용이하게 한다.

**컬렉션 클래스**  
인터페이스 List와 Set을 구현한 컬렉션 클래스들은 서로 많은 공통부분이 있어서, 공통된 부분을 다시 뽑아 Collection인터페이스를 정의할 수 있었지만
Map인터페이스는 이들과는 전혀 다른 형태로 컬렉션을 다루기 때문에 같은 상속계층도에 포함되지 못했다.  
* List: 순서가 있는 데이터의 집합, 데이터의 중복을 허용한다.
  * 구현클래스: ArrayList, LinkedList, Stack, Vector 등
* Set: 순서를 유지하지 않는 데이터의 집합, 데이터의 중복을 허용하지 않는다.
  * 구현클래스: HashSet, TreeSet 등
* Map: 키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합
  * 구현클래스: HashMap, TreeMap, Hashtable, Properties 등

✏️ **예제 11-1 ArrayList**

``` java
        ArrayList<Integer> list1=new ArrayList<>(10);
        list1.add(5);//Integer 객체를 생성하는 방법(1)
        list1.add(6);
        list1.add(Integer.valueOf(4));//Integer 객체를 생성하는 방법(2)
        list1.add(Integer.valueOf(3));
        list1.add(new Integer(0));//Integer 객체를 생성하는 방법(3)
        list1.add(new Integer(1));
```
예제에 있는 객체를 생성하는 방법 이외에도 추가적으로 분석해보았다.  
`코드 간결성`과 `가독성`, `메모리 효율`을 고려하여 Integer 객체 생성 개념을 확장하여 공부했다.
뿐만 아니라 첨부한 파일에는 Stream 클래스를 활용한 방법과 동일한 출력값이지만 콘솔 출력(형태 없음), 배열(String[]) 형태의 코드를 비교하여 데이터 가공이나 변환을 위한 저장소의 활용을 자세하게 풀이하였다.

- __오토 박싱 (Autoboxing)__
  - 설명: 기본형 타입(int, char, double 등)을 해당하는 래퍼 클래스(Integer, Character, Double 등)로 자동으로 변환하는 기능을 오토 박싱이라고 합니다.
  - 성능: 기본형 값을 직접 추가할 수 있어 코드가 더 간결해집니다.(코드 간결성), 개발자가 의도한 대로 쉽게 작성할 수 있습니다(가독성 향상).

- __Integer.valueOf(int) 메서드__
  - 설명: int 값을 Integer 객체로 변환합니다. 이 메서드는 -128에서 127 사이의 값에 대해 캐싱을 사용하여 동일한 값에 대해 동일한 객체를 반환합니다.
  - 성능: 캐시를 사용하므로, 같은 값의 Integer 객체를 여러 번 생성할 필요가 없습니다.
  - 메모리 효율: 객체가 재사용되므로, 메모리 소모가 적습니다.

- __new Integer(int) 생성자__
   - 설명: Java 9에서 deprecated(사용 중단)된 개념으로 새로운 Integer 객체를 항상 생성합니다.

> 6줄의 객체 생성코드를 종합적으로 분석하였습니다.

`5`는 기본형 int이며, 오토 박싱에 의해 새로운 Integer 객체가 생성됩니다.  
`6`도 기본형 int이며, 오토 박싱에 의해 새로운 Integer 객체가 생성됩니다.  
`Integer.valueOf(4)`를 호출하면, 값이 4인 Integer 객체가 생성됩니다. 하지만 4는 -128에서 127 사이의 값이므로, 캐시에서 가져오게 됩니다.  
새로운 객체 생성 없음 (캐시된 객체 사용).  
`Integer.valueOf(3)` 호출 시, 3 역시 캐시에서 가져오므로 새로운 객체를 생성하지 않습니다.  
새로운 객체 생성 없음 (캐시된 객체 사용).  
`new Integer(0)`는 항상 새로운 Integer 객체를 생성합니다.  
`new Integer(1)`도 새로운 Integer 객체를 생성합니다.  

결론적으로, 총 4개의 새로운 Integer 객체가 생성됩니다: 5 (1개), 6 (1개), 0 (1개), 1 (1개)

---
**ArrayList와 LinkedList의 비교**  

| 컬렉션        | 읽기 (접근시간) | 추가 / 삭제  | 비고                                                      |
|---------------|----------------|-------------|-----------------------------------------------------------|
| **ArrayList** | 빠르다         | 느리다      | 순차적인 추가/삭제는 더 빠름. 비효율적인 메모리 사용       |
| **LinkedList**| 느리다         | 빠르다      | 데이터가 많을수록 접근성이 떨어짐                            |

`배열(Array)`은 가장 기본적인 형태의 자료구조로 구조가 간단하며 사용하기 쉽고 데이터를 읽어 오는데 걸리는 시간(접근시간, access time)이 가장 빠르다는 장점을 가지고 있지만 다음과 같은 단점도 가지고 있다.  

- 크기를 변경할 수 없다.
- 비순차적인 데이터의 추가 또는 삭제에 시간이 많이 걸린다.

이러한 배열의 단점을 보완하기 위해서 링크드 리스트(linked list)라는 자료구조가 고안되었다. 배열은 모든 데이터가 연속적으로 존재하지만 링크드 리스트는 불연속적으로 존재하는 데이터를 서로 연결한 형태로 구성되어 있다.

```java
class Node{
  Node next;//다음 요소의 주소를 저장
  Object obj;//데이터를 저장
}
```
`스택`과 `큐`를 구현하기 위해서는 어떤 컬렉션 클래스를 사용하는 것이 좋을까? 순차적으로 데이터를 추가하고 삭제하는 스택에는 ArrayList와 같은 배열기반의 컬렉션 클래스가 적합하지만, 큐는 데이터를 꺼낼 때 항상 첫 번째 저장된 데이터를 삭제하므로 배열기반의 컬렉션 클래스를 사용한다면 데이터를 꺼낼 때마다 빈 공간을 채우기 위해 데이터의 복사가 발생하므로 비효율적이다. 그래서 큐는 ArrayList보다 데이터의 추가/삭제가 쉬운 LinkedList로 구현하는 것이 더 적합하다.

✏️ **예제 11-2 Stack과 Queue**

``` java
        Stack st = new Stack();
        Queue q = new LinkedList();
```
자바에서는 스택을 Stack클래스로 구현하여 제공하고 있지만 큐는 Queue인터페이스로만 정의해 놓았을 뿐 별도의 클래스를 제공하고 있지 않다. 대신 Queue인터페이스를 구현한 클래스들이 있어서 이 들 중의 하나(LinkedList, PriorityQueue 등) 를 선택해서 사용하면 된다.  

+ 타입과 생성자가 다른 이유: **다형성**  
  자바에서 다형성(Polymorphism)은 같은 타입의 객체가 서로 다른 클래스의 객체로 동작할 수 있게 하는 개념입니다. 이를 위해 상속과 메서드 오버라이딩을 많이 사용합니다. 다형성의 대표적인 예로 부모 클래스의 참조 변수가 자식 클래스의 객체를 참조할 수 있다는 점을 보여줄 수 있습니다.
    
  Queue q = new LinkedList();는 자바의 다형성을 활용하여 `인터페이스(Queue)` 를 사용해 `구현체(LinkedList)`를 참조하였다.
  여기서는 Queue라는 인터페이스 타입을 사용하지만, 실제 객체는 LinkedList 클래스를 사용해 생성된 것입니다. 이 코드는 q 변수가 Queue 타입을 따르며, 큐로서의 동작만 허용되지만, 내부적으로는 LinkedList의 메서드가 실행된다는 의미입니다.

+ **다형성의 필요성**
  + 확장성(코드의 재사용성): 다형성을 사용하면 Animal 클래스를 확장한 다른 클래스들이 추가되더라도 코드를 수정할 필요가 없습니다.
  + 유지보수성: 부모 클래스 타입으로 코드를 작성하면, 각 자식 클래스가 어떤 객체인지를 구분하지 않고도 일관된 방식으로 메서드를 호출할 수 있습니다.
  + 다형성이 없다면:
    ```
    class Dog {
        public void sound() {
            System.out.println("멍멍!");
       }
    }

    class Cat {
        public void sound() {
            System.out.println("야옹!");
        }
    }

    class Cow {
        public void sound() {
            System.out.println("음메!");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Dog myDog = new Dog();
            Cat myCat = new Cat();
            Cow myCow = new Cow();

            // 동물마다 개별적으로 메서드를 호출해야 하고
            // 동물이 추가될 때마다 if-else 또는 switch로 동작을 관리해야 함
            String animalType = "dog"; // 예시로 동물의 종류를 문자열로 구분

            if(animalType.equals("dog")) {
                myDog.sound();
            } else if(animalType.equals("cat")) {
                myCat.sound();
            } else if(animalType.equals("cow")) {
                myCow.sound();
            }
            // 동물이 추가되면 또 다른 조건을 추가해야 함
        }
    }
    ```
   + 동일한 기능을 다형성을 활용하면:

   ```
    // 부모 클래스 Animal
    class Animal {
        public void sound() {
            System.out.println("동물이 소리를 냅니다.");
        }
    }

    class Dog extends Animal {
        @Override
        public void sound() {
            System.out.println("멍멍!");
        }
    }

    class Cat extends Animal {
        @Override
        public void sound() {
            System.out.println("야옹!");
        }
    }

    class Cow extends Animal {
        @Override
        public void sound() {
            System.out.println("음메!");
        }
    }

    public class Main {
        public static void main(String[] args) {
            // Animal 배열로 다양한 동물 관리
            Animal[] animals = {new Dog(), new Cat(), new Cow()};

            // 반복문으로 일괄 처리 가능
            for (Animal animal : animals) {
                animal.sound(); // 각 동물에 맞는 sound() 메서드가 호출됨
            }
        }
    }

     ```
---
📃 **Iterator 인터페이스**
- 설명: 자바 컬렉션 프레임워크에서 컬렉션의 요소에 순차적으로 접근하기 위한 독립적인 인터페이스입니다. 이를 통해 컬렉션의 내부 구조를 몰라도 요소에 접근할 수 있습니다.
- 주요 메서드:
  - boolean hasNext(): 컬렉션에서 다음 요소가 있는지 확인합니다.
  - E next(): 다음 요소를 반환합니다.
  - void remove(): 현재 요소를 제거합니다.
- 활용: Collection 인터페이스에서 iterator() 메서드를 제공하여, 해당 컬렉션의 요소들에 접근할 수 있는 Iterator 객체를 반환합니다.

📃 **Collection 인터페이스**
- 설명: 자바 컬렉션 프레임워크의 최상위 인터페이스 중 하나로, 모든 컬렉션 클래스의 `공통적인 동작`을 정의합니다.
  List, Set, Map, Queue와 같은 컬렉션 타입은 모두 이 인터페이스를 구현하고 있습니다.
- 주요 메서드:
  - add(E e): 컬렉션에 요소를 추가합니다.
  - remove(Object o): 컬렉션에서 특정 요소를 제거합니다.
  - size(): 컬렉션에 있는 요소의 개수를 반환합니다.
  - clear(): 컬렉션에서 모든 요소를 제거합니다.
  - contains(Object o): 컬렉션에 특정 요소가 있는지 확인합니다.
  - isEmpty(): 컬렉션이 비어 있는지 확인합니다.
- 추가설명: Collection은 구체적인 동작보다는 `기본적인 데이터 그룹 관리` 기능을 제공하는 인터페이스입니다.
  List, Set, Map, Queue 등 더 구체적인 인터페이스들은 이를 확장하여 각기 다른 자료구조 특성을 반영한 동작을 정의합니다.  

📃 **Queue 인터페이스**
- 설명: Collection 인터페이스를 상속받는 하위 인터페이스 중 하나로 FIFO 구조에 맞춘 추가적인 기능을 제공합니다.  
  FIFO(First In, First Out), 즉 먼저 들어온 요소가 먼저 나가는 방식으로 동작하는 자료구조를 나타냅니다.
- 주요 메서드:
  - offer(E e): 큐의 끝에 요소를 추가합니다. 성공 시 true, 실패 시 false를 반환합니다.
  - poll(): 큐의 앞쪽에 있는 요소를 제거하고 반환합니다. 큐가 비어있으면 null을 반환합니다.
  - peek(): 큐의 앞쪽에 있는 요소를 반환하지만 제거하지 않습니다. 큐가 비어있으면 null을 반환합니다.
- 대표적인 구현체: LinkedList, PriorityQueue, ArrayDeque 등

📃 **List 인터페이스**
- 설명: Collection을 상속받아, 요소의 순서를 유지하고 중복을 허용하는 자료구조를 나타내는 인터페이스입니다.  
  List 인터페이스는 Collection에서 정의된 메서드와 더불어 리스트의 특성에 맞는 추가적인 메서드를 제공합니다.
- 주요 메서드:
  - boolean add(E e): 리스트의 끝에 요소를 추가합니다
  - void add(int index, E element): 리스트의 특정 위치에 요소를 삽입합니다.
  - E get(int index): 리스트에서 지정된 인덱스의 요소를 반환합니다.
  - E set(int index, E element): 지정된 인덱스의 요소를 다른 요소로 교체합니다.
  - E remove(int index): 리스트에서 지정된 인덱스의 요소를 삭제하고 반환합니다.
  - boolean remove(Object o): 리스트에서 지정된 객체를 삭제합니다.
  - int size(): 리스트에 포함된 요소의 개수를 반환합니다.
  - boolean isEmpty(): 리스트가 비어있는지 여부를 반환합니다.
  - int indexOf(Object o): 리스트에서 특정 요소의 첫 번째 인덱스를 반환합니다.
  - int lastIndexOf(Object o): 리스트에서 특정 요소의 마지막 인덱스를 반환합니다.
  - List<E> subList(int fromIndex, int toIndex): 리스트의 일부분을 반환합니다.
- 대표적인 구현체: ArrayList, LinkedList, Vector 등

🔍 **관계 분석**  
List, Set, Map, Stack, Queue는 자바 컬렉션 프레임워크의 다양한 인터페이스 및 클래스들입니다. 이들 각각의 상속 계층도를 보면 자바에서 어떻게 데이터 구조를 관리하는지 이해할 수 있습니다.
- `Collection`: List, Set, Queue가 상속받는 상위 인터페이스입니다.
- `List`, `Set`, `Queue`는 각각의 특징에 맞게 구체적인 클래스로 구현됩니다.
- `Map`은 Collection과 독립적으로 키-값 쌍을 관리하는 인터페이스입니다.
- `Stack`은 List의 구현체 중 하나입니다.
- List 상속 계층도
   ```plaintext
   java.lang.Object
      ↳ java.util.Collection (인터페이스)
         ↳ java.util.List (인터페이스)
            ↳ java.util.ArrayList
            ↳ java.util.LinkedList
            ↳ java.util.Vector
               ↳ java.util.Stack
   ```
   - java.util.Collection: 모든 컬렉션의 상위 인터페이스
   - java.util.List: 순서가 있는 컬렉션, 중복을 허용함
   - java.util.ArrayList: 배열 기반으로 동작하는 List의 구현체  
   - java.util.LinkedList: 연결 리스트 기반의 List 구현체✔️
   - java.util.Vector: 동기화된 List 구현체 (덜 사용됨)
   - java.util.Stack: LIFO 방식의 스택 구현체 (Vector의 서브클래스)
- Queue 상속 계층도
   ```plaintext
   java.lang.Object
      ↳ java.util.Collection (인터페이스)
         ↳ java.util.Queue (인터페이스)
            ↳ java.util.LinkedList
            ↳ java.util.PriorityQueue
            ↳ java.util.Deque (인터페이스)
               ↳ java.util.ArrayDeque
   ```
   - java.util.Collection: 모든 컬렉션의 상위 인터페이스
   - java.util.Queue: FIFO 방식으로 동작하는 컬렉션 인터페이스
   - java.util.LinkedList: 양방향 연결 리스트 기반의 Queue 구현체✔️
   - java.util.PriorityQueue: 요소의 우선순위에 따라 처리되는 Queue 구현체
   - java.util.Deque: 양방향 큐 인터페이스 (FIFO 및 LIFO 모두 가능)
   - java.util.ArrayDeque: 배열 기반의 양방향 큐 구현체                
  
🔍 **같은 결과를 반환하는 메서드 케이스 분석**  

offer()는 Queue 인터페이스의 메서드이고 add()는 Queue 인터페이스의 상위 인터페이스인 Collection 인터페이스의 메서드입니다.  
q.offer()와 q.add()는 자바의 Queue 인터페이스에서 모두 요소를 추가하는 메서드지만, 약간의 차이점이 있습니다.  
- q.offer()와 q.add()는 모두 Queue에 요소를 추가하지만, 동작 방식에 차이가 있습니다.
  - q.offer()는 실패 시 예외를 던지지 않고 false를 반환합니다. 따라서, 큐에 더 이상 요소를 추가할 수 없는 상황에서 안전하게 실패를 처리할 수 있습니다.
  - q.add()는 실패 시 예외(IllegalStateException)를 던집니다. 특히 큐의 용량이 제한된 경우, 더 이상 요소를 추가할 수 없을 때 예외가 발생할 수 있습니다.
  
따라서, 큐의 크기나 용량에 제한이 있는 상황에서는 offer()를 사용하는 것이 더 안전할 수 있습니다. 하지만, 제한이 없는 큐에서는 add()와 offer()는 같은 결과를 반환합니다.

- 케이스 1: offer(E e)
  - 동작: 큐에 요소를 추가하려고 시도하고, 성공 시 true, 실패 시 **false**를 반환합니다. 큐의 크기나 용량에 제한이 있을 때, 더 이상 요소를 추가할 수 없으면 false를 반환합니다. 예외를 던지지 않고, 안전하게 실패를 처리하는 방식입니다.
   ```java
      Queue q=new LinkedList();//Queue인터페이스의 구현체인 LinkedList를 사용
   
      q.offer("0");
      q.offer("1");
      q.offer("2");
   
      while (!q.isEmpty()){
               System.out.println(q.poll());//큐에서 요소 하나를 꺼내서 출력
      }
   ```

- 케이스 2: add(E e)
  - 동작: 큐에 요소를 추가하며, 성공하면 true를 반환하지만, 큐의 용량이 제한되어 있을 때나 추가할 수 없는 상황이면 **IllegalStateException 예외**를 던집니다.  
   ```java
      Queue q=new LinkedList();//Queue인터페이스의 구현체인 LinkedList를 사용
   
      q.add("0");
      q.add("1");
      q.add("2");
   
      while (!q.isEmpty()){
               System.out.println(q.poll());//큐에서 요소 하나를 꺼내서 출력
      }
   ```
🔍 **예외처리가 중요한 이유**  
IllegalStateException이 발생하면 해당 예외가 처리되지 않는 한 프로그램이 예외가 발생한 부분에서 종료됩니다.  
예외가 발생하는 즉시 코드 실행이 중단되고, 예외가 전파됩니다. 만약 예외를 적절하게 처리하지 않으면 프로그램은 비정상적으로 종료되며, 버그로 이어질 수 있습니다.  

하지만, try-catch 문을 사용하여 예외를 처리하면 프로그램이 종료되지 않고, 예외를 처리한 후에도 계속해서 실행될 수 있습니다. 예를 들어, 큐에 더 이상 요소를 추가할 수 없을 때 IllegalStateException을 던지지만, 이를 catch 블록에서 처리하면 프로그램이 중단되지 않고 예외 상황에 맞는 로직을 실행할 수 있습니다.  

   ```java
   Queue<String> q = new LinkedList<>();
   
   try {
       q.add("A"); // 이 부분에서 IllegalStateException이 발생할 수 있음
   } catch (IllegalStateException e) {
       System.out.println("큐에 더 이상 요소를 추가할 수 없습니다: " + e.getMessage());
   }
   
   System.out.println("프로그램이 계속 실행됩니다.");
   ```

---
✏️ **예제 11-5 Iterator**  
컬렉션 프레임웍에서는 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화하였다.  
List클래스들은 저장순서를 유지하기 때문에 Iterator를 이용해서 읽어 온 결과 역시 저장순서와 동일하지만 Set클래스들은 각 요소간의 순서가 유지 되지 않기때문에 Iteraotr를 이용해서 저장된 요소들을 읽어 와도 처음에 저장된 순서와 같지 않다.  

* Iterator: 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스
* ListIterator: Iterator에 양방향 조회기능추가(List를 구현한 경우만 사용가능)
  
```java
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

```
---
**Arrays 클래스**  

Arrays 클래스는 배열과 관련된 다양한 기능을 제공하므로, 배열을 다룰 때 굉장히 편리하게 사용할 수 있습니다.  
- Arrays: 배열을 처리하는 데 유용한 정렬, 검색, 변환 등의 메서드를 제공하는 `유틸리티 클래스`.
- Array: 기본 배열로 크기가 고정된 자료구조.
- ArrayList: 크기가 가변적인 동적 배열 리스트.

`toString()`으로 배열의 모든 요소를 문자열로 편하게 출력할 수 있다. toString()은 일차원 배열에만 사용할 수 있으므로, 다차원 배열에는 deepToString()을 사용해야 한다.  
`deepToString()`은 배열의 모든 요소를 재귀적으로 접근해서 문자열을 구성하므로 2차원뿐만 아니라 3차원 이상의 배열에도 동작한다.  
`equals()`는 두 배열에 저장된 모든 요소를 비교해서 같으면 true, 다르면 false를 반환한다. equalis()도 일차원 배열에만 사용 가능하므로, 아래 예제에서 결과값(4)와 같이 다차원 배열은 저장된 내용이 같은데오 false를 결과로 얻는다. 이때는 deepEquals()를 사용해야 한다.  

✏️ **예제 11-6 Arrays의 메서드 - 비교와 출력**  
```java

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
```

🔍 **유틸리티 클래스란,**  
유틸리티 클래스(Utility Class)는 일반적으로 특정 작업을 수행하기 위해 만들어진 클래스입니다. 이러한 클래스는 인스턴스를 생성할 필요 없이 static 메서드를 통해 직접 사용할 수 있는 메서드들을 제공합니다.  

##### 예시  
- java.util.Arrays: 배열을 처리하는 다양한 메서드를 제공합니다.
- java.util.Collections: 컬렉션(리스트, 세트 등)을 다루기 위한 메서드를 제공합니다.
- java.lang.Math: 수학적 계산을 위한 다양한 메서드를 제공합니다(예: Math.sqrt(), Math.random() 등).

##### 특징  
  - 정적 메서드: 유틸리티 클래스의 메서드는 대부분 static으로 정의되어 있어 인스턴스를 생성하지 않고도 호출할 수 있습니다.
```java
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}
``` 
  - 인스턴스화 방지: 유틸리티 클래스는 보통 생성자를 private로 정의하여 인스턴스 생성을 방지합니다. 이렇게 하면 클래스의 기능을 객체가 아닌 클래스 이름을 통해 직접 사용할 수 있습니다.
```java
public class MathUtils {
    private MathUtils() {
        // 생성자 호출 방지
    }
    public static int add(int a, int b) {
        return a + b;
    }
}
```
##### 상속도  

Arrays 클래스는 자바에서 직접 상속되지 않는 final 클래스입니다.  
즉, Arrays 클래스는 인스턴스를 만들 수 없으며, 제공되는 메서드는 모두 static으로 사용됩니다.
```
java.lang.Object
    └── java.util.Arrays (final)
```
- java.lang.Object: 모든 자바 클래스의 최상위 클래스입니다. Arrays 클래스는 이 클래스를 상속받습니다.
- java.util.Arrays: 배열을 다루기 위한 여러 정적 메서드를 제공하는 유틸리티 클래스입니다. 이 클래스는 final로 선언되어 다른 클래스가 이 클래스를 상속할 수 없습니다.

---
**Integer 클래스**  
자바에서 기본 데이터 타입인 int를 객체로 다룰 수 있도록 해주는 Wrapper 클래스입니다.  
자바는 객체 지향 언어이기 때문에 원시 타입(primitive type)인 int와 같은 기본 데이터 타입을 객체로 사용할 필요가 있을 때 Integer 클래스를 사용합니다.  
##### 특징  
- 박싱과 언박싱:
  - 박싱 (Boxing): 기본형 int를 Integer 객체로 변환하는 과정입니다.
  - 언박싱 (Unboxing): Integer 객체를 기본형 int로 변환하는 과정입니다.
    ```
    Integer intObj = 5;  // 박싱
    int num = intObj;    // 언박싱
    ```
- 문자열 변환: Integer 클래스는 문자열을 정수로 변환하는 parseInt() 메서드와 정수를 문자열로 변환하는 toString() 메서드를 제공합니다.
    ```
    int num = Integer.parseInt("123");  // 문자열을 int로 변환
    String str = Integer.toString(num);  // int를 문자열로 변환
    ```
- 비교 메서드:
  - compareTo(): 두 객체의 크기(순서)를 비교하는 데 사용됩니다. 주로 정렬이 필요할 때 사용되며, 객체의 대소 관계를 판단합니다.
    ```
    Integer a = 5;
    Integer b = 10;
    int result = a.compareTo(b);  // result는 -1 (a가 b보다 작음)
    ```

  - equals(): 두 객체가 동일한지를 비교하는 데 사용됩니다. 객체의 내용이 같은지를 판단합니다.
    ```
    Integer a = 5;
    Integer b = 5;
    boolean isEqual = a.equals(b);  // isEqual은 true (값이 동일함)
    ```
✏️ **예제 11-7(1) CompareToExample**  
```java
public class CompareToExample {
    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 10;

        // compareTo 메서드를 사용하여 비교
        int result = a.compareTo(b);  // result는 -1 (a가 b보다 작음)

        System.out.println("Result of compareTo: " + result); //결과값:Result of compareTo: -1
    }
}
```
✏️ **예제 11-7(2) Comparator와 Comparable**  
```java
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

public class Ex11_7 {
    public static void main(String[] args) {

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
```
##### 결과값
```
befor 정렬 [cat, Dog, lion, tiger]
after 정렬 [Dog, cat, lion, tiger]
대소문자 구분x [cat, Dog, lion, tiger]
역순 정렬 [tiger, lion, cat, Dog]
```


🔍 **예제 11-7(1)과 예제 11-7(2)에서 쓰인 compareTo()**  

https://chatgpt.com/share/66ebe739-6050-8002-9a89-e3327b62d10a

---


---
# Chapter 12 지네릭스, 열거형, 애너테이션 
# Chapter 13 쓰레드

