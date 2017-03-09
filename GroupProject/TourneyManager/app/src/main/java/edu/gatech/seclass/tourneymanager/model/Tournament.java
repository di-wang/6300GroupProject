package edu.gatech.seclass.tourneymanager.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName="tournament")
public class Tournament {

    @DatabaseField(generatedId= true)
    private int id;

    //TODO: create a money util class
    @DatabaseField
    private int houseCut;

    @DatabaseField
    private String allUsername;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private TournamentStatus status;

    @DatabaseField
    private int currentRound;

    @DatabaseField
    private int numberOfGamesLeftCurrentRound;

    @DatabaseField
    private int firstPrize;

    @DatabaseField
    private int secondPrize;

    @DatabaseField
    private int thirdPrize;

    @DatabaseField(foreign = true)
    private Player firstWinner;

    @DatabaseField(foreign = true)
    private Player secondWinner;

    @DatabaseField(foreign = true)
    private Player thirdWinner;

    @DatabaseField
    private Date endDate;

    public Tournament() {

    }

    public Tournament(int houseCut, int firstPrize, int secondPrize, int thirdPrize, int currentRound) {
        this.houseCut = houseCut;
        this.firstPrize = firstPrize;
        this.secondPrize = secondPrize;
        this.thirdPrize = thirdPrize;
        this.status = TournamentStatus.ONGOING;
        this.currentRound = currentRound;
        this.numberOfGamesLeftCurrentRound = currentRound / 2;
    }

    public Integer getId() {
        return this.id;
    }

    public Date getEndDate() { return this.endDate; }

    public Integer getHouseCut() { return this.houseCut; }

    public Integer getCurrentRound() { return this.currentRound; }

    public Integer getFirstPrize() { return this.firstPrize; }
    public Integer getSecondPrize() { return this.secondPrize; }
    public Integer getThirdPrize() { return this.thirdPrize; }

    public String getCurrentRoundInString() {
        if (this.currentRound == 2) {
            return "Final";
        }
        else if (this.currentRound == 4) {
            return "Semi-final";
        }
        else {
            return this.currentRound + "th";
        }
    }

    public boolean isGameLeftForCurrentRound() {
       return (this.numberOfGamesLeftCurrentRound != 0);
    }

    public void matchEnded() {
        this.numberOfGamesLeftCurrentRound -= 1;
    }

    public void nextRound() {
        this.currentRound = this.currentRound / 2;
        if (this.currentRound == 2) {
            // For finals there are two games
            this.numberOfGamesLeftCurrentRound = 2;
        }
        else {
            this.numberOfGamesLeftCurrentRound = this.currentRound / 2;
        }
    }

    public void endTournamentPrematurely() {
        // All money is refunded
        this.houseCut = 0;
        this.status = TournamentStatus.COMPLETE;
        this.endDate = new Date();
    }

    public void endTournament(Player firstPlace, Player secondPlace, Player thirdPlace) {
        this.status = TournamentStatus.COMPLETE;
        this.endDate = new Date();

        this.firstWinner = firstPlace;
        this.secondWinner = secondPlace;
        this.thirdWinner = thirdPlace;
    }

    public int[] calculatePrizesAndProfit(int totalPool){
        int firstPrize;
        int secondPrize;
        int thirdPrize;
        int profit;

        //Calculate first prize
        firstPrize = (int) Math.round((totalPool - houseCut) * .5);
        //Calculate second prize
        secondPrize = (int) Math.round((totalPool - houseCut) * .3);
        //Calculate third prize
        thirdPrize = (int) Math.round((totalPool - houseCut) * .2);
        //Calculate profit
        profit = totalPool - firstPrize - secondPrize - thirdPrize;

        int[]  a = {firstPrize, secondPrize, thirdPrize, profit};

        return a;

    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", houseCut=" + houseCut +
                ", allUsername=" + allUsername +
                ", status='" + status + '\'' +
                ", firstWinner='" + firstWinner + '\'' +
                ", secondWinner='" + secondWinner + '\'' +
                ", thirdWinner='" + thirdWinner + '\'' +
                ", endDate=" + endDate +
                '}';
    }

    public enum TournamentStatus {
        ONGOING, COMPLETE
    }

    public boolean didEndedPrematurely() {
        if (this.currentRound == 2 && this.numberOfGamesLeftCurrentRound == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
