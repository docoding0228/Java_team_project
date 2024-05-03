package mypk;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        displayMainView(); // 메인 루프 실행
    }

    private static void displayMainView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> running = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다. 2초 후 되돌아갑니다.");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
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

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 과목별 회차 점수 수정");
            System.out.println("3. 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요... ");

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