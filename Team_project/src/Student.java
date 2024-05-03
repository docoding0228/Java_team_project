import java.util.*;

public class Student {
    Scanner sc = new Scanner(System.in);
    private String name;

    public Student() {}

    /**
     * setting student name using scanner
     */
    public void setStudentName() {
        // 이름 입력
        System.out.print("성함을 입력해주세요: ");
        this.name = sc.next();
        // 테스트용
        System.out.println("testing setStudentName " + this.name);
    }

    public void setStudentSubject(){
        Subject subject = new Subject();
        subject.setSubjectList();
    }

     /* 학생 정보 조회 메서
     */
    public void searchStudent(){
        System.out.println("이름: " + this.name);
    }
}









