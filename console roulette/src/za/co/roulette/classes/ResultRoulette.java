package za.co.roulette.classes;

public class ResultRoulette {

	private String player;
	private String outCome;
	private String betType;
	private Double winnings;
	private Double amountBet;

	// constructor from super class
	public ResultRoulette() {
		super();
	}

	// constructor using fields
	public ResultRoulette(String player, String outCome, String betType, Double winnings, Double amountBet) {
		super();
		this.player = player;
		this.outCome = outCome;
		this.betType = betType;
		this.winnings = winnings;
		this.amountBet = amountBet;
	}

	// getters and setters for the fields
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getOutCome() {
		return outCome;
	}

	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Double getWinnings() {
		return winnings;
	}

	public void setWinnings(Double winnings) {
		this.winnings = winnings;
	}

	public Double getAmountBet() {
		return amountBet;
	}

	public void setAmountBet(Double amountBet) {
		this.amountBet = amountBet;
	}

	// toString method for the fields
	public String toString() {
		return "Result [player=" + player + ", outCome=" + outCome + ", betType=" + betType + ", winnings=" + winnings
				+ ", amountBet=" + amountBet + "]";
	}

}
