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
  Queue q = new LinkedList();는 자바의 다형성을 활용하여 `인터페이스(Queue)` 를 사용해 `구현체(LinkedList)`를 참조하였다.
  여기서는 Queue라는 인터페이스 타입을 사용하지만, 실제 객체는 LinkedList 클래스를 사용해 생성된 것입니다. 이 코드는 q 변수가 Queue 타입을 따르며, 큐로서의 동작만 허용되지만, 내부적으로는 LinkedList의 메서드가 실행   된다는 의미입니다.

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
- 설명: 자바 컬렉션 프레임워크의 최상위 인터페이스 중 하나로, 모든 컬렉션 클래스의 공통적인 동작을 정의합니다.
  List, Set, Map, Queue와 같은 컬렉션 타입은 모두 이 인터페이스를 구현하고 있습니다.

📃 **Queue 인터페이스**


🔍 


---
**Iterator, ListIterator**
컬렉션 프레임웍에서는 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화하였다.  
컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator 인터페이스를 정의하고, Collection 인터페이스에는 'Iterator를 구현한 클래스의 인스턴스'를 반환하는 iterator()를 정의하고 있다.
    






---
# Chapter 12 지네릭스, 열거형, 애너테이션 
# Chapter 13 쓰레드

