import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Subject {
    private List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    private List<String> optionalSubjects = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> subjectList = new ArrayList<>();

    public Subject() {}

    public void setSubjectList() {
        ArrayList<String> tempSubjectList = new ArrayList<>();

        // 필수 과목 선택
        System.out.println("필수 과목 목록:");
        for (int i = 0; i < mandatorySubjects.size(); i++) {
            System.out.println((i + 1) + ". " + mandatorySubjects.get(i));
        }

        // 최소 3개 이상의 필수 과목을 선택해야 함
        while (tempSubjectList.size() < 3 || (tempSubjectList.size() < 5 && !exitRequested())) {
            System.out.print("필수 과목 번호를 입력해주세요: ");
            int subjectIndex = sc.nextInt() - 1;

            if (subjectIndex < 0 || subjectIndex >= mandatorySubjects.size()) {
                System.out.println("유효하지 않은 과목 번호입니다.");
                continue;
            }

            String subject = mandatorySubjects.get(subjectIndex);

            if (tempSubjectList.contains(subject)) {
                System.out.println("이미 선택한 과목입니다.");
            } else {
                tempSubjectList.add(subject);
                System.out.println("선택된 과목: " + subject + " (필수)");
            }

            if (tempSubjectList.size() >= 3) {
                System.out.println("필수 과목을 3개 이상 선택하셨습니다. 계속 선택하시겠습니까? (\"yes\" 입력 시 종료)");
                if (exitRequested()) {
                    break;
                }
            }
        }

        // 선택 과목 선택
        System.out.println("선택 과목 목록:");
        for (int i = 0; i < optionalSubjects.size(); i++) {
            System.out.println((i + 1) + ". " + optionalSubjects.get(i));
        }

        // 최소 2개 이상의 선택 과목을 선택해야 함
        while (tempSubjectList.size() < 9) {
            System.out.print("선택 과목 번호를 입력해주세요: ");
            int subjectIndex = sc.nextInt() - 1;

            if (subjectIndex < 0 || subjectIndex >= optionalSubjects.size()) {
                System.out.println("유효하지 않은 과목 번호입니다.");
                continue;
            }

            String subject = optionalSubjects.get(subjectIndex);

            if (tempSubjectList.contains(subject)) {
                System.out.println("이미 선택한 과목입니다.");
            } else {
                tempSubjectList.add(subject);
                System.out.println("선택된 과목: " + subject + " (선택)");
            }

            // 선택 과목이 2개 이상일 때만 선택 종료 여부 확인
            if (tempSubjectList.size() >= 5 && tempSubjectList.size() >= 2) {
                System.out.println("선택 과목을 2개 이상 선택하셨습니다. 계속 선택하시겠습니까? (\"yes\" 입력 시 종료)");
                if (exitRequested()) {
                    break;
                }
            }
        }

        this.subjectList = tempSubjectList;
    }

    private boolean exitRequested() {
        String answer = sc.next();
        return answer.equalsIgnoreCase("yes");
    }

    public ArrayList<String> getSubjects() {
        return this.subjectList;
    }
}
