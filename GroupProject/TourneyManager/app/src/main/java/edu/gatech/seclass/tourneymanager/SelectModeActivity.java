package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
    }

    public void selectManagerMode(View view) {
        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }

    public void selectPlayerMode(View view) {
        Intent intent = new Intent(this, PlayerHomeActivity.class);
        startActivity(intent);
    }
}
