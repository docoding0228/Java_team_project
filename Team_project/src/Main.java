import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> nameMap = new HashMap<>();
        Map<Integer, ArrayList> subjectMap = new HashMap<>();
        Map<Integer, Score> scoreMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println(("수강생 정보 입력 (1), 점수 관리 (2)"));
        int select = sc.nextInt();

        System.out.println("고유번호를 입력하세요(3자리 입력) : ");
        int idNumber = sc.nextInt();

        System.out.println("이름을 입력하세요 : ");
        String name = sc.next();

        System.out.println("과목을 입력하세요.(상세보기 0 입력) : ");
        int Subject = sc.nextInt();

        if(select == 1){
            nameMap.put(idNumber, name);
        }else if(select == 2){
            if(Subject == 0){
                System.out.println("필수 과목 목록 ::");
            }
        }
    }
}