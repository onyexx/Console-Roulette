package za.co.roulette.classes;

public class Bet {

	private Double value;
	private Integer betNumber;
	private BetType betType;

	public Bet() {
		super();
	}

	public Bet(Double value, Integer betNumber, BetType betType) {
		super();
		this.value = value;
		this.betNumber = betNumber;
		this.betType = betType;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(Integer betNumber) {
		this.betNumber = betNumber;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public String toString() {
		return "Bet [value=" + value + ", betNumber=" + betNumber + ", betType=" + betType + "]";
	}

}
