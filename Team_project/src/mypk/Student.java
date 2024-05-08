package mypk;

import java.util.*;

public class Student {
    private static Scanner sc = new Scanner(System.in);

    // 수강생 ID와 이름을 저장하는 Map
    public static Map<String, Map<String,Condition>> studentMap = new HashMap<>();

    private static final List<String> Conditions = Arrays.asList("Green","Yellow","Red");

    public static class Condition {
        private final String conditionName;

        public Condition(String conditionName) {
            this.conditionName = conditionName;
        }

        public String getConditionName() {return conditionName;}
    }

    public static void registerStudent() {
        String studentId = pushID();

        // 중복된 ID가 있는지 확인
        if (studentMap.containsKey(studentId)) {
            System.out.println("이미 존재하는 ID입니다.");
        } else {
            System.out.print("수강생 이름을 입력하세요: ");
            String studentName = sc.next(); // 수강생 이름 입력

            while(true){
                System.out.println("수강생 컨디션을 입력하세요(Green, Yellow, Red) : ");
                String conditionName = sc.next();
                if (Conditions.contains(conditionName)) {
                    Condition condition = new Condition(conditionName);
                    studentMap.put(studentId, new HashMap<>());
                    studentMap.get(studentId).put(studentName, condition);
                    // 등록 성공 메시지
                    System.out.println("수강생 등록 성공!");
                    System.out.println("수강생 고유번호: " + studentId + ", 이름: " + studentName + ", 상태: " + condition.getConditionName());
                    break;
                }else {
                    System.out.println("올바른 상태를 입력해주세요");
                }
            }
        }
    }

    public static void listStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생 목록:");
            studentMap.forEach((id, studentInfoMap) -> {
                studentInfoMap.forEach((studentName, condition) ->{
                    System.out.println("ID : " + id + ", 이름 : " + studentName + ", 상태 : " + condition.conditionName + ", 선택한 과목명 : " + Subject.getStudentSubjects(id));
                });
            });
        }

    }

    // 상태별 수강생 목록을 조회하기
    public static void conditionList() {
        for (int i = 0; i < Conditions.size(); i++) {
            System.out.print("[" + (i + 1) + ". " + Conditions.get(i) + "], ");
        }
        System.out.println();

        String conditionName;

        while(true) {
            System.out.print("조회하고 싶은 상태의 번호를 입력하세요: ");
            int conditionIndex = sc.nextInt();
            if (conditionIndex >= 1 && conditionIndex <= Conditions.size()) {
                conditionName = Conditions.get(conditionIndex - 1);
                break;
            }
            else {
                System.out.println("올바른 번호를 입력하세요.");
            }
        }

        // 선택한 상태에 해당하는 학생의 이름 가져오기
        List<String> conditionCheck = new ArrayList<>();
        studentMap.forEach((id, studentInfoMap) -> {
            studentInfoMap.forEach((studentName, condition) ->{
                if (condition.getConditionName().equals(conditionName)) {
                    conditionCheck.add(studentName);
                }
            });
        });

        // 학생 이름을 사용하여 ID 가져오기 및 출력하기
        for (String studentName : conditionCheck) {
            String studentId = getStudentId(studentName);
            System.out.println("학생 ID: " + studentId + ", 이름: " + studentName);
        }
    }

    // 학생 이름으로부터 ID를 가져오는 메서드
    public static String getStudentId(String studentName) {
        for (Map.Entry<String, Map<String, Condition>> entry : studentMap.entrySet()) {
            for (Map.Entry<String, Condition> studentInfo : entry.getValue().entrySet()) {
                if (studentInfo.getKey().equals(studentName)) {
                    return entry.getKey();
                }
            }
        }
        return "Unknown";
    }



    public static void displayStudentView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 과목 등록");
            System.out.println("4. 수강생 과목 조회");
            System.out.println("5. 수강생 과목 수정");
            System.out.println("6. 상태별 수강생 조회");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.registerStudent(); // 수강생 등록
                case 2 -> Student.listStudents();  // 수강생 목록 조회
                case 3 -> Subject.manageSubjects(); // 수강생 과목 추가
                case 4 -> Subject.subjectCheck();//수강생 과목 조회
                case 5 -> Subject.subjectEdit();  //수강생 과목 수정
                case 6 -> Student.conditionList(); // 상태별 수강생 조회
                case 7 -> running = false; // 메인 화면으로 돌아가기
                default -> {
                    System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
                    running = false;
                }
            }
        }
    }

    public static boolean isRegistered(String studentId) {
        return studentMap.containsKey(studentId); // 등록 여부 확인
    }

    public static String pushID() {
        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next();
        return studentId;
    }
}
