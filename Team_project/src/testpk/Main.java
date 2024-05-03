package testpk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Student> studentMap = new HashMap<>();

        while (true) {
            try {
                System.out.println("1. 수강생 정보 입력, 2. 회차 및 점수 등록, 3. 수강생 정보 검색:");
                int selectList = sc.nextInt();  // 메뉴 선택

                if (selectList == 1) {  // 수강생 정보 입력
                    System.out.print("수강생 ID 를 입력하세요: ");
                    String studentId = sc.next();

                    if (studentMap.containsKey(studentId)) {
                        System.out.println("이미 존재하는 ID입니다.");
                    } else {
                        Student student = new Student();
                        student.setStudentName();  // 이름 설정
                        student.setStudentSubject();  // 과목 설정

                        studentMap.put(studentId, student);  // 학생 추가

                        // 선택이 완료된 후, 수강생 정보 출력
                        List<String> subjectList = student.getSubjectList();
                        System.out.println("수강생 고유번호: " + studentId);
                        System.out.println("선택된 과목 목록: " + subjectList);
                    }
                }
                else if (selectList == 2) {  // 회차 및 점수 등록

                    System.out.print("수강생 ID 를 입력하세요: ");
                    String studentId = sc.next();

                    if (!studentMap.containsKey(studentId)) {
                        System.out.println("존재하지 않는 ID입니다.");
                        continue;
                    }

                    Student student = studentMap.get(studentId);
                    List<String> subjects = student.getSubjectList();  // 수강 목록 가져오기

                    // 수강 목록을 보여주고 각 과목에 대해 점수 입력 및 등급 계산
                    System.out.println("수강생 " + studentId + "의 수강 목록: " + subjects);

                    for (String subject : subjects) {
                        System.out.print("과목 " + subject + "의 점수를 입력하세요: ");
                        int scoreValue = sc.nextInt();

                        while (scoreValue < 0 || scoreValue > 100) {
                            System.out.println("유효하지 않은 점수입니다. 0~100 사이의 값을 입력하세요.");
                            scoreValue = sc.nextInt();
                        }

                        // 등급 결정
                        Score scoreObj = new Score();  // testpk.Score 객체 생성
                        scoreObj.setNumberGrade(subject);  // 회차 및 점수 설정
                        boolean isMandatory = scoreObj.isMandatorySubject(subject);
                        String grade = scoreObj.getLetterGrade(scoreValue, isMandatory);

                        // 점수 및 등급 출력
                        System.out.println("과목 " + subject + ", 점수: " + scoreValue + ", 등급: " + grade);
                    }
                }
                else if (selectList == 3) {  // 수강생 정보 검색
                    System.out.print("수강생 ID 를 입력하세요: ");
                    String studentId = sc.next();

                    if (!studentMap.containsKey(studentId)) {
                        System.out.println("존재하지 않는 ID입니다.");
                    } else {
                        Student student = studentMap.get(studentId);
                        student.searchStudent();  // 수강생 정보 출력
                    }
                }
                else {
                    System.out.println("유효하지 않은 선택입니다.");
                }

                System.out.println("종료하시려면 'exit'을 입력하세요.");
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }

            String exitCommand = sc.next();
            if (exitCommand.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
