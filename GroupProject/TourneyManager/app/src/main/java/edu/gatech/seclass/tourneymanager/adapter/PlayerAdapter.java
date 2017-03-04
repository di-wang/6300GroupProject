package edu.gatech.seclass.tourneymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.model.Player;


public class PlayerAdapter extends ArrayAdapter<Player> {
    public PlayerAdapter(Context context, ArrayList<Player> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_list_row, parent, false);
        }
        TextView tvUsername = (TextView) convertView.findViewById(R.id.player_name_text_view);
        TextView tvPrize = (TextView) convertView.findViewById(R.id.total_prize_text_view);

        tvUsername.setText(player.getUsername());
        tvPrize.setText(player.getTotalPrize().toString());

        return convertView;
    }
}
