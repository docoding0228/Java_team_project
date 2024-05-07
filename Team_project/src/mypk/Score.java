package mypk;

import java.util.Scanner;

public class Score {
    private Student student;
    private Subject subject;
    private int score;

    private static Scanner sc = new Scanner(System.in);

    public Score(Student student, Subject subject, int score) {
        this.student = student;
        this.subject = subject;
        this.score = score;
    }

    // Getter와 Setter
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{student=" + student + ", subject=" + subject + ", score=" + score + "}";
    }

    public static void displayScoreView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.print("점수 관리를 실행 하시겠습니까? (YES/NO) : ");
            String settingScore = sc.next();
            if("no".equals(settingScore)) {
                break;
            }
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 과목별 회차 점수 수정");
            System.out.println("3. 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");

            // 수강생 번호 입력하고, 학생 - 수강생 일치하는 고유번호
            // 선택, 필수 if else
            // 점수 과목 고유번호 1.회차, 2.등급
            // 점수 입력하는거, 학생 - 과목 목록 -

            int input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    System.out.println("과목별 시험 회차 및 점수 등록 기능 호출");
                    // 과목별 점수 등록 기능 호출
                }
                case 2 -> {
                    System.out.println("과목별 회차 점수 수정 기능 호출");
                    // 점수 수정 기능 호출
                }
                case 3 -> {
                    System.out.println("특정 과목 회차별 등급 조회 기능 호출");
                    // 특정 과목 등급 조회 기능 호출
                }
                case 4 -> flag = false; // 메인 화면으로 돌아가기
                default -> {
                    System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
                    flag = false;
                }
            }
        }
    }
}