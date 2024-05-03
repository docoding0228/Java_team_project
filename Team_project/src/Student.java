import java.util.*;

public class Student {
    Scanner sc = new Scanner(System.in);
    private String name;
    private List<String> subjectList;  // 학생이 선택한 과목 목록

    public Student() {}

    // 학생 이름 설정
    public void setStudentName() {
        System.out.print("성함을 입력해주세요: ");
        this.name = sc.next();
        System.out.println("이름이 설정되었습니다: " + this.name);
    }

    // 학생 과목 설정
    public void setStudentSubject() {
        Subject subject = new Subject();
        subject.setSubjectList();  // 과목 선택
        this.subjectList = subject.getSubjects();  // 선택된 과목 목록 저장

    }

    // 선택된 과목 목록을 반환
    public List<String> getSubjectList() {
        return this.subjectList;  // 선택된 과목 목록을 반환
    }

    // 학생 정보 출력
    public void searchStudent() {
        System.out.println("학생 정보");
        System.out.println("이름: " + this.name);
        System.out.println("선택된 과목 목록: " + subjectList);
    }
}
