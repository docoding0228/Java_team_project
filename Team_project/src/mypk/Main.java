package mypk;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        displayMainView();
    }

    public static void displayMainView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 통합 조회 ");
            System.out.println("4. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요... ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.displayStudentView();
                case 2 -> Score.displayScoreView();
                case 3 -> Check.Check();
                case 4 -> running = false;
                default -> {
                    System.out.println("잘못된 입력입니다. 2초 후 되돌아갑니다.");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}