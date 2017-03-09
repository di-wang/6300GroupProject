package edu.gatech.seclass.tourneymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.model.Match;


public class MatchAdapter extends ArrayAdapter<Match> {
    public MatchAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Match match= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.match_list_row, parent, false);
        }
        TextView tvPlayerNames = (TextView) convertView.findViewById(R.id.player_names_text_view);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.status_text_view);
        TextView tvRound = (TextView) convertView.findViewById(R.id.round_text_view);
        TextView tvWinner = (TextView) convertView.findViewById(R.id.winner_text_view);

        tvPlayerNames.setText(match.getPlayer1().toString() + " vs. " + match.getPlayer2().toString());
        tvStatus.setText(match.getStatus());
        tvRound.setText(match.getRoundInString());
        tvWinner.setText(match.getWinnerInString());

        return convertView;
    }
}
