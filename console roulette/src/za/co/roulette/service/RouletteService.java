package za.co.roulette.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import za.co.roulette.classes.Bet;
import za.co.roulette.classes.Player;
import za.co.roulette.classes.ResultRoulette;

public class RouletteService {
	
	// players user for betting
	List<Player> players = new ArrayList<>();
	
	Bet bet = new Bet();
	
	// players that have being use for betting 
	public Map<String ,Player> betPlayers = new TreeMap<>();
	
	public List<ResultRoulette> resultRoulettes = new CopyOnWriteArrayList<>();
	ResultRoulette resultRoulette = null;
	
	//Load players and file 
	public List<Player> laodFile(String file){
		
		try {
			File fil = new File(file);
			
			BufferedReader buf = new BufferedReader(new FileReader(file));
			while(buf.ready()){
				//System.out.println(buf.readLine());
				Player player = new Player();
				player.setName(buf.readLine());
				
				this.players.add(player);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return players;
	}

	//Get a specific player
	public Player validatePlayer(int betTy) {
		
		Player player = players.get(betTy);
		return player;
	}
	
	//TO PLACE A BET
	public ResultRoulette placeBet(int spin,double betValue,int betType,int betNumber,Player player) {
		
		String bettyp = (betType == 1)? "EVEN" : "ODD";
		
		//FOR EVEN
		if(betType == 1 && spin % 2 == 0) {
			bet.setBetType(bet.getBetType().EVEN);
			bet.setValue(betValue * 2);
			player.setBet(bet);
			
			resultRoulette = new ResultRoulette(player.getName(),bet.getBetType().name(),"win's",bet.getValue(),betValue);
		
		}else if(betNumber == 3) {
			resultRoulette = this.forNumber(betValue,betNumber,spin,player);
			
		}else {
			resultRoulette = new ResultRoulette(player.getName(),bettyp,"Losse",0.0 ,betValue);
		}
		
		resultRoulettes.add(resultRoulette);
		return resultRoulette;
		
	}

	private ResultRoulette forNumber(double betValue, int betNumber, int spin, Player player) {
		
		if(betNumber == spin) {
			bet.setBetNumber(betNumber);
			
			bet.setValue(betValue *36);
			
			player.setBet(bet);
			
			resultRoulette = new ResultRoulette(player.getName(),"Number","Win's",bet.getValue(),betValue);
			
		}
		checkPlayersStatistics(resultRoulettes,player);
		return resultRoulette;
		
	}
	// Checking the statistic per player
			public void checkPlayersStatistics(List<ResultRoulette> resultRoulettes,Player player) {
				
				int size = 0;
				double totalWin = 0;
				double totalBet = 0;
				
				while(resultRoulettes.size() > size) {
					if(resultRoulettes.get(size).getPlayer().contentEquals(player.getName())) {
						
						totalWin += resultRoulettes.get(size).getWinnings();
						totalBet += resultRoulettes.get(size).getAmountBet().doubleValue();
						
						player.setTotalWin(totalWin);
						player.setTotalBet(totalBet);
					}
					size++;
					
				}
				betPlayers.put(player.getName(),player);
				
				//adding past records
				addingOldRecord(this.betPlayers);
				
			}
			//adding all past Records
            @SuppressWarnings("unlikely-arg-type")
			private void addingOldRecord(Map<String, Player> betPlayers) {
            	
            	File file = new File("file2.text");
            	
            	try {
            		BufferedReader read = new BufferedReader(new FileReader(file));
            		
            		for(String key : betPlayers.keySet()) {
            			
            			while(read.ready()) {
            				String first[] = read.readLine().split(",");
            				
            				if(betPlayers.get(key).getName().equals(first[0])) {
            					double totalBet = Double.valueOf(first[2]);
            					double totalWins = Double.valueOf(first[1]);
            					
            					betPlayers.get(key).setTotalBet(betPlayers.get(key).getTotalBet() + totalBet);
            					betPlayers.get(key).setTotalWin(betPlayers.get(key).getTotalWin() + totalWins);
            					
            				}
            			}
            		}
            		this.betPlayers = betPlayers;
            		
            	}catch(FileNotFoundException e) {
            		e.printStackTrace();
            		
            	}catch (IOException e) {
					
            		e.printStackTrace();
				}
			
				
			}
}
