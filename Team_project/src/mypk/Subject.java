package mypk;

import java.util.*;
import static mypk.Student.pushID;

public class Subject {
    private static final List<String> REQUIRED_SUBJECTS = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    private static final List<String> ELECTIVE_SUBJECTS = Arrays.asList("디자인_패턴", "Spring_Security", "Redis", "MongoDB");

    private static Map<String, List<String>> studentSubjects = new HashMap<>();

    private static Scanner sc = new Scanner(System.in);

    /**
     * 수강신청된 모든 과목 리스트를 불러오기 위해 사용된다.
     * @return
     */
    public static List<String> getAllSubjects() {
        List<String> allSubjects = new ArrayList<>(REQUIRED_SUBJECTS);
        allSubjects.addAll(ELECTIVE_SUBJECTS);
        return allSubjects;
    }

    /**
     * 선택지에 따른 필요한 메서드를 호출한다.
     * @throws InterruptedException 예외 발생시 메인 화면으로 이동
     */
    public static void settingSubjectView() throws InterruptedException {

            System.out.println("==================================");
            System.out.println("수강생 과목 관리 실행 중...");
            System.out.println("1. 수강생 과목 등록");
            System.out.println("2. 수강생 과목 수정");
            System.out.println("3. 이전 화면 이동");

            System.out.print("관리 항목을 선택하세요... ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> Subject.manageSubjects();
                case 2 -> Subject.subjectEdit();
                case 3 -> Student.displayStudentView();
                default -> System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
            }
    }

