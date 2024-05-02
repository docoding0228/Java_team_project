import java.util.*;
public class Main {
    public static void main(String[] args) {
        Map<Integer, String> nameMap = new HashMap<>();
        Map<Integer, ArrayList>  subjectMap = new HashMap<>();
        Map<Integer, Score> scoreMap = new HashMap<>();
        ArrayList<Integer> subjectList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("수강생 정보 입력 (1), 점수 관리 (2) : ");
        int select = sc.nextInt();

        if(select == 1){
            System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요. : "); //예외처리 이후에
            int idNumber = sc.nextInt();

            System.out.print("성함을 입력해주세요 : ");
            String Name = sc.next();
            nameMap.put(idNumber, Name);

            int Subject;

            for(int i = 0; i < 3; i++){
                System.out.print((i+1) + "번째 필수 과목을 입력해주세요. : ");
                Subject = sc.nextInt();
                subjectList.add(Subject);
            }

            for(int i = 0; i < 2; i++){
                System.out.print((i+1) + "번째 선택 과목을 입력해주세요. : ");
                Subject = sc.nextInt();
                subjectList.add(Subject);
            }

            subjectMap.put(idNumber, subjectList);

            System.out.println("사람이 추가되었습니다.");

            for (int key : nameMap.keySet()) {
                System.out.print("{" + key + ", "); // 일,이,삼 출력
                System.out.println(nameMap.get(idNumber) + "}");
            }

            for (int key : subjectMap.keySet()) {
                System.out.print("{" + key + ", "); // 일,이,삼 출력
                System.out.println(subjectMap.get(idNumber) + "}");
            }
        }

    }
}