package edu.gatech.seclass.tourneymanager.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.model.Tournament;


public class TournamentAdapter extends ArrayAdapter<Tournament> {
    public TournamentAdapter(Context context, ArrayList<Tournament> tournaments) {
        super(context, 0, tournaments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tournament tournament= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_list_row, parent, false);
        }
        TextView tvTournamentEndDate = (TextView) convertView.findViewById(R.id.player_name_text_view);
        TextView tvProfit = (TextView) convertView.findViewById(R.id.total_prize_text_view);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        tvTournamentEndDate.setText(df.format(tournament.getEndDate()));
        tvProfit.setText(tournament.getHouseCut().toString());

        return convertView;
    }
}
