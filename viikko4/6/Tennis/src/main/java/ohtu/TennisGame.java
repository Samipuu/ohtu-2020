package ohtu;

public class TennisGame {
    
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String playerOne;
    private String playerTwo;

    public TennisGame(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            playerOneScore += 1;
        else
            playerTwoScore += 1;
    }
    
    private String playerScore(int score) {
        switch(score)
            {
                case 0:
                    return "Love";
                case 1:
                    return "Fifteen";
                case 2:
                    return "Thirty";
                case 3:
                    return "Forty";
            }
        return null;
    }
    
    public String getScore() {
        String score = "";
        checkWinCondition();
        if (playerOneScore==playerTwoScore)
        {
            if(playerOneScore > 3) {
                score = "Deuce";
            } else {
                score = playerScore(playerOneScore) + "-All";
            }
        }
        else if (playerOneScore>=4 || playerTwoScore>=4)
        {
            score = checkWinCondition();
        }
        else
        {
            score = playerScore(playerOneScore) + "-" + playerScore(playerTwoScore);
        }
        return score;
    }
    
    public String checkWinCondition() {
        String score;
        int minusResult = playerOneScore-playerTwoScore;
        if (minusResult==1) score ="Advantage player1";
        else if (minusResult ==-1) score ="Advantage player2";
        else if (minusResult>=2) score = "Win for player1";
        else score ="Win for player2";
        return score;
    }
}