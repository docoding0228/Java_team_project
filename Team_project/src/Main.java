import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map<String, Student> studentMap = new HashMap<>();
        do {
            try {
//                System.out.println("==================================");
//                System.out.println("점수 관리 실행 중...");
//                System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
//                System.out.println("2. 수강생의 과목별 회차 점수 수정");
//                System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
//                System.out.println("4. 메인 화면 이동");
//                System.out.print(" 관리 항목을 선택하세요... ");
                System.out.println("1. 수강생 정보 입력,  2. 회차 및 점수 등록 : ");
                int selectlist = sc.nextInt(); // 내부 메뉴 선택

                if (selectlist == 1) { // 과목별 시험 회차 및 점수 등록
                    // student id 입력
                    System.out.println("수강생 ID 를 입력하세요");
                    String studentId = sc.next();
                    if(studentMap.containsKey(studentId)) {
                        System.out.println("이미 존재하는 ID 입니다");
                        continue;
                    }else{
                        Student student = new Student();
                        student.setStudentName();
                        student.setStudentSubject();
                        studentMap.put(studentId, student);
                    }
                } else if (selectlist == 2) { // 회차 점수 수정
                    System.out.println("해당 기능은 구현 중입니다!");
                }
//                else if (selectlist == 3) { // 특정 과목 회차별 등급 조회
//                    System.out.println("해당 기능은 구현 중입니다!");
//                } else if (selectlist == 4) { // 메인 화면 이동
//                    System.out.println("메인 화면으로 돌아갑니다.");
//                }
                else {
                    System.out.println("유효하지 않은 선택입니다.");
                }

                // nameMap 프린트
                System.out.println("현재 등록된 사람들:");
                for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                    System.out.println("{" + entry.getKey() + ", " + entry.getValue() + "}");
                }



                System.out.println("종료하시려면 'exit'을 입력하세요.");
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
                System.out.println("종료하시려면 'exit'을 입력하세요.");
            }
        } while (!sc.next().equalsIgnoreCase("exit"));
    }

}


