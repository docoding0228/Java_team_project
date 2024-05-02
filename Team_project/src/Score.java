public class Score {
    private final int subject;
    private final int StudentId;
    private final int score_rank;
    private final int episode;
    private final char rank;
    public Score(int StudentId, int subject,  int episode, int score_rank){
        this.subject = subject;
        this.StudentId = StudentId;
        this.score_rank = score_rank;
        this.episode = episode;
        if(subject <=5){
            switch(score_rank/10){
                case 10:
                case 9:
                    if(score_rank >= 95)
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
            switch(score_rank/10){
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
