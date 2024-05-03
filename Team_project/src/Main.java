import java.util.*;

enum RequiredSub {
    JAVA("Java"),
    OBJECT_ORIENTED("객체지향"),
    SPRING("Spring"),
    JPA("JPA"),
    MYSQL("MySQL");

    private final String displayName;

    RequiredSub(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

enum OptionalSub {
    DESIGN_PATTERNS("디자인 패턴"),
    SPRING_SECURITY("Spring Security"),
    REDIS("Redis"),
    MONGODB("MongoDB");

    private final String displayName;

    OptionalSub(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> nameMap = new HashMap<>();
        Map<String, ArrayList<String>> subjectMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println("\n==================================");
                System.out.println(" 내일배움캠프 수강생 관리 프로그램 실행 중... ");
                System.out.println("1. 수강생 관리");
                System.out.println("2. 점수 관리");
                System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
                System.out.println("4. 프로그램 종료");
                System.out.print("관리 항목을 선택하세요...");

                int selectlist = sc.nextInt(); // 선택을 위한 입력
                sc.nextLine(); // 개행 문자 소비

                if (selectlist == 1) { // 과목별 시험 회차 및 점수 등록
                    System.out.println("1. 수강생 정보 입력,  2. 회차 및 점수 등록 : ");
                    int subSelect = sc.nextInt(); // 내부 메뉴 선택
                    sc.nextLine(); // 개행 문자 소비

                    if (subSelect == 1) { // 수강생 정보 입력
                        System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요.): ");
                        String idNumber = sc.nextLine();

                        if (nameMap.containsKey(idNumber)) {
                            throw new Exception("이미 등록된 사용자입니다.");
                        }

                        // 이름 입력
                        System.out.print("성함을 입력해주세요: ");
                        String name = sc.nextLine();
                        nameMap.put(idNumber, name);

                        // 과목 등록
                        ArrayList<String> subjectList = new ArrayList<>();
                        System.out.println("필수 과목 목록:");
                        for (RequiredSub subject : RequiredSub.values()) {
                            System.out.println(subject.ordinal() + 1 + ". " + subject.getDisplayName());
                        }

                        while (subjectList.size() < 5) {
                            System.out.print("필수 과목 번호를 입력해주세요: ");
                            int subjectIndex = sc.nextInt() - 1; // 인덱스는 0부터 시작
                            sc.nextLine(); // 개행 문자 소비

                            if (subjectIndex < 0 || subjectIndex >= RequiredSub.values().length) {
                                System.out.println("유효하지 않은 필수 과목 번호입니다. 다시 입력해주세요.");
                                continue;
                            }

                            String subject = RequiredSub.values()[subjectIndex].getDisplayName();

                            if (subjectList.contains(subject)) {
                                System.out.println("이미 신청한 과목입니다.");
                                continue;
                            }

                            subjectList.add(subject);

                            if (subjectList.size() >= 3) {
                                System.out.println("필수 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                                String answer = sc.nextLine();
                                if (answer.equalsIgnoreCase("yes")) {
                                    break;
                                }
                            }
                        }

                        // 선택 과목 입력
                        System.out.println("선택 과목 목록:");
                        for (OptionalSub subject : OptionalSub.values()) {
                            System.out.println(subject.ordinal() + 1 + ". " + subject.getDisplayName());
                        }

                        while (subjectList.size() < 9) {
                            System.out.print("선택 과목 번호를 입력해주세요: ");
                            int subjectIndex = sc.nextInt() - 1; // 인덱스는 0부터 시작
                            sc.nextLine(); // 개행 문자 소비

                            if (subjectIndex < 0 || subjectIndex >= OptionalSub.values().length) {
                                System.out.println("유효하지 않은 선택 과목 번호입니다. 다시 입력해주세요.");
                                continue;
                            }

                            String subject = OptionalSub.values()[subjectIndex].getDisplayName();

                            if (subjectList.contains(subject)) {
                                System.out.println("이미 신청한 과목입니다.");
                                continue;
                            }

                            subjectList.add(subject);

                            if (subjectList.size() >= 6) {
                                System.out.println("선택 과목 신청을 마치시겠습니까? (\"yes\" 입력 시 종료)");
                                String answer = sc.nextLine();
                                if (answer.equalsIgnoreCase("yes")) {
                                    break;
                                }
                            }
                        }

                        subjectMap.put(idNumber, subjectList);
                        System.out.println("수강생 정보가 등록되었습니다.");
                    } else if (subSelect == 2) { // 회차 및 점수 등록
                        System.out.println("해당 기능은 구현 중입니다!");
                    } else {
                        System.out.println("유효하지 않은 선택입니다. 다시 시도해주세요.");
                    }
                } else if (selectlist == 2) { // 회차 점수 수정
                    System.out.println("해당 기능은 구현 중입니다!");
                } else if (selectlist == 3) { // 특정 과목 회차별 등급 조회
                    System.out.println("해당 기능은 구현 중입니다!");
                } else if (selectlist == 4) { // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                } else {
                    System.out.println("유효하지 않은 선택입니다.");
                }

                // nameMap 프린트
                System.out.println("현재 등록된 사람들:");
                for (Map.Entry<String, String> entry : nameMap.entrySet()) {
                    System.out.println("{" + entry.getKey() + ", " + entry.getValue() + "}");
                }

                // subjectMap 프린트
                System.out.println("현재 등록된 과목들:");
                for (Map.Entry<String, ArrayList<String>> entry : subjectMap.entrySet()) {
                    System.out.println("{" + entry.getKey() + ", " + String.join(", ", entry.getValue()) + "}");
                }

                System.out.println("종료하시려면 'exit'을 입력하세요.");
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
                System.out.println("종료하시려면 'exit'을 입력하세요.");
            }
        } while (!sc.nextLine().equalsIgnoreCase("exit"));
    }
}
