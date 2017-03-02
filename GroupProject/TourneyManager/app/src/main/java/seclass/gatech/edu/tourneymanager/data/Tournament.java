//data type class stored in database

package seclass.gatech.edu.tourneymanager.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName="TournamentTable")
public class Tournament {

    @DatabaseField(generatedId= true)
    int id;

    @DatabaseField
    int houseCut;

    @DatabaseField
    int entryPrice;

    @DatabaseField
    String allUsername;

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

    public Tournament(int id) {
        this.id = id;

    }

    public Tournament(int id, int houseCut, int entryPrice, String allUsername) {
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