package mypk;

import java.util.*;
import static mypk.Student.pushID;

public class Subject {
    // 프로그램 실행 중에 값이 변경되지 않는 상수 리스트로, 필수 과목 목록을 미리 정의
    private static final List<String> REQUIRED_SUBJECTS = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
    private static final List<String> ELECTIVE_SUBJECTS = Arrays.asList("디자인_패턴", "Spring_Security", "Redis", "MongoDB");

    private static Map<String, List<String>> studentSubjects = new HashMap<>(); // 학생별 과목 목록
    private static Scanner sc = new Scanner(System.in);

    public static List<String> getAllSubjects() {
        List<String> allSubjects = new ArrayList<>(REQUIRED_SUBJECTS);
        allSubjects.addAll(ELECTIVE_SUBJECTS);
        return allSubjects;
    }

    // ================ 수강생 삭제로 인한 추가 ================

    // 수강생 과목 정보 삭제
    public static boolean deleteStudentSubjects(String studentId) {
        if (studentSubjects.containsKey(studentId)) {
            studentSubjects.remove(studentId); // 학생 ID의 과목 목록 삭제
            return true; // 삭제 성공
        } else {
            return false; // 해당 ID에 대한 과목 정보 없음
        }
    }
    // ================ 수강생 삭제로 인한 추가 ================


