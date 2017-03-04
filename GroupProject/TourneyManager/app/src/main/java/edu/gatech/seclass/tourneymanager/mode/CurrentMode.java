package edu.gatech.seclass.tourneymanager.mode;

import android.app.Application;

public class CurrentMode extends Application {
    private static ManagerMode managerMode;
    private static PlayerMode playerMode;

    @Override
    public void onCreate() {
        super.onCreate();

        managerMode = new ManagerMode(getApplicationContext());
        playerMode = new PlayerMode(getApplicationContext());
    }

    public static ManagerMode getManagerMode() {
        return CurrentMode.managerMode;
    }

    public static PlayerMode getPlayerMode() { return CurrentMode.playerMode; }
}
