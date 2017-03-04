package edu.gatech.seclass.tourneymanager.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="match")
public class Match {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true)
    private Tournament tournament;

    @DatabaseField(foreign = true)
    private Player player1;

    @DatabaseField(foreign = true)
    private Player player2;

    @DatabaseField(foreign = true)
    private Player winner;

    @DatabaseField(dataType = DataType.ENUM_STRING)
    private MatchStatus status;

    public Match() {

    }

    public Match(Tournament tournament, Player player1, Player player2) {
        super();
        this.tournament = tournament;
        this.player1 = player1;
        this.player2 = player2;
        this.status = MatchStatus.READY;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public String getStatus() {
        return this.status.name();
    }

    public String getWinnerInString() {
        return (this.winner != null) ? this.winner.toString() : "";
    }

    public void startMatch() {
        this.status = MatchStatus.ONGOING;
    }

    public void endMatch(Player winner) throws IllegalArgumentException {
        // Check if winner is either player1 or player2
        if (player1 != winner && player2 != winner) {
           throw new IllegalArgumentException("Winner should be either player1 or player2");
        }

        this.winner = winner;

        this.status = MatchStatus.COMPLETE;
    }

    /* Methods to check match status */
    public boolean isMatchReady() {
        return (this.status == MatchStatus.READY);
    }

    public boolean isMatchOngoing() {
        return (this.status == MatchStatus.ONGOING);
    }

    public boolean isMatchComplete() {
        return (this.status == MatchStatus.COMPLETE);
    }

    private enum MatchStatus {
       READY, ONGOING, COMPLETE
    }
}
