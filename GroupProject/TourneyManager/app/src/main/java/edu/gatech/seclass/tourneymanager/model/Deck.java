package edu.gatech.seclass.tourneymanager.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="deck")
public class Deck {

    @DatabaseField(id = true)
    private String name;

    public Deck() {}

    public Deck(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
