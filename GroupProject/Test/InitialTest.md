# Test on dummy data  

### Test 1. Initialize Player and Tournament Databases
Source code:
```
public void initialize() throws SQLException {
        //call dbHelper class and create the RunTime object
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Player, Integer> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        //create
        playerDao.create(new Player(1, "Di"));
        playerDao.create(new Player(2, "Adric"));
        playerDao.create(new Player(3, "Andrew"));
        playerDao.create(new Player(4, "Ed"));

        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        tournamentDao.create(new Tournament(1));
        tournamentDao.create(new Tournament(2));

        //query
        List<Player> playerlist = playerDao.queryForAll();
        Log.d("player", playerlist.toString());
        List<Tournament> tournamentlist = tournamentDao.queryForAll();
        Log.d("tournamentlist", tournamentlist.toString());
        OpenHelperManager.releaseHelper();

    }
```


Logcat view result
```
D/player: [Player{username='1', name='Di', Phonenumber='null', Deck='null'}, Player{username='2', name='Adric', Phonenumber='null', Deck='null'}, Player{username='3', name='Andrew', Phonenumber='null', Deck='null'}, Player{username='4', name='Ed', Phonenumber='null', Deck='null'}, 

D/tournamentlist: [Tournament{id=1, houseCut=0, entryPrice=0, allUsername=null, status='null', firstWinner='null', secondWinner='null', thirdWinner='null', endDate=null}, Tournament{id=2, houseCut=0, entryPrice=0, allUsername=null, status='null', firstWinner='null', secondWinner='null', thirdWinner='null', endDate=null},
```

### Test 2. Manager function addPlayer() 

Source code:
```
//add player
        try{
            addPlayer(00, "newly added player", "180088888", "deck1");
        } catch (SQLException e){
            e.printStackTrace();
        }

 public void addPlayer(int id, String name, String phonenumber, String deck) throws SQLException {
        //call dbHelper class and create the RunTime object
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Player, Integer> playerDao= dbHelper.getPlayerRuntimeExceptionDao();
        //create
        playerDao.create(new Player(id, name, phonenumber, deck));
        //query
        List<Player> playerlist = playerDao.queryForAll();
        Log.d("player", playerlist.toString());

        OpenHelperManager.releaseHelper();

    }
```
Logcat view result
```
Tournament{id=11, houseCut=9, entryPrice=10, allUsername=team75, status='null', firstWinner='null', secondWinner='null', thirdWinner='null', endDate=null}]	
```	
### addTournament function of Manager


Source code:
```
//add player
        try{
            addPlayer(00, "newly added player", "180088888", "deck1");
        } catch (SQLException e){
            e.printStackTrace();
        }
	
```
Logcat test result
```
Player{username='21', name='newly added player', Phonenumber='180088888', Deck='deck1'}]
```


	
### Test 3. Manger function addTournament

Source code:
```
  try{
            addTournament(11, 9,10,"team75");
        } catch (SQLException e){
            e.printStackTrace();
        }

public void addTournament(int id, int houseCut, int entryPrice, String allUsername) throws SQLException {
        //call dbHelper class and create the RunTime object

        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        tournamentDao.create(new Tournament(id, houseCut, entryPrice, allUsername));
        //query
        List<Tournament> tournamentlist = tournamentDao.queryForAll();
        Log.d("tournamentlist", tournamentlist.toString());
        OpenHelperManager.releaseHelper();

    }
```
Logcat view result
```
Tournament{id=11, houseCut=9, entryPrice=10, allUsername=team75, status='null', firstWinner='null', secondWinner='null', thirdWinner='null', endDate=null}]	
```

###Test Environment Capture



