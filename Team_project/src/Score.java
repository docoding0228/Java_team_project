public class Score {
    private final int subject;
    private final int StudentId;
    private final int scoreRank;
    private final int episode;
    private final char rank;

    // 여기에 들어올것 : subject, episode(회차), 점수
    public Score(int StudentId, int subject,  int episode, int scoreRank){
        this.subject = subject;
        this.StudentId = StudentId;  // 필요 없음 , 키값,...
        this.scoreRank = scoreRank;
        this.episode = episode;
        // 이거는 따로 setRank() 메서드 만들어서 하기
        // public void setRank(scoreRank){this.rank =...}
        if(subject <=5){
            switch(scoreRank/10){
                case 10:
                case 9:
                    if(scoreRank >= 95)
                        rank = 'A';
                    else
                        rank = 'B';
                    break;
                case 8:
                    rank = 'C';
                    break;
                case 7:
                    rank = 'D';
                    break;
                case 6:
                    rank = 'F';
                    break;
                default:
                    rank = 'N';
                    break;
            }
        }else {
            switch(scoreRank/10){
                case 100:
                case 9:
                    rank = 'A';
                    break;
                case 8:
                    rank = 'C';
                    break;
                case 7:
                    rank = 'D';
                    break;
                case 6:
                    rank = 'F';
                    break;
                default:
                    rank = 'N';
                    break;
            }
        }
    }
}