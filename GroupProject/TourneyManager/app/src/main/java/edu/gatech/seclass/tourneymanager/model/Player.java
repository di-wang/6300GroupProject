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

    @DatabaseField(defaultValue = "0")
    private Integer totalPrize;

    public Player() {

    }

    public Player(String username, String name, String phoneNumber, Deck deck) {
        super();
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.deck = deck;
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

    @Override
    public String toString() {
        return username;
    }
}
