import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 학생의 고유번호에 이름을 매핑
        Map<Integer, String> nameMap = new HashMap<>();
        // 학생의 고유번호에 선택한 과목 번호를 저장
        Map<Integer, Set<Integer>> subjectMap = new HashMap<>();

        // 필수 과목 목록
        Map<Integer, String> requiredSub = new HashMap<>();
        requiredSub.put(1, "Java");
        requiredSub.put(2, "객체지향");
        requiredSub.put(3, "Spring");
        requiredSub.put(4, "JPA");
        requiredSub.put(5, "MySQL");

        Scanner sc = new Scanner(System.in);
        System.out.println("고유번호를 입력해주세요. : ");
        int idNumber = sc.nextInt();

        System.out.println("성함을 입력해주세요. : ");
        String name = sc.next();
        nameMap.put(idNumber, name);

        Set<Integer> selectedSubjects = new HashSet<>();
        while (selectedSubjects.size() < 3) { // 3개의 과목을 선택해야 함
            System.out.println("과목 번호를 입력해주세요. (상세보기 0 입력) : ");
            int subject = sc.nextInt();

            if (subject == 0) {
                // 과목 목록 출력
                System.out.println("필수 과목 목록:");
                for (Map.Entry<Integer, String> entry : requiredSub.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else if (requiredSub.containsKey(subject)) {
                // 중복된 과목 검사
                if (selectedSubjects.contains(subject)) {
                    System.out.println("중복된 과목입니다.");
                } else {
                    // 과목이 필수 과목 목록에 있는 경우 추가
                    selectedSubjects.add(subject);
                }
            } else {
                System.out.println("잘못된 과목 번호입니다. 다시 시도해주세요.");
            }

            System.out.println("현재 선택된 과목: " + selectedSubjects.size() + "개");
        }
        subjectMap.put(idNumber, selectedSubjects);

        // 결과 출력
        System.out.println("학생 정보:");
        System.out.println("ID: " + idNumber + ", 이름: " + name);
        System.out.println("선택한 과목들:");
        for (Integer sub : subjectMap.get(idNumber)) {
            System.out.println(" - " + requiredSub.get(sub));
        }
    }
}
