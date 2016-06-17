package com.gcit;

import java.util.Scanner;

public class AssignmentOne {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		numberGuessing(50, 5, input);
		System.out.println("The second game starts now");
		
		pileGame(input);
		input.close();
	}

	private static void pileGame(Scanner scanner) {


		System.out.println("How many chips does the initial pile contain?");
		int numOfChips;
		//		 / scanner = new Scanner(System.in);

		numOfChips = scanner.nextInt();
		while (numOfChips % 2 == 0 || numOfChips < 3) {
			if (numOfChips % 2 == 0) {
				System.out
				.println("You have to start with an odd number of chips. Choose another number:");
				numOfChips = scanner.nextInt();
			} else {
				System.out
				.println("You have to start with at least 3 chips. Choose another number:");
				numOfChips = scanner.nextInt();
			}
		}

		Player playerOne = new Player();
		Player playerTwo = new Player();

		System.out.println("Enter Player one's name.");
		playerOne.setName(scanner.next());

		System.out.println("Enter Player two's name.");
		playerTwo.setName(scanner.next());

		while (playerOne.getName().equalsIgnoreCase(playerTwo.getName())) {
			System.out.println("Reenter player two's name.");
			playerTwo.setName(scanner.next());
		}

		System.out.println("Player one's name is " + playerOne.getName()
				+ " and player two's name is " + playerTwo.getName());
		gameStart(numOfChips, playerOne, playerTwo);

		//		scanner.close();

	}

	private static void gameStart(int numOfChips, Player one, Player two) {
		Scanner scanner = new Scanner(System.in);
		int chipsRemoved, chipsAllowed;
		chipsAllowed = numOfChips % 2 == 0 ? numOfChips / 2
				: (numOfChips - 1) / 2;
		while (numOfChips > 0) {
			if (numOfChips == 1) {
				System.out.print(one.getName()
						+ " There is 1 chip remaining. You can only take 1. Please enter 1.");
				chipsRemoved = scanner.nextInt();
				while (chipsRemoved != 1) {
					System.out.print("You must remove 1 chip. Please enter 1.");
					chipsRemoved = scanner.nextInt();
				}
				one.addToChips(1);
				break;
			}
			System.out.println("There are " + numOfChips + " remaining.");
			System.out.println(one.getName() + " has " + one.getChips()
					+ " chips. \n" + two.getName() + " has " + two.getChips()
					+ " chips");
			System.out.println("Your turn " + one.getName());
			System.out
			.println(one.getName()
					+ " please remove some chips. You're allowed to remove 1 to "
					+ chipsAllowed);
			chipsRemoved = scanner.nextInt();
			while (chipsRemoved < 1 || chipsRemoved > chipsAllowed) {
				System.out
				.println("Wrong number of chips removed. You're allowed to remove 1 to "
						+ chipsAllowed);
				chipsRemoved = scanner.nextInt();
			}
			numOfChips -= chipsRemoved;
			// System.out.println("Number of chips remaining is " + numOfChips);
			one.addToChips(chipsRemoved);
			System.out.println(one.getName() + " has " + one.getChips()
					+ " chips. \n" + two.getName() + " has " + two.getChips()
					+ " chips");

			if (numOfChips == 0) {
				break;
			}
			if (numOfChips == 1) {
				System.out.println(two.getName()
						+ " There is 1 chip remaining. You can only take 1. Please enter 1.");
				chipsRemoved = scanner.nextInt();
				while (chipsRemoved != 1) {
					System.out.println("You must remove 1 chip");
					chipsRemoved = scanner.nextInt();
				}
				two.addToChips(1);
				break;
			}
			chipsAllowed = numOfChips % 2 == 0 ? numOfChips / 2
					: (numOfChips - 1) / 2;

			System.out.println("There are " + numOfChips + " remaining.");
			System.out.println("Your turn " + two.getName());
			System.out
			.println(two.getName()
					+ " please remove some chips. You're allowed to remove 1 to "
					+ chipsAllowed);
			chipsRemoved = scanner.nextInt();
			while (chipsRemoved < 1 || chipsRemoved > chipsAllowed) {
				System.out
				.println("Wrong number of chips removed. You're allowed to remove 1 to "
						+ chipsAllowed);
				chipsRemoved = scanner.nextInt();
			}
			numOfChips -= chipsRemoved;
			chipsAllowed = numOfChips % 2 == 0 ? numOfChips / 2
					: (numOfChips - 1) / 2;
			two.addToChips(chipsRemoved);
			System.out.println("Number of chips remaining is " + numOfChips);
			System.out.println(one.getName() + " has " + one.getChips()
					+ " chips. \n" + two.getName() + " has " + two.getChips()
					+ " chips");
			System.out
			.println("**************************************************************************************************************");

		}

		if (one.getChips() % 2 == 0) {
			System.out.println(one.getName() + " wins.");
			System.out.println(two.getName() + " You lose");
		} else {
			System.out.println(two.getName() + " wins.");
			System.out.println(one.getName() + "You lose :p");
		}

		System.out.println("Do you want to play again? Y/N");
		if (scanner.next().equalsIgnoreCase("Y")) {
			pileGame(scanner);
		}

		scanner.close();
	}

	/**
	 * 
	 * @param randomNumber
	 *            number to guess
	 * @param allowedGuesses
	 *            number of chances to guess
	 */
	private static void numberGuessing(int randomNumber, int allowedGuesses, Scanner scanner) {
		System.out.println("Please enter your guess.");
		int counter = 0;
		// scanner = null;
		try {

			//scanner = new Scanner(System.in);
			int guess = 0;
			do {
				if (scanner.hasNextInt()) {
					guess = Integer.parseInt(scanner.next());

				} else {
					scanner.next();
				}

				if (guess <= randomNumber + 10 && guess >= randomNumber - 10) {

					System.out.println("You win! Random number is "
							+ randomNumber + " and your guess was " + guess);
					return;
				} else {
					if (counter == allowedGuesses - 1) {
						break;
					}
					System.out.println("Try again!");
				}
				counter++;
			} while (counter < allowedGuesses);

			System.out.println("You lose! The number to guess is "
					+ randomNumber + ".");
		} catch (Exception e) {
			// TODO: handle exception
		}

		finally {
			//			scanner.close();

		}

	}

}

class Player {
	private String name;
	private int chips;

	public void setName(String name) {
		this.name = name;
	}

	public int getChips() {
		return chips;
	}

	public String getName() {
		return name;
	}

	public void addToChips(int chips) {
		this.chips += chips;
	}

	public Player() {
	}

	public Player(String name) {
		this.name = name;
		this.chips = 0;

	}

}
