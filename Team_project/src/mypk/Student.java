package mypk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    // 수강생 ID와 이름을 저장하는 Map
    // studentMap이라는 이름의 HashMap 객체를 생성
    // 수강생의 ID를 키로, 수강생의 이름을 값으로 저장하는 용도
    private static Map<String, String> studentMap = new HashMap<>();

    // 수강생 등록
    public static void registerStudent() {
        Scanner sc = new Scanner(System.in);

        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next(); // 수강생 ID 입력

        // 중복된 ID가 있는지 확인
        // studentMap에 주어진 studentId가 키로 존재하는지 확인
        // containsKey() 메서드는 맵(Map)에서 특정 키가 존재하는지 여부를 boolean 타입으로 반환

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
    // 수강생 목록 조회
    public static void listStudents() {
        // isEmpty() 메서드는 studentMap에 아무런 키-값 쌍이 없다면 true, 그렇지 않다면 false를 반환
        if (studentMap.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생 목록:");
            // studentMap의 모든 키-값 쌍에 대해 주어진 람다 표현식((id, name))을 적용
            // forEach() 메서드는 맵의 모든 항목을 순회하면서 람다 표현식으로 실행
            studentMap.forEach((id, name) -> System.out.println("ID: " + id + ", 이름: " + name));
        }

    }
    // 수강생 ID가 studentMap에 등록되어 있는지 확인하고, 그 결과를 boolean 타입으로 반환
    // Student.java에선 수강생 등록 여부를 확인할 때 사용되지만
    // Subject.java에서 수강생에게 과목을 추가할 때, 해당 수강생이 이미 등록된 수강생인지 확인하는데 isRegistered() 메서드를 사용
    public static boolean isRegistered(String studentId) {
        return studentMap.containsKey(studentId); // 등록 여부 확인
    }
}
