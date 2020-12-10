package za.co.roulette.classes;

public class Player {
	
	private String name;
	private Double totalWin;
	private Double totalBet;
	private Bet bet;
	
	//constructors from super class
	public Player() {
		super();
	}
        //constructor using field
	public Player(String name, Double totalWin, Double totalBet, Bet bet) {
		super();
		this.name = name;
		this.totalWin = totalWin;
		this.totalBet = totalBet;
		this.bet = bet;
	}
       //getters and setters for name,bet,totalWin,totalBet
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(Double totalWin) {
		this.totalWin = totalWin;
	}

	public Double getTotalBet() {
		return totalBet;
	}

	public void setTotalBet(Double totalBet) {
		this.totalBet = totalBet;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

     //toString method for totalWin,bet,name,totalBet
	public String toString() {
		return "Player [name=" + name + ", totalWin=" + totalWin + ", totalBet=" + totalBet + ", bet=" + bet + "]";
	}
	
	

}
