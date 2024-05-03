import java.util.*;

public class Student {
    Scanner sc = new Scanner(System.in);
    // enum 추천 (따로 클래스처럼 만드는것) >> 이걸 쓰면 나중에 목록 추가하고 관리하는것들이 쉬워지지만 이 사이즈의 프로젝트에서 사용하기에는 굳이라고 판단
    List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    List<String> optionalSubjects = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");
    private String name;

//    Map<String, String> nameMap = new HashMap<>();
//    Map<String, ArrayList<String>> subjectMap = new HashMap<>();
//    Map<String, Score> subjectScoreMap = new HashMap<>();;

    public Student() {
    }

    /**
     * setting student name using scanner
     */
    public void setStudentName() {
        // 이름 입력
        System.out.print("성함을 입력해주세요: ");
        this.name = sc.next();
        // 테스트용
        System.out.println("testing setStudentName " + this.name);
    }

    /**
     * setting mandatory subjects + non mandatory subjects list
     */
    public void setSubjectList() {
        ArrayList<String> subjectList = new ArrayList<>();
        System.out.println("필수 과목 목록:");
        for (int i = 0; i < mandatorySubjects.size(); i++) {
            System.out.println((i + 1) + ". " + mandatorySubjects.get(i));
        }
        // 필수 과목 선택
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
                if (subjectList.size() == 5) {
                    System.out.println("필수 과목 신청이 완료되었습니다.");
                    break;
                } else {
                    System.out.println("필수 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                    String answer = sc.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        break;
                    }
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

            if (subjectIndex == 3) {
                System.out.println("선택 과목 신청이 완료되었습니다!");
                break;
            } else if (subjectIndex >= 1) {
                System.out.println("선택 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    break;
                }

            }
        }
        /**************************************************************************************
         * 여기에 Subject 객체 받아서 subjectList 추가하면 됨
         subjectMap.put(idNumber, subjectList);
         **************************************************************************************/

        System.out.println("수강생 정보가 등록되었습니다.");
    }

    /**
     * 학생 정보 조회 메서드
     * @param studentID 학생 id를 인풋으로 받는다
     */
    public void searchStudent(String studentID){

    }
}









