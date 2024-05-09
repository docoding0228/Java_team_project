package mypk;

import java.util.*;
import static mypk.Student.pushID;

public class Score {
    public static class ScoreEntry {
        private final int score;
        private final String grade;

        public ScoreEntry(int score, String grade) {
            this.score = score;
            this.grade = grade;
        }

        public int getScore() { return score; }
        public String getGrade() { return grade; }
    }

    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Map<Integer, ScoreEntry>> scoreMap = new HashMap<>();

    public static void displayScoreView() throws InterruptedException {

            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 과목별 회차 점수 수정");
            System.out.println("3. 특정 상태 수강생들의 필수 과목 평균 등급");
            System.out.println("4. 이전으로 돌아가기");
            System.out.print("관리 항목을 선택하세요: ");
            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        add_Subjects_Score(); // 모든 수강 과목에 점수 추가
                        break;
                    case 2:
                        editScoresForSubject(); // 회차별 점수 수정
                        break;
                    case 3 :
                        System.out.println("아직 미구현 기능입니다.");
//                      listAllScoreByCondition();// 특정 상태 수강생들의 필수과목 평균 등급
                        break;
                    case 4 :
                        System.out.println("이전 화면으로 돌아갑니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
                sc.nextLine(); // 잘못된 입력을 버퍼에서 제거
            }

    }

    public static void displayScoreSelect(){

            System.out.println("==================================");
            System.out.println("수강생 정보 조회 중...");
            System.out.println("1. 전체 점수 조회");
            System.out.println("2. 특정 과목 회차 점수 조회");
            System.out.println("3. 이전으로 돌아가기");
            System.out.print("관리 항목을 선택하세요: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> listAllScores();
                case 2 -> listAllScoresBySubject();
                case 3 -> System.out.println("이전 화면으로 돌아갑니다.");
            }

    }

    // 특정 학생의 특정 과목에 대해 특정 회차의 점수를 추가하는 역할
    // studentId : 학생의 고유 ID, subject : 학생이 수강하는 과목, attempt : 회차 score : 점수
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

