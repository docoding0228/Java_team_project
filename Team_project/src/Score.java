import java.util.ArrayList;

public class Score {
    private final int subject;
    private final int scoreRank;
    private final int episode;
    private final char rank;
    ArrayList<ArrayList<ArrayList<Integer>>> scores = new ArrayList<>();

    // 여기에 들어올것 : subject, episode(회차), 점수
    public Score(int subject,  int episode, int scoreRank){
        this.subject = subject;
        this.scoreRank = scoreRank;
        this.episode = episode;
        // 이거는 따로 setRank() 메서드 만들어서 하기
        // public void setRank(scoreRank){this.rank =...}
        if(subject <=5){
            switch(scoreRank/10){
                case 10:
                case 9:
                    if(scoreRank >= 95)
                        this.rank = 'A';
                    else
                        this.rank = 'B';
                    break;
                case 8:
                    this.rank = 'C';
                    break;
                case 7:
                    this.rank = 'D';
                    break;
                case 6:
                    this.rank = 'F';
                    break;
                default:
                    this.rank = 'N';
                    break;
            }
        }else {
            switch(scoreRank/10){
                case 100:
                case 9:
                    this.rank = 'A';
                    break;
                case 8:
                    this.rank = 'C';
                    break;
                case 7:
                    this.rank = 'D';
                    break;
                case 6:
                    this.rank = 'F';
                    break;
                default:
                    this.rank = 'N';
                    break;
            }
        }
    }

}

