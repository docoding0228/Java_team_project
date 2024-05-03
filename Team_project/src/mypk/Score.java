package mypk;

public class Score {
    private Student student;
    private Subject subject;
    private int score;

    public Score(Student student, Subject subject, int score) {
        this.student = student;
        this.subject = subject;
        this.score = score;
    }

    // Getter와 Setter
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{student=" + student + ", subject=" + subject + ", score=" + score + "}";
    }
}

