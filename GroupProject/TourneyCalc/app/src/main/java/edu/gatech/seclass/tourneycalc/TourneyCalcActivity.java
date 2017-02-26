package edu.gatech.seclass.tourneycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TourneyCalcActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttoncalculate;
    private TextView housecutvalue, firstprizevalue, secondprizevalue, thirdprizevalue;
    private EditText entrancefee, entrantsnumber, housepercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney_calc);
        init();
    }

    private void init() {
        buttoncalculate = (Button)findViewById(R.id.buttonCalculate);
        entrancefee = (EditText)findViewById(R.id.entranceFee);
        entrantsnumber = (EditText)findViewById(R.id.entrantsNumber);
        housepercentage = (EditText)findViewById(R.id.housePercentage);

        housecutvalue = (TextView)findViewById(R.id.houseCutValue);
        firstprizevalue = (TextView)findViewById(R.id.firstPrizeValue);
        secondprizevalue = (TextView)findViewById(R.id.secondPrizeValue);
        thirdprizevalue = (TextView)findViewById(R.id.thirdPrizeValue);

        buttoncalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean isValid = true;
        String fee = entrancefee.getText().toString();
        //For some reason a try/catch was the only way i could get it to error message on a blank entry
        try {
            if (Integer.parseInt(fee) < 1) {
                entrancefee.setError("Invalid Fee");
                isValid = false;
            } else {
                entrancefee.setError(null);
            }
        } catch (NumberFormatException e) {
            entrancefee.setError("Invalid Fee");
            isValid = false;
        }
        String number = entrantsnumber.getText().toString();
        try {
            if (Integer.parseInt(number) < 4) {
                entrantsnumber.setError("Invalid Number of Entrants");
                isValid = false;
            } else {
                entrantsnumber.setError(null);
            }
        } catch (NumberFormatException e) {
            entrantsnumber.setError("Invalid Fee");
            isValid = false;
        }
        String percent = housepercentage.getText().toString();
        try {
            if (Integer.parseInt(percent) < 1 || Integer.parseInt(percent) > 99) {
                housepercentage.setError("Invalid House Percentage");
                isValid = false;
            } else {
                housepercentage.setError(null);
            }
        } catch (NumberFormatException e) {
            housepercentage.setError("Invalid Fee");
            isValid = false;
        }

        switch(view.getId()) {
            case R.id.buttonCalculate:
                //Use this incase I make a clear feature or additional buttons
                if (isValid == true) {
                    int totalamount = Integer.parseInt(fee) * Integer.parseInt(number);
                    float percentvalue = (float) Integer.parseInt(percent) / 100;
                    int cut = Math.round(totalamount * percentvalue);
                    housecutvalue.setText(String.valueOf(cut));
                    int first = (int) Math.round((totalamount - cut) * 0.5);
                    firstprizevalue.setText(String.valueOf(first));
                    int second = (int) Math.round((totalamount - cut) * 0.3);
                    secondprizevalue.setText(String.valueOf(second));
                    int third = (int) Math.round((totalamount - cut) * 0.2);
                    thirdprizevalue.setText(String.valueOf(third));
                } else {
                    housecutvalue.setText(String.valueOf(""));
                    firstprizevalue.setText(String.valueOf(""));
                    secondprizevalue.setText(String.valueOf(""));
                    thirdprizevalue.setText(String.valueOf(""));
                }
        }
    }

}
