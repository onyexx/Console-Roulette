package za.co.roulette;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SplittableRandom;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import za.co.roulette.classes.BetType;
import za.co.roulette.classes.Player;
import za.co.roulette.classes.ResultRoulette;
import za.co.roulette.service.RouletteService;

public class RouletteTest {

	static List<Player> players = new ArrayList<>();

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Player player = null;
		ResultRoulette resultRoulette = new ResultRoulette();
		RouletteService reRouletteService = new RouletteService();
		Map<String, Player> betPlayers = new TreeMap<>();
		SplittableRandom random = new SplittableRandom();
		List<ResultRoulette> resultRoulettes = new CopyOnWriteArrayList<>();

		int playerNumber = 0, number = 0, betType = 0, betValue = 0, chioce = 0, control = 0;

		// spin value captured
		int spin = random.nextInt(1, 36);

		players = reRouletteService.laodFile("Players.txt");

		do {
			System.out.println("\n \tPlayers");
			while (players.size() != playerNumber) {
				player = RouletteTest.players.get(playerNumber);

				System.out.println("\t" + playerNumber++ + "." + player.getName());
			}
			playerNumber = 0;
			for (;;) {
				scan.nextLine();
				try {
					System.out.println("\nSelect player you want To Bet:");

					chioce = scan.nextInt();

					break;

				} catch (Exception e) {
					System.out.println();
					System.err.println("Only Numbers Allowed");
				}
			}
			if (chioce >= players.size()) {
				System.out.println("Invalid Player Name {try Between 0 -" + (players.stream().count() - 1) + "}");
				continue;
			}
			player = reRouletteService.validatePlayer(chioce);

			for (;;) {
				try {
					scan.nextLine();
					System.out.println("\nSelect Bet Value/Amount : R");
					betValue = scan.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Please Numbers...{3.0,6.7..ect}");
				}
			}
			System.out.println("\n");
			for (;;) {
				try {
					scan.hasNextLine();
					System.out.println("\t1." + BetType.EVEN + "\n \t2." + BetType.ODD + "\n \t3." + BetType.NUMBER
							+ "\nSelect Bet Type:");
					betType = scan.nextInt();

					if (betType >= 1 && betType <= 3) {
						
						break;
						
					} else {
						System.err.println("Bet must Be Greater than 0 and less than 4\n");
					}
				} catch (Exception e) {
					System.out.println();
					System.err.println("Please Numbers....");
				}
			}
			if (betType == 3) {
				System.out.println("Place Your Bet on Number(1-36) :");
				number = scan.nextInt();
			}
			// Placing Bet
			resultRoulette = reRouletteService.placeBet(spin, betValue, betType, number, player);

			// Checking the Statistic per player
			reRouletteService.checkPlayersStatistics(reRouletteService.resultRoulettes, player);

			control++;
			player = null;
		} while (players.size() > control);

		betPlayers = reRouletteService.betPlayers;

		resultRoulettes = reRouletteService.resultRoulettes;

		System.out.println("\nNumber:" + spin + "           output 1");
		line();
		printHeader1();
		control = 0;

		while (resultRoulettes.size() > control) {

			printRow1(resultRoulettes.get(control).getPlayer(), resultRoulettes.get(control).getBetType(),
					resultRoulettes.get(control).getOutCome(), resultRoulettes.get(control).getWinnings());
			control++;
		}
		System.out.println("\n\n           output2");
		line();
		printHeader2();
		control = 0;

		for (String key : betPlayers.keySet()) {
			printRow2(betPlayers.get(key).getName(), betPlayers.get(key).getTotalWin(),
					betPlayers.get(key).getTotalBet());

		}

	}

	static void line() {
		System.out.printf("--------------------------------------------------------------------------------\n");
	}

	static void printHeader1() {
		System.out.printf("%-20s \t %-20s \t %-15s \t %-15s \n", "Player", "Bet", "Outcome", "Winnings");
	}

	static void printRow1(String player, String bet, String outcome, double winnings) {
		System.out.printf("%-23s \t %-23s \t %-23s \t %-23s \n ", player, bet, outcome, winnings);

	}

	static void printHeader2() {
		System.out.printf("%-21s \t %-21s \t %-21s \n", "Player", "Total Win", "Total Bet");
	}

	static void printRow2(String player, double totalWins, double totalBet) {
		System.out.printf("%-23s \t %-23s \t %-23s \n", player, totalWins, totalBet);
	}

}
