package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Deck;

public class AddPlayerActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        RuntimeExceptionDao<Deck, String> deckDao = getHelper().getDeckRuntimeExceptionDao();
        List<Deck> list = deckDao.queryForAll();

        ArrayAdapter<Deck> dataAdapter = new ArrayAdapter<Deck>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addPlayerButtonClickHandler(View view) {
        String username = ((EditText)findViewById(R.id.usernameEditText)).getText().toString();
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.phoneNumberEditText)).getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Deck deck = (Deck) spinner.getSelectedItem();

        CurrentMode.getManagerMode().addPlayerToSystem(username, name, phoneNumber, deck);

        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }
}
