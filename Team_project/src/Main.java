import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Score score = new Score();
        score.setNumberGrade("Math");

        Scanner sc = new Scanner(System.in);
        Map<String, Student> studentMap = new HashMap<>();
        do {
            try {
                System.out.println("1. 수강생 정보 입력,  2. 회차 및 점수 등록 : ,  3. 수강생 정보 검색 : ");
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
                } else if (selectlist == 3) { // 수강생 검색 기능
                    while(true) {
                        // 비어있으면 등록된 것 없으니 ㄷ빠져나가고
                        if(studentMap.isEmpty()){
                            System.out.println("등록된 사용자가 없습니다");
                            break;
                        }

                        System.out.println("수강생 ID 를 입력하세요 (type \"exit\" to quit): ");
                        String studentId = sc.next();

                        if(studentId.equals("exit")) {
                            break;
                        } else if (studentMap.containsKey(studentId)) {
                            for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                                if (studentId.equals(entry.getKey())){
                                    System.out.println("[ 검색된 학생 결과입니다 ]");
                                    System.out.println("ID: " + studentId);
                                    entry.getValue().searchStudent();
                                }
                            }
                            break;
                        } else {
                            System.out.println("존재하지 않는 ID 입니다. 올바른 ID를 입력해주세요");
                            continue;
                        }
                    }
                }
                else {
                    System.out.println("유효하지 않은 선택입니다.");
                }

                System.out.println("종료하시려면 'exit'을 입력하세요.");
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
                System.out.println("종료하시려면 'exit'을 입력하세요.");
            }
        } while (!sc.next().equalsIgnoreCase("exit"));
    }

}


