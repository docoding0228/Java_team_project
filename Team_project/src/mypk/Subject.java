package mypk;

import java.util.*;

public class Subject {
    // 프로그램 실행 중에 값이 변경되지 않는 상수 리스트로, 필수 과목 목록을 미리 정의
    private static final List<String> REQUIRED_SUBJECTS = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    private static final List<String> ELECTIVE_SUBJECTS = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");

    private static Map<String, List<String>> studentSubjects = new HashMap<>(); // 학생별 과목 목록

    // 수강생 과목 추가
    public static void manageSubjects() {
        Scanner sc = new Scanner(System.in);

        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next();

        // 학생 등록 여부 확인
        // studentId가 Student.isRegistered() 메서드에서 false를 반환하는지 확인
        // 수강생 ID가 등록되지 않았음을 의미
        if (!Student.isRegistered(studentId)) {
            System.out.println("등록되지 않은 ID 입니다.");
            return;
        }

        // 학생에게 필수과목과 선택과목을 입력받는 과정

        // studentSubjects 맵에서 주어진 studentId가 키로 존재하지 않으면, 해당 키로 빈 ArrayList를 추가
        // studentSubjects.containsKey(studentId)가 false이면, 해당 키로 아직 아무런 값이 추가되지 않았음을 의미
        // 수강생이 아직 과목을 추가하지 않은 경우, 새로운 과목 목록을 생성하고 해당 수강생 ID와 연결하는 데 사용
        if (!studentSubjects.containsKey(studentId)) {
            studentSubjects.put(studentId, new ArrayList<>());
        }

        // studentSubjects 맵에서 studentId에 해당하는 값을 가져와 subjects 리스트에 할당
        // 수강생 ID에 대해 어떤 과목이 등록되어 있는지 확인하거나 추가 작업을 수행할 때 사용
        List<String> subjectlist = studentSubjects.get(studentId);

        // 필수과목 추가
        System.out.println("필수 과목 목록: " + REQUIRED_SUBJECTS);
        System.out.println("필수 과목을 최소 3개 이상 선택하세요.");
        boolean addingRequiredSubjects = true;

        // addingRequiredSubjects 변수가 true인 동안 계속 실행되는 while 루프를 시작
        // addingRequiredSubjects 변수가 false가 되면 루프가 종료
        while (addingRequiredSubjects) {
            System.out.print("필수 과목을 입력하세요: ");

            //  필수 과목 입력 부분
            String requiredSubject = sc.next();

            // 내가 입력한 필수 과목이, 초기에 선언한 REQUIRED_SUBJECTS(필수과목) 목록에 있는지 확인
            // 그리고 subjectlist에는 포함되어 있지 않을 때 필수 과목을 subjectlist 추가
            // 수강생이 필수 과목을 추가할 때, 필수 과목 목록에 포함된 과목이면서 중복되지 않은 과목만 추가하도록 하는 데 사용
            if (REQUIRED_SUBJECTS.contains(requiredSubject) && !subjectlist.contains(requiredSubject)) {
                subjectlist.add(requiredSubject);
                // contains는 컬렉션(리스트, 맵 등) 또는 문자열에서 특정 요소 또는 문자열이 포함되어 있는지 확인하는 메서드
                // REQUIRED_SUBJECTS.contains(requiredSubject): 초기필수과목에서 내가 입력한 필수과목이 존재하는지 확인
            } else {
                System.out.println("올바르거나 중복되지 않은 과목을 입력하세요.");
            }

            // subjectlist 크기가 3 이상인지를 확인하는 조건문
            if (subjectlist.size() >= 3) { // 필수과목을 3개 이상 입력했을 때
                System.out.print("필수 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();

                // answer가 "NO"와 같은지, 대소문자를 구분하지 않고 확인
                if ("NO".equalsIgnoreCase(answer)) {
                    // 만약 "NO"와 같다면, addingRequiredSubjects 변수를 false로 설정하여 필수 과목 추가 작업을 중단
                    addingRequiredSubjects = false; // 선택과목으로 넘어가기
                }
            }
        }

        // 선택과목 추가
        System.out.println("선택 과목 목록: " + ELECTIVE_SUBJECTS);
        boolean addingElectiveSubjects = true;

        while (addingElectiveSubjects) {
            System.out.print("선택 과목을 입력하세요: ");
            String electiveSubject = sc.next();

            if (ELECTIVE_SUBJECTS.contains(electiveSubject) && !subjectlist.contains(electiveSubject)) {
                subjectlist.add(electiveSubject);
            } else {
                System.out.println("올바르거나 중복되지 않은 과목을 입력하세요.");
            }

            if (subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).count() >= 2) { // 선택과목 2개 이상 입력했을 때
                System.out.print("선택 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();

                if ("NO".equalsIgnoreCase(answer)) {
                    addingElectiveSubjects = false; // 입력 종료
                }
            }
        }

        // 최종 선택된 과목 목록 출력
        System.out.println("수강생 ID: " + studentId);
        System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
        System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());
    }
}
