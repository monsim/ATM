package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMTester {
	public static void main(String[] args) {
		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");
		ATM atm1A = new ATM(bankA);
		ATM atm2A = new ATM(bankA);
		ATM atm1B = new ATM(bankB);
		ATM atm2B = new ATM(bankB);
		Account currentAccount = new Account("password1", 100, "test");
		Card currentCard = null;
		Account oneA = new Account("testsetsteestse", 100, "id1");
		Card oneC = new Card(bankA.getBankID() + oneA.getAccountID()); 
		oneA.setCard(oneC);
		bankA.addToAccounts(oneA);
		
		Account twoA = new Account("yay", 100, "id2");
		Card twoC = new Card(bankA.getBankID() + twoA.getAccountID()); 
		twoA.setCard(twoC);
		bankA.addToAccounts(twoA);
		
		Account threeA = new Account("12345", 100, "id3");
		Card threeC = new Card(bankA.getBankID() + threeA.getAccountID()); 
		threeA.setCard(threeC);
		bankA.addToAccounts(threeA);
		
		Account expiredAccount = new Account("13740", 100, "id4");
		Card expiredCard = new Card(bankA.getBankID() + expiredAccount.getAccountID(), 2017, 03, 29);
		expiredAccount.setCard(expiredCard);
		bankA.addToAccounts(expiredAccount);
		
		System.out.println("bankA state: " + bankA.bankState());
		
		
		
		Account oneABankB = new Account("whatshouldthepasswordbe", 100, "id5");
		Card oneCBankB = new Card(bankB.getBankID() + oneA.getAccountID()); 
		oneABankB.setCard(oneCBankB);
		bankB.addToAccounts(oneABankB);
		
		Account twoABankB = new Account("whoevenknows", 100, "id6");
		Card twoCBankB = new Card(bankB.getBankID() + twoABankB.getAccountID()); 
		twoABankB.setCard(twoCBankB);
		bankB.addToAccounts(twoABankB);
		
		Account threeABankB = new Account("98765", 100, "id7");
		Card threeCBankB = new Card(bankB.getBankID() + threeABankB.getAccountID()); 
		threeABankB.setCard(threeCBankB);
		bankB.addToAccounts(threeABankB);
		
		System.out.println("bankB state: " + bankB.bankState());
		
		
		
		Bank currentBank = null;
		ATM currentATM = null;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice of ATM: "); //ATM1_A, ATM2_A, ATM1_B, and ATM2_B
		String input = sc.nextLine().toLowerCase();
		System.out.println(input);
		if (input.equals("atm_1a")) {
			currentATM = atm1A;
			currentBank = bankA;
			System.out.println("y");
		} else if (input.equals("atm_1b")) {
			currentATM = atm1B;
			currentBank = bankB;
			System.out.println("c");
		} else if (input.equals("atm_2a")) {
			currentATM = atm1A;
			currentBank = bankA;
			System.out.println("d");
		} else if (input.equals("atm_2b")) {
			currentATM = atm1B;
			currentBank = bankB;
			System.out.println("a");
		} else {
			System.out.println("Sorry that ATM is not an option. Please run again");
		}
		
		currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
		currentAccount.setCard(currentCard);
		
		if (currentCard != null) {
			if (currentCard.isExpired()) {
				System.out.println("This card is expired and returned to you");
				currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
			} else {
				System.out.println("The card is accepted. Please enter your password: ");
				input = sc.nextLine();
				if (currentAccount.getPassword().equals(input)) {
					System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw: ");
					double amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
					amount = currentATM.transaction(amount, currentAccount, currentCard);
					System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
					while(!sc.nextLine().equals("quit")) {
						amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
						amount = currentATM.transaction(amount, currentAccount, currentCard);
						System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
					}
				} else {
					System.out.println(" This is a wrong password. Enter your password.");
					input = sc.nextLine();
					if (currentAccount.getPassword().equals(input)) {
						System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw: ");
						double amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
						amount = currentATM.transaction(amount, currentAccount, currentCard);
						System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
						while(!sc.nextLine().equals("quit")) {
							amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
							amount = currentATM.transaction(amount, currentAccount, currentCard);
							System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
						}
					}
				}
			}
		} else {
			System.out.println("This card is not supported by this ATM");
			currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
			currentAccount.setCard(currentCard);
			if (currentCard.isExpired()) {
				System.out.println("This card is expired and returned to you");
				currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
			} else {
				System.out.println("The card is accepted. Please enter your password: ");
				input = sc.nextLine();
				if (currentAccount.getPassword().equals(input)) {
					System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw: ");
					double amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
					amount = currentATM.transaction(amount, currentAccount, currentCard);
					System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
					while(!sc.nextLine().equals("quit")) {
						amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
						amount = currentATM.transaction(amount, currentAccount, currentCard);
						System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
					}
				} else {
					System.out.println("YIKES");
				}
			}
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		//need to change it so account number is part of card number
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter your choice of ATM: "); //ATM1_A, ATM2_A, ATM1_B, and ATM2_B.
//		String input = sc.nextLine().toLowerCase();
//		String key = input.substring(input.length()-1, input.length()); //A or B
//		
//		
//		boolean isBank = false;
//		while (!isBank) {
//			if (bankA.getBankID().equals((key))) { // a
//				currentBank = bankA;
//				currentATM = atm1A;
//				currentBank.addToAccounts(currentAccount);
//				String card = currentBank.getBankID() + currentAccount.getAccountID();
//				currentCard = new Card(card);
//				currentAccount.setCard(currentCard);
//				isBank = true;
//			} else if (bankB.getBankID().equals(key)) { // b
//				currentBank = bankB;
//				currentATM = atm1B;
//				currentBank.addToAccounts(currentAccount);
//				String card = currentBank.getBankID() + currentAccount.getAccountID();
//				currentAccount.setCard(currentCard);
//				currentCard = new Card(card);
//				isBank = true;
//			} else {
//				System.out.println("That Bank is not supported by this ATM");
//				System.out.println("Enter your choice of ATM: ");
//				input = sc.nextLine().toLowerCase();
//				key = input.substring(0, 1);
//			}
//		}
//		
//		System.out.println("Enter your card: ");
//		input = sc.nextLine().toLowerCase();
//		String id = input.substring(0,1);
//		while(!currentBank.verifyBank(id)) {
//			System.out.println("This card is not supported by this ATM");
//			System.out.println("Enter your card: ");
//			input = sc.nextLine();
//			id = input.substring(0,1);
//		}
//		
//		System.out.println("cardnumer: " + currentCard.getCardNumber() + "inptu: " + input);
//		
//		
//		if (!currentCard.getCardNumber().equals(input)) {
//			System.out.println("That card does not exist.  Enter your card: ");
//			input = sc.nextLine();
//			id = input.substring(0,1);
//		}
//		
//		currentAccount.setCard(currentCard);
//		if (!currentCard.isExpired()) {
//			System.out.println("The card is accepted. Please enter your password: ");
//		} else {
//			System.out.println("Your card is expired.");
//			System.out.println("Enter your card: ");
//			input = sc.nextLine().toLowerCase();
//			id = input.substring(0,1);
//			currentCard = new Card(input);
//			currentAccount.setCard(currentCard);
//		}	
//		
//		input = sc.nextLine();
//		String password = currentAccount.getPassword();
//		while (!input.equals(password)) {
//			System.out.println("This is a wrong password. Enter your password: ");
//			input = sc.nextLine();
//		}
//		
//		System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw: ");
//		double amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
//		amount = currentATM.transaction(amount, currentAccount, currentCard);
//		
//		
//		System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
//		while(!sc.nextLine().equals("quit")) {
//			amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
//			amount = currentATM.transaction(amount, currentAccount, currentCard);
//			System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
//		}
//		
//		
//		
//		
//		
//		
	}
}
