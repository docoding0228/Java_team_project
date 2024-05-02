import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> nameMap = new HashMap<>();
        Map<String, ArrayList>  subjectMap = new HashMap<>();
        Map<String, Score> scoreMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        do
        {
            try {
                System.out.println("수강생 정보 입력 (1), 점수 관리 (2) : ");
                int select = sc.nextInt();

                // 사람생성
                if (select == 1) {
                    System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요.) : ");//예외처리 이후에
                    String idNumber = sc.next();
                    for (String key : nameMap.keySet()) {
                        if (idNumber.equals(key)) {
                            throw new Exception("이미 등록된 사용자입니다.");
                        }
                    }
                    // 이름 입력
                    System.out.print("성함을 입력해주세요 : ");
                    String Name = sc.next();
                    nameMap.put(idNumber, Name);

/************************************************************************************************************************/
                    // 과목 입력하기  -> 숫자과목 대신 string 으로 받기? map 에다가 잘 해서 하기?
/************************************************************************************************************************/
                    int Subject;
                    ArrayList<Integer> subjectList = new ArrayList<>();
                    while(true){
                        System.out.print("필수 과목을 입력해주세요. : ");
                        Subject = sc.nextInt();

                        if(subjectList.contains(Subject)){
                            System.out.println("이미 신청한 과목입니다!");
                            continue;
                        }

                        if (Subject<1 || Subject>5) {
                            System.out.println("필수 과목은 1~5번 사이로 골라주세요!");
                            continue;
                        }else{
                            subjectList.add(Subject);
                        }

                        if(subjectList.size() >= 5){
                            System.out.println("필수과목을 더이상 신청 할 수 없습니다!");
                            break;
                        }

                        if(subjectList.size() >= 3){
                            System.out.println("필수 과목 신청을 마치시겠습니까? (type \"yes\" to quit)");
                            String answerPilsoo = sc.next();
                            if (answerPilsoo.equals("yes")) {
                                break;
                            }
                        }
                    }
                    // 선택과목 입력
                    ArrayList<Integer> tempSubjectList = new ArrayList<>();
                    while(true) {
                        System.out.print("선택 과목을 입력해주세요. : ");
                        Subject = sc.nextInt();

                        if(tempSubjectList.contains(Subject)){
                            System.out.println("이미 신청한 과목입니다!");
                            continue;
                        }

                        if (Subject<6 || Subject>9) {
                            System.out.println("필수 과목은 6~9번 사이로 골라주세요!");
                            continue;
                        }else{
                            tempSubjectList.add(Subject);
                        }

                        if(tempSubjectList.size() >= 4){
                            System.out.println("선택 과목을 더이상 신청 할 수 없습니다!");
                            break;
                        }

                        if(tempSubjectList.size() >= 2){
                            System.out.println("선택 과목 신청을 마치시겠습니까? (type \"yes\" to quit)");
                            String answerPilsoo = sc.next();
                            if (answerPilsoo.equals("yes")) {
                                break;
                            }
                        }
                    }

                    for(Integer tempSubject : tempSubjectList){
                        subjectList.add(tempSubject);
                    }

                    subjectMap.put(idNumber, subjectList);
                    System.out.println("사람이 추가되었습니다.");
                }
                // 점수관리 시스템 구현할 곳
                else{
                    System.out.println("해당 기능은 구현중입니다!");
                }

                // nameMap 프린트
                for (String key : nameMap.keySet()) {
                    System.out.print("{" + key + ", ");
                    System.out.println(nameMap.get(key) + "}");
                }
                // subjectMap 프린트
                for (String key : subjectMap.keySet()) {
                    System.out.print("{" + key + ", ");
                    System.out.println(subjectMap.get(key) + "}");
                }

                System.out.println("type \"exit\" to exit");
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("type \"exit\" to exit");
            }
        } while(!sc.next().equals("exit"));
    }
}