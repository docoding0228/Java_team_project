import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Score {
    private String subject;
    private String scoreRank;
    private String episode;
    private String rank;
    Scanner sc = new Scanner(System.in);

    // 여기에 들어올것 : subject, episode(회차), 점수
    public Score(){}

    // 이것도 subject에 넣어서 과목을 입력받아야하고, 입력 받을때 어떻게 받을지? 과목 이름으로 필수/선택인지 구분할수 있게 만들기
    public void setNumberGrade(String subject){
        Map<String, ArrayList<String>> gradeMap = new HashMap<>();
        ArrayList<String> gradeSaved = new ArrayList<>();
        this.subject = subject;

        System.out.println("시험 회차를 입력하세요");
        gradeSaved.add(sc.next());  // ep 추가
        System.out.println("점수를 입력하세요");
        scoreRank = sc.next();
        gradeSaved.add(scoreRank);  // scoreRank 추가
        int scoreRankInt = Integer.parseInt(scoreRank);
        gradeSaved.add(getLetterGrade(scoreRankInt));

        gradeMap.put(subject, gradeSaved);
        System.out.println(subject);
        System.out.println(gradeSaved);
    }

    public String getLetterGrade(int scoreRankInt){
        // 필수과목 선택과목 으로 if문 바꾸기
        if(true){
            switch(scoreRankInt/10){
                case 10:
                case 9:
                    if(scoreRankInt >= 95)
                        this.rank = "A";
                    else
                        this.rank = "B";
                    break;
                case 8:
                    this.rank = "C";
                    break;
                case 7:
                    this.rank = "D";
                    break;
                case 6:
                    this.rank = "F";
                    break;
                default:
                    this.rank = "N";
                    break;
            }
        }else {
            switch(scoreRankInt/10){
                case 10:
                    this.rank = "A";
                    break;
                case 9:
                    this.rank = "B";
                    break;
                case 8:
                    this.rank = "C";
                    break;
                case 7:
                    this.rank = "D";
                    break;
                case 6:
                    this.rank = "F";
                    break;
                default:
                    this.rank = "N";
                    break;
            }
        }
        return rank;
    }

}

