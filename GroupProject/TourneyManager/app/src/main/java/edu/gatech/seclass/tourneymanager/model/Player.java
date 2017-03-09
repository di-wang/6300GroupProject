package edu.gatech.seclass.tourneymanager.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="player")
public class Player {

    @DatabaseField(id = true)
    private String username;

    @DatabaseField
    private String name;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField(foreign = true)
    private Deck deck;

    @DatabaseField
    private int totalPrize;

    public Player() {

    }

    public Player(String username, String name, String phoneNumber, Deck deck) {
        super();
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.deck = deck;
        this.totalPrize = 0;
    }

    public String getUsername() {
        return username;
    }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public Deck getDeck() { return deck; }

    public Integer getTotalPrize() {
        return totalPrize;
    }
    public void addTotalPrize(Integer prize) {
        try {
            this.totalPrize += prize;
        }
        catch (NullPointerException e){
            this.totalPrize = prize;
        }
    }

    @Override
    public String toString() {
        return username;
    }
}
