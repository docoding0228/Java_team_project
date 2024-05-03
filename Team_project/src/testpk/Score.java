package testpk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Score {
    private String subject;
    private String scoreRank;
    private String episode;
    private String rank;
    Scanner sc = new Scanner(System.in);

    public Score() {}

    // 필수 과목인지 확인하는 메소드
    public boolean isMandatorySubject(String subject) {
        List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
        return mandatorySubjects.contains(subject);
    }

    // 과목별로 시험 회차와 점수 설정
    public void setNumberGrade(String subject) {
        Map<String, ArrayList<String>> gradeMap = new HashMap<>();
        ArrayList<String> gradeSaved = new ArrayList<>();
        this.subject = subject;

        // 시험 회차 유효성 검사 (1~10 사이)
        int episodeIndex = -1;
        while (episodeIndex < 1 || episodeIndex > 10) {
            System.out.print("시험 회차를 입력하세요 (1 ~ 10): ");
            episodeIndex = sc.nextInt();
            if (episodeIndex < 1 || episodeIndex > 10) {
                System.out.println("유효하지 않은 회차입니다. 1에서 10 사이의 수를 입력하세요.");
            }
        }
        gradeSaved.add(String.valueOf(episodeIndex));

        // 점수 유효성 검사 (0~100 사이)
        int score = -1;
        while (score < 0 || score > 100) {
            System.out.print("점수를 입력하세요 (0 ~ 100): ");
            score = sc.nextInt();
            if (score < 0 || score > 100) {
                System.out.println("유효하지 않은 점수입니다. 0에서 100 사이의 값을 입력하세요.");
            }
        }
        gradeSaved.add(String.valueOf(score));

        // 등급 결정
        boolean isMandatory = isMandatorySubject(subject);
        gradeSaved.add(getLetterGrade(score, isMandatory));

        gradeMap.put(subject, gradeSaved);

        System.out.println("과목: " + subject);
        System.out.println("회차 및 점수 정보: " + gradeSaved);
    }

    // 점수에 따른 등급 반환
    public String getLetterGrade(int score, boolean isMandatory) {
        if (isMandatory) {
            switch (score / 10) {
                case 10:
                case 9:
                    return score >= 95 ? "A" : "B";
                case 8:
                    return "C";
                case 7:
                    return "D";
                case 6:
                    return "F";
                default:
                    return "N";  // 60점 미만
            }
        } else {
            switch (score / 10) {
                case 10:
                    return "A";
                case 9:
                    return "B";
                case 8:
                    return "C";
                case 7:
                    return "D";
                case 6:
                    return "F";
                default:
                    return "N";
            }
        }
    }
}