        String grade = calculateGrade(subject, score);
        scoreMap.putIfAbsent(key, new HashMap<>());
        scoreMap.get(key).put(attempt, new ScoreEntry(score, grade));
    }

    private static String getCategory(String subject) {
        List<String> requiredSubjects = Subject.getRequiredSubjects();
        List<String> electiveSubjects = Subject.getElectiveSubjects();

        if (requiredSubjects.contains(subject)) {
            return "필수 과목";
        } else if (electiveSubjects.contains(subject)) {
            return "선택 과목";
        } else {
            return "알 수 없는 과목";
        }
    }

    private static void add_Subjects_Score() {
        System.out.print("수강생 ID를 입력하세요: ");
        String studentId = sc.next();

        List<String> subjects = Subject.getStudentSubjects(studentId);

        if (subjects.isEmpty()) {
            System.out.println("등록된 수강 과목이 없습니다.");
            return;
        }

        System.out.println("수강 과목 목록: " + subjects);
        int attempt = -1;

        while (true) {
            System.out.print("회차를 입력하세요 (1~10): ");
            attempt = sc.nextInt();

            if (attempt < 1 || attempt > 10) {
                System.out.println("회차는 1~10 사이여야 합니다. 다시 입력하세요.");
            } else {
                boolean attemptcheck = false;

                for (String subject : subjects) {
                    String key = studentId + "-" + subject;
                    if (scoreMap.containsKey(key) && scoreMap.get(key).containsKey(attempt)) {
                        System.out.println("이미 해당 회차에 점수가 입력되어 있습니다.");
                        attemptcheck = true;
                        break;
                    }
                }
                if (!attemptcheck) {
                    break;
                }
            }
        }

        for (String subject : subjects) {
            while (true) {
                String category = getCategory(subject);
                System.out.print(category + " " + subject + "에 대한 점수를 입력하세요 (0~100): ");
                int score = sc.nextInt();

                if (score < 0 || score > 100) {
                    System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다. 다시 입력하세요.");
                } else {
                    addScore(studentId, subject, attempt, score);
                    System.out.println(category + " " + subject + "에 점수가 등록되었습니다.");
                    break;
                }
            }
        }
    }

    public static boolean deleteStudentScores(String studentId) {
        List<String> keyRemove = new ArrayList<>();
        for (String key : scoreMap.keySet()) {
            if (key.startsWith(studentId + "-")) {
                keyRemove.add(key);
            }
        }
        for (String key : keyRemove) {
            scoreMap.remove(key);
        }
        return !keyRemove.isEmpty();
    }

    public static void editScore(String studentId, String subject, int attempt, int score) {
        if (score < 0 || score > 100) {
            System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다.");
            return;
        }

        String key = studentId + "-" + subject;

        if (!scoreMap.containsKey(key)) {
            System.out.println("해당 과목은 미등록 상태입니다.");
            return;
        }

        if (!scoreMap.get(key).containsKey(attempt)) {
            System.out.println("이미 해당 회차는 미등록 상태입니다.");
            return;
        }

        String grade = calculateGrade(subject, score);

        scoreMap.put(key, new HashMap<>());
        scoreMap.get(key).put(attempt, new ScoreEntry(score, grade));
    }

    private static void editScoresForSubject() {
        String studentId = pushID();

        List<String> subjects = Subject.getStudentSubjects(studentId);

        if (subjects.isEmpty()) {
            System.out.println("등록된 수강 과목이 없습니다.");
            return;
        }

        System.out.print("수강중인 과목 목록: ");
        for (int i = 0; i < subjects.size() - 1; i++) {
            System.out.print("[" + (i + 1) + ". " + subjects.get(i) + "], ");
        }
        System.out.println("[" + (subjects.size()) + ". " + subjects.get(subjects.size() - 1) + "]");

        String subjectToEdit;

        while (true) {
            System.out.print("수정하고 싶은 과목의 번호를 입력하세요: ");
            int editSubjectsIndex = sc.nextInt();
            if (editSubjectsIndex >= 1 && editSubjectsIndex <= subjects.size()) {
                subjectToEdit = subjects.get(editSubjectsIndex - 1);
                break;
            }
            else {
                System.out.println("올바른 번호를 입력하세요.");
            }
        }
        String key = studentId + "-" + subjectToEdit;

        int attempt = -1;

        if (scoreMap.containsKey(key)) {
            while (true) {
                System.out.print("회차를 입력하세요 (1~10): ");
                attempt = sc.nextInt();

                if (attempt < 1 || attempt > 10) {
                    System.out.println("회차는 1~10 사이여야 합니다. 다시 입력하세요.");
                } else {
                    boolean attemptcheck = false;
                    if (scoreMap.containsKey(key) && (!scoreMap.get(key).containsKey(attempt))) {
                        System.out.println("해당 회차는 점수 미등록 상태입니다.");
                        attemptcheck = true;
                    }
                    if (!attemptcheck) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("등록되지 않은 과목입니다.");
        }

        while (true) {
            String category = getCategory(subjectToEdit);
            System.out.print(category + " " + subjectToEdit + "에 대한 점수를 입력하세요 (0~100): ");
            int score = sc.nextInt();

            if (score < 0 || score > 100) {
                System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다. 다시 입력하세요.");
            } else {
                editScore(studentId, subjectToEdit, attempt, score);
                System.out.println(category + " " + subjectToEdit + "에 점수가 수정되었습니다.");
                break;
            }
        }
    }

    public static String calculateGrade(String subject, int score) {
        String category = getCategory(subject);

        if (category.equals("필수 과목")) {
            if (score >= 95 && score <= 100) {
                return "A";
            } else if (score >= 90 && score <= 94) {
                return "B";
            } else if (score >= 80 && score <= 89) {
                return "C";
            } else if (score >= 70 && score <= 79) {
                return "D";
            } else if (score >= 60 && score <= 69) {
                return "E";
            } else {
                return "F";
            }
        } else {
            if (score >= 90 && score <= 100) {
                return "A";
            } else if (score >= 80 && score <= 89) {
                return "B";
            } else if (score >= 70 && score <= 79) {
                return "C";
            } else if (score >= 60 && score <= 69) {
                return "D";
            } else if (score >= 50 && score <= 59) {
                return "E";
            } else {
                return "F";
            }
        }
    }

    public static void listAllScores() {
        Map<String, Map<Integer, Map<String, ScoreEntry>>> groupedScores = new HashMap<>();
        for (String key : scoreMap.keySet()) {
            String[] parts = key.split("-");
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
                System.out.println();
            }
        }
    }

    public static void listAllScoresBySubject() {
        String inputId = pushID();
        List<String> allSubjects = Subject.getStudentSubjects(inputId);


        System.out.println("등록된 수강 과목 목록: " + allSubjects);
        System.out.print("조회할 과목을 입력하세요: ");
        String subjectToSearch = sc.next();

        boolean found = false; // 조회된 결과가 있는지 확인하기 위한 변수

        for (String key : scoreMap.keySet()) {
            String[] parts = key.split("-");
            String studentId = parts[0];
            String subject = parts[1];

            if (subject.equals(subjectToSearch)) {
                Map<Integer, ScoreEntry> scores = scoreMap.get(key);
                found = true;

                System.out.println("학생 번호: " + studentId + ", 과목: " + subject);
                System.out.println("1.특정 과목의 회차별 등급 조회");
                System.out.println("2.특정 과목의 평균 등급 조회");

                try {
                    int Subjectchoice = sc.nextInt();

                    switch (Subjectchoice) {
                        case 1:
                            for (int attempt : scores.keySet()) {
                                ScoreEntry scoreEntry = scores.get(attempt);
                                System.out.println("회차: " + attempt + "회차 | 점수: " + scoreEntry.getScore() + " | 등급: " + scoreEntry.getGrade());
                                System.out.println();
                            }
                            break;
                        case 2:
                            int totalScore = 0;
                            int totalAttempts = scores.size();
                            for (ScoreEntry scoreEntry : scores.values()) {
                                totalScore += scoreEntry.getScore();
                            }

                            if (totalAttempts > 0) {
                                double averageScore = (double) totalScore / totalAttempts;
                                String averageGrade = calculateGrade(subjectToSearch, (int) Math.round(averageScore));
                                System.out.println("과목의 평균 등급: " + averageGrade);
                            } else {
                                System.out.println("해당 과목에 대한 등급 정보가 없습니다.");
                            }
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
                    sc.nextLine();
                }
            }
        }
        if (!found) {
            System.out.println("해당 과목에 대한 등급 정보가 없습니다.");
        }
    }
}
