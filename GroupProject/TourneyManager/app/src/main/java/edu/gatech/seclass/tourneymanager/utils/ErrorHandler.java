package edu.gatech.seclass.tourneymanager.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.sql.SQLException;

public class ErrorHandler {
    public static void SQLExceptionHandler(SQLException e, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Database Error")
                .setMessage("Error occured during database operation. " +
                        "Please try this again and contact support if this problem persists.")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        Log.e("Database error", e.getStackTrace().toString());
    }
}
