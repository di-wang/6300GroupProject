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
    private int firstPrize;

    @DatabaseField
    private int secondPrize;

    @DatabaseField
    private int thirdPrize;

    @DatabaseField
    private String firstWinner;

    @DatabaseField
    private String secondWinner;

    @DatabaseField
    private String thirdWinner;

    @DatabaseField
    private Date endDate;

    @DatabaseField
    private int totalProfit;

    public Tournament() {

    }

    public Tournament(int houseCut, int firstPrize, int secondPrize, int thirdPrize) {
        this.houseCut = houseCut;
        this.firstPrize = firstPrize;
        this.secondPrize = secondPrize;
        this.thirdPrize = thirdPrize;
        this.status = TournamentStatus.ONGOING;
    }

    public int getId() {
        return this.id;
    }

    public Date getEndDate() { return this.endDate; }

    public Integer getHouseCut() { return this.houseCut; }

    public void endTournament() {
        this.status = TournamentStatus.COMPLETE;
        this.endDate = new Date();
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
}
