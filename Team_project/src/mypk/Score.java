package mypk;

import java.util.*;

public class Score {
    private static final Scanner sc = new Scanner(System.in);

    // 학생의 수강 과목을 키로 하고, 회차별 점수와 등급을 값으로 가지는 맵
    private static final Map<String, Map<Integer, ScoreEntry>> scoreMap = new HashMap<>();
    // String : 학생 ID와 과목
    // Integer : 회차
    // ScoreEntry : 점수와 등급

    // 점수와 등급을 나타내는 클래스
    public static class ScoreEntry {
        private final int score;
        private final String grade;

        // 점수와 등급을 초기화
        public ScoreEntry(int score, String grade) {
            this.score = score;
            this.grade = grade;
        }

        // 점수와 등급을 반환 getScore, getGrade
        public int getScore() { return score; }
        public String getGrade() { return grade; }
    }

    // 특정 학생의 특정 과목에 대해 특정 회차의 점수를 추가하는 역할
    // studentId : 학생의 고유 ID, subject : 학생이 수강하는 과목, attempt : 회차 score : 점수
    public static void addScore(String studentId, String subject, int attempt, int score) {
        if (score < 0 || score > 100) {
            System.out.println("음수이거나 100점이 넘는 점수는 입력받을 수 없습니다.");
            return;
        }

        // studentId(학생 ID)와 subject(과목 이름)를 연결하여 하나의 키를 생성
        // 중간에 "-" 사용하는 이유 : 문자열을 결합할 때 구분자를 사용하여 명확하게 구분하고 읽기 쉽게 만들기 위해 "-" 같은 특수 문자를 사용
        // 구분자가 없을경우 ID와 과목이름이 직접 연결되기에, 데이터 중복 및 오류발생 가능성이 있음.
        String key = studentId + "-" + subject;

        // 해당 회차에 이미 점수가 있는지 확인
        if (scoreMap.containsKey(key) && scoreMap.get(key).containsKey(attempt)) {
            System.out.println("이미 해당 회차에 점수가 입력되어 있습니다.");
            return;
        }

        // subject(과목)과 score(점수)를 기반으로 등급을 계산
        String grade = calculateGrade(subject, score);

        // key가 scoreMap에 없을 경우, 빈 맵(new HashMap<>)을 추가합니다.
        // key가 scoreMap에 있을 경우, 맵에는 저장 하지 않는다.
        // NullPointerException 방지하기 위해서 선언.
        // 자바 초기화를 하지 않으면, 무조건 Null 이다.
        // HashMap 같은 객체와 같은 타입은 선언이 없을시 null 이다.
        // scoreMap.putIfAbsent(key, new HashMap<>()); 에서 에러가 없어야,
        // scoreMap.get(key).put(attempt, new ScoreEntry(score, grade)); 작동되기 때문이다.

        scoreMap.putIfAbsent(key, new HashMap<>());
        // 최종 : 맵.put 맵.get -> 잘못 입력(null, 잘못된 값) 방지.

        // key로 scoreMap에서 값을 가져온 후, 해당 맵에 attempt(회차)에 대해 ScoreEntry 객체를 추가
        // ScoreEntry는 점수와 등급을 저장하는 객체
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
        // 점수 등급 계산: calculateGrade 메서드에서 getCategory(subject)를
        // 사용하여 과목이 필수인지 선택인지를 구분하고, 그에 따라 점수 등급을 계산

        // 점수 추가 시 출력 메시지: add_Subjects_Score 메서드에서 getCategory(subject)를 사용하여 과목의 카테고리를 알아내고,
        // 그에 따라 출력 메시지(예: "필수 과목", "선택 과목")를 결정

        // 전체 회차별 점수 및 등급 조회: listAllScores 메서드에서도 각 과목이 필수인지 선택인지 구분하여 출력할 때 사용
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
        } else { // 선택 과목
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

    // 전체 회차별 점수 및 등급 조회
    public static void listAllScores() {
        Map<String, Map<Integer, Map<String, ScoreEntry>>> groupedScores = new HashMap<>();
        // 학생 ID, 회차, 과목에 따라 점수와 등급을 저장하기 위한 맵 - groupedScores 초기화

        // scoreMap의 모든 키를 순회하면서 각 키에 대한 데이터를 가져온다.
        // keySet() = 주어진 맵의 모든 키를 Set 형태로 반환하는 메서드
        // scoreMap에 있는 모든 키를 Set 형태로 얻을 수 있다.
        for (String key : scoreMap.keySet()) {

            // key를 "-"로 분리하여 studentId(학생 ID)와 subject(과목 이름)를 추출
            // 다시 분리하는 이유 :
            // studentId는 학생별로 데이터를 그룹화하거나 학생 정보를 조회하는데 사용
            // subject는 과목별로 작업을 수행하거나 과목 정보를 가져오는 데 사용

            // 학생 ID와 과목 분리: 예를 들어, key가 "123-Math"라면,
            // split("-")을 사용하면 parts[0]에는 "123"이, parts[1]에는 "Math"가 저장
            // 최종 : 여러 정보가 연결되어 있는 경우 각 부분을 쉽게 추출
            String[] parts = key.split("-"); // 학생 번호와 과목을 분리
            String studentId = parts[0];
            String subject = parts[1];

            // key로 scoreMap에서 회차별 점수 맵을 가져옴. 해당 맵은 Integer(회차)와 ScoreEntry(점수와 등급)로 구성
            Map<Integer, ScoreEntry> scores = scoreMap.get(key);

            // scores의 모든 회차와 점수-등급 엔트리를 순회하면서 데이터를 조회.
            // Map.Entry 를 사용함으로써, Map.Entry의 집합을 얻고, 이를 기반으로 맵의 모든 키-값 쌍을 순회
            // Map.Entry를 사용하면 getKey() 메서드를 통해 키에 접근하고, getValue() 메서드를 통해 값에 접근
            // 최종 : 맵의 특정 엔트리에 접근하거나, 키와 값을 추출
            for (Map.Entry<Integer, ScoreEntry> entry : scores.entrySet()) {

                // 각 회차의 점수와 등급을 변수에 저장.
                int attempt = entry.getKey();
                ScoreEntry scoreEntry = entry.getValue();

                // studentId(학생 ID) 키가 존재하지 않을 때 빈 HashMap을 추가합니다
                groupedScores.putIfAbsent(studentId, new HashMap<>()); // 학생 ID별 맵 추가

                // attempt(회차) 키가 존재하지 않을 때 빈 HashMap을 추가합니다
                groupedScores.get(studentId).putIfAbsent(attempt, new HashMap<>()); // 회차별 맵 추가

                // 학생과 특정 회차 데이터를 가져옴 -> 학생 ID와 회차별로 과목별 점수와 등급을 저장하는 역할
                groupedScores.get(studentId).get(attempt).put(subject, scoreEntry);
            }
        }

        // 출력 형식 변경 및 정렬
        // 학생 ID별로 데이터(회차별 과목별 점수와 등급)를 저장
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

    public static void listAllScoresBySubject() {
        List<String> allSubjects = new ArrayList<>();
        for (String key : scoreMap.keySet()) {
            String[] parts = key.split("-"); // 학생 번호와 과목을 분리
            String subject = parts[1];
            if (!allSubjects.contains(subject)) {
                allSubjects.add(subject);
            }
        }
        System.out.println("등록된 수강 과목 목록: " + allSubjects);
        // 조회할 과목을 입력받음
        System.out.print("조회할 과목을 입력하세요: ");
        String subjectToSearch = sc.next();

        boolean found = false; // 조회된 결과가 있는지 확인하기 위한 변수

        // 입력한 과목에 대한 등급을 조회
        for (String key : scoreMap.keySet()) {
            String[] parts = key.split("-"); // 학생 번호와 과목을 분리
            String studentId = parts[0];
            String subject = parts[1];

            if (subject.equals(subjectToSearch)) {
                Map<Integer, ScoreEntry> scores = scoreMap.get(key);
                found = true; // 조회된 결과가 있다고 표시
                System.out.println("학생 번호: " + studentId + ", 과목: " + subject);

                for (int attempt : scores.keySet()) {
                    ScoreEntry scoreEntry = scores.get(attempt);
                    System.out.println("회차: " + attempt + "회차 | 점수: " + scoreEntry.getScore() + " | 등급: " + scoreEntry.getGrade());
                }
                System.out.println(); // 과목별 회차 구분을 위해 빈 줄 추가
            }
        }

        // 조회된 결과가 없는 경우 메시지 출력
        if (!found) {
            System.out.println("해당 과목에 대한 등급 정보가 없습니다.");
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
            System.out.println("4. 특정 과목 회차별 등급을 조회");
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
                        listAllScoresBySubject();
                        break;
                    case 5:
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
