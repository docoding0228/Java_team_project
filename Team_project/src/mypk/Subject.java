package mypk;

import java.util.*;

public class Subject {
    private static final List<String> REQUIRED_SUBJECTS = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    private static final List<String> ELECTIVE_SUBJECTS = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");

    private static Map<String, List<String>> studentSubjects = new HashMap<>(); // 학생별 과목 목록

    public static void manageSubjects() {
        Scanner sc = new Scanner(System.in);

        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next();

        // 학생 등록 여부 확인
        if (!Student.isRegistered(studentId)) {
            System.out.println("등록되지 않은 ID 입니다.");
            return;
        }

        // 학생에게 필수과목과 선택과목을 입력받는 과정
        if (!studentSubjects.containsKey(studentId)) {
            studentSubjects.put(studentId, new ArrayList<>());
        }

        List<String> subjects = studentSubjects.get(studentId);

        // 필수과목 추가
        System.out.println("필수 과목 목록: " + REQUIRED_SUBJECTS);
        System.out.println("필수 과목을 최소 3개 이상 선택하세요.");
        boolean addingRequiredSubjects = true;

        while (addingRequiredSubjects) {
            System.out.print("필수 과목을 입력하세요: ");
            String requiredSubject = sc.next();

            if (REQUIRED_SUBJECTS.contains(requiredSubject) && !subjects.contains(requiredSubject)) {
                subjects.add(requiredSubject);
            } else {
                System.out.println("올바르거나 중복되지 않은 과목을 입력하세요.");
            }

            if (subjects.size() >= 3) { // 필수과목을 3개 이상 입력했을 때
                System.out.print("필수 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();

                if ("NO".equalsIgnoreCase(answer)) {
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

            if (ELECTIVE_SUBJECTS.contains(electiveSubject) && !subjects.contains(electiveSubject)) {
                subjects.add(electiveSubject);
            } else {
                System.out.println("올바르거나 중복되지 않은 과목을 입력하세요.");
            }

            if (subjects.stream().filter(ELECTIVE_SUBJECTS::contains).count() >= 2) { // 선택과목 2개 이상 입력했을 때
                System.out.print("선택 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();

                if ("NO".equalsIgnoreCase(answer)) {
                    addingElectiveSubjects = false; // 입력 종료
                }
            }
        }

        // 최종 선택된 과목 목록 출력
        System.out.println("수강생 ID: " + studentId);
        System.out.println("필수 과목: " + subjects.stream().filter(REQUIRED_SUBJECTS::contains).toList());
        System.out.println("선택 과목: " + subjects.stream().filter(ELECTIVE_SUBJECTS::contains).toList());
    }
}
