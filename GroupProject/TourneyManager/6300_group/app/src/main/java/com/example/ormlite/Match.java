package com.example.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="MatchTable")
public class Match {
	
	@DatabaseField(generatedId= true)
	int id;

	@DatabaseField
	String player1;

	@DatabaseField
	String player2;

	@DatabaseField
	String status;
	
	@DatabaseField
	String result;

	public Match() {

	}
	public Match(int id) {
		super();
		this.id = id;
	}

	public Match(int id, String player1, String player2) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player(int id, String player1, String player2, String status, String result) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.status = status;
		this.result = result;
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