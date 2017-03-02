//data type class stored in database

package seclass.gatech.edu.tourneymanager.data;

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
}