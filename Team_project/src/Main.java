import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> nameMap = new HashMap<>();
        Map<String, ArrayList<String>> subjectMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
        List<String> optionalSubjects = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");

        do {
            try {
                System.out.println("==================================");
                System.out.println("점수 관리 실행 중...");
                System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
                System.out.println("2. 수강생의 과목별 회차 점수 수정");
                System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
                System.out.println("4. 메인 화면 이동");
                System.out.print(" 관리 항목을 선택하세요... ");
                int selectlist = sc.nextInt();
                sc.nextLine(); // 개행 문자 소비

                if (selectlist == 1) { // 과목별 시험 회차 및 점수 등록
                    System.out.println("1. 수강생 정보 입력,  2. 회차 및 점수 등록 : ");
                    int subSelect = sc.nextInt(); // 내부 메뉴 선택
                    sc.nextLine(); // 개행 문자 소비

                    if (subSelect == 1) { // 수강생 정보 입력
                        System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요.): ");
                        String idNumber = sc.nextLine();

                        if (nameMap.containsKey(idNumber)) {
                            throw new Exception("이미 등록된 사용자입니다.");
                        }

                        // 이름 입력
                        System.out.print("성함을 입력해주세요: ");
                        String name = sc.nextLine();
                        nameMap.put(idNumber, name);

                        // 과목 등록
                        ArrayList<String> subjectList = new ArrayList<>();
                        System.out.println("필수 과목 목록:");
                        for (int i = 0; i < mandatorySubjects.size(); i++) {
                            System.out.println((i + 1) + ". " + mandatorySubjects.get(i));
                        }

                        while (subjectList.size() < 5) {
                            System.out.print("필수 과목 번호를 입력해주세요: ");
                            int subjectIndex = sc.nextInt() - 1; // 인덱스는 0부터 시작
                            sc.nextLine(); // 개행 문자 소비

                            if (subjectIndex < 0 || subjectIndex >= mandatorySubjects.size()) {
                                System.out.println("유효하지 않은 필수 과목 번호입니다. 다시 입력해주세요.");
                                continue;
                            }

                            String subject = mandatorySubjects.get(subjectIndex);

                            if (subjectList.contains(subject)) {
                                System.out.println("이미 신청한 과목입니다.");
                                continue;
                            }

                            subjectList.add(subject);

                            if (subjectList.size() >= 3) {
                                System.out.println("필수 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                                String answer = sc.nextLine();
                                if (answer.equalsIgnoreCase("yes")) {
                                    break;
                                }
                            }
                        }

                        // 선택 과목 입력
                        System.out.println("선택 과목 목록:");
                        for (int i = 0; i < optionalSubjects.size(); i++) {
                            System.out.println((i + 1) + ". " + optionalSubjects.get(i));
                        }

                        while (subjectList.size() < 9) {
                            System.out.print("선택 과목 번호를 입력해주세요: ");
                            int subjectIndex = sc.nextInt() - 1;
                            sc.nextLine(); // 개행 문자 소비

                            if (subjectIndex < 0 || subjectIndex >= optionalSubjects.size()) {
                                System.out.println("유효하지 않은 선택 과목 번호입니다. 다시 입력해주세요.");
                                continue;
                            }

                            String subject = optionalSubjects.get(subjectIndex);

                            if (subjectList.contains(subject)) {
                                System.out.println("이미 신청한 과목입니다.");
                                continue;
                            }

                            subjectList.add(subject);

                            if (subjectList.size() >= 6) {
                                System.out.println("선택 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                                String answer = sc.nextLine();
                                if (answer.equalsIgnoreCase("yes")) {
                                    break;
                                }
                            }
                        }

                        subjectMap.put(idNumber, subjectList);
                        System.out.println("수강생 정보가 등록되었습니다.");
                    } else if (subSelect == 2) { // 회차 및 점수 등록
                        System.out.println("해당 기능은 구현 중입니다!");
                    } else {
                        System.out.println("유효하지 않은 선택입니다. 다시 시도해주세요.");
                    }
                } else if (selectlist == 2) { // 회차 점수 수정
                    System.out.println("해당 기능은 구현 중입니다!");
                } else if (selectlist == 3) { // 특정 과목 회차별 등급 조회
                    System.out.println("해당 기능은 구현 중입니다!");
                } else if (selectlist == 4) { // 메인 화면 이동
                    System.out.println("메인 화면으로 돌아갑니다.");
                } else {
                    System.out.println("유효하지 않은 선택입니다.");
                }

                // nameMap 프린트
                System.out.println("현재 등록된 사람들:");
                for (Map.Entry<String, String> entry : nameMap.entrySet()) {
                    System.out.println("{" + entry.getKey() + ", " + entry.getValue() + "}");
                }

                // subjectMap 프린트
                System.out.println("현재 등록된 과목들:");
                for (Map.Entry<String, ArrayList<String>> entry : subjectMap.entrySet()) {
                    System.out.println("{" + entry.getKey() + ", " + String.join(", ", entry.getValue()) + "}");
                }

                System.out.println("종료하시려면 'exit'을 입력하세요.");
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
                System.out.println("종료하시려면 'exit'을 입력하세요.");
            }
        } while (!sc.nextLine().equalsIgnoreCase("exit"));
    }
}

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

