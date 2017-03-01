//data type class stored in database

package com.example.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;

@DatabaseTable(tableName="TournamentTable")
public class Tournament {

    @DatabaseField(generatedId= true)
    int id;

    @DatabaseField
    int houseCut;

    @DatabaseField
    int entryPrice;

    @DatabaseField
    List allUsername;

    @DatabaseField
    String status;

    @DatabaseField
    String firstWinner;

    @DatabaseField
    String secondWinner;

    @DatabaseField
    String thirdWinner;

    @DatabaseField
    Date endDate;

    public Tournament() {

    }

    public Tournament(int id, int houseCut, int entryPrice, List allUsername) {
        this.id = id;
        this.houseCut = houseCut;
        this.entryPrice = entryPrice;
        this.allUsername = allUsername;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", houseCut=" + houseCut +
                ", entryPrice=" + entryPrice +
                ", allUsername=" + allUsername +
                ", status='" + status + '\'' +
                ", firstWinner='" + firstWinner + '\'' +
                ", secondWinner='" + secondWinner + '\'' +
                ", thirdWinner='" + thirdWinner + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}