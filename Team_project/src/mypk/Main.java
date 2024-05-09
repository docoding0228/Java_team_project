package mypk;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        displayMainView(); // 메인 루프 실행
    }
    public static void displayMainView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 프로그램 종료");

            int choice = sc.nextInt();

            switch (choice) {
                    case 1 -> {
                        // 필수 및 선택 과목 정보를 가져옴
                        List<String> subjectlist = Subject.getAllSubjects(); // 전체 과목 리스트도 가져옴
                        // displayStudentView 메서드에 필수 및 선택 과목 정보 전달
                        Student.displayStudentView(subjectlist);
                    }

                case 2 -> running = false; // 점수 관리
                default -> {
                    System.out.println("잘못된 입력입니다. 2초 후 되돌아갑니다.");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}