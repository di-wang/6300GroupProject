package edu.gatech.seclass.tourneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.adapter.MatchAdapter;
import edu.gatech.seclass.tourneymanager.adapter.TournamentAdapter;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Tournament;
import edu.gatech.seclass.tourneymanager.utils.ErrorHandler;

public class ViewPastProfitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_profit);

        try {
            List<Tournament> tournamentList = getTournamentList();

            initializePastProfitListView(tournamentList);
            showTotalProfit(tournamentList);
        }
        catch (SQLException e) {
            ErrorHandler.SQLExceptionHandler(e, this);
        }
    }

    private void initializePastProfitListView(List<Tournament> tournamentList) {
        ListView listview = (ListView) findViewById(R.id.pastProfitListView);

        TournamentAdapter adapter = new TournamentAdapter(this, (ArrayList<Tournament>) tournamentList);

        listview.setAdapter(adapter);
    }

    private void showTotalProfit(List<Tournament> tournamentList) {
        TextView totalProfitTextView = (TextView) findViewById(R.id.totalProfitTextView);

        Integer totalProfit = calcTotalProfit(tournamentList);

        totalProfitTextView.setText(totalProfit.toString());
    }

    private List<Tournament> getTournamentList() throws SQLException {
        return CurrentMode.getManagerMode().viewPastProfits();
    }

    private Integer calcTotalProfit(List<Tournament> tournamentList) {
        Integer totalProfit = 0;

        for (int i = 0; i < tournamentList.size(); i++) {
            totalProfit += tournamentList.get(i).getHouseCut();
        }

        return totalProfit;
    }
}
