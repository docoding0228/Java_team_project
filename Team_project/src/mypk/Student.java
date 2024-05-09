package mypk;
import java.util.*;


public class Student {
    private static Scanner sc = new Scanner(System.in);

    // 수강생 ID와 이름을 저장하는 Map
    public static Map<String, Map<String,Condition>> studentMap = new HashMap<>();
    public static Map<String, Map<String, Student.Condition>> getStudentMap() {
        return studentMap;
    }

    private static final List<String> Conditions = Arrays.asList("Green","Yellow","Red");

    static class Condition {
        private String conditionName;

        public Condition() {}

        // 컨디션 정보 가져오기
        public void setExistingCondition(String conditionName) {this.conditionName=conditionName;};

        // 컨디션을 입력 받아서 넣기
        public void setCondition(){
            while(true) {
                System.out.println("상태정보를 입력해주세요 (Green, Yellow, Red) : ");
                String tempNewCondition = sc.next();
                if(tempNewCondition.equals("Green") || tempNewCondition.equals("Yellow") || tempNewCondition.equals("Red")) {
                    this.conditionName = tempNewCondition;
                    break;
                }else{
                    System.out.println("상태정보를 정확하게 입력해주세요.");
                }
            }
        }

        public String getConditionName() {return this.conditionName;}

    }

    public static void registerStudent() {
        String studentId = pushID();

        // 중복된 ID가 있는지 확인
        if (studentMap.containsKey(studentId)) {
            System.out.println("이미 존재하는 ID입니다.");
        } else {
            System.out.print("수강생 이름을 입력하세요: ");
            String studentName = sc.next(); // 수강생 이름 입력
            Condition condition = new Condition();
            condition.setCondition();
            studentMap.put(studentId, new HashMap<>());
            studentMap.get(studentId).put(studentName, condition);

            // 수강생 등록
            //studentMap.put(studentId, studentName);

            // 등록 성공 메시지
            System.out.println("수강생 등록 성공!");
            System.out.println("수강생 고유번호: " + studentId + ", 이름: " + studentName + ", 상태: " + condition.getConditionName());
        }
    }

    public static void listStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생 목록:");
            studentMap.forEach((id, studentInfoMap) -> {
                studentInfoMap.forEach((studentName, condition) ->{
                    System.out.println("ID : " + id + ", 이름 : " + studentName + ", 상태 : " + condition.conditionName + ", 수강중인 과목명 : " + Subject.getStudentSubjects(id));
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
        System.out.println("컨디션이 " + conditionName + " 인 학생 목록입니다.");
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
    // ================ 수강생 삭제로 인한 추가 ================
    // 수강생 정보 삭제 및 관련 기록 삭제

    public static void deleteStudent() {
        String studentId = pushID(); // ID 입력

        // 해당 학생 ID가 등록되어 있는지 확인
        if (!studentMap.containsKey(studentId)) {
            System.out.println("해당 ID는 등록되지 않았습니다.");
            return;
        }

        // 학생 정보 삭제
        studentMap.remove(studentId); // 학생 ID 제거
        System.out.println("수강생 ID " + studentId + "이 삭제되었습니다.");

        // 수강생의 과목 정보도 삭제
        if (Subject.deleteStudentSubjects(studentId)) {
            System.out.println("해당 수강생의 과목 정보가 함께 삭제되었습니다.");
        } else {
            System.out.println("해당 수강생의 과목 정보는 없습니다.");
        }

        // 수강생의 점수 기록도 삭제
        if (mypk.Score.deleteStudentScores(studentId)) {
            System.out.println("해당 수강생의 점수 기록이 함께 삭제되었습니다.");
        } else {
            System.out.println("해당 수강생의 점수 기록은 없습니다.");
        }
    }

    // ================ 수강생 삭제로 인한 추가 ================

    public static void displayStudentView() throws InterruptedException {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 수정 및 삭제");
            System.out.println("3. 수강생 과목 등록");
            System.out.println("4. 수강생 과목 수정");
            System.out.println("5. 이전으로 돌아가기");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.registerStudent(); // 수강생 등록
                case 2 -> Student.editStudent();
                case 3 -> Subject.manageSubjects();
                case 4 -> Subject.subjectEdit();
                case 5 -> System.out.println(" 이전 화면으로 돌아갑니다." );
                default -> System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
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

    public static void searchAll()
    {
        {
            System.out.println("통합 조회 화면입니다. 조회하고 싶은 내용을 선택하세요.");
            System.out.println("1. 전체 수강생 조회");
            System.out.println("2. 수강생 과목 조회");
            System.out.println("3. 전체 점수 조회");
            System.out.println("4. 과목별 평균 점수 조회");
            System.out.println("5. 상태별 수강생 조회");
            System.out.println("6. 이전으로 돌아가기");
            System.out.print(" 관리 항목을 선택하세요... ");

            // 상태별 수강생 조회
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.listStudents();
                case 2 -> Subject.subjectCheck();
                case 3 -> Score.listAllScores();
                case 4 -> Score.listAllScoresBySubject();
                case 5 -> Student.conditionList();
                case 6 -> System.out.println(" 이전 화면으로 돌아갑니다.");
                default -> {
                    System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
                }
            }
        }
    }

    // ===================== 수강생 이름 수정 =================================

    public static void editStudentName(String studentId) {
        if (!studentMap.containsKey(studentId)) {
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        }

        // 수강생 이름 수정
        Condition tempCondition = new Condition();
        Set<String> keys = studentMap.get(studentId).keySet();
        for (String key : keys) {
            tempCondition.setExistingCondition(studentMap.get(studentId).get(key).getConditionName());
        }
        System.out.print("현재 입력된 이름은: " + " 입니다. ");
        System.out.print("새로운 이름을 입력하세요: ");
        String studentNewName = sc.next();
        Map<String, Condition> tempMap = new HashMap<>();
        tempMap.put(studentNewName, tempCondition);
        studentMap.put(studentId, tempMap);

        System.out.println("수강생 이름이 수정되었습니다.");
    }
    // ===================== 수강생 이름 수정 =================================


    public static void editStudentCondition(String studentId) {
        if (!studentMap.containsKey(studentId)) {
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        }

        // 수강생 상태 수정
        for (String key : studentMap.get(studentId).keySet()) {
            studentMap.get(studentId).get(key).setCondition();
        }
        System.out.println("수강생 상태가 수정되었습니다.");
    }

    public static void editStudent() {
        String studentId = pushID();

        if (!studentMap.containsKey(studentId)) {
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("수정하고 싶은 내용을 선택하세요.");
            System.out.println("1. 수강생 이름 수정");
            System.out.println("2. 수강생 상태 수정");
            System.out.println("3. 수강생 삭제");
            System.out.println("4. 이전으로 돌아가기");
            System.out.print(" 관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> editStudentName(studentId);
                case 2 -> editStudentCondition(studentId);
                case 3 -> deleteStudent();
                case 4 -> running = false;
                default -> {
                    System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
                    running = false;
                }
            }
        }
    }
}

