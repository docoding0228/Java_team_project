package mypk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    // 수강생 ID와 이름을 저장하는 Map
    private static Map<String, String> studentMap = new HashMap<>();

    public static void registerStudent() {
        Scanner sc = new Scanner(System.in);

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
}
