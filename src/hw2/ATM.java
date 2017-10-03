package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	Bank bankAssociated;
	private double withdrawMax;
	private String name;
	
	/**
	 * creates a new ATM object
	 * @param b the Bank associated with the ATM
	 */
	public ATM(Bank b) {
		this.bankAssociated = b;
		this.withdrawMax = 50;
	}
	
	/**
	 * returns the withdraw maximum for the ATM
	 * @return the withdraw maximum for the ATM
	 */
	public double getWithdrawMax() {
		return this.withdrawMax;
	}
	
	/**
	 * returns the bank associated with the ATM
	 * @return the bank associated with the ATM
	 */
	public Bank getBankAssociated() {
		return this.bankAssociated;
	}
	
	/**
	 * transaction dialog to withdraw money
	 * @param amount the amount to withdraw
	 * @param account the Account to withdraw the money from 
	 * @param card the Card to withdraw the money from 
	 * @return the amount withdrawn
	 */
	public double transaction(double amount, Account account, Card card) {
		Scanner sc = new Scanner(System.in);
		double withdrawLimit = this.withdrawMax;
//		System.out.println("amount: " + amount + " withdraw limit: " + withdrawMax);
		while (amount > withdrawLimit) {
			System.out.println("This amount exceeds the maximum amount you can withdraw per transaction. Please enter the amount or quit.");
			amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
		}
		double currentBalance = account.getBalance();
//		System.out.println("CurrentBalnce: " + currentBalance);
		while (currentBalance < amount) {
			System.out.println("The amount exceeds the current balance. Enter another amount or quit. ");
			amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
		}
		
		account.withdrawMoney(amount);
		card.addTransaction(amount);
		return amount;
	}
	
	/**
	 * checks if the given Card is part of the given Bank
	 * @param currentBank the current Bank
	 * @param currentCard the current Card
	 * @param currentAccount the current Account
	 * @return the Card that is part of the Bank, returns null if the card does not exist
	 */
	public Card checkCard(Bank currentBank, Card currentCard, Account currentAccount) {
		// check if card is part of the bank
		System.out.println("Enter your card");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		boolean exists = false;
		ArrayList<Account> accounts = currentBank.getAccounts();
		for (Account a : accounts) {
			String toCompare = a.getCard().getCardNumber();
//			System.out.println(toCompare + "!!!");
//			System.out.println(input);
			if (toCompare.equals(input)) {
				currentCard = a.getCard();
				currentAccount.setPassword(a.getPassword());
				System.out.println("equals");
				exists = true;
				break;
			}
		}
		if (exists) return currentCard;
		else return null;
	}
}
