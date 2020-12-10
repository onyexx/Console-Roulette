package za.co.roulette.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
}
