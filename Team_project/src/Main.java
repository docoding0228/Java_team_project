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

        do {
            if (select == 1) {
                nameMap.put(001,"이름");   // int 대신 string "001"
                System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요. : ");//예외처리 이후에
                int idNumber = sc.nextInt();
                for (int key : nameMap.keySet()){
                    if (idNumber == key){
                        System.out.println("이미 등록된 사용자입니다.");
                    }else{
                        // 이름 입력
                        System.out.print("성함을 입력해주세요 : ");
                        String Name = sc.next();
                        nameMap.put(idNumber, Name);

                        // 과목 입력
                        int Subject;
                        for (int i = 0; i < 3; i++) {
                            System.out.print((i + 1) + "번째 필수 과목을 입력해주세요. : ");
                            Subject = sc.nextInt();
                            subjectList.add(Subject);
                        }
                        for (int i = 0; i < 2; i++) {
                            System.out.print((i + 1) + "번째 선택 과목을 입력해주세요. : ");
                            Subject = sc.nextInt();
                            subjectList.add(Subject);
                        }
                        subjectMap.put(idNumber, subjectList);

                        System.out.println("사람이 추가되었습니다.");
                    }
                }



                for (int key : nameMap.keySet()) {
                    System.out.print("{" + key + ", "); // 일,이,삼 출력
                    System.out.println(nameMap.get(idNumber) + "}");
                }

                for (int key : subjectMap.keySet()) {
                    System.out.print("{" + key + ", "); // 일,이,삼 출력
                    System.out.println(subjectMap.get(idNumber) + "}");
                }
            }
            System.out.println("type \"continue\" to continue");
        } while(sc.next().equals("continue"));
    }

}
//for (int key : nameMap.keySet()) {
//        System.out.print("{" + key + ", "); // 일,이,삼 출력
//        System.out.println(nameMap.get(idNumber) + "}");
//}