    /**
     * 학생이 수강중인 과목을 삭제하기 위한 메서드이다.
     * @param studentId studentId 를 통해 학생에 접근한 뒤 과목을 삭제한다.
     * @return 제대로 삭제 되었는지 boolean 값으로 리턴해준다.
     */
    public static boolean deleteStudentSubjects(String studentId) {
        if (studentSubjects.containsKey(studentId)) {
            studentSubjects.remove(studentId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 수강신청을 위한 메서드이다.
     * @throws InterruptedException 예외 발생시 메인 화면으로 이동
     */
    public static void manageSubjects() throws InterruptedException {
        String studentId = pushID();

        if (!Student.isRegistered(studentId)) {
            System.out.println("등록되지 않은 ID 입니다.");
            return;
        }

        if (!studentSubjects.containsKey(studentId)) {
            studentSubjects.put(studentId, new ArrayList<>());
        }

        List<String> subjectlist = studentSubjects.get(studentId);

        System.out.print("필수 과목 목록: ");
        for (int i = 0; i < ELECTIVE_SUBJECTS.size(); i++) {
            System.out.print("[" + (i + 1) + ". " + REQUIRED_SUBJECTS.get(i) + "], ");
        }

        System.out.println("[" + (5) + ". " + REQUIRED_SUBJECTS.get(4) + "]");

        int subjectSize = 0;
        while (true) {
            if(subjectlist.size() >= 5) {
                break;
            } else if (subjectlist.size() >= 3) {
                System.out.print("필수 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();
                if ("NO".equalsIgnoreCase(answer)) {
                    break;
                }
            }

            System.out.print("희망하는하는 필수 과목의 번호를 입력하세요: ");
            int requiredSubjectsIndex = sc.nextInt();

            if (requiredSubjectsIndex >= 1 && requiredSubjectsIndex <= REQUIRED_SUBJECTS.size()) {
                String requiredSubject = REQUIRED_SUBJECTS.get(requiredSubjectsIndex - 1);
                if (!subjectlist.contains(requiredSubject)) {
                    subjectlist.add(requiredSubject);
                    subjectSize++;
                } else {
                    System.out.println("이미 선택한 과목입니다.");
                }
            }
            else {
                System.out.println("올바른 번호를 입력하세요.");
            }
        }

        System.out.print("선택 과목 목록: ");
        for (int i = 0; i < ELECTIVE_SUBJECTS.size() - 1; i++) {
            System.out.print("[" + (i + 1) + ". " + ELECTIVE_SUBJECTS.get(i) + "], ");
        }
        System.out.println("[4. " + ELECTIVE_SUBJECTS.get(3) + "]");

        while (true) {
            if(subjectlist.size() >= subjectSize + 4){
                break;
            }

            if (subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).count() >= 2) {
                System.out.print("선택 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("no")) {
                    break;
                }
            }

            System.out.print("희망하는 선택 과목의 번호를 입력하세요: ");
            int electiveSubjectsIndex = sc.nextInt();

            if (electiveSubjectsIndex >= 1 && electiveSubjectsIndex <= ELECTIVE_SUBJECTS.size()) {
                String electiveSubject = ELECTIVE_SUBJECTS.get(electiveSubjectsIndex - 1);
                if (!subjectlist.contains(electiveSubject)) {
                    subjectlist.add(electiveSubject);
                } else {
                    System.out.println("이미 선택한 과목입니다.");
                }
            } else {
                System.out.println("올바른 번호를 입력하세요.");
            }
        }

        System.out.println("수강생 ID: " + studentId);
        System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
        System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());

    }

    /**
     * 학생이 수강신청한 과목 리스트를 가져오는 메서드이다.
     * @param studentId 수강신청된 과목 리스트를 불러오기 위해 studentId 사용
     * @return 학생이 수강신청한 과목 list 반환
     */
    public static List<String> getStudentSubjects(String studentId) {
        if (studentSubjects.containsKey(studentId)) {
            return new ArrayList<>(studentSubjects.get(studentId));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     *
     * @return 필수과목리스트를 반환
     */
    public static List<String> getRequiredSubjects() {
        return REQUIRED_SUBJECTS;
    }

    /**
     *
     * @return 선택과목 리스트를 반환
     */
    public static List<String> getElectiveSubjects() {
        return ELECTIVE_SUBJECTS;
    }

    /**
     * 학생이 수강중인 과목을 print 하기 위해 사용
     */
    public static void subjectCheck() {
        System.out.println("수강생 ID를 입력해주세요:");
        String studentId = sc.next();

        if (!Student.isRegistered(studentId)) {
            System.out.println("등록되지 않은 학생입니다.");
        } else {
            System.out.println("수강생 ID: " + studentId);
            List<String> subjectlist = studentSubjects.get(studentId);
            if (subjectlist != null) {
                System.out.println("수강 과목: " + subjectlist);
            } else {
                System.out.println("등록된 과목이 없습니다.");
            }
        }
    }

    /**
     * 학생이 과목을 추가할지 삭제할지 선택하는 메서드
     */
    public static void subjectEdit() {
        System.out.println("수정할 수강생 ID를 입력해주세요.");
        String pushId = sc.next();

        if (!Student.isRegistered(pushId)) {
            System.out.println("등록되지 않은 학생입니다.");
            return;
        }
        while (true) {
            List<String> subjectlist = studentSubjects.get(pushId);
            if (subjectlist == null) {
                System.out.println("수정할 과목이 없습니다.");
                break;
            } else {
                System.out.println("수정할 과목: " + subjectlist);
                System.out.println("1. 추가");
                System.out.println("2. 삭제");
                int editChoice = sc.nextInt();

                if (editChoice == 1) {
                    System.out.println("추가할 과목의 종류를 선택하세요 (필수과목:1, 선택과목:2): ");
                    int category = sc.nextInt();
                    List<String> subjectCategory = null;
                    if (category == 1) {
                        subjectCategory = REQUIRED_SUBJECTS;
                    }else if (category == 2) {
                        subjectCategory = ELECTIVE_SUBJECTS;
                    }

                    switch (category) {
                        case 1:
                            System.out.println(REQUIRED_SUBJECTS);
                            break;
                        case 2:
                            System.out.println(ELECTIVE_SUBJECTS);
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                            break;
                    }

                    System.out.println("추가할 과목의 번호를 입력하세요: ");
                    int newSubjectIndex = sc.nextInt();
                    if (newSubjectIndex >= 1 && newSubjectIndex <= subjectCategory.size()) {
                        String newSubject = subjectCategory.get(newSubjectIndex - 1);
                        if (!subjectlist.contains(newSubject)) {
                            subjectlist.add(newSubject);
                            System.out.println("과목이 추가되었습니다.");
                            break;
                        } else {
                            System.out.println("이미 선택한 과목입니다.");
                        }
                    } else {
                        System.out.println("잘못된 과목 번호입니다.");
                    }
                } else if (editChoice == 2) {
                    System.out.println("삭제할 과목 번호를 입력하세요: ");
                    int subjectIndex = sc.nextInt();

                    if (subjectIndex >= 1 && subjectIndex <= subjectlist.size()) {
                        subjectlist.remove(subjectIndex - 1);
                        System.out.println("과목이 삭제되었습니다.");
                        break;
                    } else {
                        System.out.println("잘못된 선택입니다.");
                    }
                } else {
                    System.out.println("잘못된 과목 번호입니다.");
                }
            }
        }
    }
}