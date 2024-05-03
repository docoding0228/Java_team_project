import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Subject {
    List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    List<String> optionalSubjects = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");
    Scanner sc = new Scanner(System.in);
    private ArrayList<String> subjectList = new ArrayList<>();


    public Subject(){}


    public void setSubjectList() {
        ArrayList<String> tempSubjectList = new ArrayList<>();
        System.out.println("필수 과목 목록:");
        for (int i = 0; i < mandatorySubjects.size(); i++) {
            System.out.println((i + 1) + ". " + mandatorySubjects.get(i));
        }
        // 필수 과목 선택
        while (tempSubjectList.size() < 5) {
            System.out.print("필수 과목 번호를 입력해주세요: ");
            int subjectIndex = sc.nextInt() - 1; // 인덱스는 0부터 시작

            if (subjectIndex < 0 || subjectIndex >= mandatorySubjects.size()) {
                System.out.println("유효하지 않은 필수 과목 번호입니다. 다시 입력해주세요.");
                continue;
            }

            String subject = mandatorySubjects.get(subjectIndex);

            if (tempSubjectList.contains(subject)) {
                System.out.println("이미 신청한 과목입니다.");
                continue;
            }

            tempSubjectList.add(subject);

            if (tempSubjectList.size() >= 3) {
                if (tempSubjectList.size() == 5) {
                    System.out.println("필수 과목 신청이 완료되었습니다.");
                    break;
                } else {
                    System.out.println("필수 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                    String answer = sc.next();
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

        int sizeCheck = 0;
        while (tempSubjectList.size() < 9) {
            System.out.print("선택 과목 번호를 입력해주세요: ");
            int subjectIndex = sc.nextInt() - 1;

            if (subjectIndex < 0 || subjectIndex >= optionalSubjects.size()) {
                System.out.println("유효하지 않은 선택 과목 번호입니다. 다시 입력해주세요.");
                continue;
            }

            String subject = optionalSubjects.get(subjectIndex);

            if (tempSubjectList.contains(subject)) {
                System.out.println("이미 신청한 과목입니다.");
                continue;
            }

            tempSubjectList.add(subject);
            sizeCheck++;

            if (sizeCheck == 4) {
                System.out.println("선택 과목 신청이 완료되었습니다!");
                break;
            } else if (sizeCheck >= 2) {
                System.out.println("선택 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
        this.subjectList = tempSubjectList;
    }

    /**
     *
     * @return 해당 인원의 수강리스트를 반환
     */
    public ArrayList<String> getSubjects() {
        return this.subjectList;
    }
}
