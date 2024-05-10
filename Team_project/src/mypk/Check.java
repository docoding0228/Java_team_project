package mypk;

import java.util.Scanner;


public class Check {
    private static Scanner sc = new Scanner(System.in);

    /**
     * 선택지에 따른 필요한 메서드를 불러온다.
     * @throws InterruptedException 예외 발생시 메인 화면으로 이동
     */
    public static void Check() throws InterruptedException {
            System.out.println("==================================");
            System.out.println("수강생 정보 조회 중...");
            System.out.println("1. 수강생 정보 조회");
            System.out.println("2. 수강생 과목 조회");
            System.out.println("3. 점수 조회");
            System.out.println("4. 상태별 수강생 조회");
            System.out.println("5. 이전으로 돌아가기");

            System.out.print("관리 항목을 선택하세요... ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Student.listStudents();
                case 2 -> Subject.subjectCheck();
                case 3 -> Score.displayScoreSelect();
                case 4 -> Student.conditionList();
                case 5 -> System.out.println("이전 화면으로 돌아갑니다.");

                default -> {
                    System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
                }

            }

    }
}
