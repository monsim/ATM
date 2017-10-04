package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMSystem {
	public static void main(String[] args) {
		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");
		ATM atm1A = new ATM(bankA, "Wells Fargo");
		ATM atm2A = new ATM(bankA, "Wells Fargo");
		ATM atm1B = new ATM(bankB, "Bank of America");
		ATM atm2B = new ATM(bankB, "Bank of America");
		Account currentAccount = new Account("password1", 100, "test", "Susan");
		Card currentCard = null;
		Account oneA = new Account("testsetsteestse", 100, "id1", "Angus");
		Card oneC = new Card(bankA.getBankID() + oneA.getAccountID()); 
		oneA.setCard(oneC);
		bankA.addToAccounts(oneA);
		
		Account twoA = new Account("yay", 100, "id2", "Walter");
		Card twoC = new Card(bankA.getBankID() + twoA.getAccountID()); 
		twoA.setCard(twoC);
		bankA.addToAccounts(twoA);
		
		Account threeA = new Account("12345", 100, "id3", "Priyanka");
		Card threeC = new Card(bankA.getBankID() + threeA.getAccountID()); 
		threeA.setCard(threeC);
		bankA.addToAccounts(threeA);
		
		Account expiredAccount = new Account("13740", 100, "id4", "Anthony");
		Card expiredCard = new Card(bankA.getBankID() + expiredAccount.getAccountID(), 2017, 03, 29);
		expiredAccount.setCard(expiredCard);
		bankA.addToAccounts(expiredAccount);
		
		System.out.println("Wells Fargo state: " + bankA.bankState());
		
		
		
		Account oneABankB = new Account("whatshouldthepasswordbe", 100, "id5", "Walnut");
		Card oneCBankB = new Card(bankB.getBankID() + oneA.getAccountID()); 
		oneABankB.setCard(oneCBankB);
		bankB.addToAccounts(oneABankB);
		
		Account twoABankB = new Account("whoevenknows", 100, "id6", "Creek");
		Card twoCBankB = new Card(bankB.getBankID() + twoABankB.getAccountID()); 
		twoABankB.setCard(twoCBankB);
		bankB.addToAccounts(twoABankB);
		
		Account threeABankB = new Account("98765", 100, "id7", "Bianca");
		Card threeCBankB = new Card(bankB.getBankID() + threeABankB.getAccountID()); 
		threeABankB.setCard(threeCBankB);
		bankB.addToAccounts(threeABankB);
		
		System.out.println("Bank of America state: " + bankB.bankState());
		
		
		System.out.println(atm1A.atmState(1, "A")); //wells fargo
		System.out.println(atm1B.atmState(1, "B")); //boa
		System.out.println(atm2A.atmState(2, "A")); //wells fargo
		System.out.println(atm2B.atmState(2, "B")); //boa
		
		
		Bank currentBank = null;
		ATM currentATM = null;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice of ATM: "); //ATM1_A, ATM2_A, ATM1_B, and ATM2_B
		String input = sc.nextLine().toLowerCase();
		System.out.println(input);
		if (input.equals("atm_1a")) {
			currentATM = atm1A;
			currentBank = bankA; //wells fargo
		} else if (input.equals("atm_1b")) {
			currentATM = atm1B;
			currentBank = bankB; //boa
		} else if (input.equals("atm_2a")) {
			currentATM = atm1A;
			currentBank = bankA; //wells fargo
		} else if (input.equals("atm_2b")) {
			currentATM = atm1B;
			currentBank = bankB; //boa
		} else {
			System.out.println("Sorry that ATM is not an option. Please run again");
		}
		
		currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
		currentAccount.setCard(currentCard);
		
		if (currentCard != null) {
			if (currentCard.isExpired()) {
				System.out.println("This card is expired and returned to you");
				currentCard = currentATM.checkCard(currentBank, currentCard, currentAccount);
				testMethod(currentCard, currentAccount, currentBank, currentATM);
			} else {
				System.out.println("The card is accepted. Please enter your password: ");
				input = sc.nextLine();
				if (currentAccount.getPassword().equals(input)) {
					System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw: ");
					double amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
					amount = currentATM.transaction(amount, currentAccount, currentCard);
					System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
					String toCheck = "";
					while(!toCheck.equals("quit")) {
						amount = Integer.parseInt(sc.nextLine().replaceAll("$", ""));
						amount = currentATM.transaction(amount, currentAccount, currentCard);
						System.out.println("$" + amount + " is withdrawn from  your account. The remaining balance of this account is $" + currentAccount.getBalance() + ". If you have more transactions, enter the amount or quit.");
						toCheck = sc.nextLine();
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
				currentAccount.setCard(currentCard);
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
					System.out.println("Not allowed, please run again");
				}
			}
		}
	}
	
	
	/**
	 * the method to call again in case of a wrong password
	 * @param currentCard the current Card
	 * @param currentAccount the current Account
	 * @param currentBank the current Bank
	 * @param currentATM the current ATM
	 */
	public static void testMethod(Card currentCard, Account currentAccount, Bank currentBank, ATM currentATM) {
		Scanner sc = new Scanner(System.in);
		String input;
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
					System.out.println("This is a wrong password. Enter your password.");
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
				currentAccount.setCard(currentCard);
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
					System.out.println("This is a wrong password. Enter your password.");
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
		}
	}
}
