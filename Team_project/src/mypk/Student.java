package mypk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    private static Scanner sc = new Scanner(System.in);

    // 수강생 ID와 이름을 저장하는 Map
    public static Map<String, Map<String,Condition>> studentMap = new HashMap<>();

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

            System.out.println("수강생 컨디션을 입력하세요(Green, Yellow, Red) : ");
            String conditionName = sc.next();

            Condition condition = new Condition(conditionName);
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
                    System.out.println("ID : " + id + ", 이름 : " + studentName + ", 상태 : " + condition.conditionName + ", 선택한 과목명 : " + Subject.getStudentSubjects(id));
                });
            });
        }

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
        if (Score.deleteStudentScores(studentId)) {
            System.out.println("해당 수강생의 점수 기록이 함께 삭제되었습니다.");
        } else {
            System.out.println("해당 수강생의 점수 기록은 없습니다.");
        }
    }

    // ================ 수강생 삭제로 인한 추가 ================



    public static void displayStudentView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 삭제");
            System.out.println("3. 수강생 목록 조회");
            System.out.println("4. 수강생 과목 추가");
            System.out.println("5. 수강생 과목 수정");
            System.out.println("6. 수강생 과목 조회");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.registerStudent(); // 수강생 등록
                case 2 -> Student.deleteStudent(); // 수강생 삭제
                case 3 -> Student.listStudents(); // 수강생 목록 조회
                case 4 -> Subject.manageSubjects(); // 수강생 과목 추가
                case 5 -> Subject.subjectEdit(); // 수강생 과목 조회
                case 6 -> Subject.subjectCheck(); // 수강색 과목 수정
                case 7 -> running = false; // 메인 화면으로 돌아가기
                default -> {
                    System.out.println("잘못된 입력입니다.");
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
