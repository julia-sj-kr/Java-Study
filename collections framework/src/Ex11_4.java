import java.util.*;

public class Ex11_4 {
    static Queue q=new LinkedList();
    static final int MAX_SIZE=5;

    public static void main(String[] args) {
        System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");

        while (true){
            System.out.println(">>");
            try {
                //화면으로부터 라인단위로 입력받는다.
                Scanner s=new Scanner(System.in);
                String input=s.nextLine().trim();

                if ("".equals(input)) continue;

                //equalsIgnoreCase 메서드는 Java의 String 클래스에 정의된 메서드로, 두 문자열이 대소문자를 구분하지 않고 동일한지를 비교하는 데 사용됩니다.
                //이 메서드는 대소문자를 무시하고 문자열의 내용을 비교하므로, 사용자 입력이나 다양한 형식의 문자열을 비교할 때 유용합니다.
                if (input.equalsIgnoreCase("q")){
                    System.exit(0);
                } else if (input.equalsIgnoreCase("help")){
                    System.out.println(" help - 도움말을 보여줍니다.");
                    System.out.println(" q 또는 Q - 프로그램을 종료합니다.");
                    System.out.println(" history - 최근에 입력한 명령어를 "+MAX_SIZE+"개 보여줍니다.");

                }else if (input.equalsIgnoreCase("history")){
                    int i=0;
                    LinkedList tmp=(LinkedList) q;
                    ListIterator it=tmp.listIterator();

                    while (it.hasNext()){
                        System.out.println(++i+"."+it.next());
                    }
                }else {
                    save(input);
                    System.out.println(input);
                }

            }catch (Exception e){
                System.out.println("입력오류입니다.");
            }
        }
    }
    public static void save(String input){
        //queue에 저장한다.
        if(!"".equals(input)) {
            q.offer(input);
        }

        //queue의 최대크기를 넘으면 제일 처음 입력된 것을 삭제한다.
        if (q.size()>MAX_SIZE){//size()는 Collection 인터페이스에 정의
            q.remove();
        }
    }
}
