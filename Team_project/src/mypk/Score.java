package mypk;

import java.util.*;
import static mypk.Student.pushID;

public class Score {
    /**
     * 스코어를 위한 이너클래스로 점수에 따른 letter grade(A,B,C...) 를 저장하기 위해 사용된다.
     */
    public static class ScoreEntry {
        private final int score;
        private final String grade;

        /**
         *
         * @param score 점수 값을 입력한대로 학생의 점수에 저장된다.
         * @param grade Letter grade 를 입력한대로 학생의 Letter grade 로 저장된다.
         */
        public ScoreEntry(int score, String grade) {
            this.score = score;
            this.grade = grade;
        }

        /**
         *
         * @return 학생의 점수를 리턴해준다
         */
        public int getScore() { return score; }

        /**
         *
         * @return 학생의 letter grade 를 리턴해준다.
         */
        public String getGrade() { return grade; }
    }

    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Map<Integer, ScoreEntry>> scoreMap = new HashMap<>();

    /**
     * 점수관리 창으로써 선택지에 따라 필요한 메서드를 불러온다.
     * @throws InterruptedException 숫자가 아닌 다른것을 입력했을때 발생하는 오류를 처리하기 위해 사용된다.
     */
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

    /**
     * 점수 조회 관련 선택 창으로 선택지에 따른 필요한 메서드를 불러온다.
     */
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

    /**
     * 학생의 특정 회차에 점수를 저장하기 위한 메서드이다.
     * @param studentId studentId 를 받아와서 studentMap 에서 특정 학생으로 접근하기 위한 key 로 사용된다.
     * @param subject scoreMap 에서 특정 과목을 저장하기 위한 key 를 만들기 위해 사용된다. "studentId + "-" + subject"
     * @param attempt 시험회차를 지정하는데 사용된다.
     * @param score 실질적인 점수값을 저장하는데 사용된다.
     */
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

    /**
     *
     * @param subject 해당 과목이 필수인지 선택인지 확인되는 값이다.
     * @return 해당 과목이 필수과목인지 선택과목인지 String 값으로 리턴한다.
     */
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

    /**
     * 선택한 회차에 모든 과목들에 점수를 등록하는 기능이다.
     */
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

    /**
     * 점수 삭제 기능
     * @param studentId studentId 를 key 값으로 사용하여 특정 학생을 불러오기 위해 사용된다.
     * @return 삭제가 되었는지 확인하기 위한 boolean 값이 리턴된다.
     */
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

    /**
     * 특정 점수를 수정하는 기능이다.
     * @param studentId 특정 학생을 불러오기 위해 필요한 key 값으로 사용된다.
     * @param subject 특정 과목을 불러오기 위한 key 를 만드는데 사용된다. "key = studentId + "-" + subject"
     * @param attempt 수정하려는 회차를 고르기 위해 사용된다.
     * @param score 새롭게 입력하려는 점수이다.
     */
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

    /**
     * 점수 수정을 위한 메서드로 내부에서 editScore() 메서드를 호출하여 점수를 수정한다.
     */
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

    /**
     * 점수에 따른 Letter grade 를 계산하는 메서드이다.
     * @param subject 과목이 필수인지 선택인지 분리하기 위해 사용된다.
     * @param score 점수를 입력받아 letter grade 로 변환한다.
     * @return 점수에 따른 letter grade 값을 반환한다.
     */
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

    /**
     * 등록된 모든 점수를 출력하는 메서드이다.
     */
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

    /**
     * 특정 과목의 평균 등급 및 특정 과목의 회차별 점수를 조회하기 위해 사용된다.
     */
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
