//data type class stored in database

package com.example.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="PlayerTable")
public class Player {
	
	@DatabaseField(generatedId= true)
	int id;

	@DatabaseField
	String name;

	@DatabaseField
	String Phonenumber;

	@DatabaseField
	String Deck;

	public Player() {

	}
	public Player(int id) {
		super();
		this.id = id;
	}

	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Player(int id, String name, String phonenumber, String deck) {
		super();
		this.id = id;
		this.name = name;
		Phonenumber = phonenumber;
		Deck = deck;
	}

	@Override
	public String toString() {
		return "Player{" +
				"username='" + id + '\'' +
				", name='" + name + '\'' +
				", Phonenumber='" + Phonenumber + '\'' +
				", Deck='" + Deck + '\'' +
				'}';
	}
    public int[] calculatePrizesAndProfit(int totalPool){
        int firstPrize;
        int secondPrize;
        int thirdPrize;
        int profit;

        //Calculate first prize
        firstPrize = (int) Math.round((totalPool - houseCut) * .5);
       //Calculate second prize
        secondPrize = (int) Math.round((totalPool - houseCut) * .3);
        //Calculate third prize
        thirdPrize = (int) Math.round((totalPool - houseCut) * .2);
        //Calculate profit
        profit = totalPool - firstPrize - secondPrize - thirdPrize;
        
        int[]  a = {firstPrize, secondPrize, thirdPrize, profit};
        
        return a;

    }
}