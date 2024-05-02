import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> nameMap = new HashMap<>();
        Map<String, ArrayList<String>> subjectMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        List<String> mandatorySubjects = Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL");
        List<String> optionalSubjects = Arrays.asList("디자인 패턴", "Spring Security", "Redis", "MongoDB");

        do {
            try {
                System.out.println("수강생 정보 입력 (1), 점수 관리 (2) : ");
                int select = sc.nextInt();
                sc.nextLine(); // nextInt() 후의 개행 문자 소비

                // 사람 생성
                if (select == 1) {
                    System.out.print("고유번호를 입력해주세요(3자리로 입력해주세요.) : ");
                    String idNumber = sc.nextLine();

                    if (nameMap.containsKey(idNumber)) {
                        throw new Exception("이미 등록된 사용자입니다.");
                    }

                    // 이름 입력
                    System.out.print("성함을 입력해주세요 : ");
                    String name = sc.nextLine();
                    nameMap.put(idNumber, name);

                    ArrayList<String> subjectList = new ArrayList<>();

                    // 필수 과목 입력
                    System.out.println("필수 과목 목록: " + String.join(", ", mandatorySubjects));
                    while (subjectList.size() < 5) {
                        System.out.print("필수 과목을 입력해주세요: ");
                        String subject = sc.nextLine();

                        if (!mandatorySubjects.contains(subject)) {
                            System.out.println("유효하지 않은 필수 과목입니다. 다시 입력해주세요.");
                            continue;
                        }

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
                    System.out.println("선택 과목 목록: " + String.join(", ", optionalSubjects));
                    while (subjectList.size() < 9) {
                        System.out.print("선택 과목을 입력해주세요: ");
                        String subject = sc.nextLine();

                        if (!optionalSubjects.contains(subject)) {
                            System.out.println("유효하지 않은 선택 과목입니다. 다시 입력해주세요.");
                            continue;
                        }

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
                    System.out.println("사람이 추가되었습니다.");
                }
                // 점수관리 시스템
                else {
                    System.out.println("해당 기능은 구현 중입니다!");
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
