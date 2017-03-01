//data type class stored in database

package com.example.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="PlayerTable")
public class Player {
	
	@DatabaseField(generatedId= true)
	String username;

	@DatabaseField
	String name;

	@DatabaseField
	String Phonenumber;

	@DatabaseField
	String Deck;

	public Player() {

	}
	public Player(String username) {
		super();
		this.username = username;
	}

	public Player(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}

	public Player(String username, String name, String phonenumber, String deck) {
		super();
		this.username = username;
		this.name = name;
		Phonenumber = phonenumber;
		Deck = deck;
	}

	@Override
	public String toString() {
		return "Player{" +
				"username='" + username + '\'' +
				", name='" + name + '\'' +
				", Phonenumber='" + Phonenumber + '\'' +
				", Deck='" + Deck + '\'' +
				'}';
	}
}