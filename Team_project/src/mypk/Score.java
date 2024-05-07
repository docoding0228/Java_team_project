package mypk;

import java.util.*;

public class Score {
    private static final Scanner sc = new Scanner(System.in);

    // 학생의 수강 과목을 키로 하고, 회차별 점수와 등급을 값으로 가지는 맵
    private static final Map<String, Map<Integer, ScoreEntry>> scoreMap = new HashMap<>();

    // 학생-과목 구조의 클래스 정의
    public static class ScoreEntry {
        private final int score;
        private final String grade;

        public ScoreEntry(int score, String grade) {
            this.score = score;
            this.grade = grade;
        }

        public int getScore() {
            return score;
        }

        public String getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return "Score: " + score + ", Grade: " + grade;
        }
    }

    // 점수 추가 기능
    public static void addScore(String studentId, String subject, int attempt, int score) {
        if (score < 0 || score > 100) {
            System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다.");
            return;
        }

        String key = studentId + "-" + subject;

        if (scoreMap.containsKey(key) && scoreMap.get(key).containsKey(attempt)) {
            System.out.println("이미 해당 회차에 점수가 입력되어 있습니다.");
            return;
        }

        String grade = calculateGrade(subject, score); // 등급 계산

        scoreMap.putIfAbsent(key, new HashMap<>());
        scoreMap.get(key).put(attempt, new ScoreEntry(score, grade));
    }

    // 과목이 필수과목인지 선택과목인지 구분
    private static String getCategory(String subject) {
        List<String> requiredSubjects = Subject.getRequiredSubjects(); // 필수 과목 목록
        List<String> electiveSubjects = Subject.getElectiveSubjects(); // 선택 과목 목록

        if (requiredSubjects.contains(subject)) {
            return "필수 과목";
        } else if (electiveSubjects.contains(subject)) {
            return "선택 과목";
        } else {
            return "알 수 없는 과목"; // 예외 처리
        }
    }


    // 모든 수강 과목에 점수 추가
    private static void add_Subjects_Score() {
        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next();

        List<String> subjects = Subject.getStudentSubjects(studentId);

        if (subjects == null || subjects.isEmpty()) {
            System.out.println("등록된 수강 과목이 없습니다.");
            return;
        }

        System.out.println("수강 과목 목록: " + subjects);
        int attempt = -1;

        while (true) { // 회차 입력이 유효할 때까지 반복
            System.out.print("회차를 입력하세요 (1~10): ");
            attempt = sc.nextInt();

            if (attempt < 1 || attempt > 10) {
                System.out.println("회차는 1~10 사이여야 합니다. 다시 입력하세요.");
            } else {
                boolean isAttemptUsed = false;

                for (String subject : subjects) {
                    String key = studentId + "-" + subject;
                    if (scoreMap.containsKey(key) && scoreMap.get(key).containsKey(attempt)) {
                        System.out.println("이미 해당 회차에 점수가 입력되어 있습니다.");
                        isAttemptUsed = true;
                        break;
                    }
                }

                if (!isAttemptUsed) {
                    break; // 유효한 회차 입력이 확인된 경우
                }
            }
        }

        // 유효한 회차 입력 후 점수 추가
        for (String subject : subjects) {
            while (true) { // 점수가 유효할 때까지 반복
                String category = getCategory(subject);
                System.out.print(category + " " + subject + "에 대한 점수를 입력하세요 (0~100): ");
                int score = sc.nextInt();

                if (score < 0 || score > 100) {
                    System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다. 다시 입력하세요.");
                } else {
                    addScore(studentId, subject, attempt, score);
                    System.out.println(category + " " + subject + "에 점수가 등록되었습니다.");
                    break; // 유효한 점수 입력 후 루프 종료
                }
            }
        }
    }

    // 점수에 따른 등급을 계산
    public static String calculateGrade(String subject, int score) {
        String category = getCategory(subject);

        if (category.equals("필수 과목")) {
            if (score >= 95) {
                return "A";
            } else if (score >= 90) {
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else {
                return "F";
            }
        } else { // 선택 과목
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    // 전체 회차별 점수 및 등급 조회
    public static void listAllScores() {
        Map<String, Map<Integer, Map<String, ScoreEntry>>> groupedScores = new HashMap<>();

        for (String key : scoreMap.keySet()) {
            String[] parts = key.split("-"); // 학생 번호와 과목을 분리
            String studentId = parts[0];
            String subject = parts[1];

            Map<Integer, ScoreEntry> scores = scoreMap.get(key);

            for (Map.Entry<Integer, ScoreEntry> entry : scores.entrySet()) {
                int attempt = entry.getKey();
                ScoreEntry scoreEntry = entry.getValue();

                groupedScores.putIfAbsent(studentId, new HashMap<>());
                groupedScores.get(studentId).putIfAbsent(attempt, new HashMap<>());
                groupedScores.get(studentId).get(attempt).put(subject, scoreEntry);
            }
        }

        // 출력 형식 변경 및 정렬
        for (String studentId : groupedScores.keySet()) {
            Map<Integer, Map<String, ScoreEntry>> attempts = groupedScores.get(studentId);

            for (int attempt : attempts.keySet()) {
                System.out.println("학생 번호: " + studentId + ", 회차: " + attempt + "회차");

                Map<String, ScoreEntry> subjects = attempts.get(attempt);

                List<String> requiredSubjects = new ArrayList<>();
                List<String> electiveSubjects = new ArrayList<>();

                for (String subject : subjects.keySet()) {
                    String category = getCategory(subject);
                    if (category.equals("필수 과목")) {
                        requiredSubjects.add(subject);
                    } else {
                        electiveSubjects.add(subject);
                    }
                }

                requiredSubjects.sort(Comparator.naturalOrder());
                electiveSubjects.sort(Comparator.naturalOrder());

                System.out.println("================ 필수 과목 ================");
                for (String subject : requiredSubjects) {
                    ScoreEntry scoreEntry = subjects.get(subject);
                    System.out.println("과목: " + subject + " | 점수: " + scoreEntry.getScore() + " | 등급: " + scoreEntry.getGrade());
                }

                System.out.println("================ 선택 과목 ================");
                for (String subject : electiveSubjects) {
                    ScoreEntry scoreEntry = subjects.get(subject);
                    System.out.println("과목: " + subject + " | 점수: " + scoreEntry.getScore() + " | 등급: " + scoreEntry.getGrade());
                }

                System.out.println(); // 회차 구분을 위해 빈 줄 추가
            }
        }
    }

    // 점수 관리 메뉴
    public static void displayScoreView() throws InterruptedException {
        boolean running = true;
        while (running) {
            System.out.print("점수 관리를 실행하시겠습니까? (YES/NO): ");
            String answer = sc.next();

            if ("no".equalsIgnoreCase(answer)) {
                break;
            }

            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 과목별 회차 점수 수정");
            System.out.println("3. 전체 회차별 점수 및 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요: ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        add_Subjects_Score(); // 모든 수강 과목에 점수 추가
                        break;
                    case 2:
                        //editScoresForSubject(); // 회차별 점수 수정
                        break;
                    case 3:
                        listAllScores(); // 전체 회차별 점수 및 등급 조회
                        break;
                    case 4:
                        running = false; // 메인 화면 이동
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
                sc.nextLine(); // 잘못된 입력을 버퍼에서 제거
            }
        }
    }
}