    // 수강생 과목 추가
    public static void manageSubjects() throws InterruptedException {
        String studentId = pushID();
        // 학생 등록 여부 확인
        // studentId가 Student.isRegistered() 메서드에서 false를 반환하는지 확인
        // 수강생 ID가 등록되지 않았음을 의미
        if (!Student.isRegistered(studentId)) {
            System.out.println("등록되지 않은 ID 입니다.");
            return;
        }

        // 학생에게 필수과목과 선택과목을 입력받는 과정

        // studentSubjects 맵에서 주어진 studentId가 키로 존재하지 않으면, 해당 키로 빈 ArrayList를 추가
        // studentSubjects.containsKey(studentId)가 false이면, 해당 키로 아직 아무런 값이 추가되지 않았음을 의미
        // 수강생이 아직 과목을 추가하지 않은 경우, 새로운 과목 목록을 생성하고 해당 수강생 ID와 연결하는 데 사용
        if (!studentSubjects.containsKey(studentId)) {
            studentSubjects.put(studentId, new ArrayList<>());
        }

        // studentSubjects 맵에서 studentId에 해당하는 값을 가져와 subjects 리스트에 할당
        // 수강생 ID에 대해 어떤 과목이 등록되어 있는지 확인하거나 추가 작업을 수행할 때 사용
        List<String> subjectlist = studentSubjects.get(studentId);
        //studentSubjects.get(studentId) << 빈어레이 리스트  List<String> subjectlist = studentSubjects.get(studentId);

        // 필수과목 추가
        System.out.print("필수 과목 목록: ");
        for (int i = 0; i < ELECTIVE_SUBJECTS.size(); i++) {
            System.out.print("[" + (i + 1) + ". " + REQUIRED_SUBJECTS.get(i) + "], ");
        }

        System.out.println("[" + (5) + ". " + REQUIRED_SUBJECTS.get(4) + "]");

        int subjectSize = 0;
        while (true) {
            System.out.print("희망하는하는 필수 과목의 번호를 입력하세요: ");
            int requiredSubjectsIndex = sc.nextInt();
            // 입력한 번호가 1이상이고 리스트 크기 이하인지 확인
            if (requiredSubjectsIndex >= 1 && requiredSubjectsIndex <= REQUIRED_SUBJECTS.size()) {
                //번호에 따른 과목을 가져옴
                String requiredSubject = REQUIRED_SUBJECTS.get(requiredSubjectsIndex - 1);
                //가져온 과목이 이미 선택한 과목인지 확인 아니라면 목록에 추가
                if (!subjectlist.contains(requiredSubject)) {
                    subjectlist.add(requiredSubject);
                    subjectSize++;
                } else {
                    System.out.println("이미 선택한 과목입니다.");
                }
            }// 번호가 맞는 번호인지 확인 아니라면 목록에 추가
            else {
                System.out.println("올바른 번호를 입력하세요.");
            }

            // subjectlist 크기가 3 이상인지를 확인하는 조건문
            if (subjectlist.size() >= 3) {
                System.out.print("필수 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();

                // answer가 "NO"와 같은지, 대소문자를 구분하지 않고 확인
                if (answer.equalsIgnoreCase("no")) {
                    // 만약 "NO"와 같다면, addingRequiredSubjects 변수를 false로 설정하여 필수 과목 추가 작업을 중단
                    break; // 선택과목으로 넘어가기
                }
            }
        }

        // 선택과목 추가
       System.out.print("필수 과목 목록: ");
        for (int i = 0; i < ELECTIVE_SUBJECTS.size() - 1; i++) {
            System.out.print("[" + (i + 1) + ". " + ELECTIVE_SUBJECTS.get(i) + "], ");
        }
        System.out.println("[4. " + ELECTIVE_SUBJECTS.get(3) + "]");

        while (true) {
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

            if(subjectlist.size() >= subjectSize + 4){
                Score.displayScoreView();
                break;
            }

            if (subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).count() >= 2) {
                System.out.print("선택 과목을 더 추가하시나요? (YES/NO): ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("no")) {
                    Score.displayScoreView(); //일단 이거를 내일
                    break;
                }
            }
        }

        // 최종 선택된 과목 목록 출력
        System.out.println("수강생 ID: " + studentId);
        System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
        System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());

    }

    // ================================== 스코어로 인한 추가

    // 수강생의 수강 과목 목록을 반환
    public static List<String> getStudentSubjects(String studentId) {
        if (studentSubjects.containsKey(studentId)) {
            return new ArrayList<>(studentSubjects.get(studentId)); // 안전을 위해 복사본 반환
        } else {
//            System.out.println("수강생 ID " + studentId + "에 등록된 수강 과목이 없습니다.");
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    // 필수 과목 목록 반환
    public static List<String> getRequiredSubjects() {
        return REQUIRED_SUBJECTS; // 필수 과목 목록 반환
    }

    // 선택 과목 목록 반환
    public static List<String> getElectiveSubjects() {
        return ELECTIVE_SUBJECTS; // 선택 과목 목록 반환
    }

    // ================================== 스코어로 인한 추가

    //조회 기능
    public static void subjectCheck() {
        String studentId = pushID();

        if (!Student.isRegistered(studentId)) {//등록 확인
            System.out.println("등록되지 않은 학생입니다.");
        } else {//등록된경우
            System.out.println("수강생 ID: " + studentId);//ID출력
            List<String> subjectlist = studentSubjects.get(studentId);
            if (subjectlist != null) {
                System.out.println("[수강 과목]" );
                System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
                System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());
            } else {
                System.out.println("등록된 과목이 없습니다.");
            }
        }
    }

    //수정기능
    public static void subjectEdit() {
        String pushId = pushID();

        //등록 안되어있는경우
        if (!Student.isRegistered(pushId)) {
            System.out.println("등록되지 않은 학생입니다.");
            return;
        }
        //등록되어있는경우
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
                    //추가기능
                    System.out.println("추가할 과목의 종류를 선택하세요 (필수과목:1, 선택과목:2): ");
                    int category = sc.nextInt();
                    List<String> subjectCategory = null; //초기화
                    if (category == 1) {
                        subjectCategory = REQUIRED_SUBJECTS;
                    }else if (category == 2) {
                        subjectCategory = ELECTIVE_SUBJECTS;
                    }

                    switch (category) {// 과목 목록 출력
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
                            System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
                            System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());
                            break;
                        } else {
                            System.out.println("이미 선택한 과목입니다.");
                        }
                    } else {
                        System.out.println("잘못된 과목 번호입니다.");
                    }

                    //삭제기능
                } else if (editChoice == 2) {
                    System.out.println("삭제할 과목 번호를 입력하세요: ");
                    int subjectIndex = sc.nextInt();

                    if (subjectIndex >= 1 && subjectIndex <= subjectlist.size()) {
                        subjectlist.remove(subjectIndex - 1);
                        System.out.println("과목이 삭제되었습니다.");
                        System.out.println("필수 과목: " + subjectlist.stream().filter(REQUIRED_SUBJECTS::contains).toList());
                        System.out.println("선택 과목: " + subjectlist.stream().filter(ELECTIVE_SUBJECTS::contains).toList());
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