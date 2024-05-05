package mypk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    private static Scanner sc = new Scanner(System.in);
    // 수강생 ID와 이름을 저장하는 Map
    private static Map<String, String> studentMap = new HashMap<>();

    public static void registerStudent() {
        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next(); // 수강생 ID 입력

        // 중복된 ID가 있는지 확인
        if (studentMap.containsKey(studentId)) {
            System.out.println("이미 존재하는 ID입니다.");
        } else {
            System.out.print("수강생 이름을 입력하세요: ");
            String studentName = sc.next(); // 수강생 이름 입력

            // 수강생 등록
            studentMap.put(studentId, studentName);

            // 등록 성공 메시지
            System.out.println("수강생 등록 성공!");
            System.out.println("수강생 고유번호: " + studentId + ", 이름: " + studentName);
        }
    }

    public static void listStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생 목록:");
            studentMap.forEach((id, name) -> System.out.println("ID: " + id + ", 이름: " + name));
        }

    }

    public static void displayStudentView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 과목 등록");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.registerStudent(); // 수강생 등록
                case 2 -> Student.listStudents();  // 수강생 목록 조회
                case 3 -> Subject.manageSubjects(); // 수강생 과목 추가
                case 4 -> running = false; // 메인 화면으로 돌아가기
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
}
//public static String pushID() {
//    System.out.print("수강생 ID를 입력하세요: ");
//    String studentId = sc.next();
//    return studentId;
//}
